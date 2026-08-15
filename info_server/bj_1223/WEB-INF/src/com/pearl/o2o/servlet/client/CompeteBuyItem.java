package com.pearl.o2o.servlet.client;

import java.util.Date;

import com.pearl.o2o.exception.BaseException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.service.onbuy.CompeteBuy;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.ExceptionMessage;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class CompeteBuyItem extends BaseClientServlet {

	private static final long serialVersionUID = -8206549504192724631L;
	private String[] paramNames = { "pid", "index", "amount","payMethod"};
	@Override
	protected String innerService(String... args) {
		try {
			for(int i=1;i<args.length;i++){	
				if(!args[i].matches("^\\d+$")){		
					return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
				}
			}
			
			int playerId = StringUtil.toInt(args[0]) ;
			int index = StringUtil.toInt(args[1]);
			int payAmount = StringUtil.toInt(args[2]);
			int payMethod = StringUtil.toInt(args[3]);
			
	
			//1:黄金卡 2：勋章
			if(payMethod!=1 && payMethod!=2){
			
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
			
			Date time = new Date();
		
			if(time.after(getService.getCompeteBuyTime(1))||time.before(getService.getCompeteBuyTime(0))){
				return Converter.warn(ExceptionMessage.ACTIVITY_NOT_START);
			}
			if(Constants.SWITCH_COMPETEBUY!=1){
				return Converter.warn(ExceptionMessage.ACTIVITY_NOT_START);
			}
			Player player = getService.getSimplePlayerById(playerId);
			CommonUtil.checkNull(player, ExceptionMessage.NO_HAVE_THE_CHARACTER);
			
			//检测是否输过二级密码
			if(!checkEnterSPW(playerId)){
				return Converter.error(CommonMsg.INPUT_SECOND_PASSWORD);
			}
			
			int result = CommonUtil.buyPlayerItem(new CompeteBuy(player, index, payAmount,payMethod));
			
			//log pay method
			String useToPay;
			switch (payMethod) {
			case 1: useToPay="GC";  //黄金卡
				break;
			case 2: useToPay="MD";	//勋章
				break;
//			case -1:   useToPay="FC";	//fc
//				break;
			default: useToPay="ERROR";
				break;
			}
			
			switch (result) {
			case CompeteBuy.NOT_ENOUGH:
				return Converter.warn(ExceptionMessage.NOT_ENOUGH_ITEM);
			case CompeteBuy.CANT_LOWER_LEAST:
				return Converter.warn(ExceptionMessage.CANT_LOWER_LEAST_NUM);
			case CompeteBuy.PARAM_EROR:
				return Converter.warn(ExceptionMessage.PARAM_ERROR_MSG);
			case CompeteBuy.FAIL:
				return Converter.warn(ExceptionMessage.ERROR_MESSAGE_RETRY);
			case CompeteBuy.SUCCESS:
				ServiceLocator.competeBuylog.info("CompeteBuyItem/price:\t"+playerId + "\t" + index + "\t" + payAmount+ "\t" +useToPay);
				return "result=" + CompeteBuy.SUCCESS;
			default:
				return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
			}
		}catch(BaseException be){
			ServiceLocator.competeBuylog.warn("CompeteBuyItem:\t",be);
			return Converter.warn(be.getMessage());
		} 
		catch (Exception e) {
			ServiceLocator.competeBuylog.error("CompeteBuyItem:\t",e);
			return Converter.error(ExceptionMessage.ERROR_MESSAGE_ALL);
		}
	}
	
	@Override
	protected String getLockKey(String[] paramNames) {
		return CommonUtil.getLockKey(StringUtil.toInt(paramNames[0]));
	}
	
	@Override
	protected String[] paramNames() {
		return paramNames;
	}
	
}
