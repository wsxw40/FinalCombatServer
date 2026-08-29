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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("	repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("	buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"buff"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		durable = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("		quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("		p_suitable = { ");
sb.append("\r\n");
List theItempSuits277 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits") instanceof List) theItempSuits277=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits").getClass().isArray()) theItempSuits277=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"pSuits")).collect(Collectors.toList());
}
theItempSuits277.forEach(pSuit->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cSide"));
sb.append(", ");
sb.append("\r\n");
sb.append("		minutes_left = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cGender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		length = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wMaxLength"));
sb.append(" , ");
sb.append("\r\n");
sb.append("		offset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wXOffset"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("    		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"stopPpower"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	    	recoil = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"recoil"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	    	accuracy = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"accuracy"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	range = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"range"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	    	ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wAmmoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	weight = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"weight"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    	explode_time = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wExplodeTime"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	explode_range = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wHurtRange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	parts = { ");
sb.append("\r\n");
List theItemparts125 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof List) theItemparts125=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts").getClass().isArray()) theItemparts125=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
}
theItemparts125.forEach(part->{
try{
sb.append("		{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			posx = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wPoseX"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			posy = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wPoseY"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			posz = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wPoseZ"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			camera = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wCamera"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("			direction = 0 ,  ");
sb.append("\r\n");
List theItemresource732 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource732=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource732=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource732.forEach(res->{
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
List newList465 = new ArrayList<>();
if (null!=context.get("newList")){
if (context.get("newList") instanceof List) newList465=(List<?>)context.get("newList");
else if (context.get("newList").getClass().isArray()) newList465=Stream.of((Object[])context.get("newList")).collect(Collectors.toList());
}
newList465.forEach(part->{
try{
List partresources414 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(part,"resources")){
if (O2oUtil.getSmarty4jProperty(part,"resources") instanceof List) partresources414=(List<?>)O2oUtil.getSmarty4jProperty(part,"resources");
else if (O2oUtil.getSmarty4jProperty(part,"resources").getClass().isArray()) partresources414=Stream.of((Object[])O2oUtil.getSmarty4jProperty(part,"resources")).collect(Collectors.toList());
}
partresources414.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"name"));
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
List deleteList572 = new ArrayList<>();
if (null!=context.get("deleteList")){
if (context.get("deleteList") instanceof List) deleteList572=(List<?>)context.get("deleteList");
else if (context.get("deleteList").getClass().isArray()) deleteList572=Stream.of((Object[])context.get("deleteList")).collect(Collectors.toList());
}
deleteList572.forEach(res->{
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