/**
 * 
 */
package com.pearl.o2o.protocal.sm.responseBinaryRPC;

import java.util.ArrayList;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;

import com.pearl.o2o.protocal.pojo.PlayerItem;
import com.pearl.o2o.protocal.pojo.SysCharacter;
import com.pearl.o2o.protocal.sm.SM_ResponseBinaryRPC;

/**
 * @author lifengyang
 * 未完成，太痛苦了，我的天啊！！！！！！！！！啊啊啊啊啊啊啊啊啊啊 啊啊啊啊 啊 啊啊啊 神人啊 
 */
public class s_get_character_info extends SM_ResponseBinaryRPC {

	public s_get_character_info(int userDataLength) {
		super(userDataLength);
	}

	@Override
	protected final void fillResponseData(ChannelBuffer message) {
	}

	private void mergeCharacterList(ChannelBuffer buffer) {
		int characterListSize = buffer.readInt();
		Character[] characterList = new Character[characterListSize];
		for (int i = 0; i < characterList.length; i++) {
			int playerId = buffer.readInt();
			SysCharacter sysCharacter = new SysCharacter();
			sysCharacter.setId(buffer.readInt());
			sysCharacter.setName(readStringByLengthField(buffer));
			sysCharacter.setResourceName(readStringByLengthField(buffer));
			String headResource = readStringByLengthField(buffer);
			sysCharacter.setMaxHp(buffer.readInt());
			sysCharacter.setExHp(buffer.readInt());
			sysCharacter.setIsFired(buffer.readInt());
			
			sysCharacter.setRunSpeed(buffer.readFloat());
			sysCharacter.setWalkSpeed(buffer.readFloat());
			sysCharacter.setCrouchSpeed(buffer.readFloat());
			sysCharacter.setAccelSpeed(buffer.readFloat());
			sysCharacter.setJumpSpeed(buffer.readFloat());
			sysCharacter.setThrowSpeed(buffer.readFloat());
			
			sysCharacter.setControllerHeight(buffer.readFloat());
			sysCharacter.setControllerRadius(buffer.readFloat());
			sysCharacter.setControllerCrouchHeight(buffer.readFloat());
			
			sysCharacter.setIsEnable(buffer.readByte());
			sysCharacter.setScoreOffset(buffer.readByte());
			buffer.readInt();

			List<PlayerItem> costumeList = new ArrayList<PlayerItem>();
//			for(PlayerItem pi:costumeList){
//				if(pi!=null){
//					SysItem si=ServiceLocator.getService.getSysItemByItemId(pi.getItemId());
//					pi.writeCostume(out,si,sysCharacter);
//				}else{
//					out.write(BinaryUtil.toByta(0));
//				}
//			}
			
		}
	}

	@Override
	protected final void mergeResponse(ChannelBuffer buffer) {
		int uId = buffer.readInt();
		int name = buffer.readInt();
		byte characterSize = buffer.readByte();
		int buffListSize = buffer.readInt();
		PlayerItem[] buffList = new PlayerItem[buffListSize];
		for (int i = 0; i < buffListSize; i++) {
			byte iId = buffer.readByte();
			byte iBuffId = buffer.readByte();
			float iValue = buffer.readByte();
			buffList[i] = new PlayerItem(iId, iBuffId, iValue);
		}
		if (buffer.readable()) {
			mergeCharacterList(buffer);
		}
	}

	@Override
	public final String getMessageStringFace() {
		return toString();
	}

	@Override
	public String toString() {
		return "s_get_character_info [queueId=" + queueId + ", queueCurrentSize=" + queueCurrentSize + ", rpcId=" + rpcId + ", statusCode=" + statusCode + ", type=" + type + ", datalength=" + datalength + "]";
	}

}
