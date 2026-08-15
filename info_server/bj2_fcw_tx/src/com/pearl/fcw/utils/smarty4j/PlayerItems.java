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

public class PlayerItems implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("stName = ");
sb.append(context.get("stName"));
sb.append(" ");
sb.append("\r\n");
sb.append("type = ");
sb.append(context.get("type"));
sb.append(" ");
sb.append("\r\n");
sb.append("meltingLevel = ");
sb.append(context.get("meltingLevel"));
sb.append(" ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("type"),"==",0))){
sb.append("	items= { ");
sb.append("\r\n");
List list831 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list831=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list831=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list831=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list831=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list831=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list831=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list831=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list831=Stream.of(context.get("list")).collect(Collectors.toList());
}
list831.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}		 ");
sb.append("\r\n");
}
if( (O2oUtil.compare(context.get("type"),"==",1))){
sb.append("	items=  ");
sb.append("	{ ");
sb.append("\r\n");
List list230 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list230=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list230=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list230=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list230=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list230=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list230=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list230=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list230=Stream.of(context.get("list")).collect(Collectors.toList());
}
list230.forEach(theItem->{
try{
sb.append("	{    ");
sb.append("\r\n");
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}		 ");
sb.append("\r\n");
}
return sb.toString();
}

}