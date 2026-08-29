#!/bin/sh
. /etc/profile
sqlDir="/usr/local/fc_app/bj/playerTopSql/";
resultDir="/usr/local/fc_app/bj/topResult/"
#redisKeyPrefix1="top:"
#redisKeyPrefix2="playerTop:rowNum:"
#redisKeyPrefix3="playerTop:rank:"
#redisKeyPrefix4="playerTop:firstLastRankRecord:"
redisKeyPrefix1="t:"
redisKeyPrefix2="pt:rn:"
redisKeyPrefix3="pt:r:"
redisKeyPrefix4="pt:rcd:"
redisDir="/usr/local/bin/"
password="123456"
export LANG="zh_CN.GB18030;zh_CN.UTF-8"
cd $sqlDir;

echo start player top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
rm -rf $resultDir/k*
echo "finish clean"
#1   generate sq.  result#
for fileName in `ls kAssistTop.sql kCommonTop.sql kFightNumTop.sql kControlTop.sql kKnifeTop.sql kRevengeTop.sql`;do
        echo $fileName\c
        index=`expr index $fileName "."`
        ((index--))
        #echo $index
        shortFileName=`expr substr $fileName 1 $index`
        #echo $shortFileName
        mysql -u bj -D bj  --default-character-set=utf8  -p$password < $sqlDir$fileName > $resultDir$shortFileName.dat1
        #touch $resultDir$shortFileName`date +%Y_%m_%d`.tmp
        echo ...done
done
echo 1.0player top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
cd $resultDir
#2 convert datasource into redis command #
#command file1:    a sorted list  of a certain mode#
#command file2:    a sorted list with all mode of a player s#
echo "begin to process file"
for fileName in `ls *Top.dat1`;do
        index=`expr index $fileName "."`
        ((index--))
        #echo $index
        shortFileName=`expr substr $fileName 1 $index`
		
        commandFile1=$resultDir$shortFileName`date +%Y_%m_%d`.cm1
		commandFile2=${resultDir}kPlayerTopRowNum`date +%Y_%m_%d`.cm2
		commandFile3=${resultDir}kPlayerTopRank`date +%Y_%m_%d`.cm3
		
        key1=$redisKeyPrefix1$shortFileName
				
		echo del $key1 > $commandFile1
		
        lineCount=0
      	
      	row=0
		rank=1
		value=0
		isgo=0
		num=0
        row_num=0
		rank_num=0
		value_num=0
		first_value_num=0
		last_value_num=0
		while read LINE
        do
           if [ "$lineCount" -ne 0 ] ;then	
           		echo
			    arr=(`echo $LINE|sed 's/  */ /g'`);
				cid=${arr[0]}  # 0:cid, 2:value
				row_num=$(( num+1 ))
				rank_num=1;
				(( num++ ))
				value_num=${arr[2]}
				if [ "$lineCount" -eq 1 ] ;then
				 first_value_num=$value_num
				fi
				if [ $isgo -eq 0 ]; then
					value=${arr[2]}
					isgo=1
				fi
				
				if [ $value_num -lt $value ]; then	
					(( rank++ ))
					rank_num=$rank
					value=$value_num
				else rank_num=$rank
				fi
				lineExceptRowNum=${arr[@]:1}
				if [ $row_num -lt 10000 ]; then
                	echo rpush $key1 \"{`echo $row_num,$lineExceptRowNum|sed 's/  */\,/g'`},\" >> $commandFile1
                fi
				key2=$redisKeyPrefix2$shortFileName:$cid
				echo set $key2 $row_num	>>	$commandFile2			
				key3=$redisKeyPrefix3$shortFileName:$cid
				echo set $key3 $row_num >> $commandFile3
							
           fi
        ((lineCount++))
        done < $fileName 
		last_value_num=$value_num
		redis_value=`date +%Y-%m-%d`:$first_value_num:$last_value_num
		key4=$redisKeyPrefix4$shortFileName
		echo set $key4 $redis_value >> $commandFile3
done
echo 2.0player top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
#set into redis#
echo "set into redis....."
for fileName in `ls *Top*.cm1 *Top*.cm2 *Top*.cm3`;do
		#iconv  -f GB18030 -t utf8 $fileName -o $fileName.utf8
		$redisDir/redis-cli	-p 6380 < $fileName 2> error
		echo set $fileName into redis successfully
		rm -rf $fileName
done

echo finished player top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
echo all finished 