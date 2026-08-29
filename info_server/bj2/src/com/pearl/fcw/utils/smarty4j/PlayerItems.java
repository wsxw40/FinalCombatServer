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
List list446 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list446=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list446=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list446.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
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
List list934 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list934=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list934=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list934.forEach(theItem->{
try{
sb.append("	{    ");
sb.append("\r\n");
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
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