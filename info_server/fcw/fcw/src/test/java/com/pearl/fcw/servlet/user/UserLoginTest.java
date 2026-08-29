package com.pearl.fcw.servlet.user;

import io.netty.channel.ChannelHandlerContext;

import java.util.LinkedHashMap;

import org.junit.Test;

import com.pearl.fcw.BaseTest;
import com.pearl.fcw.info.core.network.ClientMessage;

public class UserLoginTest extends BaseTest {

	@Override
	public Boolean messageHandler(ChannelHandlerContext ctx, Object msg){
		System.out.println("Response message : "+this.getClass().getName());
		return false;
	}

	@Test
    public void normal() {
        sendBinaryRPC("s_character_online", new LinkedHashMap<String, Object>(){
			private static final long serialVersionUID = -7601236815366216786L;
			{
				put("pid",10086);
				put("userName","Test");
				put("name","Test01");
				put("ip","127.0.0.1");
				put("isXunleiVip","1");
				put("internetCafe","1");
				put("accountType",new Short("10"));
			}
        }, ClientMessage.CM_RequestBinaryRPC);
    }
}
