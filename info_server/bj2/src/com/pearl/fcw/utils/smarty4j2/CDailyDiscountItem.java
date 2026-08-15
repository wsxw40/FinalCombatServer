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

public class CDailyDiscountItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("theItem = { ");
sb.append("\r\n");
List prototheItemList155 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList155=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList155=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList155.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar1401=new HashMap<>();
includeContextVar1401.put("sysItem",O2oUtil.getSmarty4jProperty(discount,"sysItem"));
sb.append(new SysItem().get(includeContextVar1401));
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
List prototheItemList729 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList729=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList729=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList729.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
List discountflagList753 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(discount,"flagList")){
if (O2oUtil.getSmarty4jProperty(discount,"flagList") instanceof List) discountflagList753=(List<?>)O2oUtil.getSmarty4jProperty(discount,"flagList");
else if (O2oUtil.getSmarty4jProperty(discount,"flagList").getClass().isArray()) discountflagList753=Stream.of((Object[])O2oUtil.getSmarty4jProperty(discount,"flagList")).collect(Collectors.toList());
}
discountflagList753.forEach(flag->{
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
List prototheItemList537 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList") instanceof List) prototheItemList537=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList").getClass().isArray()) prototheItemList537=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"theItemList")).collect(Collectors.toList());
}
prototheItemList537.forEach(discount->{
try{
sb.append("		{ ");
sb.append("\r\n");
List discountflagList227 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(discount,"flagList")){
if (O2oUtil.getSmarty4jProperty(discount,"flagList") instanceof List) discountflagList227=(List<?>)O2oUtil.getSmarty4jProperty(discount,"flagList");
else if (O2oUtil.getSmarty4jProperty(discount,"flagList").getClass().isArray()) discountflagList227=Stream.of((Object[])O2oUtil.getSmarty4jProperty(discount,"flagList")).collect(Collectors.toList());
}
discountflagList227.forEach(ratio->{
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