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

public class ServerList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("servers = { ");
sb.append("\r\n");
List serverList998 = new ArrayList<>();
if (null!=context.get("serverList")){
if (context.get("serverList") instanceof List) serverList998=(List<?>)context.get("serverList");
else if (context.get("serverList").getClass().isArray()) serverList998=Stream.of((Object[])context.get("serverList")).collect(Collectors.toList());
}
serverList998.forEach(server->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(server,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(server,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	online=");
sb.append(O2oUtil.getSmarty4jPropertyNil(server,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("	max=");
sb.append(O2oUtil.getSmarty4jPropertyNil(server,"max"));
sb.append(" ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}