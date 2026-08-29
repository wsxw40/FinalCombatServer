package test;

import java.util.HashMap;
import java.util.Map;
import java.util.TimerTask;

public class TestTask {
	public static class LoginTestTask extends TimerTask{
		static SyncClient client;

		public static SyncClient getClient() {
			return client;
		}


		public static void setClient(SyncClient client) {
			LoginTestTask.client = client;
		}


		@Override
		public void run() {
			for(int i=1;i<246;i++){
				System.out.println(i);
//			CRequest crRequest = new CRequest("c_shop_req_buy");
//			Map<String, String> parameters = new HashMap<String, String>();
//			parameters.put("pid", ""+i);
//			parameters.put("sid", "1433");
//			parameters.put("t", "1");
//			parameters.put("cid", "4");
//			parameters.put("costid", "1267");
//			parameters.put("packid", "1");
//			crRequest.parameters=parameters;
//			System.out.println(client.send(crRequest));
				
				
//			CRequest crRequest = new CRequest("c_player_achievement");
//			Map<String, String> parameters = new HashMap<String, String>();
//			parameters.put("pid", ""+i);
//			parameters.put("type", "1");
//			parameters.put("characterid", "5");
//			parameters.put("page", "1");
//			crRequest.parameters=parameters;
//			System.out.println(client.send(crRequest));
			
//			CRequest crRequest = new CRequest("c_storage_list");
//			Map<String, String> parameters = new HashMap<String, String>();
//			parameters.put("pid", ""+i);
//			parameters.put("t", "1");
//			parameters.put("cid", "4");
//			parameters.put("p", "1");
//			crRequest.parameters=parameters;
//			System.out.println(client.send(crRequest));
			
			
			CRequest crRequest = new CRequest("c_character_get");
			Map<String, String> parameters = new HashMap<String, String>();
			parameters.put("pid", ""+i);
			parameters.put("cid", "1");
//			parameters.put("cid", "4");
//			parameters.put("p", "1");
			crRequest.parameters=parameters;
			System.out.println(client.send(crRequest));
			}
			
//			SRequest srRequest = new SRequest("s_character_online");
//			srRequest.addParameter(1);
//			srRequest.addParameter("yaobo-pc");
//			srRequest.addParameter("192.168.6.1");
//			srRequest.addParameter("1");
//			byte[] returns=(byte[])client.send(srRequest);
////			for()
//			
//			 srRequest = new SRequest("s_character_offline");
//			srRequest.addParameter(1);
//			srRequest.addParameter(1373);
//			returns=(byte[])client.send(srRequest);
		}
		
	}

}
