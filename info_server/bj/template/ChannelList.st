channels = {
#foreach from=$channelList item=channel#
	{
	id=#$channel.id#,
	name="#$channel.name#",
	online=#$channel.online#, 
	max=#$channel.max#
	},
#/foreach#
}
