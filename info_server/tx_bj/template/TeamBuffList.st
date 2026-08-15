num = #$num#
contribution = #$contribution#
contributionLimit = #$contributionLimit#	#*贡献值需求*#
items = {
	#foreach from=$list item=teamBuffView#	
	{
		teambuffid=#$teamBuffView.sysTeamBuff.id#,
		iBuffId=#$teamBuffView.sysItem.iBuffId#,
		iValue=#$teamBuffView.sysItem.iValue#,
		status = #$teamBuffView.status#,
		payType = #$teamBuffView.sysTeamBuff.payType#,
		cost = #$teamBuffView.sysTeamBuff.cost#,
		isBuff = #$teamBuffView.sysItem.isBuff#,
		display="#$teamBuffView.sysItem.displayName#",
		name="#$teamBuffView.sysItem.name#",
		description ="#$teamBuffView.sysItem.description#",
		#include file="Timelimit.st" unitType=$teamBuffView.sysTeamBuff.unitType unit=$teamBuffView.sysTeamBuff.unit#
		color=#$teamBuffView.sysItem.gunProperty.color#,
		sendperson = #$teamBuffView.sysItem.isHot#,
		#if $teamBuffView.sysItem.wId==4# wid = #$teamBuffView.sysItem.wId#, #/if#
		common={
			type = #$teamBuffView.sysItem.type#,
			level = #$teamBuffView.sysItem.level#,
			modified_desc="#$teamBuffView.sysItem.modifiedDesc#",
			characters={#foreach from=$teamBuffView.sysItem.characterList item=ids)# #$ids#, #/foreach#},
			subtype = #$teamBuffView.sysItem.subType#,
			#if $teamBuffView.sysItem.type==1# wid=#$teamBuffView.sysItem.wId#, #/if#
			seq=#$teamBuffView.sysItem.seq#,
			is_vip=#$teamBuffView.sysItem.isVip#,
			is_new=#$teamBuffView.sysItem.isNew#,
			is_hot=#$teamBuffView.sysItem.isPopular#,
			is_web=#$teamBuffView.sysItem.isWeb#,
			minutes_left=#$teamBuffView.expireMinutesLeft#,
			star=#$teamBuffView.sysItem.figthNumOutput#,   		
			strength=#if $teamBuffView.sysItem.isStrengthen == 0#-1#else# #$teamBuffView.sysItem.strengthLevel# #/if#,
			cResistanceFire=#$teamBuffView.sysItem.cResistanceFire#,
			cResistanceBlast=#$teamBuffView.sysItem.cResistanceBlast#,
			cResistanceBullet=#$teamBuffView.sysItem.cResistanceBullet#,
			cResistanceKnife=#$teamBuffView.sysItem.cResistanceKnife#,
			cBloodAdd=#$teamBuffView.sysItem.cBloodAdd#,
			cResistanceFire_add=0,
			cResistanceBlast_add=0,
			cResistanceBullet_add=0,
			cResistanceKnife_add=0,
			cBloodAdd_add=0,
			rareLevel=#$teamBuffView.sysItem.rareLevel#,
		},
	    performance = {
    		damange = #$teamBuffView.sysItem.damange#,					
	    	speed =#$teamBuffView.sysItem.shootSpeed#,	
	    	ammos = #$teamBuffView.sysItem.wAmmoOneClip#,
	    	ammo_count=#$teamBuffView.sysItem.wAmmoCount#,				
		},
	    gunproperty={
	    	#foreach from=$teamBuffView.sysItem.gunProperty.propertyList item=property# {
	    	#if $teamBuffView.sysItem.gunProperty.color!=1# #$teamBuffView.sysItem.gunProperty.color#,#else# 1,#/if#
	    	"#$property.basePropertyStr#"}, #/foreach#
	    },
	    package = {
	    	#foreach from=$teamBuffView.sysItem.packages item=item# 
	    		"#$item.displayName#", 
	    	#/foreach#
	    },
	    resource = {
		    #if $teamBuffView.sysItem.type==1#
		    	type=#$teamBuffView.sysItem.wId#, #foreach from=$teamBuffView.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
		    #elseif $teamBuffView.sysItem.type==2#
		    	type=#$teamBuffView.sysItem.cId#,#foreach from=$teamBuffView.sysItem.resources item=resource# "#$resource#", #/foreach#
		    #elseif $teamBuffView.sysItem.type==3#
		    	type=#$teamBuffView.sysItem.cId#,#foreach from=$teamBuffView.sysItem.resources item=resource# "#$resource#", #/foreach#
		    #else#
		    	#foreach from=$teamBuffView.sysItem.resources item=resource# 
		    		"#$resource#", 
		    	#/foreach#
		    #/if#
	    },
		quantity=#$teamBuffView.sysTeamBuff.unit#,
	},
	#/foreach#
}