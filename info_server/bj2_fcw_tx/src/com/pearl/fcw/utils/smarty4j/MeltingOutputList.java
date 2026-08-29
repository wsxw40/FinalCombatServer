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

public class MeltingOutputList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("items = { ");
sb.append("\r\n");
List list235 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list235=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list235=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list235=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list235=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list235=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list235=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list235=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list235=Stream.of(context.get("list")).collect(Collectors.toList());
}
list235.forEach(sysItem->{
try{
sb.append("	{    ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List sysItemcharacterList938 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof List) sysItemcharacterList938=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof int[]) sysItemcharacterList938=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof long[]) sysItemcharacterList938=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof float[]) sysItemcharacterList938=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof double[]) sysItemcharacterList938=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof byte[]) sysItemcharacterList938=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof String[]) sysItemcharacterList938=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList").getClass().isArray()) sysItemcharacterList938=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
}
sysItemcharacterList938.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList540 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList540=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof int[]) sysItemgunPropertypropertyList540=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof long[]) sysItemgunPropertypropertyList540=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof float[]) sysItemgunPropertypropertyList540=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof double[]) sysItemgunPropertypropertyList540=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof byte[]) sysItemgunPropertypropertyList540=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof String[]) sysItemgunPropertypropertyList540=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList540=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList540.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("			},  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List sysItempackages802 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"packages")){
if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof List) sysItempackages802=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"packages");
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof int[]) sysItempackages802=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof long[]) sysItempackages802=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof float[]) sysItempackages802=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof double[]) sysItempackages802=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof byte[]) sysItempackages802=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof String[]) sysItempackages802=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages").getClass().isArray()) sysItempackages802=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
}
sysItempackages802.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource435 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resource")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof List) sysItemresource435=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resource");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof int[]) sysItemresource435=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof long[]) sysItemresource435=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof float[]) sysItemresource435=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof double[]) sysItemresource435=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof byte[]) sysItemresource435=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof String[]) sysItemresource435=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource").getClass().isArray()) sysItemresource435=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
}
sysItemresource435.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e5){
logger.error(e5.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources99 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources99=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources99=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources99=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources99=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources99=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources99=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources99=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources99=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources99.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources14 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources14=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources14=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources14=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources14=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources14=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources14=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources14=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources14=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources14.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else {
List sysItemresources239 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources239=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources239=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources239=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources239=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources239=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources239=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources239=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources239=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources239.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}