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

public class DailyGift implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( (O2oUtil.compare(context.get("msg"),"!=",null))){
sb.append("	msg=\"");
sb.append(context.get("msg"));
sb.append("\"  ");
sb.append("\r\n");
} else {
sb.append("	msg={}  ");
sb.append("\r\n");
}
sb.append("check_gift={	 ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("daitemsy"),"!=",null))){
List daitemsy756 = new ArrayList<>();
if (null!=context.get("daitemsy")){
if (context.get("daitemsy") instanceof List) daitemsy756=(List<?>)context.get("daitemsy");
else if (context.get("daitemsy").getClass().isArray()) daitemsy756=Stream.of((Object[])context.get("daitemsy")).collect(Collectors.toList());
}
daitemsy756.forEach(theItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			item_num=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(" ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
}
sb.append("} ");
sb.append("\r\n");
sb.append("is_gift=");
sb.append(context.get("isGift"));
sb.append(" ");
sb.append("\r\n");
sb.append("quietBounds={ ");
sb.append("\r\n");
sb.append("	disResBounds=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("quietBounds"),"disResBounds"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}