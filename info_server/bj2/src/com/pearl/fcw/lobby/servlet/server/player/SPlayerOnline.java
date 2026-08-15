package com.pearl.fcw.lobby.servlet.server.player;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.service.Servletable;
import com.pearl.fcw.lobby.service.CharacterService;
import com.pearl.fcw.lobby.service.WPlayerService;
import com.pearl.fcw.proto.enums.EPlayerVipType;
import com.pearl.fcw.proto.server.RequestPlayerOnline;
import com.pearl.fcw.proto.server.ResponsePlayerOnline;
import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.servlet.server.BaseServerServlet;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;

/**
 * 创建角色并登录
 */
@Service("fcw_s_player_online")
public class SPlayerOnline extends BaseServerServlet implements Servletable {

    private static final long serialVersionUID = 1900068574275475347L;
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private SPlayerOnline fcw_s_player_online;
    @Resource
    private CharacterService characterService;
    @Resource
    private WPlayerService wPlayerService;

    @Override
 protected byte[] innerService(BinaryReader in) throws IOException, Exception {
        try {
            return fcw_s_player_online.server(in);
        } catch (BaseException e) {
            logger.error("s_player_online has error : ", e);
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            out.write(BinaryUtil.toByta(e.getMessage()));
            return out.toByteArray();
        } catch (Exception e) {
            logger.error("s_player_online has error : ", e);
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
        requestBuilder.setName(reader.readString());
        requestBuilder.setIp(reader.readString());
        if (Integer.parseInt(reader.readString()) > 0) {
            requestBuilder.setVipType(EPlayerVipType.XUNLEI_VIP);
        }
        requestBuilder.setInternetCafe(Integer.parseInt(reader.readString()));
        requestBuilder.setAccountTypeValue(reader.readShort());
        requestBuilder.setClientVersion("*");

        ResponsePlayerOnline response = characterService.login(requestBuilder.build(), true);

        out.write(BinaryUtil.toBytas(response.getUid(), //
                response.getPlayerId(), response.getName(), response.getRank(), response.getExp(), response.getProfile(), //
                response.getParam7(),//
                response.getIsNew(), response.getIsVip(), (byte) response.getInternetCafe(),//
                (byte) response.getSysItemIValueInBuff(),//
                response.getIcon(),//
                (byte) response.getIsCheckToday(),//
                response.getTop(), response.getFightNum(), response.getWinRate(),//
                response.getTeamId(), response.getTeamName(), response.getTeamLevel(), (byte) response.getIsGm()));
        return out.toByteArray();
    }

}
