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
	private static final String[] paramNames = { "pid", "type" };

	@Override
	protected String innerService(String... args) {
		try {

			// 蓝图response数据过于巨大，超出协议最大范围
			// 20150507 蓝图回馈内容采取分请求
			// 类型11、枪械蓝图种类1
			// 类型12、枪械蓝图种类2
			// 类型1x、...

			// 类型21、服装类蓝图 暂无
			// 类型2x、...

			// 类型31、素材蓝图种类1 
			// 类型32、素材蓝图种类2
			// 类型3x、...
			
			// 类型51、强化增幅能源
			int type = Integer.parseInt(args[1]);

			MeltingMenu rootMenu = (MeltingMenu) ConfigurationUtil.beanFactory
					.getBean("blueMapRootMenu"+type);
			List<MeltingMenu> menus = rootMenu.getChildren();
			StringBuilder result = new StringBuilder("list={\n");
			for (MeltingMenu menu : menus) {
				result.append(Converter.getMeltingMenuStr(menu)).append(",")
						.append("\n");
			}
			return result.append("\n}").toString();
		} catch (Exception e) {
			log.error("", e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}

}
