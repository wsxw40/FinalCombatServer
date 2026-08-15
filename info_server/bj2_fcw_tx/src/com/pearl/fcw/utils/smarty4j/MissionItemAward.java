package com.pearl.fcw.utils.smarty4j;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.lilystudio.smarty4j.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil.Ctx;

public class MissionItemAward implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("items = { ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("awardItemVos"),"!=",null))){
List awardItemVos905 = new ArrayList<>();
if (null!=context.get("awardItemVos")){
if (context.get("awardItemVos") instanceof List) awardItemVos905=(List<?>)context.get("awardItemVos");
else if (context.get("awardItemVos") instanceof int[]) awardItemVos905=Stream.of((int[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos") instanceof long[]) awardItemVos905=Stream.of((long[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos") instanceof float[]) awardItemVos905=Stream.of((float[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos") instanceof double[]) awardItemVos905=Stream.of((double[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos") instanceof byte[]) awardItemVos905=Stream.of((byte[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos") instanceof String[]) awardItemVos905=Stream.of((String[])context.get("awardItemVos")).collect(Collectors.toList());
else if (context.get("awardItemVos").getClass().isArray()) awardItemVos905=Stream.of(context.get("awardItemVos")).collect(Collectors.toList());
}
awardItemVos905.forEach(awardItemVo->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"),"==",1)){
Context includeContextVar553=new Context();
includeContextVar553.set("sysItem",O2oUtil.getSmarty4jProperty(awardItemVo,"item"));
includeContextVar553.set("unitType",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"));
includeContextVar553.set("unit",O2oUtil.getSmarty4jProperty(awardItemVo,"itemNum"));
includeContextVar553.set("isVip",O2oUtil.getSmarty4jProperty(awardItemVo,"vip"));
sb.append(new MissionItemAwardEntity().get(includeContextVar553));
} else {
Context includeContextVar8883=new Context();
includeContextVar8883.set("sysItem",O2oUtil.getSmarty4jProperty(awardItemVo,"item"));
includeContextVar8883.set("unitType",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"));
includeContextVar8883.set("unit",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unit"));
includeContextVar8883.set("isVip",O2oUtil.getSmarty4jProperty(awardItemVo,"vip"));
sb.append(new MissionItemAwardEntity().get(includeContextVar8883));
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}