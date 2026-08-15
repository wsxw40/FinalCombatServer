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

public class SysQuest implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("title = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("desc = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"desc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("icon = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("resource = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"resource"));
sb.append("\", ");
sb.append("\r\n");
sb.append("uiType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"uiType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("uiAction = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"uiAction.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("uiGroup = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"uiGroup"));
sb.append(", ");
sb.append("\r\n");
sb.append("uiIndex = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"uiIndex"));
sb.append(", ");
sb.append("\r\n");
sb.append("number = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("completeCount = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"completeCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("repeatType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"repeatType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("repeatParam = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"repeatParam"));
sb.append(", ");
sb.append("\r\n");
sb.append("startTime = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"startTime"));
sb.append("\", ");
sb.append("\r\n");
sb.append("endTime = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"endTime"));
sb.append("\", ");
sb.append("\r\n");
sb.append("color = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"color.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysCharacterId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"sysCharacterId"));
sb.append(", ");
sb.append("\r\n");
sb.append("parentId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"parentId"));
sb.append(", ");
sb.append("\r\n");
sb.append("difficulty = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysQuest"),"difficulty"));
sb.append(", ");
sb.append("\r\n");
sb.append("item={ ");
sb.append("\r\n");
List sysQuestitemList329 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"itemList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"itemList") instanceof List) sysQuestitemList329=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"itemList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"itemList").getClass().isArray()) sysQuestitemList329=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"itemList")).collect(Collectors.toList());
}
sysQuestitemList329.forEach(sysItemPrice->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar9660=new HashMap<>();
includeContextVar9660.put("sysItemPrice",sysItemPrice);
sb.append(new SysItemPrice().get(includeContextVar9660));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("vipItem={ ");
sb.append("\r\n");
List sysQuestvipItemList989 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"vipItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"vipItemList") instanceof List) sysQuestvipItemList989=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"vipItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"vipItemList").getClass().isArray()) sysQuestvipItemList989=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysQuest"),"vipItemList")).collect(Collectors.toList());
}
sysQuestvipItemList989.forEach(sysItemPrice->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar1395=new HashMap<>();
includeContextVar1395.put("sysItemPrice",sysItemPrice);
sb.append(new SysItemPrice().get(includeContextVar1395));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}