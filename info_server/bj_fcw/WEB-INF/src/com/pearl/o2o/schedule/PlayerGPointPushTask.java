package com.pearl.o2o.schedule;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.PlayerGPointTotal;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.TransferDataToDC;

/**
 * 统计 Player 的 c币汇总信息，发给analyse库
 * 定时，每天早上0点做
 */
public class PlayerGPointPushTask implements Runnable {

	private static Logger logger = LoggerFactory.getLogger(PlayerGPointPushTask.class);

	private TransferDataToDC transferDataToDc;
	@Override
	public void run() {
		try {
			PlayerGPointTotal total = ServiceLocator.getService.getTotalGPoint();
//			System.out.println("gson="+new Gson().toJson(total));
			String message=String.format("%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s\t%s",total.getTotalGPoint(),total.getTotalPlayer(),
					total.getAmount1(),total.getAmount2(),total.getAmount3(),total.getAmount4(),total.getAmount5(),total.getAmount6(),
					total.getAmount7(),total.getAmount8(),total.getAmount9(),total.getAmount10(),total.getAmount11(),total.getAmount12());

			getTransferDataToDc().addLog("bjGPointTotal", message);
			
//			StringBuilder sb = new StringBuilder(64);
//			sb.append(ConfigurationUtil.INFO_ANALYSER_URL)
//			  .append("addGPointTotal.do?gpointJson=").append(new Gson().toJson(total));
//			new DefaultHttpClient().execute(new HttpGet(sb.toString()));

		} catch (Exception e) {
			e.printStackTrace();
			logger.error("PlayerGPointPushTask/Error:\t", e);
		}
	}
	public TransferDataToDC getTransferDataToDc() {
		return transferDataToDc;
	}
	public void setTransferDataToDc(TransferDataToDC transferDataToDc) {
		this.transferDataToDc = transferDataToDc;
	}
	
	
	
}
