package com.pearl.fcw.info.lobby.servlet.server;

import java.io.ByteArrayOutputStream;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.core.persistence.utils.BinaryUtil;
import com.pearl.fcw.info.lobby.pojo.Player;
import com.pearl.fcw.info.lobby.service.PlayerService;
import com.pearl.fcw.info.lobby.servlet.BaseServlet;

@Service("s_character_online")
public class SPlayerOnline extends BaseServlet {
	private static final long serialVersionUID = 7553992516814453244L;
	static Logger logger = LoggerFactory.getLogger(SPlayerOnline.class
			.getName());

	@Resource
	private PlayerService playerService;

//	@Override
//	protected int getLockKey(BinaryChannelBuffer in) throws Exception {
//		in.readInt();
//		String name = in.readString().trim();
//		Integer playerId = playerService.getByUserName(name).getId();
//		if (playerId == null) {
//			return 0;
//		} else {
//			return playerId;
//		}
//	}

	
	@SuppressWarnings("unused")
	@Override
	protected byte[] innerService(BinaryChannelBuffer params) throws Exception {
		ByteArrayOutputStream out = new ByteArrayOutputStream();

		int uid = params.readInt();
		String userName = params.readString().trim();
		String name = params.readString();
		String ip = params.readString();
		final String isXunleiVip = params.readString();
		String internetCafe = params.readString();
		int accountType = params.readShort();

		Player player = playerService.get(uid);
		if (player == null) {
			player = playerService.createPlayer(userName, name, accountType);
		}

		// TODO
		out.write(BinaryUtil.toByta(uid));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta(""));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta(""));
		out.write(BinaryUtil.toByta("Test error info message."));
		out.write(BinaryUtil.toByta((byte) 0));
		out.write(BinaryUtil.toByta((byte) 0));
		out.write(BinaryUtil.toByta((byte) 0));
		out.write(BinaryUtil.toByta((byte) 0));
		out.write(BinaryUtil.toByta(""));
		out.write(BinaryUtil.toByta((byte) 0));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta((float) 0));
		out.write(BinaryUtil.toByta(0));
		out.write(BinaryUtil.toByta(""));
		out.write(BinaryUtil.toByta(1));
		out.write(BinaryUtil.toByta((byte) 0)); // is_gm

		return out.toByteArray();
	}

}
