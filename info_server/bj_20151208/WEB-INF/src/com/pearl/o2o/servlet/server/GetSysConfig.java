package com.pearl.o2o.servlet.server;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.o2o.pojo.SysActivity;
import com.pearl.o2o.utils.BinaryReader;
import com.pearl.o2o.utils.BinaryUtil;
import com.pearl.o2o.utils.Constants;

public class GetSysConfig extends BaseServerServlet {
	private static final long serialVersionUID = 6032163311968210585L;
	static Logger logger = LoggerFactory.getLogger(GetSysConfig.class.getName());
	
	protected  byte[] innerService(BinaryReader r) throws IOException, Exception{
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try{
			Map<String, String> configs = getService.getSysConfig();
			Set<String> httpPaySet=new HashSet<String>();
			for(String str:configs.keySet()){
				if(str.indexOf("httppay")!=-1){
					httpPaySet.add(str);
				}
			}
			List<SysActivity> list=getService.getAvailableActivities();
			SysActivity activity=null;
			List<SysActivity> loginActivities=new ArrayList<SysActivity>();;
			for(SysActivity sa:list){
				if(sa.getAction()==Constants.ACTION_ACTIVITY.RANDOM_ACTIVITY.getValue()
						&& "N".equals(sa.getIsDeleted())
						&& "Y".equals(sa.getTheSwitch())){
					activity=sa;
				}else if(sa.getAction()==Constants.ACTION_ACTIVITY.HOUR2HOUR_ACTIVITY.getValue()
						&& "N".equals(sa.getIsDeleted())
						&& "Y".equals(sa.getTheSwitch())
						&& sa.getStartTime().after(new Date())){
					int start=sa.getValue();
					Calendar c = Calendar.getInstance();
					c.setTime(sa.getStartTime());
					c.set(Calendar.HOUR_OF_DAY, start);
					c.set(Calendar.MINUTE, 0);
					c.set(Calendar.SECOND, 0);
					if(c.getTime().after(new Date())){
						sa.setStartTime(c.getTime());
						loginActivities.add(sa);
					}
				}
			}
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("wallow.button"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("ads.button"))));
			
			if(activity!=null){
				out.write(BinaryUtil.toByta((byte)("Y".equals(activity.getTheSwitch())?1:0)));
				out.write(BinaryUtil.toByta((byte)activity.getTargetNum()));//mins
				out.write(BinaryUtil.toByta((byte)activity.getValue()));//people num
			}else{
				out.write(BinaryUtil.toByta((byte)0));
				out.write(BinaryUtil.toByta((byte)0));//mins
				out.write(BinaryUtil.toByta((byte)0));
			}
			out.write(BinaryUtil.toByta(loginActivities.size()));
			if(loginActivities.size()>0){
				for(SysActivity sa:loginActivities){
					out.write(BinaryUtil.toByta(sa.getId()));
					out.write(BinaryUtil.toByta((int)(sa.getStartTime().getTime()/1000)));//s
				}
			}
			out.write(BinaryUtil.toByta(configs.get("http.logo")));
			out.write(BinaryUtil.toByta(configs.get("http.report")));
			out.write(BinaryUtil.toByta(configs.get("http.info")));
			out.write(BinaryUtil.toByta(configs.get("http.fcm")));
			out.write(BinaryUtil.toByta(configs.get("http.gw")));
			out.write(BinaryUtil.toByta(configs.get("http.advertisingv")));
			out.write(BinaryUtil.toByta(configs.get("http.advertisingh")));
			out.write(BinaryUtil.toByta(configs.get("http.url")));
			out.write(BinaryUtil.toByta(httpPaySet.size()));
			for(String key:httpPaySet){
				String value=configs.get(key);
				String[] array=key.split("\\.");
				if(array.length!=2){
					out.write(BinaryUtil.toByta(""));
					out.write(BinaryUtil.toByta(""));
				}
				out.write(BinaryUtil.toByta(array[1]));
				out.write(BinaryUtil.toByta(value));
			}
			out.write(BinaryUtil.toByta(configs.get("http.loginserver")));
			out.write(BinaryUtil.toByta(configs.get("facebook.achievement.id")));
			
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("playername.minlength"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("playername.maxlength"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("teamname.minlength"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("teamname.maxlength"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("roomname.minlength"))));
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("roomname.maxlength"))));
			
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_STT_TIME_HOUR));
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_STT_WEEK_DAY));
			
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_END_TIME_HOUR));
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_END_WEEK_DAY));
				
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_OVR_TSK_TIME_HOUR));
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_SENDITEM_DELAY));
			out.write(BinaryUtil.toByta((byte)Constants.CMPT_BUY_SENDITEM_START_WEEK_DAY));
			//version for新手buff
			out.write(BinaryUtil.toByta(configs.get("newer.buff.version")));
			//限时抢拍 货币
			out.write(BinaryUtil.toByta(configs.get("compete.currency")));
			//限时抢拍宝箱物品ID
			out.write(BinaryUtil.toByta(configs.get("compete.rareBox")));
			out.write(BinaryUtil.toByta(configs.get("compete.masterworkBox")));
			out.write(BinaryUtil.toByta(configs.get("compete.uncommonBox")));
			
			//功能开关(第一位是蓝图列表，第二位是限时抢拍，第三位是二级密码:1为开，0为关;第四位是资源争夺战匹配开关;第五位为资源战挑战开关:1为开，0为关)
			out.write(BinaryUtil.toByta(configs.get("client.switch"))); 
			out.write(BinaryUtil.toByta(Byte.parseByte(configs.get("switch.exitopenexe"))));
			
			
			return out.toByteArray();
		}catch (Exception e) {
			logger.warn("Error in GetSysConfig: ", e);
			throw e;
		}
	}
}
