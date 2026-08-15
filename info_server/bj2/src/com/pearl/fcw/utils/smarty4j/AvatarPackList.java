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

public class AvatarPackList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("player_rank = ");
sb.append(context.get("rank"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List list282 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list282=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list282=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list282.forEach(theItem->{
try{
sb.append("	{    ");
sb.append("\r\n");
sb.append("		pid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			c_side = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gender = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cGender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources47 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources47=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources47=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources47.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
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