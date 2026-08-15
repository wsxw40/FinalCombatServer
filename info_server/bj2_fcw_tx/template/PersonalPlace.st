teamResourceWar={
	fc=#$fc#,
	resoucesNum=#$resoucesNum#,
	teamStorage={
		#if $storageType==3#
			tank={
				curPage=#$curPage#,
				pages=#$pages#,
				#foreach from=$itemList item=playerItem#
				{
					level=#$playerItem.level#,
					playerItemId = #$playerItem.id#,
					sysItemId = #$playerItem.sysItem.id#,
					display = "#$playerItem.sysItem.displayName#",
					name = "#$playerItem.sysItem.name#",
					description = "#$playerItem.sysItem.description#",
					value = #$playerItem.quantityForZYZDZPersonal#,
					buycd="#$playerItem.buyCD#",
					durable = 100,
					bullet = 100,	    				
	    				resDisPrice={
						id=#$playerItem.sysItem.resDisPricesList[0].id#,
						unittype=#$playerItem.sysItem.resDisPricesList[0].unitType#,
						cost=#$playerItem.sysItem.resDisPricesList[0].cost#,
						unit=#$playerItem.sysItem.resDisPricesList[0].unit#
					},							
	    				resDisPrices={
						#foreach from=$playerItem.sysItem.resDisPricesList item=pay# 
	    					{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
						#/foreach#
					},
					performance={
						power={
							property = #$playerItem.property1.property#,
							level = #$playerItem.property1.level#,
							value = #$playerItem.property1.value#,
							curExp = #$playerItem.property1.curExp#,
							maxExp = #$playerItem.property1.maxExp#,								
						},
						fireSpeed={
							property = #$playerItem.property2.property#,
							level = #$playerItem.property2.level#,
							value = #$playerItem.property2.value#,
							curExp = #$playerItem.property2.curExp#,
							maxExp = #$playerItem.property2.maxExp#,
						},
						hp={
							property = #$playerItem.property3.property#,
							level = #$playerItem.property3.level#,
							value = #$playerItem.property3.value#,
							curExp = #$playerItem.property3.curExp#,
							maxExp = #$playerItem.property3.maxExp#,
						},
						moveSpeed={
							property = #$playerItem.property6.property#,
							level = #$playerItem.property6.level#,
							value = #$playerItem.property6.value#,
							curExp = #$playerItem.property6.curExp#,
							maxExp = #$playerItem.property6.maxExp#,
						},
					},
				},
				#/foreach#
			},	
		#else#
			buff={
				curPage=#$curPage#,
				pages=#$pages#,
				#foreach from=$itemList item=playerItem#
				{
					sysItemId = #$playerItem.sysItem.id#,
					display = "#$playerItem.sysItem.displayName#",
					name = "#$playerItem.sysItem.name#",
					description = "#$playerItem.sysItem.description#",
					value = #$playerItem.quantityForZYZDZPersonal#,
					lid=#$playerItem.sysItem.iId#,
					capacity=#$playerItem.sysItem.wAccuracyTime#,
					time=#$playerItem.sysItem.wAccuracyTimeModefier#,
					resPrices={
						#foreach from=$playerItem.sysItem.resPricesList item=pay#  
						{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
						#/foreach#
					},
					crPrices={
						#foreach from=$playerItem.sysItem.crPricesList item=pay#  
						{id=#$pay.id#,unittype=#$pay.unitType#,cost=#$pay.cost#,unit=#$pay.unit#,}, 
						#/foreach#
					},
				},
				#/foreach#
			},
		#/if#
	},
}