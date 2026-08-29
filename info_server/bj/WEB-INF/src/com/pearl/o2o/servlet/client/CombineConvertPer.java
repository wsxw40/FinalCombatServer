package com.pearl.o2o.servlet.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.CombineProperty;
import com.pearl.o2o.pojo.PlayerItem;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.StringUtil;

/**
 * 合成系统--转换前接口
 * @author wuxiaofei
 *
 */
public class CombineConvertPer extends BaseClientServlet {
	private static final long serialVersionUID = -2470899400867670372L;
	static Logger log = LoggerFactory.getLogger(CombineConvertPer.class.getName());
	private String[] paramNames = { "pid", "fromItemId", "toItemId"};

	public CombineConvertPer() {
		super();
	}

	/* (non-Javadoc)
	 * @see com.pearl.o2o.servlet.client.BaseClientServlet#getLockKey(java.lang.String[])
	 */
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String innerService(String... args) {
		int playerId = Integer.parseInt(args[0]);
		int fromItemId = Integer.parseInt(args[1]);
		int toItemId = Integer.parseInt(args[2]);
		try {
			if(playerId <= 0){
				throw new BaseException(ExceptionMessage.NO_HAVE_THE_CHARACTER);
			}
			PlayerItem fromItem = getService.getPlayerItemById(playerId, fromItemId);
			PlayerItem toItem = getService.getPlayerItemById(playerId, toItemId);

			if(fromItem.getType()!=toItem.getType()){
				log.error("The two things which prepare to convert have different item type!");
				return Converter.error("Type is inconsistent!");
			}
//			int rate = CombineProperty.getConvertPlayerItemLoseRate(fromItem.getLevel(),toItem.getLevel(),fromItem.getSysItem().getRareLevel(),toItem.getSysItem().getRareLevel());

			int rateFrom = CombineProperty.calcConvertPlayerItemLoseRate(fromItem.getLevel(),fromItem.getSysItem().getRareLevel(),toItem.getSysItem().getRareLevel());
			int rateTo = CombineProperty.calcConvertPlayerItemLoseRate(toItem.getLevel(),toItem.getSysItem().getRareLevel(),fromItem.getSysItem().getRareLevel());
			int rate = (rateFrom*100 + rateTo*100 - rateFrom*rateTo)/100;
			int fromLevel = fromItem.getLevel();
			fromItem.setLevel(toItem.getLevel());
			toItem.setLevel(fromLevel);
			
			fromItem.calculateParam(fromItem.getSysItem());
			toItem.calculateParam(toItem.getSysItem());
			fromItem.setHoleNum(fromItem.getMaxHoleNum());
			toItem.setHoleNum(toItem.getMaxHoleNum());
			fromItem.setFightNum(toItem.getFightNum());
			toItem.setFightNum(fromItem.getFightNum());
			
			
			return Converter.combineConvertPer(rate ,fromItem, toItem,rateFrom,rateTo);
		} catch (Exception e) {
			log.warn("Error in CombineConvertPer: ", e);
			return Converter.error(e.getMessage());
		}
	}

	@Override
	protected String[] paramNames() {
		return paramNames;
	}
}
