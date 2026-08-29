package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class CreatePackEquipment extends BaseClientServlet {
	private static final long serialVersionUID = -6515353905004343636L;
	static Logger log = LoggerFactory.getLogger(CreatePackEquipment.class.getName());
	private String[] paramNames={"uid","pid","cid","playeritemid","t"};
	public CreatePackEquipment() {
		super();
	}
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int characterId=StringUtil.toInt(args[2]);
			int playerItemId=StringUtil.toInt(args[3]);
			int type=StringUtil.toInt(args[4]);
			if(Constants.DEFAULT_WEAPON_TYPE == type){
				updateService.updateWeaponPackEquipment( playerId, type, playerItemId, characterId);
			}else if(Constants.DEFAULT_COSTUME_TYPE == type || Constants.DEFAULT_PART_TYPE == type){
				updateService.updateCostumePackEquipment( playerId, type, playerItemId, characterId);
			}
			Player player=getService.getSimplePlayerById(playerId);
			soClient.updateCharacterInfo(player, player.getTeam(), player.getRank());
			return Converter.createPackEquipment(null);
		}
		catch (NotEquipException nee) {
			return Converter.createPackEquipment(nee.getMessage());
		}
		catch (Exception e) {
			log.warn("Exception in CreatePackEquipment",e);
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
