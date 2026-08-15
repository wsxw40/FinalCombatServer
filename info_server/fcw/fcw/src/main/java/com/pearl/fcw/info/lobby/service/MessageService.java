package com.pearl.fcw.info.lobby.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.utils.Constants;

public class MessageService extends BaseService {
	private static Logger logger = LoggerFactory
			.getLogger(MessageService.class);
	private static final Player SYSTEM_SENDER = new Player();
	static {
		SYSTEM_SENDER.setId(0);
		SYSTEM_SENDER.setName(Constants.SYSTEM_NAME);
	}

}
