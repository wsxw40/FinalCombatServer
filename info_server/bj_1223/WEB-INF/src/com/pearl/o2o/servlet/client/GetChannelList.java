package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.StringUtil;

public class GetChannelList extends BaseClientServlet {
	private static final long serialVersionUID = -8900434171788568386L;
	static Logger log = LoggerFactory.getLogger(GetChannelList.class.getName());
	private String[] paramNames={"sid"};
	public GetChannelList() {
		super();
	}
	@Override
	protected String innerService(String... args) {
//		/try {
//			String serverId = args[0];
//			// to do get the channel list by serverid
//			String result = "";
//			if (serverId != null) {
//				int server=StringUtil.toInt(serverId);
//				result = getService.getChannelList(server);
//			}
//			return result;
//		} catch (NumberFormatException e) {
//			return  "1000";// return NumberFormatException erro code
//		}
//		catch(Exception e){
//			log.error("Error in GetChannelList: " + e);
//			return Converter.error("系统出现异常错误，请联系GM");
//		}
			return "";
	}
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
