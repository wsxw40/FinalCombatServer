package com.pearl.o2o.servlet.client;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.MeltingMenu;
import com.pearl.o2o.utils.ConfigurationUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;

public class GetBlueMaps extends BaseClientServlet {
	static Logger log = LoggerFactory.getLogger(GetBlueMaps.class.getName());
	private static final long serialVersionUID = 6794704252925989446L;
	private static final String[] paramNames = {};
	
	@Override
	protected String innerService(String... args) {
		try {
			MeltingMenu rootMenu = (MeltingMenu)ConfigurationUtil.beanFactory.getBean("blueMapRootMenu");
			List<MeltingMenu> menus = rootMenu.getChildren();
			StringBuilder result = new StringBuilder("list={\n");
			for(MeltingMenu menu : menus){
				result.append(Converter.getMeltingMenuStr(menu)).append(",").append("\n");
			}
			return result.append("\n}").toString();
		}catch (Exception e) {
			log.error("",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
