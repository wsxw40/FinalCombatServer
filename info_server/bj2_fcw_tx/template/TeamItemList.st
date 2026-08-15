teamResourceWar={
	placeLevel=#$placeLevel#,
	isLeader=#$isLeader#,
	resoucesNum=#$resoucesNum#,
	time = #$time#,
	fc=#$fc#,
	playerRes=#$playerRes#,
	teamStorage={
		#if $storageType==1 || $storageType==5 #
			#if $storageType==1 #
				defense={
					curPage=#$curPage#,
					pages=#$pages#,
					#foreach from=$itemList item=teamItem#
					{
						isEvolve=#$teamItem.oilOrWallUpgrade#,
						level=#$teamItem.level#,
						teamItemId = #$teamItem.id#,
						sysItemId = #$teamItem.sysItem.id#,
						display = "#$teamItem.sysItem.displayName#",
						name = "#$teamItem.sysItem.name#",
						description = "#$teamItem.sysItem.description#",
						value = #$teamItem.showQuantity#,
						maxquantity=#$teamItem.maxQuantity#,
						buycd="#$teamItem.buyCD#",
						durable = #$teamItem.durable#,
						resteamprice={
							id=#$teamItem.currentCreatePayMent.id#,
							unittype=#$teamItem.currentCreatePayMent.unitType#,
							cost=#$teamItem.currentCreatePayMent.cost#,
							unit=#$teamItem.currentCreatePayMent.unit#
						},	
						resteamprices={
							#foreach from=$teamItem.sysItem.resTeamPricesList item=pay# 
							{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
							#/foreach#
						},	    					
						performance={
							hp={
								property = #$teamItem.property3.property#,
								level = #$teamItem.property3.level#,
								value = #$teamItem.property3.value#,
								curExp = #$teamItem.property3.curExp#,
								maxExp = #$teamItem.property3.maxExp#,
							},
						},
					},
					#/foreach#
				},
			#/if#
			
			#if $storageType==5 #
				machine={
					curPage=#$curPage#,
					pages=#$pages#,
					#foreach from=$itemList item=teamItem#
					{
						isEvolve=#$teamItem.oilOrWallUpgrade#,
						level=#$teamItem.level#,
						teamItemId = #$teamItem.id#,
						sysItemId = #$teamItem.sysItem.id#,
						display = "#$teamItem.sysItem.displayName#",
						name = "#$teamItem.sysItem.name#",
						description = "#$teamItem.sysItem.description#",
						value = #$teamItem.showQuantity#,
						maxquantity=#$teamItem.maxQuantity#,
						buycd="#$teamItem.buyCD#",
						resteamprice={
							id=#$teamItem.currentCreatePayMent.id#,
							unittype=#$teamItem.currentCreatePayMent.unitType#,
							cost=#$teamItem.currentCreatePayMent.cost#,
							unit=#$teamItem.currentCreatePayMent.unit#
						},	
						resteamprices={
							#foreach from=$teamItem.sysItem.resTeamPricesList item=pay# 
							{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
							#/foreach#
						},
						performance={
							hp={
								property = #$teamItem.property3.property#,
								level = #$teamItem.property3.level#,
								value = #$teamItem.property3.value#,
								curExp = #$teamItem.property3.curExp#,
								maxExp = #$teamItem.property3.maxExp#,
							},
						},
					},
					#/foreach#
				},
			#/if#
		#/if#
		#if $storageType==2 #
			attack={
				curPage=#$curPage#,
				pages=#$pages#,
				#foreach from=$itemList item=teamItem#
				{
					isEvolve=#$teamItem.towerIsUpgrade#,
					level=#$teamItem.level#,
					teamItemId = #$teamItem.id#,
					sysItemId = #$teamItem.sysItem.id#,
					display = "#$teamItem.sysItem.displayName#",
					name = "#$teamItem.sysItem.name#",
					description = "#$teamItem.sysItem.description#",
					maxquantity=#$teamItem.maxQuantity#,
					value = #$teamItem.showQuantity#,
					buycd="#$teamItem.buyCD#",
					durable = #$teamItem.durable#,
					bullet = #$teamItem.bullet#,
					resteamprice={
						id=#$teamItem.currentCreatePayMent.id#,
						unittype=#$teamItem.currentCreatePayMent.unitType#,
						cost=#$teamItem.currentCreatePayMent.cost#,
						unit=#$teamItem.currentCreatePayMent.unit#
					},		
					resteamprices={
						#foreach from=$teamItem.sysItem.resTeamPricesList item=pay# 
							{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
						#/foreach#
					},
					performance={
						power={
							property = #$teamItem.property1.property#,
							level = #$teamItem.property1.level#,
							value = #$teamItem.property1.value#,
							curExp = #$teamItem.property1.curExp#,
							maxExp = #$teamItem.property1.maxExp#,
						},
						fireSpeed={
							property = #$teamItem.property2.property#,
							level = #$teamItem.property2.level#,
							value = #$teamItem.property2.value#,
							curExp = #$teamItem.property2.curExp#,
							maxExp = #$teamItem.property2.maxExp#,
						},
						hp={
							property = #$teamItem.property3.property#,
							level = #$teamItem.property3.level#,
							value = #$teamItem.property3.value#,
							curExp = #$teamItem.property3.curExp#,
							maxExp = #$teamItem.property3.maxExp#,
						},
						fireDistance={
							property = #$teamItem.property4.property#,
							level = #$teamItem.property4.level#,
							value = #$teamItem.property4.value#,
							curExp = #$teamItem.property4.curExp#,
							maxExp = #$teamItem.property4.maxExp#,
						},
						rotateSpeed={
							property = #$teamItem.property5.property#,
							level = #$teamItem.property5.level#,
							value = #$teamItem.property5.value#,
							curExp = #$teamItem.property5.curExp#,
							maxExp = #$teamItem.property5.maxExp#,
						},
					},
				},
				#/foreach#
			},
		#/if#
		#if $storageType==3 #
			scene={},
		#/if#	
	},
}