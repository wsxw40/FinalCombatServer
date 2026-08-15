items ={
	#foreach from=$list item=ch#	
	{
		cid=#$ch.id#,
		name="#$ch.name#",
		is_open= 
			#if $ch.isDefault=="Y"# 
				1 ,
			#else# 
				0 ,
			#/if#
	},
	#/foreach#		
}