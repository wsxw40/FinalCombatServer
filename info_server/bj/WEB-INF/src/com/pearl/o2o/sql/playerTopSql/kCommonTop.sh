#!/bin/sh
. /etc/profile
sqlDir="/usr/local/fc_app/bj/playerTopSql/";
resultDir="/usr/local/fc_app/bj/topResult/"
#redisKeyPrefix1="top:"
#redisKeyPrefix2="playerTop:rowNum:"
#redisKeyPrefix3="playerTop:rank:"
redisKeyPrefix1="t:"
redisKeyPrefix2="pt:rn:"
redisKeyPrefix3="pt:r:"
redisDir="/usr/local/fc_app/redis/"
password="xlyouxi@pde"
export LANG="zh_CN.GB18030;zh_CN.UTF-8"
cd $sqlDir;

echo start week top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
rm -rf $resultDir/w*
echo "finish clean"
#1   generate sq.  result#
for fileName in `ls *Week*.sql`;do
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
echo 1.week top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
cd $resultDir
#2 convert datasource into redis command #
#command file1:    a sorted list  of a certain mode#
#command file2:    a sorted list with all mode of a player s#
echo "begin to process file"
for fileName in `ls *Week*.dat1`;do
        index=`expr index $fileName "."`
        ((index--))
        #echo $index
        shortFileName=`expr substr $fileName 1 $index`
		
        commandFile1=${resultDir}$shortFileName`date +%Y_%m_%d`.cm1
		commandFile2=${resultDir}WeekRowNum`date +%Y_%m_%d`.cm2
		commandFile3=${resultDir}WeekRank`date +%Y_%m_%d`.cm3
		
        key1=$redisKeyPrefix1$shortFileName
				
		echo del $key1 > $commandFile1
		
        lineCount=0
        row=0
		rank=1
		value=0
		isgo=0
		num=0;
		row_num=0
		rank_num=0
		value_num=0
		while read LINE
        do
           if [ "$lineCount" -ne 0 ] ;then	
			    arr=(`echo $LINE|sed 's/  */ /g'`);
				cid=${arr[0]}  # 0:cid, 2:value
				row_num=$(( num+1 ))
				rank_num=1;
				(( num++ ))
				value_num=${arr[2]}
		
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
				if [ $row_num -lt 400 ]; then
					 echo rpush $key1 \"{`echo $rank_num,$lineExceptRowNum|sed 's/  */\,/g'`},\" >> $commandFile1
				fi
				key2=$redisKeyPrefix2$shortFileName:$cid
				echo set $key2 $row_num	>>	$commandFile2			
				key3=$redisKeyPrefix3$shortFileName:$cid
				echo set $key3 $rank_num >> $commandFile3
							
           fi
        ((lineCount++))
        done < $fileName 
		
done
echo 2.0week top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
#set into redis#
echo "set into redis....."
for fileName in `ls *Week*.cm1 *Week*.cm2 *Week*.cm3`;do
		#iconv  -f GB18030 -t utf8 $fileName -o $fileName.utf8
		$redisDir/redis-cli	-p 6380 < $fileName 2> error
		echo set $fileName into redis successfully
		rm -rf $fileName
done

echo finished week top task at `date +%Y_%m_%d%H:%M:%S`>>${sqlDir}top.log
echo all finished 