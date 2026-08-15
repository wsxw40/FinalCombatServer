time=#$leftSecond#
currentExp = #$cExp#
award_time="#$next#"
items={
	#foreach from=$gifts item=theItem#
	{
		sid=#$theItem.sysItem.id#,
		display="#$theItem.sysItem.displayName#",
		name="#$theItem.sysItem.name#",
		characters={
			#foreach from=$theItem.sysItem.characterList item=ids# 
				#$ids#, 
			#/foreach#
		},
		description ="#$theItem.sysItem.description#",
		#include file="Timelimit.st" unitType=$theItem.unitType unit=$theItem.unit#
		sendperson = #$theItem.sysItem.isHot#,
		#if $theItem.sysItem.wId==4# 
			wid = #$theItem.sysItem.wId#, 
		#/if#
		common={
			level = #$theItem.sysItem.level#,
			type = #$theItem.sysItem.type#,
			subtype = #$theItem.sysItem.subType#,
			quantity =  #$theItem.unit#,
			#if $theItem.sysItem.type==1# 
				wid=#$theItem.sysItem.wId#, 
			#/if#
			seq=#$theItem.sysItem.seq#,
			is_vip=#$theItem.sysItem.isVip#,
			is_new=#$theItem.sysItem.isNew#,
			is_hot=#$theItem.sysItem.isHot#,
			rareLevel=#$theItem.sysItem.rareLevel#,
		},
		performance = {
			damange = #$theItem.sysItem.damange#,					
			speed =#$theItem.sysItem.shootSpeed#,		
			ammos = #$theItem.sysItem.wAmmoOneClip#,
			ammo_count=#$theItem.sysItem.wAmmoCount#,				
		},
		color=#$theItem.sysItem.gunProperty.color#,
		gunproperty={{
			#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# 
			{
	    			#if $theItem.sysItem.gunProperty.color!=1# 
					#$theItem.sysItem.gunProperty.color#,
				#else# 
					1,
				#/if#
	    			"#$property.basePropertyStr#"
			}, 
			#/foreach#
		}},
	},
	#/foreach#
}
#if ($errorMsg!=null)# 
	error="#$errorMsg#" 
#else# 
	error=nil 
#/if#
