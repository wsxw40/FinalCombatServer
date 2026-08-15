package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * @author wuxiaofei
 *
 */
public class CombineSortMaterial extends BaseClientServlet {
	private static final long serialVersionUID = 2412223517067028215L;
	private static Logger log = LoggerFactory.getLogger(CombineSortMaterial.class.getName());
	private static final String[] paramNames = {"pid","type"};
	
	protected String innerService(String... args) {
		try{
			final Integer playerId = StringUtil.toInt(args[0]);
			final Integer type = StringUtil.toInt(args[1]);
			if (type == Constants.DEFAULT_MATERIAL_TYPE || type == Constants.DEFAULT_OPEN_TYPE) {
				CommonUtil.sortAllMaterial(playerId, type);
			}
			if(type == Constants.DEFAULT_ITEM_TYPE){
				CommonUtil.sortMaterialsBySubType(playerId, type, Constants.DEFAULT_ITEM_SUBTYPE.COST.getValue());
			}
			return "result=1";
		}
		catch(Exception e){
			log.warn("Error in CombineSortMaterial: " , e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}	
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
