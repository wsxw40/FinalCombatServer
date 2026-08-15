package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class CreatePlayer extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CreatePlayer.class.getName());
	private String[] paramNames={"uid","name","id","side"};
	public CreatePlayer(){
		super();
	}	
	@Override
	protected String innerService(String... args) {
		return null;
//		Player player = null;
//		try{
//			int userId = StringUtil.toInt(args[0]);
//			String name = args[1];
//			int id = StringUtil.toInt(args[2]);
//			int side = StringUtil.toInt(args[3]);
////			player = createService.createPlayer(userId, name.trim(), id,side);
//			if(player!=null){
//				return Converter.createPlayer(player.getId(),null,null);			
//			}else{
//				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL); 
//			}
//		}
//		catch(NotBuyEquipmentException be){
//			return Converter.createPlayer(0,be.getMessage(),null);
//		}
////		catch(BaseException be){
////			return Converter.createPlayer(0,null,be.getMessage());
////		}		
//		catch(Exception e){
//			log.warn("Error in CreatePlayer: ", e);
//			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);	
//		}
	} 
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
