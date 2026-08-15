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

public class ItemToolTips implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{	 ");
sb.append("\r\n");
sb.append("	sid=");
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
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar7372=new Context();
includeContextVar7372.set("unitType",O2oUtil.getSmarty4jProperty(context.get("theItem"),"unitType"));
includeContextVar7372.set("unit",O2oUtil.getSmarty4jProperty(context.get("theItem"),"unit"));
sb.append(new Timelimit().get(includeContextVar7372));
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List theItemcharacterList88 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"characterList") instanceof List) theItemcharacterList88=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"characterList").getClass().isArray()) theItemcharacterList88=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"characterList")).collect(Collectors.toList());
}
theItemcharacterList88.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList495 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList495=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList495=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList495.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List theItempackages175 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof List) theItempackages175=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages").getClass().isArray()) theItempackages175=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
}
theItempackages175.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource73 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource73=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource73=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource73.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e5){
logger.error(e5.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources682 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources682=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources682=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources682.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources327 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources327=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources327=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources327.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else {
List theItemresources93 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources93=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources93=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources93.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}