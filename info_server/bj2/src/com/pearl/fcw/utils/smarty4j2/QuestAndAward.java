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

public class QuestAndAward implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("sysQuests = { ");
sb.append("\r\n");
List protosysQuestList62 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"sysQuestList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysQuestList") instanceof List) protosysQuestList62=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"sysQuestList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysQuestList").getClass().isArray()) protosysQuestList62=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"sysQuestList")).collect(Collectors.toList());
}
protosysQuestList62.forEach(sysQuest->{
try{
sb.append("			{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar331=new HashMap<>();
includeContextVar331.put("sysQuest",sysQuest);
sb.append(new SysQuest().get(includeContextVar331));
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("playerQuests = { ");
sb.append("\r\n");
List protoplayerQuestList582 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"playerQuestList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"playerQuestList") instanceof List) protoplayerQuestList582=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"playerQuestList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"playerQuestList").getClass().isArray()) protoplayerQuestList582=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"playerQuestList")).collect(Collectors.toList());
}
protoplayerQuestList582.forEach(playerQuest->{
try{
sb.append("			{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar8379=new HashMap<>();
includeContextVar8379.put("playerQuest",playerQuest);
sb.append(new PlayerQuest().get(includeContextVar8379));
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("awardSysQuestId={ ");
sb.append("\r\n");
List protoawardSysQuestIdList540 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"awardSysQuestIdList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"awardSysQuestIdList") instanceof List) protoawardSysQuestIdList540=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"awardSysQuestIdList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"awardSysQuestIdList").getClass().isArray()) protoawardSysQuestIdList540=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"awardSysQuestIdList")).collect(Collectors.toList());
}
protoawardSysQuestIdList540.forEach(id->{
try{
sb.append("			$id, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}