package com.pearl.fcw.info.lobby.servlet;

import java.io.Serializable;
import java.util.Map;

import javax.annotation.Resource;

import com.pearl.fcw.info.core.lock.InfoServerLock;
import com.pearl.fcw.info.core.network.Executable;
import com.pearl.fcw.info.core.network.MessageException;
import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.lobby.utils.CommonUtil;
import com.pearl.fcw.info.lobby.utils.Constants;

public class BaseServlet implements Serializable, Executable {

	private static final long serialVersionUID = 2091617130107076069L;

	@Resource
	protected InfoServerLock lock;

	protected String[] paramNames() {
		return new String[0];
	};

	/**
	 * @return player id
	 * */
	protected int getLockKey(BinaryChannelBuffer in) throws Exception {// SERVER
		return 0;
	};

	protected byte[] innerService(BinaryChannelBuffer params) throws Exception { // SERVER
		return null;
	}

	/**
	 * @return player id
	 * */
	protected int getLockKey(String[] paramNames) {	// CLIENT
		return 0;
	}

	protected byte[] innerService(String... params) throws Exception { // CLIENT
		return null;
	}

	@Override
	public byte[] execute(Map<String, String> params) throws Exception {

		String[] values = (String[]) params.values().stream().toArray();

		String key = CommonUtil.getLockKey(getLockKey(values));
		if (key == null) {
			return innerService(values);
		}
		if (lock.tryLock(key, Constants.SYNCHRONIZE_TIMEOUT)) {
			try {
				return innerService(values);
			} finally {
				lock.unlock(key);
			}
		} else {
			throw new MessageException("KEY: " + key + " SERVER BUSY."); // FIXME
		}
	}

	@Override
	public byte[] execute(BinaryChannelBuffer params) throws Exception {
		String key = CommonUtil.getLockKey(getLockKey(params.clone()));
		if (key == null) {
			return innerService(params);
		}
		if (lock.tryLock(key, Constants.SYNCHRONIZE_TIMEOUT)) {
			try {
				return innerService(params);
			} finally {
				lock.unlock(key);
			}
		} else {
			throw new MessageException("KEY: " + key + " SERVER BUSY."); // FIXME
		}
	}

}
