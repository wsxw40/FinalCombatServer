package com.pearl.fcw.lobby.service;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.gm.service.WSysConfigService;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayerInfo;

/**
 * 二级密码
 */
@Service
public class SecondPasswordService {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private WSysConfigService wSysConfigService;
    @Resource
    private WPlayerService wPlayerService;

    /**
	 * 检测用户是否输入并通过二级密码验证<br/>
	 * 在系统和玩家均设置了二级密码的前提下，玩家的二级密码标记为0就返回false。其余状态返回true
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
    public boolean hasSecondPassword(int playerId) throws Exception {
        String clientSwitch = wSysConfigService.findList(null).stream().filter(p -> "client.switch".equals(p.getKey())).map(p -> p.getValue()).findFirst().orElse("");
        try {
			if ("1".equals(clientSwitch.indexOf(2))) {//系统设置了二级密码验证
                ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
				if (pwPlayer.hasSecondPassword().get() != 0) {//玩家有二级密码
                    ProxyWPlayerInfo pwPlayerInfo = wPlayerService.findProxyWPlayerInfo(playerId);
					if (pwPlayerInfo.cachesEntity().get().getSecondPasswordFlag() == 0) {
                        return false;
                    }
                }
            }
        } catch (Exception e) {
            logger.warn("hasSecondPassword has error : ", e);
            return true;
        }
        return true;
    }
}
