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

public class DailyDiscountItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("theItem = { ");
sb.append("\r\n");
List prototheItemList109 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList109=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList109=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList109.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar8067=new HashMap<>();
includeContextVar8067.put("sysItem",O2oUtil.getSmarty4jProperty(discount,"sysItem"));
sb.append(new SysItem().get(includeContextVar8067));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("flag = { ");
sb.append("\r\n");
List prototheItemList632 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList632=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList632=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList632.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
List discountflagList402 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(discount,"flagList")){
if (O2oUtil.getSmarty4jProperty(discount,"flagList") instanceof List) discountflagList402=(List<?>)O2oUtil.getSmarty4jProperty(discount,"flagList");
else if (O2oUtil.getSmarty4jProperty(discount,"flagList").getClass().isArray()) discountflagList402=Stream.of((Object[])O2oUtil.getSmarty4jProperty(discount,"flagList")).collect(Collectors.toList());
}
discountflagList402.forEach(flag->{
try{
sb.append("			");
sb.append(flag);
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
sb.append("discounts = { ");
sb.append("\r\n");
List prototheItemList142 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList142=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList142=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList142.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
List discountflagList741 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(discount,"flagList")){
if (O2oUtil.getSmarty4jProperty(discount,"flagList") instanceof List) discountflagList741=(List<?>)O2oUtil.getSmarty4jProperty(discount,"flagList");
else if (O2oUtil.getSmarty4jProperty(discount,"flagList").getClass().isArray()) discountflagList741=Stream.of((Object[])O2oUtil.getSmarty4jProperty(discount,"flagList")).collect(Collectors.toList());
}
discountflagList741.forEach(ratio->{
try{
sb.append("			");
sb.append(ratio);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}