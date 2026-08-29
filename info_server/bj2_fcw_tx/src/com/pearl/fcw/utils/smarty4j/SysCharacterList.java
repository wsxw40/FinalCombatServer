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

public class SysCharacterList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("items ={ ");
sb.append("\r\n");
List list469 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list469=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list469=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list469=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list469=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list469=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list469=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list469=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list469=Stream.of(context.get("list")).collect(Collectors.toList());
}
list469.forEach(ch->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jProperty(ch,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(ch,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		is_open=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(ch,"isDefault"),"==","Y")){
sb.append("				1 , ");
sb.append("\r\n");
} else {
sb.append("				0 , ");
sb.append("\r\n");
}
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