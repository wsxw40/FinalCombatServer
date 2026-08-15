package com.pearl.o2o.servlet.client;


import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.NotEquipException;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

public class DeletePackEquipment extends BaseClientServlet {
	static Logger log = LoggerFactory.getLogger(DeletePackEquipment.class.getName());
	private static final long serialVersionUID = -3467750958211447191L;
	private String[] paramNames={"uid","pid","cid","t","seq"};
	public DeletePackEquipment() {
		super();
	}
	@Override
	protected String innerService(String... args) {
		try{
//			int userId = StringUtil.toInt(args[0]);
			int playerId=StringUtil.toInt(args[1]);
			int characterId=StringUtil.toInt(args[2]);
			int type=StringUtil.toInt(args[3]);
			int seq=StringUtil.toInt(args[4]);
			//no this function
			if(type==1&&(seq==1||seq==2||seq==3)){
				throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
			}else if(type==2){
				throw new NotEquipException(ExceptionMessage.CANNOT_UNPACK);
			}else{
//				deleteService.deletePackEquip(playerId,characterId,type,seq);
				updateService.updatePlayerPackWithDefault(playerId, type==1?"W":"C", characterId, type, seq);
			}
			
			return Converter.deletePackEquipment(null);
		}catch (NotEquipException e) {
			log.debug("base exception in DeletePackEquipment servlet:"+e.getMessage());
			return Converter.deletePackEquipment(e.getMessage());
		}
		catch (Exception nee) {
			log.warn("exception in DeletePackEquipment servlet",nee);
			return Converter.deletePackEquipment(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
