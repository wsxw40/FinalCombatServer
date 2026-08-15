package com.pearl.o2o.servlet.gm;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.pearl.o2o.pojo.InterfaceRecord;
import com.pearl.o2o.pojo.Payment;
import com.pearl.o2o.pojo.Player;
import com.pearl.o2o.pojo.SysItem;
import com.pearl.o2o.utils.CommonMsg;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Constants;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.KeywordFilterUtil;
import com.pearl.o2o.utils.MD5Util;
import com.pearl.o2o.utils.ServiceLocator;
import com.pearl.o2o.utils.StringUtil;

public class GetAwardServlet extends BaseGMServlet {
	private static final long serialVersionUID = -169422595433967626L;

	/**
	 	成功返回：ok:%1
			其中，
			%1 表示订单号。
		失败返回：fail:%1，
			其中，
			%1 表示错误代码。
				0 表示时间戳过期
				1 表示验证串错误
				2 表示订单号重复
				3 表示角色不存在
				4 表示未知错误
	 */
	@SuppressWarnings("unchecked")
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		PrintWriter out = res.getWriter();
		try{
//		Map<String, String> map = req.getParameterMap();
//		for(String str:map.values()){
//			System.out.println(str);
//		}
		String timestamp = new String(req.getParameter("ts").getBytes("ISO-8859-1"));
		String regionId = new String(req.getParameter("rg").getBytes("ISO-8859-1"));
		String eventId = new String(req.getParameter("eid").getBytes("ISO-8859-1"));
		String account =URLDecoder.decode(new String(req.getParameter("uid").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String roleId  =URLDecoder.decode(new String(req.getParameter("rid").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String title  =URLDecoder.decode(new String(req.getParameter("mt").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String content  =URLDecoder.decode(new String(req.getParameter("mc").getBytes("ISO-8859-1"),"utf-8"),"utf-8");
		String orderId = new String(req.getParameter("oid").getBytes("ISO-8859-1"));
		String sign = new String(req.getParameter("sn").getBytes("ISO-8859-1"));
		
		int size = Integer.parseInt(new String(req.getParameter("size").getBytes("ISO-8859-1")));
		List<ItemClass> list=new ArrayList<ItemClass>();
		for(int i = 1; i<=size; i++){
			list.add(new ItemClass(new String(req.getParameter("it" + i).getBytes("ISO-8859-1")), new String(req.getParameter("payid" + i).getBytes("ISO-8859-1"))));
		}
//		String sysItemId = new String(req.getParameter("it1").getBytes("ISO-8859-1"));
//		String itemNum = new String(req.getParameter("payid1").getBytes("ISO-8859-1"));
		ServiceLocator.fileLog.debug(timestamp);
		ServiceLocator.fileLog.debug(regionId);
		ServiceLocator.fileLog.debug(eventId);
		ServiceLocator.fileLog.debug(account);
		ServiceLocator.fileLog.debug(roleId);
		ServiceLocator.fileLog.debug(title);
		ServiceLocator.fileLog.debug(content);
		ServiceLocator.fileLog.debug(orderId);
		ServiceLocator.fileLog.debug(sign);
//		String str = MD5Util.md5(timestamp + account + roleId + regionId + eventId + orderId + sysItemId + itemNum + key);
//		ServiceLocator.nosqlLogger.error(str);
			//step 1 : check parameters
			DateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
			Date time = new Date();
			try{
				time = simpleDateFormat.parse(timestamp);
				Date now = new Date();
				if((now.getTime() - time.getTime()) > 60000){//超过一分钟
					out.write("fail:0");//时间戳过期
					out.close();
					return;
				}
			}catch(ParseException e){
				out.write("fail:0");//时间戳格式
				out.close();
				return;
			}
			int playerId = getService.getPlayerIdByUserId(account);
			Player player = getService.getSimplePlayerById(playerId);
			if(null == player){//角色不存在
				out.write("fail:3");
				out.close();
				return;
			}
			InterfaceRecord doubleRecord = dao.getByOrderId(orderId);
			if(null != doubleRecord){//订单号重复
				out.write("fail:2");
				out.close();
				return;
			}
			
			StringBuilder unEncodeStr = new StringBuilder();
			unEncodeStr.append(timestamp).append(account).append(roleId).append(regionId).append(eventId).append(orderId);
			for(ItemClass s : list){
				unEncodeStr.append(s.getItemId());
			}
			unEncodeStr.append(key);
			String encodeStr = MD5Util.md5(unEncodeStr.toString());
			if(!encodeStr.equals(sign)){//验证串错误
				out.write("fail:1");
				out.close();
				return;
			}
			
			//step 2 : send awards
			//验证payid
			List<String> failedItemList = new ArrayList<String>();
			StringBuilder sysItemIds = new StringBuilder();
			StringBuilder paymentIds = new StringBuilder();
			for(ItemClass s : list){
				Payment payment = null;
				if(!"0".equals(s.getPayId())){
					payment = getService.getPaymentById(Integer.parseInt(s.getItemId()), Integer.parseInt(s.getPayId()));
					if(null == payment || payment.getItemId() != Integer.parseInt(s.getItemId())){
						failedItemList.add(s.getItemId());
					}
				}
				sysItemIds.append(s.getItemId()).append(",");
				paymentIds.append(s.getPayId()).append(",");
			}
			
			if(failedItemList.size() > 0){
				StringBuilder failedCode = new StringBuilder();
				for(String failedItem : failedItemList){
					failedCode.append(failedItem).append(",");
				}
				out.write("fail:5;" + failedCode.toString());
				out.close();
				return;
			}
			InterfaceRecord record = new InterfaceRecord();
			record.setType(0);
			record.setAccount(account);
			record.setContent(content);
			record.setRegionId(Integer.parseInt(regionId));
			record.setRoleId(roleId);
			record.setTimestamp(time);
			record.setTitle(title);
			record.setEventId(eventId);
			record.setItemnum(sysItemIds.toString());
			record.setSysItemId(paymentIds.toString());
			record.setOrderId(orderId);
			dao.insert(record);
			try{
				SysItem sysItem = null;
				List<SysItem> sysItemList = new ArrayList<SysItem>();
				StringBuilder ids = new StringBuilder();
				for(ItemClass s : list){
					sysItem = getService.getSysItemByItemId(Integer.parseInt(s.getItemId()));
					if(sysItem == null){
						ServiceLocator.exceptionLog.error("error happen in  GetAwardServlet:sysItem=null itemId="+s.getItemId());
						out.write("fail:4");
						out.close();
						return;
					}
					Payment payment = null;
					if(!"0".equals(s.getPayId())){
						payment = getService.getPaymentById(Integer.parseInt(s.getItemId()), Integer.parseInt(s.getPayId()));
					}
					createService.awardToPlayer(player, sysItem, payment, ids, Constants.BOOLEAN_YES);
					sysItemList.add(sysItem);
				}
				String msg = Converter.xunleiGiftCMD(sysItemList);
				messageService.sendSystemMailWithSysItemInContent(player,  KeywordFilterUtil.filter(StringUtil.escapeIndex(title))
						, KeywordFilterUtil.filter(StringUtil.escapeIndex(content)),ids.toString());
				soClient.puchCMDtoClient(player.getName(), msg);
				out.write("ok:" + orderId);
				out.close();
			}catch (Exception e) {
				dao.delete(record);
				ServiceLocator.exceptionLog.error("error happen send item in  GetAwardServlet return fail:4 account="+account+" content="+content+" title="+title+
						" orderId="+orderId+" sysItemIds="+sysItemIds.toString()+" paymentIds="+paymentIds.toString(),e);
				out.write("fail:4");
				out.close();
			}
		} catch(Exception e){
			String ip = "";
			if (req.getHeader("x-forwarded-for") == null) {
				ip = req.getRemoteAddr();
			}
			ip = req.getHeader("x-forwarded-for");
			ServiceLocator.exceptionLog.error("error happen in  GetAwardServlet ip from "+ip,e);
			out.write("fail:4");
			out.close();
			return;
		}
	} 
	
	@Override
	protected String getLockKey(HttpServletRequest request) {
		try {
			String userId = request.getParameter("uid");
			Integer playerId = getService.getPlayerIdByUserId(userId);
			return CommonUtil.getLockKey(playerId);
		} catch (Exception e) {
			return null;
		}
	}
	
	class ItemClass{
		private String itemId;
		private String payId;
		public ItemClass(String itemId,String payId) {
			this.itemId=itemId;
			this.payId=payId;
		}
		public String getItemId() {
			return itemId;
		}
		public void setItemId(String itemId) {
			this.itemId = itemId;
		}
		public String getPayId() {
			return payId;
		}
		public void setPayId(String payId) {
			this.payId = payId;
		}
	}
}
