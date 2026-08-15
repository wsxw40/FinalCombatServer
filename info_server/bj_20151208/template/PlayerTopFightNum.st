page = #$page#
pages = #$pages#
left_time=#$leftTime#
top_num=#$topNum#
row_num=#$rowNum#
data = 
{
{"排名","玩家","战斗力","杀人/死亡","助攻","胜率"},
#foreach from=$list item=entry#
	#$entry#
#/foreach#
}
