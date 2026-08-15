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

public class PlayerPartList implements Ctx {
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
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("defaultItem"),"!=",null) ){
sb.append("	{ ");
sb.append("\r\n");
sb.append("		pid=0, ");
sb.append("\r\n");
sb.append("		display=\"\", ");
sb.append("\r\n");
sb.append("		name=\"defaultpart\", ");
sb.append("\r\n");
sb.append("		parent_id=0, ");
sb.append("\r\n");
sb.append("		description =\"\", ");
sb.append("\r\n");
sb.append("		resource = {}, ");
sb.append("\r\n");
sb.append("		common={}, ");
sb.append("\r\n");
sb.append("		performance = {}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}
List list910 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list910=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list910=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list910=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list910=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list910=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list910=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list910=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list910=Stream.of(context.get("list")).collect(Collectors.toList());
}
list910.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
sb.append("			pid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			parent_id=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"parentItemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
List theItemresources286 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources286=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources286=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources286=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources286=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources286=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources286=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources286=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources286=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources286.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				durable = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				p_suitable = { ");
sb.append("\r\n");
List theItempSuits822 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"pSuits")){
if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof List) theItempSuits822=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"pSuits");
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof int[]) theItempSuits822=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof long[]) theItempSuits822=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof float[]) theItempSuits822=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof double[]) theItempSuits822=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof byte[]) theItempSuits822=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof String[]) theItempSuits822=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits").getClass().isArray()) theItempSuits822=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
}
theItempSuits822.forEach(pSuit->{
try{
sb.append("						\"");
sb.append(pSuit);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				c_side = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("				gender = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cGender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				length = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wMaxLength"));
sb.append(" , ");
sb.append("\r\n");
sb.append("				offset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wXOffset"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				stop_power = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"stopPpower"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("				recoil = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"recoil"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("				accuracy = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"accuracy"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				range = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"range"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				weight = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"weight"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				explode_time = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wExplodeTime"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("				explode_range = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wHurtRange"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}