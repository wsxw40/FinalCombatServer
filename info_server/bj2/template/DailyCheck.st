result=#$result#
day="#$date#"
week=#$day#
check_days={
	#foreach from=$checkList item=checkDay#
		"#$checkDay#",
	#/foreach#
}