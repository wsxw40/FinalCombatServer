package com.pearl.o2o.servlet.server;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryChannelBuffer;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;


public class RandomAward extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = LoggerFactory.getLogger(RandomAward.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		try{
			int size = r.readInt();
			List<Integer> playerIds = new ArrayList<Integer>();
			while(size > 0){
				playerIds.add(r.readInt());
				size --;
			}
//			List<SysActivity> list=getService.getAvailableActivities();
//			for(SysActivity sa:list){
//				if(sa.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()&&"N".equals(sa.getIsDeleted())){
					for(Integer playerId:playerIds){
//						for(SysItem item : sa.getItemList()){
							updateService.updatePlayerActivity(Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue(), (int) playerId, null, 0, 0, null, null);
// }
//						updateService.awardRandom(playerId);
					}
//				}
//			}
			
			
			return Constants.EMPTY_BYTE_ARRAY;
		}catch (Exception e) {
			logger.warn("Error in RandomAward: ", e);
			throw e;
		}
	}

}
