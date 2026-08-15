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
List awardItemVos638 = new ArrayList<>();
if (null!=context.get("awardItemVos")){
if (context.get("awardItemVos") instanceof List) awardItemVos638=(List<?>)context.get("awardItemVos");
else if (context.get("awardItemVos").getClass().isArray()) awardItemVos638=Stream.of((Object[])context.get("awardItemVos")).collect(Collectors.toList());
}
awardItemVos638.forEach(awardItemVo->{
try{
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"),"==",1)){
Context includeContextVar967=new Context();
includeContextVar967.set("sysItem",O2oUtil.getSmarty4jProperty(awardItemVo,"item"));
includeContextVar967.set("unitType",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"));
includeContextVar967.set("unit",O2oUtil.getSmarty4jProperty(awardItemVo,"itemNum"));
includeContextVar967.set("isVip",O2oUtil.getSmarty4jProperty(awardItemVo,"vip"));
sb.append(new MissionItemAwardEntity().get(includeContextVar967));
} else {
Context includeContextVar3425=new Context();
includeContextVar3425.set("sysItem",O2oUtil.getSmarty4jProperty(awardItemVo,"item"));
includeContextVar3425.set("unitType",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unitType"));
includeContextVar3425.set("unit",O2oUtil.getSmarty4jProperty(awardItemVo,"item.unit"));
includeContextVar3425.set("isVip",O2oUtil.getSmarty4jProperty(awardItemVo,"vip"));
sb.append(new MissionItemAwardEntity().get(includeContextVar3425));
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