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

public class CShopList implements Ctx {
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
List protoitemsList302 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList") instanceof List) protoitemsList302=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList").getClass().isArray()) protoitemsList302=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"itemsList")).collect(Collectors.toList());
}
protoitemsList302.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar857=new HashMap<>();
includeContextVar857.put("sysItem",sysItem);
sb.append(new SysItem().get(includeContextVar857));
sb.append("		}, ");
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