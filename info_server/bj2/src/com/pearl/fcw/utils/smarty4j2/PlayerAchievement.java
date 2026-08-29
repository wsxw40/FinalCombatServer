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

public class PlayerAchievement implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysCharacterId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"sysCharacterId"));
sb.append(", ");
sb.append("\r\n");
sb.append("status = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"status.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysAchievementId= { ");
sb.append("\r\n");
List playerAchievementsysAchievementIdList291 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerAchievement"),"sysAchievementIdList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerAchievement"),"sysAchievementIdList") instanceof List) playerAchievementsysAchievementIdList291=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerAchievement"),"sysAchievementIdList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerAchievement"),"sysAchievementIdList").getClass().isArray()) playerAchievementsysAchievementIdList291=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerAchievement"),"sysAchievementIdList")).collect(Collectors.toList());
}
playerAchievementsysAchievementIdList291.forEach(sysAchievementId->{
try{
sb.append("		");
sb.append(sysAchievementId);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("group = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"group"));
sb.append(", ");
sb.append("\r\n");
sb.append("level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("number = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("backUp = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerAchievement"),"backUp"));
sb.append("\", ");
sb.append("\r\n");
return sb.toString();
}

}