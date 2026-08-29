package com.pearl.o2o.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.socket.SocketClientNew;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.ServiceLocator;
/**
 * 赛前公告
 * @author zhaolianming
 *
 */
public class LeagueGameNotice implements Runnable {
    public static Logger log = LoggerFactory.getLogger(LeagueGameNotice.class);
    private int time = 0;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public LeagueGameNotice(int time) {
        super();
        this.time = time;
    }

    @Override
    public void run() {
        try {
            SocketClientNew soClient = ServiceLocator.soClient;
            if (this.time >= 2 * 60) {
                int number=this.time/60;
                number = number > 5 ? 5 : number;
                for (int i = number; i > 1; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛"+number+"分后开始，请做好准备准时参加");
                    Thread.sleep(60 * 1000);
                }
                for (int i = 6; i > 0; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛"+i*10+"秒后开始，请做好准备准时参加");
                    Thread.sleep(10 * 1000);
                }
                for (int i = 6; i > 0; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛已开始，请在"+i*10+"秒内进入战斗");
                    Thread.sleep(10 * 1000);
                }
                soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, "战队赛已进入战斗");
            } else if (this.time >= 1 * 60) {
                for (int i = 6; i > 0; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛"+i*10+"秒后开始，请做好准备准时参加");
                    Thread.sleep(10 * 1000);
                }
                for (int i = 6; i > 0; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛已开始，请在"+i*10+"秒内进入战斗");
                    Thread.sleep(10 * 1000);
                }
                soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, "战队赛已进入战斗");
            } else if (this.time >= 0) {
                for (int i = 6; i > 0; i--) {
                    soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER,"战队赛已开始，请在"+i*10+"秒内进入战斗");
                    Thread.sleep(10 * 1000);
                }
                soClient.proxyBroadCast(Constants.MSG_NOTICE, Constants.SYSTEM_SPEAKER, "战队赛已进入战斗");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public int scheduledTime() {
        if (time >= 2 * 60) {
            int number = time / 60;
            number = number > 5 ? 5 : number;
            return time - number * 60;
        } else if (time >= 1 * 60) {
            return time - 1 * 60;
        } else if (time >= 0) {
            return time;
        }
        return time;
    }
}
