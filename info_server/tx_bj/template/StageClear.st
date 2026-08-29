rid = #$stageClear.rid#
type = #$stageClear.type#
lct = #$lct#
winner = #$stageClear.winner#
terrorist_score = #$stageClear.terroristScore#
police_score = #$stageClear.policeScore#
activity=#$stageClear.onlineActivityOffset#

#if $stageClear.type==5#
	team0={#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,#$playerInfo.costReliveCoin#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			vipGet=#$playerInfo.getChances.vipGet#,
			netBarGet=#$playerInfo.getChances.netBarGet#,
			activityGet=#$playerInfo.getChances.activityGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},		
			buff_list =
			{
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
			},
			
		#/foreach#}
	team1={}
#elseif $stageClear.type==10#
	team0={#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,#$playerInfo.costReliveCoin#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			vipGet=#$playerInfo.getChances.vipGet#,
			netBarGet=#$playerInfo.getChances.netBarGet#,
			activityGet=#$playerInfo.getChances.activityGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},		
			buff_list =
			{
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
			awards_count=#$playerInfo.boss2AwardsNum#,
			awards_list =
			{
				#foreach from=$playerInfo.boss2Awards item=theItem#
					{
						sid=#$theItem.sysItem.id#,
						display="#$theItem.sysItem.displayName#",
						name="#$theItem.sysItem.name#",
						modified_desc="#$theItem.sysItem.modifiedDesc#",
						characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
						description ="#$theItem.sysItem.description#",
						#include file="Timelimit.st" unitType=$theItem.unitType unit=$theItem.unit#
						sendperson = #$theItem.sysItem.isHot#,
						item_num=#$theItem.unit#,
						total_rare_level=#if $theItem.unitType==1# #$theItem.sysItem.rareLevel*$theItem.unit# #else# #$theItem.sysItem.rareLevel# #/if#,
						#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
						common={
							level = #$theItem.sysItem.level#,
							type = #$theItem.sysItem.type#,
							subtype = #$theItem.sysItem.subType#,
							#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
							seq=#$theItem.sysItem.seq#,
							is_vip=#$theItem.sysItem.isVip#,
							is_new=#$theItem.sysItem.isNew#,
							is_hot=#$theItem.sysItem.isHot#,
							star=#$theItem.sysItem.figthNumOutput#,    		
							strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.sysItem.strengthLevel##/if#,
							cResistanceFire=#$theItem.sysItem.cResistanceFire#,
							cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
							cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
							cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
							cBloodAdd=#$theItem.sysItem.cBloodAdd#,
							cResistanceFire_add=0,
							cResistanceBlast_add=0,
							cResistanceBullet_add=0,
							cResistanceKnife_add=0,
							cBloodAdd_add=0,
							rareLevel=#$theItem.sysItem.rareLevel#,
						},
					    performance = {
				    		damange = #$theItem.sysItem.damange#,					
					    	speed =#$theItem.sysItem.shootSpeed#,	
					    	ammos = #$theItem.sysItem.wAmmoOneClip#,
					    	ammo_count=#$theItem.sysItem.wAmmoCount#,				
						},
					    color=#$theItem.sysItem.gunProperty.color#,
					    gunproperty={
					    	#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# {
					    	#if $theItem.sysItem.gunProperty.color!=1# #$theItem.sysItem.gunProperty.color#,#else# 1,#/if#
					    	"#$property.basePropertyStr#"}, #/foreach#
					    },
					    package = {#foreach from=$theItem.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
					    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
					    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
					    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
					   
					    
					    resource = {
					    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
					    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #else#
					    #foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #/if#
					    },
					},
				#/foreach#
			},
			brands_list =
			{
				#foreach from=$playerInfo.brands item=theItem#
					{
						sid=#$theItem.sysItem.id#,
						display="#$theItem.sysItem.displayName#",
						name="#$theItem.sysItem.name#",
						modified_desc="#$theItem.sysItem.modifiedDesc#",
						characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
						description ="#$theItem.sysItem.description#",
						#include file="Timelimit.st" unitType=$theItem.unitType unit=$theItem.unit#
						sendperson = #$theItem.sysItem.isHot#,
						item_num=#$theItem.unit#,
						total_rare_level=#if $theItem.unitType==1# #$theItem.sysItem.rareLevel*$theItem.unit# #else# #$theItem.sysItem.rareLevel# #/if#,
						#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
						common={
							level = #$theItem.sysItem.level#,
							type = #$theItem.sysItem.type#,
							subtype = #$theItem.sysItem.subType#,
							#if $theItem.sysItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
							seq=#$theItem.sysItem.seq#,
							is_vip=#$theItem.sysItem.isVip#,
							is_new=#$theItem.sysItem.isNew#,
							is_hot=#$theItem.sysItem.isHot#,
							star=#$theItem.sysItem.figthNumOutput#,    		
							strength=#if $theItem.sysItem.isStrengthen == 0#-1#else##$theItem.sysItem.strengthLevel##/if#,
							cResistanceFire=#$theItem.sysItem.cResistanceFire#,
							cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
							cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
							cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
							cBloodAdd=#$theItem.sysItem.cBloodAdd#,
							cResistanceFire_add=0,
							cResistanceBlast_add=0,
							cResistanceBullet_add=0,
							cResistanceKnife_add=0,
							cBloodAdd_add=0,
							rareLevel=#$theItem.sysItem.rareLevel#,
						},
					    performance = {
				    		damange = #$theItem.sysItem.damange#,					
					    	speed =#$theItem.sysItem.shootSpeed#,	
					    	ammos = #$theItem.sysItem.wAmmoOneClip#,
					    	ammo_count=#$theItem.sysItem.wAmmoCount#,				
						},
					    color=#$theItem.sysItem.gunProperty.color#,
					    gunproperty={
					    	#foreach from=$theItem.sysItem.gunProperty.propertyList item=property# {
					    	#if $theItem.sysItem.gunProperty.color!=1# #$theItem.sysItem.gunProperty.color#,#else# 1,#/if#
					    	"#$property.basePropertyStr#"}, #/foreach#
					    },
					    package = {#foreach from=$theItem.sysItem.packages item=item# "#$item.displayName#", #/foreach#},
					    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
					    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
					    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
					    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},						
					   
					    
					    resource = {
					    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.sysItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
					    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #else#
					    #foreach from=$theItem.sysItem.resources item=resource# "#$resource#", #/foreach#
					    #/if#
					    },
					},
				#/foreach#
			},
			},
			
		#/foreach#}
	team1={}
#elseif $stageClear.type==15#
	winnerRes =#$stageClear.winnerRes#
	loserRes=#$stageClear.loserRes#
	team0costItems={#foreach from=$stageClear.teamaCostItems item=theItem#{
				costNum=#$theItem.quantity#,
				#if $theItem.sysItem.iId!=null# iid=#$theItem.sysItem.iId#, #/if#
				display="#$theItem.sysItem.displayName#",
				name="#$theItem.sysItem.name#",
				createtime=#$theItem.createTime#,
				unit_type=#$theItem.playerItemUnitType#,
				modified_desc="#$theItem.modifiedDesc#",
				characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
				description ="#$theItem.sysItem.description#",
				pack_id = #$theItem.pack#,
				repair_cost = #$theItem.repairCost#,
				#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
				#if $theItem.sysItem.iId==1# buff = #$theItem.buff#, #/if#
				#if $theItem.sysItem.iId==5# buff = #$theItem.buff#, #/if#	
				isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
				mType = #$theItem.sysItem.mType#,
				mValue = #$theItem.sysItem.mValue#,
				common={
				isAsset = #$theItem.sysItem.isAsset#,
					isOpenQuality=#if $theItem.sysItem.wId==13# 0,#else# 1, #/if#
					level = #$theItem.level#,
					materialNeed = #$theItem.materialNeed#,
					type = #$theItem.sysItem.type#,
					subtype = #$theItem.sysItem.subType#,
					#if $theItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
					durable = #if $theItem.durableInt<=0# 0,#else# #$theItem.durableInt#, #/if#
					quantity =  #$theItem.quantity#,
					minutes_left = #if $theItem.timeLeft<=0# 0,#else# #$theItem.timeLeft#, #/if#
					seq=#$theItem.sysItem.seq#,
					is_vip=#$theItem.sysItem.isVip#,
					is_new=1,
					star = #$theItem.fightNum#,   		
					strength=#$theItem.level#,
					holeNum=#$theItem.holeNum#,
					cResistanceFire=#$theItem.sysItem.cResistanceFire#,
					cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
					cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
					cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
					cBloodAdd=#$theItem.sysItem.cBloodAdd#,
					cResistanceFire_add=#$theItem.cResistanceFire#,
					cResistanceBlast_add=#$theItem.cResistanceBlast#,
					cResistanceBullet_add=#$theItem.cResistanceBullet#,
					cResistanceKnife_add=#$theItem.cResistanceKnife#,
					cBloodAdd_add=#$theItem.cBloodAdd#,
					rareLevel=#$theItem.sysItem.rareLevel#,
				},
					
			    performance = {
				    		damange = #$theItem.sysItem.damange#,					
					    	speed =#$theItem.sysItem.shootSpeed#,	
					    	damange_add = #$theItem.damange - $theItem.sysItem.damange#,			
				    		speed_add = #$theItem.shootSpeed - $theItem.sysItem.shootSpeed#,			
					    	ammos = #$theItem.sysItem.wAmmoOneClip#,
					    	ammo_count=#$theItem.sysItem.wAmmoCount#,				
				    },
			     color=#$theItem.gunProperty.color#,
			    
			    parts = {#foreach from=$theItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
			    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
			    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
			    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
			    package = {#foreach from=$theItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
				resource = {
			    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
			    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #else#
			    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #/if#
			    },
			},
			#/foreach#}
	team1costItems={#foreach from=$stageClear.teambCostItems item=theItem#{
				costNum=#$theItem.quantity#,
				#if $theItem.sysItem.iId!=null# iid=#$theItem.sysItem.iId#, #/if#
				display="#$theItem.sysItem.displayName#",
				name="#$theItem.sysItem.name#",
				createtime=#$theItem.createTime#,
				unit_type=#$theItem.playerItemUnitType#,
				modified_desc="#$theItem.modifiedDesc#",
				characters={#foreach from=$theItem.sysItem.characterList item=ids)# #$ids#, #/foreach#},
				description ="#$theItem.sysItem.description#",
				pack_id = #$theItem.pack#,
				repair_cost = #$theItem.repairCost#,
				#if $theItem.sysItem.wId==4# wid = #$theItem.sysItem.wId#, #/if#
				#if $theItem.sysItem.iId==1# buff = #$theItem.buff#, #/if#
				#if $theItem.sysItem.iId==5# buff = #$theItem.buff#, #/if#	
				isDefault = #if $theItem.isDefault=="Y"#0#else#1#/if#,
				mType = #$theItem.sysItem.mType#,
				mValue = #$theItem.sysItem.mValue#,
				common={
				isAsset = #$theItem.sysItem.isAsset#,
					isOpenQuality=#if $theItem.sysItem.wId==13# 0,#else# 1, #/if#
					level = #$theItem.level#,
					materialNeed = #$theItem.materialNeed#,
					type = #$theItem.sysItem.type#,
					subtype = #$theItem.sysItem.subType#,
					#if $theItem.type==1# wid=#$theItem.sysItem.wId#, #/if#
					durable = #if $theItem.durableInt<=0# 0,#else# #$theItem.durableInt#, #/if#
					quantity =  #$theItem.quantity#,
					minutes_left = #if $theItem.timeLeft<=0# 0,#else# #$theItem.timeLeft#, #/if#
					seq=#$theItem.sysItem.seq#,
					is_vip=#$theItem.sysItem.isVip#,
					is_new=1,
					star = #$theItem.fightNum#,   		
					strength=#$theItem.level#,
					holeNum=#$theItem.holeNum#,
					cResistanceFire=#$theItem.sysItem.cResistanceFire#,
					cResistanceBlast=#$theItem.sysItem.cResistanceBlast#,
					cResistanceBullet=#$theItem.sysItem.cResistanceBullet#,
					cResistanceKnife=#$theItem.sysItem.cResistanceKnife#,
					cBloodAdd=#$theItem.sysItem.cBloodAdd#,
					cResistanceFire_add=#$theItem.cResistanceFire#,
					cResistanceBlast_add=#$theItem.cResistanceBlast#,
					cResistanceBullet_add=#$theItem.cResistanceBullet#,
					cResistanceKnife_add=#$theItem.cResistanceKnife#,
					cBloodAdd_add=#$theItem.cBloodAdd#,
					rareLevel=#$theItem.sysItem.rareLevel#,
				},
					
			    performance = {
				    		damange = #$theItem.sysItem.damange#,					
					    	speed =#$theItem.sysItem.shootSpeed#,	
					    	damange_add = #$theItem.damange - $theItem.sysItem.damange#,			
				    		speed_add = #$theItem.shootSpeed - $theItem.sysItem.shootSpeed#,			
					    	ammos = #$theItem.sysItem.wAmmoOneClip#,
					    	ammo_count=#$theItem.sysItem.wAmmoCount#,				
				    },
			     color=#$theItem.gunProperty.color#,
			    
			    parts = {#foreach from=$theItem.parts item=part# {#$part.sysItem.subType#,"#$part.sysItem.displayName#", #$part.id#,}, #/foreach#},
			    gpprices={#foreach from=$theItem.sysItem.gpPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
			    crprices={#foreach from=$theItem.sysItem.crPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},
			    voucherprices={#foreach from=$theItem.sysItem.voucherPricesList item=pay# 
			    		{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, #/foreach#},	
			    package = {#foreach from=$theItem.packages item=item# "#$item.sysItem.displayName#", #/foreach#},
				resource = {
			    #if $theItem.sysItem.type==1# type=#$theItem.sysItem.wId#, #foreach from=$theItem.resource item=res#  #if($res!="")# "#$res#", #/if# #/foreach#
			    #elseif $theItem.sysItem.type==2# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #elseif $theItem.sysItem.type==3# type=#$theItem.sysItem.cId#,#foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #else#
			    #foreach from=$theItem.resources item=resource# "#$resource#", #/foreach#
			    #/if#
			    },
			},
			#/foreach#}
	team0={#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#, #$playerInfo.rank#, 
			isvip=#$playerInfo.isVip#,
			icon="#$playerInfo.headGif#",
			baseRes=#$playerInfo.earnRobRes#,
			captainRes=#$playerInfo.captainAddRes#,
			playerContribution=#$playerInfo.playerContribution#,
			},
		#/foreach#}
	team1={#foreach from=$stageClear.stageClearPlayerInfoTeam1 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#, #$playerInfo.rank#, 
			isvip=#$playerInfo.isVip#,
			icon="#$playerInfo.headGif#",
			baseRes=#$playerInfo.earnRobRes#,
			captainRes=#$playerInfo.captainAddRes#,
			playerContribution=#$playerInfo.playerContribution#,
			},
		#/foreach#}
#elseif $stageClear.isMatch==7#
	items={#foreach from=$stageClear.items item=thisItem#
					{#$thisItem.id#,"#$thisItem.name#"},#/foreach#
				}
	team0={#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},
			item_list={#foreach from=$playerInfo.items item=thisItem#
					{#$thisItem.sysItem.id#,#$thisItem.unit#},#/foreach#
				},
			buff_list ={#foreach from=$playerInfo.buffList item=buff#
						{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},#/foreach#
				},
			},
		#/foreach#}
	team1={#foreach from=$stageClear.stageClearPlayerInfoTeam1 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},
			item_list={#foreach from=$playerInfo.items item=thisItem#
					{#$thisItem.sysItem.id#,#$thisItem.unit#},#/foreach#
				},
			buff_list ={#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},#/foreach#
				},
			},
		#/foreach#}
#else#
	team0={#foreach from=$stageClear.stageClearPlayerInfoTeam0 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			vipGet=#$playerInfo.getChances.vipGet#,
			netBarGet=#$playerInfo.getChances.netBarGet#,
			activityGet=#$playerInfo.getChances.activityGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},
			buff_list =
				{
					#foreach from=$playerInfo.buffList item=buff#
						{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
					#/foreach#
				},
			},
		#/foreach#}
	team1={#foreach from=$stageClear.stageClearPlayerInfoTeam1 item=playerInfo#{
			#$playerInfo.id#, "#$playerInfo.name#", #$playerInfo.side#,#$playerInfo.currRank#,#$playerInfo.currExp#,#$playerInfo.currGp#, #$playerInfo.rank#, 
			#$playerInfo.exp#, #$playerInfo.gp#, #$playerInfo.score#, #$playerInfo.kill#, #$playerInfo.dead#, 
			#$playerInfo.controlNum#, #$playerInfo.revengeNum#, #$playerInfo.assistNum#, #$playerInfo.knifeKill#, 
			#$playerInfo.maxHeadshotNum#, #$playerInfo.maxHeadshotCharacter#, #$playerInfo.maxKillNum#, #$playerInfo.maxKillNumCharacter#,#$playerInfo.maxDamageNum#, #$playerInfo.maxDamageNumCharacter#,
			#$playerInfo.maxHealthNum#, #$playerInfo.maxHealthNumCharacter#,  #$playerInfo.maxLiveTime#, #$playerInfo.maxLiveTimeCharacter#,
			isvip=#$playerInfo.isVip#,
			addedVipExp=#$playerInfo.vipExp#,
			internetCafe=#$playerInfo.internetCafe#,
			icon="#$playerInfo.headGif#",
			playerTeamExp=#$playerInfo.playerTeamExp#,
			playerContribution=#$playerInfo.playerContribution#,
			isTeamAdd=#$playerInfo.isTeamAdd#,
			get_exps=
			{
			gameGet=#$playerInfo.getExps.gameGet#,
			vipGet=#$playerInfo.getExps.vipGet#,
			netBarGet=#$playerInfo.getExps.netBarGet#,
			activityGet=#$playerInfo.getExps.activityGet#,
			itemAdd=#$playerInfo.getExps.itemAdd#,
			teamAdd=#$playerInfo.getExps.teamAdd#,
			},
			get_gps=
			{
			gameGet=#$playerInfo.getGps.gameGet#,
			vipGet=#$playerInfo.getGps.vipGet#,
			netBarGet=#$playerInfo.getGps.netBarGet#,
			activityGet=#$playerInfo.getGps.activityGet#,
			itemAdd=#$playerInfo.getGps.itemAdd#,
			teamAdd=#$playerInfo.getGps.teamAdd#,
			},
			get_chances=
			{
			gameGet=#$playerInfo.getChances.gameGet#,
			vipGet=#$playerInfo.getChances.vipGet#,
			netBarGet=#$playerInfo.getChances.netBarGet#,
			activityGet=#$playerInfo.getChances.activityGet#,
			totalGet=#$playerInfo.getChances.totalGet#,
			},
			buff_list =
			{
				#foreach from=$playerInfo.buffList item=buff#
					{"#$buff.sysItem.name#","#$buff.sysItem.displayName#",},
				#/foreach#
			},
			},
		#/foreach#}
#/if#


mvp =
{	#if $mvpPlayer !=null#
		"#$mvpPlayer.name#",
		#$mvpPlayer.rank#,
		"#$mvpPlayer.icon#",
		"#$mvpWeapon.sysItem.name#",
		"#$mvpWeapon.sysItem.displayName#",
		#$mvpPlayer.exp#,
		#$mvpWeapon.gunProperty.color#,
		#if $mvpWeapon.sysItem.isStrengthen == 0#-1#else##$mvpWeapon.level##/if#,
	#else#
		"",1,"","","",1,-1,-1,
	#/if#
	}	
