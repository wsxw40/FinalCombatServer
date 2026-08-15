package com.pearl.fcw.utils.smarty4j2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;

public class SysAchievement implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("title = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"type.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("number = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("color = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"color.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysCharacterId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"sysCharacterId"));
sb.append(", ");
sb.append("\r\n");
sb.append("action = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"action.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("statType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"statType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("parent = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"parent"));
sb.append(", ");
sb.append("\r\n");
sb.append("group = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"group"));
sb.append(", ");
sb.append("\r\n");
sb.append("backUp = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysAchievement"),"backUp"));
sb.append("\", ");
sb.append("\r\n");
sb.append("gift= { ");
sb.append("\r\n");
List sysAchievementgiftList848 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysAchievement"),"giftList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysAchievement"),"giftList") instanceof List) sysAchievementgiftList848=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysAchievement"),"giftList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysAchievement"),"giftList").getClass().isArray()) sysAchievementgiftList848=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysAchievement"),"giftList")).collect(Collectors.toList());
}
sysAchievementgiftList848.forEach(payment->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar9761=new HashMap<>();
includeContextVar9761.put("payment",payment);
sb.append(new Payment().get(includeContextVar9761));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}