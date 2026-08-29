list={
	#foreach from=$list item=p#
		{#$p.id#,"#$p.icon#","#$p.nikeName#","#$p.content#","#$p.createTimeStr#"},
	#/foreach#
}