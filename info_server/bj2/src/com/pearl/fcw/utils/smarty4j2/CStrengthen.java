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

public class CStrengthen implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"result"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List protoitemsList694 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList") instanceof List) protoitemsList694=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList").getClass().isArray()) protoitemsList694=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")).collect(Collectors.toList());
}
protoitemsList694.forEach(playerItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar6012=new HashMap<>();
includeContextVar6012.put("playerItem",playerItem);
sb.append(new PlayerItem().get(includeContextVar6012));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("items2= { ");
sb.append("\r\n");
List protoitems2List351 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"items2List")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items2List") instanceof List) protoitems2List351=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"items2List");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"items2List").getClass().isArray()) protoitems2List351=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"items2List")).collect(Collectors.toList());
}
protoitems2List351.forEach(playerItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar7298=new HashMap<>();
includeContextVar7298.put("playerItem",playerItem);
sb.append(new PlayerItem().get(includeContextVar7298));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("failAwards={ ");
sb.append("\r\n");
List protofailAwardsList932 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"failAwardsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"failAwardsList") instanceof List) protofailAwardsList932=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"failAwardsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"failAwardsList").getClass().isArray()) protofailAwardsList932=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"failAwardsList")).collect(Collectors.toList());
}
protofailAwardsList932.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar5233=new HashMap<>();
includeContextVar5233.put("playerItem",sysItem);
sb.append(new SysItem().get(includeContextVar5233));
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