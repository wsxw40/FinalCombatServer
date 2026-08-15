type=#$type#
#if ($type == 0)#
scoce=#$qwInfo[0]#
kill=#$qwInfo[1]#
dead=#$qwInfo[2]#
assist=#$qwInfo[3]#
win=#$qwInfo[4]#
fail=#$qwInfo[5]#
rank=#$qwInfo[6]#
rankNum=#$qwInfo[7]#
kValue=#$qwInfo[8]#
killSum=#$qwInfo[1]#			
rate=#$rate#		
#/if#
#if ($type == 1)#
page=#$page#
pageNum=#$pageNum#
items =
{#foreach from=$days item=day#	
{s=#$day[0]#,k=#$day[1]#,d=#$day[2]#,a=#$day[3]#,w=#$day[4]#,f=#$day[5]#,r=#$day[6]#,ks=#$day[9]#,t="#$day[11]#"},
#/foreach#}
#/if#
#if ($type == 2)#
page=#$page#
pageNum=#$pageNum#
items =
{#foreach from=$sums item=sum#	
{s=#$sum[0]#,k=#$sum[1]#,d=#$sum[2]#,w=#$sum[4]#,f=#$sum[5]#,r=#$sum[6]#,rN=#$sum[7]#,n="#$sum[9]#",pR=#$sum[10]#,vR=#$sum[11]#,},
#/foreach#}
#/if#