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
List list938 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list938=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list938=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list938.forEach(theItem->{
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
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
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
sb.append("			durable = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			length = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wMaxLength"));
sb.append(" , ");
sb.append("\r\n");
sb.append("			offset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wXOffset"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"stopPpower"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			recoil = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"recoil"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			accuracy = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"accuracy"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			range = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"range"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			weight = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"weight"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			explode_time = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wExplodeTime"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			explode_range = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wHurtRange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List theItemparts337 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts337=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts337=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts337.forEach(part->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
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
List theItemdirection630 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"direction")){
if (O2oUtil.getSmarty4jProperty(theItem,"direction") instanceof List) theItemdirection630=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"direction");
else if (O2oUtil.getSmarty4jProperty(theItem,"direction").getClass().isArray()) theItemdirection630=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"direction")).collect(Collectors.toList());
}
theItemdirection630.forEach(dir->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				posx = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wPoseX"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				posy = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wPoseY"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				posz = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wPoseZ"));
sb.append(",  ");
sb.append("\r\n");
sb.append("				camera = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wCamera"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("				direction = 0 ,  ");
sb.append("\r\n");
List theItemresource653 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource653=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource653=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource653.forEach(res->{
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