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

public class CItemExpiredNotify implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List protolist156 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"list")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"list") instanceof List) protolist156=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"list");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"list").getClass().isArray()) protolist156=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"list")).collect(Collectors.toList());
}
protolist156.forEach(item->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"expireSecondsLeft"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("list1={ ");
sb.append("\r\n");
List protolist1659 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"list1")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"list1") instanceof List) protolist1659=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"list1");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"list1").getClass().isArray()) protolist1659=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"list1")).collect(Collectors.toList());
}
protolist1659.forEach(item->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"durable"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}