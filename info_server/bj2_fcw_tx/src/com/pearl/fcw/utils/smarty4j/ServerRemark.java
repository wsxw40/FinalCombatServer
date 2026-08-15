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

public class ServerRemark implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("servers = { ");
sb.append("\r\n");
List serverList438 = new ArrayList<>();
if (null!=context.get("serverList")){
if (context.get("serverList") instanceof List) serverList438=(List<?>)context.get("serverList");
else if (context.get("serverList") instanceof int[]) serverList438=Stream.of((int[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList") instanceof long[]) serverList438=Stream.of((long[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList") instanceof float[]) serverList438=Stream.of((float[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList") instanceof double[]) serverList438=Stream.of((double[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList") instanceof byte[]) serverList438=Stream.of((byte[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList") instanceof String[]) serverList438=Stream.of((String[])context.get("serverList")).collect(Collectors.toList());
else if (context.get("serverList").getClass().isArray()) serverList438=Stream.of(context.get("serverList")).collect(Collectors.toList());
}
serverList438.forEach(server->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	id=");
sb.append(O2oUtil.getSmarty4jProperty(server,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	index=");
sb.append(O2oUtil.getSmarty4jProperty(server,"index"));
sb.append(", ");
sb.append("\r\n");
sb.append("	remark=\"");
sb.append(O2oUtil.getSmarty4jProperty(server,"remark"));
sb.append("\" ");
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