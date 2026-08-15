day="#$date#"
week=#$day#
today_num=#$today#
my_num="#$myNum#"
tomorrow_num="#$tomorrow#"
is_gift=#$isGift#
is_show_award=#$isShowAward#
check_days={
	#foreach from=$checkList item=checkDay#
		"#$checkDay#",
	#/foreach#
}
check_gift={}
freeCheckTimes=#$freeCheckTimes#
checkNum=#$checkNum#