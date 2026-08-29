package com.pearl.fcw.info.lobby.servlet.player;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.lobby.service.PlayerService;
import com.pearl.fcw.info.lobby.servlet.client.BaseClientSynServlet;

@Service("c_PlayerLogin")
public class PlayerLogin extends BaseClientSynServlet {

    private static final long serialVersionUID = 8952708355586885616L;

    @Resource
    private PlayerService playerService;

    @Override
    public byte[] innerService(long playerId, byte[] params) throws Exception {

        /*PlayerLoginRequest request = getProtoParam(params, new PlayerLoginRequest());
        Player player = playerService.get(request.getId());
        
        response.setPlayerInfo(protoPlayerInfo);
        return outProtoByByte(response);*/

        return null;
    }
}
