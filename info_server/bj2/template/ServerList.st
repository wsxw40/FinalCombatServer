servers = {
#foreach from=$serverList item=server#
	{
	id=#$server.id#,
	name="#$server.name#",
	online=#$server.online#,
	max=#$server.max#
	},
#/foreach#
}