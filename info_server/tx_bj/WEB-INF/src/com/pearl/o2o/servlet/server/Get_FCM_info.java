package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
/**
 * 防沉迷
 * @author zhaolianming
 *
 */
public class Get_FCM_info extends BaseServerServlet {
	private static final long serialVersionUID = -186025727266294629L;
	static Logger LOG = LoggerFactory.getLogger(Get_FCM_info.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		int pid=0;
		try{
			pid=r.readInt();
			if (pid==0) {
				throw new IllegalArgumentException("FCM_info pid==0");
			}
			int time = getTime(pid);
			
			out.write(BinaryUtil.toByta(pid));
			out.write(BinaryUtil.toByta(time == -1 ? 1 : 0));
			out.write(BinaryUtil.toByta(time));
			//=========================
			System.out.println(pid);
			System.out.println(time == -1 ? 1 : 0);
			System.out.println(time);
		}catch (Exception e) {
			out.write(BinaryUtil.toByta(pid));
			out.write(BinaryUtil.toByta(1));
			out.write(BinaryUtil.toByta(-1));
			LOG.warn("Error in get_FCM_info: ", e);
			System.out.println(-1);
			//throw e;
		}
		return out.toByteArray();
	}
	/**
	 * 获得防沉迷时间
	 * @param playerId
	 * @return
	 * @throws Exception
	 */
	public static int getTime(int playerId){
		int time = -1;
//		if (playerId == -1)
//			return -1;
//		String txOpenKey;
//		String userName;
//		String appid="1105440722";
//		String ip="119.147.19.43";
//		
//		try {
//			txOpenKey = nosqlService.getTxOpenKey(playerId);
//			userName = getService.getPlayerById(playerId).getUserName();
//		} catch (Exception e) {
//			return -1;
//		}
//		//APP ID:1105440722
//		//APP KEY:UNlgGitcb9DkjJN1 
//		
//		
//		StringBuffer sb = new StringBuffer();
//		sb.append("{\"ret\":0,\"msg\":\"操作成功！\",\"audit\":0,\"gametime\":100}");
//		String urlStr = "http://"+ip+"/user/get_antiaddiction_info?"
//			+"openid="+userName
//			+"&openkey="+txOpenKey
//			+"&appid="+appid
//			+"&pf="+""
//			+"&sig="+"";
//	        URL url;
//	        URLConnection connection ;
//	        InputStreamReader r = null;
//	        BufferedReader rd = null;
//	        try {
//	            url = new URL(urlStr);
//	            connection = url.openConnection();
//	            r = new InputStreamReader(connection.getInputStream());
//	            rd = new BufferedReader(r);
//	            String line;
//	            while ((line = rd.readLine()) != null) {
//	                sb.append(line);
//	            }
//	            Gson gson = new Gson();
//	    		Map<String,String> fromJson = gson.fromJson(sb.toString(),new  TypeToken<Map<String,String>>(){}.getType());
//	    		if (fromJson.get("msg").equals("操作成功！")) 
//	    			time= fromJson.get("audit").equals("1") ?-1:StringUtil.toInt(fromJson.get("gametime"));
//	        } catch (IOException e) {
//	            e.printStackTrace();
//	        }finally{
//	           if (r!=null) try {r.close();} catch (IOException e1) {e1.printStackTrace();}
//	           if (rd!=null) try {rd.close();} catch (IOException e1) {e1.printStackTrace();}
//	        }
	        return time;
	}
}