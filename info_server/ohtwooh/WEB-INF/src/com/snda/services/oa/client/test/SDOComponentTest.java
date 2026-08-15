package com.snda.services.oa.client.test;

import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.pearl.o2o.pojo.SysItem;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOComponent;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo.ItemPurchaseEntry;
import com.snda.services.oa.client.callback.SDOItemAccountLockNotify;
import com.snda.services.oa.client.callback.SDOItemAccountUnlockNotify;

public class SDOComponentTest {
	private static Logger logger = Logger.getLogger(SDOComponentTest.class);
	//private static BeanFactory beanFactory = new FileSystemXmlApplicationContext("//home/wengjie/tomcat-6.0.24/webapps/SDO/WEB-INF/applicationContext.xml");
	private static BeanFactory beanFactory = new FileSystemXmlApplicationContext("D:/Tomcat_6.0.16/webapps/ohtwooh/WEB-INF/test.applicationContext.xml");;
	private SDOComponent sdoComponent = (SDOComponentMock) beanFactory.getBean("sdoComponent");
	//private SDOComponentImpl sdoComponent = new SDOComponentImpl("/home/wengjie/workspace/sdoa4server.ini");
	private static Map<String,Object> lockMap = new ConcurrentHashMap<String,Object>();
	static {
		lockMap.put("qfth1", new Object());
		lockMap.put("qfth2", new Object());
		lockMap.put("qfth3", new Object());
		lockMap.put("qfth4", new Object());
	}
	
	public static void main(String[] args) throws Exception {
		final SDOComponentTest self = new SDOComponentTest();
		
	/*	new MultiThread(10, new Runnable(){
			final Random random = new Random();
			@Override
			public void run() {
				int id=1 + random.nextInt(4);;
				String key = "qfth" + id;
				try {
					synchronized(lockMap.get(key)){
						self.testSendAccountLockRequest(key);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		
		}).startAndWait();*/
		
		//self.sdoComponent.cleanup();
	}
	
	private PlayerInfo generatePlayerInfo(String sdoUid){
		PlayerInfo playerInfo = new PlayerInfo(sdoUid,1,1,"127.0.0.1",1,1);
		return playerInfo;
	}
	
	
	private SDOItemOrder generateSdoItemOrder(String uid) throws Exception{
		int amount = 100;
		PlayerInfo playerInfo = generatePlayerInfo(uid);
		String contextId = sdoComponent.generateUniqueId();
		
		SysItem sysItem = new SysItem();
		sysItem.setCost1(amount);
		sysItem.setId(new Random().nextInt(100));
		
		ItemPurchaseEntry itemEntry = new ItemPurchaseEntry(sysItem,1,1,"NO");
		Set<ItemPurchaseEntry> set = new HashSet<ItemPurchaseEntry>();
		set.add(itemEntry);
		
		//Set<ItemPurchaseEntry> itemsInfo, PlayerInfo playerInfo, PayType payType, String contextId
		SDOItemOrder order = new SDOItemOrder(set,playerInfo,SDOItemOrder.PayType.TICKET,contextId);
		
		return order;
	}
	
	public void testSendAccountLockRequest(String uid) throws Exception{
		SDOItemOrder order = generateSdoItemOrder(uid);
		logger.info("before  " + uid + " balance :" + getTicketBalance(order.getPlayerInfo()));
		
		SDOItemAccountLockNotify lockResp = sdoComponent.sendAccountLockRequest(order);
		//random number to choose unlock or rollback
		Random random = new Random();
		int type = random.nextInt(2);
		
		SDOItemAccountUnlockNotify unlockResp;
		
		if (lockResp.getResult() != 0){
			throw new Exception("error result " + lockResp.getResult());
		}
		if (type == 1) {
			logger.info(uid + " unlock " + order.getAmount());
			unlockResp = sdoComponent.sendAccountUnlockRequest(order);
		}else {
			logger.info(uid + " rollback " + order.getAmount());
			unlockResp = sdoComponent.sendAccountRollbackRequest(order);
		}
		
		if (unlockResp.getResult() !=0) {
			throw new Exception("error code " + unlockResp.getResult());
		}
		
		logger.info("after " + uid + " balance :" + getTicketBalance(order.getPlayerInfo()));
	}
	
	private  int getTicketBalance(PlayerInfo playerInfo) throws Exception{
		return sdoComponent.getBalance(playerInfo, SDOItemOrder.PayType.TICKET);
	}
}
