package com.pearl.fcw.lobby.servlet.server.player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.CharacterService;
import com.pearl.fcw.proto.server.RequestPlayerOnline;
import com.pearl.fcw.proto.server.ResponsePlayerOnline;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 角色登录（角色如果不存在，客户端会重新请求SPlayerOnline接口）
 */
@Service("fcw_s_character_online")
public class SCharacterOnline extends BaseServerServlet implements Servletable {

    private static final long serialVersionUID = 1326252580819046140L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private CharacterService characterService;
    @Resource
    private SCharacterOnline fcw_s_character_online;

    @Override
    public String getLockedKey(BinaryReader reader) {
        try {
            reader.readInt();
            String userName = reader.readString().trim();
            return userName;
        } catch (Exception e) {
            logger.error("getLockedKey has error ", e);
        }
        return Servletable.super.getLockedKey(reader);
    }

    @Override
    protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
            return fcw_s_character_online.server(in);
        } catch (BaseException e) {
			logger.error("fcw_s_character_online has error : ", e);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(BinaryUtil.toBytas(0, -1, e.getMessage()));
            return out.toByteArray();
        } catch (Exception e) {
			logger.error("fcw_s_character_online has error : ", e);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            return out.toByteArray();
        }
    }

    @Override
    public byte[] server(BinaryReader reader) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int uid = reader.readInt();
        RequestPlayerOnline.Builder requestBuilder = RequestPlayerOnline.newBuilder();
        requestBuilder.setUserId(uid);
        requestBuilder.setUserName(reader.readString().trim());
        requestBuilder.setIp(reader.readString());
        requestBuilder.setClientVersion(reader.readString());
		try {
			requestBuilder.setVipTypeValue(Integer.parseInt(reader.readString()));
		} catch (Exception e) {
		}
		try {
			requestBuilder.setInternetCafe(Integer.parseInt(reader.readString()));
		} catch (Exception e) {
		}
        requestBuilder.setAccountTypeValue(reader.readShort());

        ResponsePlayerOnline response = characterService.login(requestBuilder.build(), false);

        out.write(BinaryUtil.toBytas(response.getUid(), //
                response.getPlayerId(), response.getName(), response.getRank(), response.getExp(), response.getProfile(), //
				//response.getParam7(),//比s_player_online少一个参数
                response.getIsNew(), response.getIsVip(), (byte) response.getInternetCafe(),//
                (byte) response.getSysItemIValueInBuff(),//
                response.getIcon(),//
                (byte) response.getIsCheckToday(),//
                response.getTop(), response.getFightNum(), response.getWinRate(),//
                response.getTeamId(), response.getTeamName(), response.getTeamLevel(), (byte) response.getIsGm()));
        return out.toByteArray();
    }
}
