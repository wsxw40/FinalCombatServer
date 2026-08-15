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

public class WeaponPackList implements Ctx {
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
List list569 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list569=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list569=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list569=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list569=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list569=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list569=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list569=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list569=Stream.of(context.get("list")).collect(Collectors.toList());
}
list569.forEach(theItem->{
try{
sb.append("	{    ");
sb.append("\r\n");
sb.append("		pid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			durable = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			length = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wMaxLength"));
sb.append(" , ");
sb.append("\r\n");
sb.append("			offset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wXOffset"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			stop_power = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"stopPpower"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			recoil = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"recoil"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			accuracy = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"accuracy"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			range = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"range"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			weight = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"weight"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			explode_time = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wExplodeTime"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			explode_range = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wHurtRange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List theItemparts536 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts536=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts536=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts536=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts536=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts536=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts536=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts536=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts536=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts536.forEach(part->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(", },  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		direction = { ");
sb.append("\r\n");
List theItemdirection583 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"direction")){
if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof List) theItemdirection583=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"direction");
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof int[]) theItemdirection583=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof long[]) theItemdirection583=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof float[]) theItemdirection583=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof double[]) theItemdirection583=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof byte[]) theItemdirection583=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof String[]) theItemdirection583=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"direction").getClass().isArray()) theItemdirection583=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
}
theItemdirection583.forEach(dir->{
try{
sb.append("				");
sb.append(dir);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				posx = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wPoseX"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				posy = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wPoseY"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				posz = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wPoseZ"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				camera = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wCamera"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("				direction = 0 ,  ");
sb.append("\r\n");
List theItemresource48 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource48=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource48=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource48=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource48=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource48=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource48=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource48=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource48=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource48.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e4){
logger.error(e4.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}