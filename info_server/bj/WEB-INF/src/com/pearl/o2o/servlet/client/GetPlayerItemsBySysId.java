package com.pearl.o2o.servlet.client;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.pojo.PlayerMelting;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class GetPlayerItemsBySysId extends BaseClientServlet {
	private static final long serialVersionUID = 5117201445216191262L;
	static Logger log = LoggerFactory.getLogger(GetPlayerItemsBySysId.class
			.getName());
	private static final String[] paramNames = { "pid", "t" };

	// rpc.safecall("get_pitems_by_sysid",{pid = 10638000,t = 0},function(data)
	// end)
	protected String innerService(String... args) {
		try {
			int playerId = StringUtil.toInt(args[0]);
			int t = StringUtil.toInt(args[1]);
			int aAppliance = StringUtil.toInt(getService.getSysConfig().get(
					"qwzb.appliance.a"));
			int bAppliance = StringUtil.toInt(getService.getSysConfig().get(
					"qwzb.appliance.b"));
			int cAppliance = StringUtil.toInt(getService.getSysConfig().get(
					"qwzb.appliance.c"));
			int aBlueprint = StringUtil.toInt(getService.getSysConfig().get(
					"qwzb.blueprint.a"));
			int[] sysitems = new int[] { aAppliance, bAppliance, cAppliance,
					aBlueprint };
			List<PlayerItem> list = new ArrayList<PlayerItem>();
			if (t == 0) {
				for (int i = 0; i < sysitems.length; i++) {
					list.addAll(getService.getPlayerItemsBySysId(playerId,
							sysitems[i]));
				}
			}
			if (t == 1) {
				for (int i = 0; i < sysitems.length; i++) {
					List<PlayerItem> playerItems = getService.getPlayerItemsBySysId(playerId, sysitems[i]);
					if (playerItems.size()==0) {
						PlayerItem playerItem = new PlayerItem();
						playerItem.setId(0);
						playerItem.setItemId(sysitems[i]);
						list.add(playerItem);
					} else {
						for (PlayerItem playerItem : playerItems) {
							if (playerItem.getQuantity() >= 1 && playerItem.getIsDeleted().equals("N")) {
								list.add(playerItem);
								break;
							}
						}
					}
				}
			}
			PlayerMelting melting = getService.getPlayerMeltingDao().getPlayerMelting(playerId);
			if (melting==null) {
				melting = getService.getPlayerMeltingDao().getPlayerMelting(playerId);
			}
			// String result=Converter.getQwInfo(type,null,null,page,1,1);
			String result = Converter.playerItems(list, t,melting.getLevel());
			return result;
		} catch (Exception e) {
			log.warn("Exception in GetPlayerItems", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[1]));
	}
}
