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

public class CStrengthItemList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("player_rank = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"playerRank"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List protoitemsList164 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList") instanceof List) protoitemsList164=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList").getClass().isArray()) protoitemsList164=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")).collect(Collectors.toList());
}
protoitemsList164.forEach(playerItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar2901=new HashMap<>();
includeContextVar2901.put("playerItem",playerItem);
sb.append(new PlayerItem().get(includeContextVar2901));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("maxStartExp = { ");
sb.append("\r\n");
List protogstExpList254 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"gstExpList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"gstExpList") instanceof List) protogstExpList254=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"gstExpList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"gstExpList").getClass().isArray()) protogstExpList254=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"gstExpList")).collect(Collectors.toList());
}
protogstExpList254.forEach(gstList->{
try{
sb.append("		{ ");
sb.append("\r\n");
List gstListiList756 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(gstList,"iList")){
if (O2oUtil.getSmarty4jProperty(gstList,"iList") instanceof List) gstListiList756=(List<?>)O2oUtil.getSmarty4jProperty(gstList,"iList");
else if (O2oUtil.getSmarty4jProperty(gstList,"iList").getClass().isArray()) gstListiList756=Stream.of((Object[])O2oUtil.getSmarty4jProperty(gstList,"iList")).collect(Collectors.toList());
}
gstListiList756.forEach(exp->{
try{
sb.append("			");
sb.append(exp);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("everyDayExp={ ");
sb.append("\r\n");
List protoeverydayGstExpInCombineList186 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineList") instanceof List) protoeverydayGstExpInCombineList186=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineList").getClass().isArray()) protoeverydayGstExpInCombineList186=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineList")).collect(Collectors.toList());
}
protoeverydayGstExpInCombineList186.forEach(exp->{
try{
sb.append("		");
sb.append(exp);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("everyDayIsVisiable={ ");
sb.append("\r\n");
List protoeverydayGstExpInCombineUseFlagList511 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineUseFlagList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineUseFlagList") instanceof List) protoeverydayGstExpInCombineUseFlagList511=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineUseFlagList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineUseFlagList").getClass().isArray()) protoeverydayGstExpInCombineUseFlagList511=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGstExpInCombineUseFlagList")).collect(Collectors.toList());
}
protoeverydayGstExpInCombineUseFlagList511.forEach(useFlag->{
try{
sb.append("		");
sb.append(useFlag);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("everyDayCPoint={ ");
sb.append("\r\n");
List protoeverydayGpointInCombineList79 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGpointInCombineList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGpointInCombineList") instanceof List) protoeverydayGpointInCombineList79=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGpointInCombineList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGpointInCombineList").getClass().isArray()) protoeverydayGpointInCombineList79=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"everydayGpointInCombineList")).collect(Collectors.toList());
}
protoeverydayGpointInCombineList79.forEach(gPoint->{
try{
sb.append("		");
sb.append(gPoint);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}