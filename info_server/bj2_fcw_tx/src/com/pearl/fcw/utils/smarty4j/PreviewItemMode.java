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

public class PreviewItemMode implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("items= {	 ");
sb.append("\r\n");
sb.append("	pid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("	repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("	buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"buff"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"isDefault"),"==","Y")){
sb.append("			0 , ");
sb.append("\r\n");
} else {
sb.append("			1 , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		durable = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("		quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("		p_suitable = { ");
sb.append("\r\n");
List theItempSuits246 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof List) theItempSuits246=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof int[]) theItempSuits246=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof long[]) theItempSuits246=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof float[]) theItempSuits246=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof double[]) theItempSuits246=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof byte[]) theItempSuits246=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof String[]) theItempSuits246=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits").getClass().isArray()) theItempSuits246=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
}
theItempSuits246.forEach(pSuit->{
try{
sb.append("				\"");
sb.append(pSuit);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		c_side = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("		minutes_left = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cGender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		length = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wMaxLength"));
sb.append(" , ");
sb.append("\r\n");
sb.append("		offset = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wXOffset"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("    		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	stop_power = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"stopPpower"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	    	recoil = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"recoil"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	    	accuracy = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"accuracy"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	range = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"range"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wAmmoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	weight = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"weight"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	explode_time = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wExplodeTime"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	explode_range = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wHurtRange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	parts = { ");
sb.append("\r\n");
List theItemparts634 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof List) theItemparts634=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof int[]) theItemparts634=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof long[]) theItemparts634=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof float[]) theItemparts634=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof double[]) theItemparts634=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof byte[]) theItemparts634=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof String[]) theItemparts634=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts").getClass().isArray()) theItemparts634=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
}
theItemparts634.forEach(part->{
try{
sb.append("		{");
sb.append(O2oUtil.getSmarty4jProperty(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			posx = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wPoseX"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			posy = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wPoseY"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			posz = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wPoseZ"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			camera = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wCamera"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("			direction = 0 ,  ");
sb.append("\r\n");
List theItemresource612 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource612=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof int[]) theItemresource612=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof long[]) theItemresource612=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof float[]) theItemresource612=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof double[]) theItemresource612=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof byte[]) theItemresource612=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof String[]) theItemresource612=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource612=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource612.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("	resource_new ={ ");
sb.append("\r\n");
List newList877 = new ArrayList<>();
if (null!=context.get("newList")){
if (context.get("newList") instanceof List) newList877=(List<?>)context.get("newList");
else if (context.get("newList") instanceof int[]) newList877=Stream.of((int[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList") instanceof long[]) newList877=Stream.of((long[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList") instanceof float[]) newList877=Stream.of((float[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList") instanceof double[]) newList877=Stream.of((double[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList") instanceof byte[]) newList877=Stream.of((byte[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList") instanceof String[]) newList877=Stream.of((String[])context.get("newList")).collect(Collectors.toList());
else if (context.get("newList").getClass().isArray()) newList877=Stream.of(context.get("newList")).collect(Collectors.toList());
}
newList877.forEach(part->{
try{
List partresources860 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(part,"resources")){
if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof List) partresources860=(List<?>)O2oUtil.getSmarty4jProperty(part,"resources");
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof int[]) partresources860=Stream.of((int[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof long[]) partresources860=Stream.of((long[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof float[]) partresources860=Stream.of((float[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof double[]) partresources860=Stream.of((double[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof byte[]) partresources860=Stream.of((byte[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof String[]) partresources860=Stream.of((String[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(part,"resources").getClass().isArray()) partresources860=Stream.of(O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
}
partresources860.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"name"));
sb.append("/");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e5){
logger.error(e5.toString());
}
});
}catch(Exception e5){
logger.error(e5.toString());
}
});
List deleteList687 = new ArrayList<>();
if (null!=context.get("deleteList")){
if (context.get("deleteList") instanceof List) deleteList687=(List<?>)context.get("deleteList");
else if (context.get("deleteList") instanceof int[]) deleteList687=Stream.of((int[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList") instanceof long[]) deleteList687=Stream.of((long[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList") instanceof float[]) deleteList687=Stream.of((float[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList") instanceof double[]) deleteList687=Stream.of((double[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList") instanceof byte[]) deleteList687=Stream.of((byte[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList") instanceof String[]) deleteList687=Stream.of((String[])context.get("deleteList")).collect(Collectors.toList());
else if (context.get("deleteList").getClass().isArray()) deleteList687=Stream.of(context.get("deleteList")).collect(Collectors.toList());
}
deleteList687.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("				\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}