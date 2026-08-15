package com.pearl.fcw.lobby.service;

import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.core.cache.EntityManager;
import com.pearl.fcw.core.cache.redis.RedisClient;
import com.pearl.fcw.core.service.DmServiceImpl;
import com.pearl.fcw.lobby.pojo.WTeam;
import com.pearl.fcw.lobby.pojo.proxy.ProxyWPlayer;
import com.pearl.o2o.utils.CacheUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.SecondPasswordStatus;

/**
 * 二级密码
 * @author zhaolianming
 */
@Service
public class WSecondPasswordService extends DmServiceImpl<WTeam, Integer> {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Resource
    private EntityManager entitymanager;
    @Resource
    private WPlayerService wPlayerService;
    @Resource
    private   RedisClient redisClient;

    /**
     * 提交二级密码重置请求
     * @param playerId
     * @return
     */
    public String forgetSecondPassword(int playerId) {
        try {
            ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
            if(Integer.valueOf(pwPlayer.secondPassword().get())==SecondPasswordStatus.EMPTY){
                //已经请求过了
                //提交二级密码重置请求时间
                Date date = pwPlayer.applyResetSpwTime().get();
                if (date == null)
                    return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
                long flagTime = date.getTime();
                //剩余时间
                int leftSeconds =SecondPasswordStatus.THREE_DAY - (int) ((Calendar.getInstance().getTimeInMillis() - flagTime) / 1000);
                //提交二级密码重置请求的冷却时间未达到
                if(leftSeconds>0)
                    return SecondPasswordStatus.HAD_APPLY_EMPTY+leftSeconds;
            }else{
                //未请求过
                pwPlayer.hasSecondPassword().set((byte)SecondPasswordStatus.EMPTY);
                pwPlayer.applyResetSpwTime().set(new Date());
            }
        } catch (Exception e) {
            logger.error("Error happened when EMPTY second passowrd "+e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
        return SecondPasswordStatus.SUCCESS;
    }

    /**
     * 获得二级密码
     * @param playerId
     * @return
     */
    public String getSecondPasswordStatus(int playerId) {
        try {
            ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
            if (pwPlayer == null)
                return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
            Integer status = Byte.toUnsignedInt(pwPlayer.hasSecondPassword().get()) ;
            if(status==null)
                status=0;
            if (status == SecondPasswordStatus.EMPTY) {
                //提交二级密码重置请求时间
                Date date = pwPlayer.applyResetSpwTime().get();
                if (date == null)
                    return Converter.GetSecondPasswordStatus(status, 0);
                long flagTime = date.getTime();
                //剩余时间
                int leftSeconds =SecondPasswordStatus.THREE_DAY - (int) ((Calendar.getInstance().getTimeInMillis() - flagTime) / 1000);
                if (leftSeconds <= 0) {
                    pwPlayer.hasSecondPassword().set((byte)SecondPasswordStatus.UNSET);
                    pwPlayer.secondPassword().set(null);
                    pwPlayer.applyResetSpwTime().set(null);
                    return Converter.GetSecondPasswordStatus(SecondPasswordStatus.UNSET, 0);
                } else {
                    return Converter.GetSecondPasswordStatus(status, leftSeconds);
                }
            }
            return Converter.GetSecondPasswordStatus(status, 0);
        } catch (Exception e) {
            logger.error("Error happened when get second passowrd status"+e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
    }

    /**
     * 更改二级密码
     * @param playerId
     * @param oldPassword 旧密码
     * @param newPassword 新密码
     * @return
     */
    public String resetSecondPassword(int playerId, String oldPassword, String newPassword) {
        try {
            String md5OldPass=MD5Util.md5(oldPassword);
            ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
            if (pwPlayer == null)
                return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
            if (pwPlayer.hasSecondPassword().get() != 0 && md5OldPass.endsWith(pwPlayer.secondPassword().get())) {
                String md5NewPass=MD5Util.md5(newPassword);
                pwPlayer.hasSecondPassword().set((byte)1);
                pwPlayer.secondPassword().set(md5NewPass);
            }else{
                logger.warn("Incorrect input for reset second password for player "+playerId);
                return SecondPasswordStatus.FAIL;
            }
        } catch (Exception e) {
            logger.error("Error happened when set second passowrd "+e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
        return SecondPasswordStatus.SUCCESS;
    }

    /**
     * 设置或验证二级密码
     * @param playerId
     * @param password
     * @param type
     * @return
     */
    public String setOrCheckSecondPassword(int playerId, String password, int type) {
        try {
            ProxyWPlayer pwPlayer = wPlayerService.findProxyWPlayer(playerId);
            if (pwPlayer == null)
                return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
            if (type == SecondPasswordStatus.SET_PASSWORD) {//0:设置密码;
                String md5Password=MD5Util.md5(password);
                pwPlayer.hasSecondPassword().set((byte)1);
                pwPlayer.secondPassword().set(md5Password);
            } else if (type == SecondPasswordStatus.CHECK_PASSWORD) {//1:验证密码
                if ((SecondPasswordStatus.SET == pwPlayer.hasSecondPassword().get() || SecondPasswordStatus.EMPTY == pwPlayer.hasSecondPassword().get()) && pwPlayer.secondPassword().get() != null) {
                    //得出md5
                    String md5Password = MD5Util.md5(password);
                    //对比
                    if (!md5Password.equals(pwPlayer.secondPassword().get()))
                        return SecondPasswordStatus.FAIL;
                    //申请清空密码操作
                    if (SecondPasswordStatus.EMPTY == pwPlayer.hasSecondPassword().get()) {
                        //如果之前是忘记状态，现在设置为已设置状态
                        pwPlayer.hasSecondPassword().set((byte) SecondPasswordStatus.SET);
                    }
                    //表示用户此处登录已经输过密码
                    //mcc.set(CacheUtil.sPlayerSPWIE(playerId),Constants.CACHE_TIMEOUT_DAY,1,Constants.CACHE_TIMEOUT);
                    redisClient.setex(CacheUtil.sPlayerSPWIE(playerId), Constants.CACHE_TIMEOUT_DAY, 1);
                    return SecondPasswordStatus.SUCCESS;
                }
            }else{
                return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
            }
        } catch (Exception e) {
            logger.error("Error happened when set second passowrd "+e);
            return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
        }
        return SecondPasswordStatus.SUCCESS;
    }
}