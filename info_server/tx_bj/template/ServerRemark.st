servers = {
#foreach from=$serverList item=server#
	{
	id=#$server.id#,
	index=#$server.index#,
	remark="#$server.remark#"
	},
#/foreach#
}