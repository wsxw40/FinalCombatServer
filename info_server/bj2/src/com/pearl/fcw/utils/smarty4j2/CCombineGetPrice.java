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

public class CCombineGetPrice implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("prices = { ");
sb.append("\r\n");
List protopricesList848 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"pricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"pricesList") instanceof List) protopricesList848=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"pricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"pricesList").getClass().isArray()) protopricesList848=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"pricesList")).collect(Collectors.toList());
}
protopricesList848.forEach(price->{
try{
sb.append("		");
sb.append(price);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List protoitemsList598 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList") instanceof List) protoitemsList598=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList").getClass().isArray()) protoitemsList598=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")).collect(Collectors.toList());
}
protoitemsList598.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar2951=new HashMap<>();
includeContextVar2951.put("sysItem",sysItem);
sb.append(new SysItem().get(includeContextVar2951));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}