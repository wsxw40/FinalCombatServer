package com.snda.services.oa.client.test;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.ibatis.sqlmap.client.SqlMapClient;
import com.pearl.o2o.dao.ItemOrderDao;
import com.pearl.o2o.exception.NotBuyEquipmentException;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SDOItemOrderPojo;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.service.CreateService;
import com.pearl.o2o.service.GetService;
import com.pearl.o2o.service.UpdateService;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.GoodsInfoConverter;
import com.pearl.o2o.utils.GoodsInfoConverterImpl;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo;
import com.snda.services.oa.client.bean.PlayerInfo;
import com.snda.services.oa.client.bean.SDOItemOrder;
import com.snda.services.oa.client.bean.ItemsPurchaseInfo.ItemPurchaseEntry;
import com.snda.services.oa.client.impl.SDOComponentImpl;

public class SDOOrderTest {
	private static BeanFactory beanFactory = new FileSystemXmlApplicationContext("D:/Tomcat_6.0.16/webapps/ohtwooh/WEB-INF/test.applicationContext.xml");;
	//private static BeanFactory beanFactory = new FileSystemXmlApplicationContext("//home/wengjie/tomcat-6.0.24/webapps/SDO/WEB-INF/applicationContext.xml");;
	
	private static SqlMapClient client = (SqlMapClient) beanFactory.getBean("sqlMapClient");
	private static ItemOrderDao itemOrderDao = (ItemOrderDao) beanFactory.getBean("itemOrderDao");
	private static GetService getService = (GetService) beanFactory.getBean("getService");
	private static CreateService createService = (CreateService) beanFactory.getBean("createService");
	private static UpdateService updateService = (UpdateService) beanFactory.getBean("updateService");
	private static Player player = new Player();	
	private static SDOComponentImpl sdoComponent = (SDOComponentImpl) beanFactory.getBean("sdoComponent");
	//private static SDOComponentImpl sdoComponent = new SDOComponentImpl("/home/wengjie/workspace/sdoa4server.ini");
	static {
		player.setUserId(1);
		player.setId(1);
		createService.setSdoComponent(sdoComponent);
	}
	private static PlayerInfo playerInfo = new PlayerInfo("qfth1",24,166,"127.0.0.1",1,1);
	
	
	public static void main(String[] args) throws Exception {
		testCreatePlayerItem();
		//testPurchase();
		//testInsertOrderPojo();
		//testSelectOrderByOrderId();
		//testGoodsInfoConvertor();
		//testCreateSDOItemOrder();
	}
	
	public static void testCreatePlayerItem() throws Exception{
		int userId = 67;
		int playerId = 286;
		int type = 1;
		int subType = 6;
		int sysId = 13;
		
		Map map = createService.createPlayerItem(userId, playerId, type, subType, sysId, 1, "NO");
		System.out.print(map.get("gp"));
		System.out.print(map.get("id"));
		System.out.print(map.get("cr"));
	}
	
	
	public static void testPurchase() throws NotBuyEquipmentException, Exception{
		createService.purchaseSDOItem(playerInfo, generateItemsInfo());
	}
	
	private static Set<ItemPurchaseEntry> generateItemsInfo() throws Exception{
		Set<ItemPurchaseEntry> itemsInfo = new HashSet<ItemPurchaseEntry>();
		//int userId,int playerId,int type, int subType,int itemId
		SysItem item = getService.getSysItemByItemId(24,166, 1,6,13);
		itemsInfo.add(new ItemPurchaseEntry(item,2,1,Constants.BOOLEAN_NO));
		
		return itemsInfo;
	}
	
	public static SDOItemOrder createSDOItemOrder() throws Exception{
		
		String contextId = "contexId";
		//TODO
		return new SDOItemOrder(generateItemsInfo(),playerInfo,SDOItemOrder.PayType.TICKET,contextId);
	}
	
	public static void testCreateSDOItemOrder() throws Exception{
		SDOItemOrder order = createSDOItemOrder();
		createService.createSDOItem(order);
	}
	public static void testSelectOrderByOrderId() throws SQLException{
		System.out.print(client.queryForObject("Order.selectOrderByOrderId", "contexId"));
	}
	
	public static void testInsertOrderPojo() throws SQLException{
		SDOItemOrderPojo orderPojo = new SDOItemOrderPojo();
		orderPojo.setOrderId("orderId1");
		orderPojo.setO2oUid(1);
		orderPojo.setSdoUid("1");
		orderPojo.setPaytype(3);
		orderPojo.setPlayerId(2);
		orderPojo.setState("DELETED");
		orderPojo.setGoodsinfo("goodsInfo");
		orderPojo.setEndpointIp("127.0.0.1");
		
		client.insert("Order.insert", orderPojo);
	}
	
	public static void testGoodsInfoConvertor() throws Exception{
		GoodsInfoConverter convertor = new GoodsInfoConverterImpl();
		Set<ItemPurchaseEntry> itemSet = new HashSet<ItemPurchaseEntry>();
		//date
		SysItem item1 = (SysItem) client.queryForObject("SysItem.selectSysItemById",1);
		SysItem item2 = (SysItem) client.queryForObject("SysItem.selectSysItemById",2);;
		SysItem item3 = (SysItem) client.queryForObject("SysItem.selectSysItemById",3);;
		
		itemSet.add(new ItemPurchaseEntry(item1, 1,1,Constants.BOOLEAN_NO));
		itemSet.add(new ItemPurchaseEntry(item1, 2,1,Constants.BOOLEAN_NO));
		itemSet.add(new ItemPurchaseEntry(item1, 3,1,Constants.BOOLEAN_NO));
		
		System.out.println(ReflectionToStringBuilder.toString(item1));
		System.out.println(ReflectionToStringBuilder.toString(item2));
		System.out.println(ReflectionToStringBuilder.toString(item3));
		
		//begin test 
		String plainStr = convertor.convertItemsToPlainString(new ItemsPurchaseInfo (itemSet));
		System.out.println(plainStr);
		ItemsPurchaseInfo itemsInfo = convertor.convertPlainStringToItems(plainStr, getService);
		for (ItemPurchaseEntry itemInfo : itemsInfo.getItemsInfo()){
			SysItem item = itemInfo.getItem();
			System.out.println(ReflectionToStringBuilder.toString(item));
			System.out.println(itemInfo.getQuantity() + "," +itemInfo.getCostType() + "," +itemInfo.getAmount());
		}
	}
}
