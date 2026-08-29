package com.pearl.fcw.info.lobby.servlet.server;

import org.springframework.stereotype.Service;

import com.pearl.fcw.info.core.persistence.utils.BinaryChannelBuffer;
import com.pearl.fcw.info.lobby.servlet.BaseServlet;

@Service("s_get_level_info")
public class SLevelInfo extends BaseServlet {

	private static final long serialVersionUID = 8449242457860139805L;
//	private static Logger logger = LoggerFactory.getLogger(SLevelInfo.class);
	@Override
	public byte[] execute(BinaryChannelBuffer params) throws Exception {
		// TODO Auto-generated method stub
		return super.execute(params);
	}

}
