package com.pearl.o2o.servlet.server;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
//在数据量大的时候会游一定的延迟写log,不过不影响游戏进行
public class SetKillInfo extends BaseServerServlet {
	private static final long serialVersionUID = -186025727266294629L;
	static Logger logger = LoggerFactory.getLogger(SetKillInfo.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
//		ByteArrayOutputStream out = new ByteArrayOutputStream();
//		try{
//			Map<Integer, Player> playerMap=new HashMap<Integer, Player>();
//			Map<Integer, SysCharacter> characterMap=new HashMap<Integer, SysCharacter>();
//			byte mapId = r.readByte();
//			LevelModeInfo levelInfo=getService.getLevelModeInfo((int)mapId);
//			int count = r.readInt();
//			for(int k=0;k<count;k++){
//				int cid=r.readInt();
//				int size = r.readInt();
//				Player kill = playerMap.get(cid);
//				if(kill==null){
//					kill=getService.getSimplePlayerById(cid);
//					playerMap.put(cid, kill);
//				}
//				for (int i=0;i<size;i++) {
//					
//					float x = r.readFloat();
//					float y = r.readFloat();
//					float z = r.readFloat();
//					int killtime=r.readInt();
//					int deadId = r.readInt();
//					byte deadCharacterId = r.readByte();
//					byte killCharacterId = r.readByte();
//					int killWeaponId = r.readInt();
//					try{
//						if(killWeaponId!=0&&killCharacterId<=10&&deadCharacterId!=120){
//							Player dead = playerMap.get(deadId);
//							if(dead==null){
//								dead=getService.getSimplePlayerById(deadId);
//								playerMap.put(deadId, dead);
//							}
//							SysCharacter deadCharacter = characterMap.get((int)deadCharacterId);
//							if(deadCharacter==null){
//								deadCharacter=getService.getSysCharacter((int)deadCharacterId);
//								characterMap.put((int)deadCharacterId, deadCharacter);
//							}
//							SysCharacter killCharacter = characterMap.get((int)killCharacterId);
//							if(killCharacter==null){
//								killCharacter=getService.getSysCharacter((int)killCharacterId);
//								characterMap.put((int)killCharacterId, killCharacter);
//							}
//							SysItem si = getService.getSysItemByItemId(killWeaponId);
//							ServiceLocator.nosqlService.addXunleiLog("4.1"+Constants.XUNLEI_LOG_DELIMITER+CommonUtil.simpleDateFormat.format(new Date(killtime*1000l))
//									+Constants.XUNLEI_LOG_DELIMITER+dead.getUserName()
//									+Constants.XUNLEI_LOG_DELIMITER+dead.getName()
//									+Constants.XUNLEI_LOG_DELIMITER+deadCharacter.getName()
//									+Constants.XUNLEI_LOG_DELIMITER+dead.getRank()
//									+Constants.XUNLEI_LOG_DELIMITER+levelInfo.getDisplayName()
//									+Constants.XUNLEI_LOG_DELIMITER+x
//									+Constants.XUNLEI_LOG_DELIMITER+y
//									+Constants.XUNLEI_LOG_DELIMITER+z
//									+Constants.XUNLEI_LOG_DELIMITER+levelInfo.getType()
//									+Constants.XUNLEI_LOG_DELIMITER+kill.getUserName()
//									+Constants.XUNLEI_LOG_DELIMITER+kill.getName()
//									+Constants.XUNLEI_LOG_DELIMITER+killCharacter.getName()
//									+Constants.XUNLEI_LOG_DELIMITER+kill.getRank()
//									+Constants.XUNLEI_LOG_DELIMITER+si.getDisplayName()
//							);
//						}
//					}catch (Exception e) {
//						logger.error("Error in setkillinfo:killWeaponId="+killWeaponId, e);
//					}
//				}
//			}
//			return out.toByteArray();
//		}catch (Exception e) {
//			logger.warn("Error in setkillinfo: ", e);
////			throw e;
//		}
		return null;
	}
}
