history = {
	#foreach from=$teamHistoryList item=teamHistory#
    {#$teamHistory.result#, "#$teamHistory.attendees#", #$teamHistory.createTime#},	
    #/foreach#
}