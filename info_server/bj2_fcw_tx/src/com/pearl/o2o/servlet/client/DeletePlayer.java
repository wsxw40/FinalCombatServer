package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;
@SuppressWarnings("unuse")
public class DeletePlayer extends BaseClientServlet {

	private static final long serialVersionUID = 9135258354077710613L;
	static Logger log = LoggerFactory.getLogger(DeletePlayer.class.getName());
	private String[] paramNames={"uid","cid"};
	public DeletePlayer(){
		super();
	}	
	@Override
	protected String innerService(String... args) {
		try {
			int userId = StringUtil.toInt(args[0]);
			Integer playerId = 0;
			String cid = args[1];

			if (cid != null) {
				playerId = Integer.parseInt(cid);
			}

			if (playerId > 0) {
				if (deleteService.deletePlayer(userId, playerId)) {
					return "error = nil";
				} else {
					return "error = \"Can not create character\"";
				}
			} else {
				return "error = \"Please input cid\"";
			}
		} catch (Exception e) {
			log.warn("exception in DeletePlayer servlet", e);
			return ExceptionMessage.ERROR_MESSAGE_ALL;

		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
