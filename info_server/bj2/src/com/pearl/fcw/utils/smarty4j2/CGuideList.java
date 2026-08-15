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

public class CGuideList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("playerAchievement={ ");
sb.append("\r\n");
List protoplayerAchievementList881 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievementList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievementList") instanceof List) protoplayerAchievementList881=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievementList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievementList").getClass().isArray()) protoplayerAchievementList881=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievementList")).collect(Collectors.toList());
}
protoplayerAchievementList881.forEach(playerAchievement->{
try{
Map<String, Object> includeContextVar8152=new HashMap<>();
includeContextVar8152.put("playerAchievement",playerAchievement);
sb.append(new PlayerAchievement().get(includeContextVar8152));
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("nextSysAchievement={ ");
sb.append("\r\n");
List protonextSysAchievementList883 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievementList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievementList") instanceof List) protonextSysAchievementList883=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievementList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievementList").getClass().isArray()) protonextSysAchievementList883=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievementList")).collect(Collectors.toList());
}
protonextSysAchievementList883.forEach(sysAchievement->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar107=new HashMap<>();
includeContextVar107.put("sysAchievement",sysAchievement);
sb.append(new SysAchievement().get(includeContextVar107));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}