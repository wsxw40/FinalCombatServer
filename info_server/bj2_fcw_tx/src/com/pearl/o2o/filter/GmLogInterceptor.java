package com.pearl.o2o.filter;


import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.aop.AfterReturningAdvice;

import com.pearl.o2o.dao.impl.nonjoin.GmLogDao;
import com.pearl.o2o.pojo.BaseMappingBean;
import com.pearl.o2o.pojo.GmLog;
import com.pearl.o2o.pojo.GmUser;
import com.pearl.o2o.utils.CommonMsg;

public class GmLogInterceptor implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object obj, Method method, Object[] args, Object target) throws Throwable {
		
		boolean b = Modifier.isPublic(method.getModifiers());
		//只对类中的public方法进行拦截
		if(b == true){
			GmUser gmUser = (GmUser)args[0];
			String type = target.getClass().getSimpleName();
			//int index = type.indexOf("$");
			type = type.substring(4, 10);
			String methodName = method.getName();
			//create
//			if("presentToPlayer".equals(methodName)){
//				methodName = CommonMsg.GIFT_TO_PLAYER;
//			} else if("presentToPlayers".equals(methodName)){
//				methodName = CommonMsg.GIFT_TO_PLAYERS;
//			} else if("createSysConfig".equals(methodName)){
//				methodName = CommonMsg.ADD_SYS_CONFIG;
//			} else if("createSysCharacter".equals(methodName)){
//				methodName = CommonMsg.ADD_SYS_CHARACTER;
//			} else if("createServer".equals(methodName)){
//				methodName = CommonMsg.ADD_SERVER;
//			} else if("createChannel".equals(methodName)){
//				methodName = CommonMsg.ADD_CHANNEL;
//			} else if("createLevelInfo".equals(methodName)){
//				methodName = CommonMsg.ADD_MAP;
//			} else if("createLevelWeapon".equals(methodName)){
//				methodName = CommonMsg.ADD_LEVEL_WEAPON;
//			} else if("createSysItem".equals(methodName)){
//				methodName = CommonMsg.ADD_SYS_ITEM;
//			} else if("createPayment".equals(methodName)){
//				methodName = CommonMsg.ADD_PAYMENT;
//			} else if("createSysNotice".equals(methodName)){
//				methodName = CommonMsg.ADD_SYS_NOTICE;
//			} else if("createGMNoticeNow".equals(methodName)){
//				methodName =  CommonMsg.ADD_GM_NOTICE;
//			} else if("createBlackIP".equals(methodName)){
//				methodName = CommonMsg.ADD_BANNED_IP;
//			} else if("createBannedWord".equals(methodName)){
//				methodName = CommonMsg.ADD_BANNED_WROD;
//			} else if("createSystemMessage".equals(methodName)){
//				methodName = CommonMsg.SEND_SYS_MESSAGE;
//			} else if("kickPlayer".equals(methodName)){
//				methodName = CommonMsg.KICK_PLAYER;
//			} else if("createGmUser".equals(methodName)){
//				methodName = CommonMsg.ADD_GM_USER;
//			} else if("createGmGroup".equals(methodName)){
//				methodName = CommonMsg.ADD_GM_GROUP;
//			} else if("createGmUserGroup".equals(methodName)){
//				methodName = CommonMsg.ADD_GM_USER_TO_GROUP;
//			} else if("createGmGroupPrivilege".equals(methodName)){
//				methodName = CommonMsg.PRIVILEGE_TO_GROUP;
//			}
//			//delete
//			else if("deleteSysCharacter".equals(methodName)){
//				methodName = CommonMsg.DEL_CHARACTER;
//			} else if("deleteSysConfig".equals(methodName)){
//				methodName = CommonMsg.DEL_SYS_CONFIG;
//			} else if("deleteServerById".equals(methodName)){
//				methodName = CommonMsg.DEL_SERVER;
//			} else if("deleteChannelById".equals(methodName)){
//				methodName = CommonMsg.DEL_CHANNEL;
//			} else if("deletePackAll".equals(methodName)){
//				methodName = CommonMsg.DEL_PACKGE_ITEM;
//			} else if("deleteSysItem".equals(methodName)){
//				methodName = CommonMsg.DEL_SYS_ITEM;
//			} else if("deletePayment".equals(methodName)){
//				methodName = CommonMsg.DEL_PAYMENT;
//			} else if("deletePlayerItem".equals(methodName)){
//				methodName = CommonMsg.DEL_PLAYER_ITEM;
//			} else if("deleteLevelInfo".equals(methodName)){
//				methodName = CommonMsg.DEL_MAP;
//			} else if("deleteLevelWeapon".equals(methodName)){
//				methodName = CommonMsg.DEL_LEVEL_WEAPON;
//			} else if("deleteSysNotice".equals(methodName)){
//				methodName = CommonMsg.DEL_SYS_NOTICE;
//			} else if("deleteBlackIP".equals(methodName)){
//				methodName = CommonMsg.DEL_BANNED_IP;
//			} else if("deleteGmGroup".equals(methodName)){
//				methodName = CommonMsg.DEL_GM_GROUP;
//			} else if("deleteGmUserGroup".equals(methodName)){
//				methodName = CommonMsg.DEL_GM_USER_FROM_GROUP;
//			} else if("deleteGmUser".equals(methodName)){
//				methodName = CommonMsg.DEL_GM_USER;
//			} 
//			//update
//			else if("updateSysCharacter".equals(methodName)){
//				methodName = CommonMsg.UPDATE_CHARACTER;
//			} else if("updatePlayer".equals(methodName)){
//				methodName = CommonMsg.UPDATE_PLAYER;
//			} else if("updateServerList".equals(methodName)){
//				methodName = CommonMsg.UPDATE_SERVER;
//			} else if("updateLevelInfoList".equals(methodName)){
//				methodName = CommonMsg.UPDATE_MAPS;
//			} else if("updateLevelInfo".equals(methodName)){
//				methodName = CommonMsg.UPDATE_MAP;
//			} else if("updateLevelWeapons".equals(methodName)){
//				methodName = CommonMsg.UPDATE_LEVEL_WEAPONS;
//			} else if("updateChannelList".equals(methodName)){
//				methodName = CommonMsg.UPDATE_CHANNEL;
//			} else if("updateSysItem".equals(methodName)){
//				methodName = CommonMsg.UPDATE_SYS_ITEM;
//			} else if("updateSysNotice".equals(methodName)){
//				methodName = CommonMsg.UPDATE_SYS_NOTICE;
//			}else if("updateBlackIP".equals(methodName)){
//				methodName = CommonMsg.UPDATE_BANNED_IP;
//			}else if("updateBlackWhite".equals(methodName)){
//				methodName = CommonMsg.UPDATE_BLACK_WHITE;
//			}else if("updateBannedWord".equals(methodName)){
//				methodName = CommonMsg.UPDATE_BANNED_WORD;
//			}else if("loadBannedWordFile".equals(methodName)){
//				methodName = CommonMsg.LOAD_BANNED_WORD;
//			}else if("updateSysConfig".equals(methodName)){
//				methodName = CommonMsg.UPDATE_SYS_CONFIG;
//			}else if("updateGmUser".equals(methodName)){
//				methodName = CommonMsg.UPDATE_GM_USER;
//			}else if("updateGmGroup".equals(methodName)){
//				methodName = CommonMsg.UPDATE_GM_GROUP;
//			}
//			String o = args.toString();
			
			GmLog gmLog = new GmLog();
			gmLog.setGmUserId(gmUser.getId());
			gmLog.setGmUserName(gmUser.getName());
			gmLog.setType(type);
			gmLog.setMethodName(methodName);
			StringBuilder argsStr = new StringBuilder();
			if(args.length>1){
				for(int i=1;i<args.length;i++){
					if(args[i] instanceof List){
						List temp = (List) args[i];
						argsStr.append("[").append(StringUtils.join(temp, ";")).append("]").append(";");
					}else{
						argsStr.append(args[i].toString()).append(";");
					}
				}
			}
			gmLog.setArgs(argsStr.toString());
			gmLog.setRecordTime(new Date());
			
			gmLogDao.createGmLog(gmLog);
		}			
	}

	
	private GmLogDao gmLogDao;
	
	public GmLogDao getGmLogDao() {
		return gmLogDao;
	}
	public void setGmLogDao(GmLogDao gmLogDao) {
		this.gmLogDao = gmLogDao;
	}

}
