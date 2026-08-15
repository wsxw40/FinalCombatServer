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

public class MeltingOutput implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("item = {    ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar3512=new Context();
includeContextVar3512.set("unitType",context.get("unitType"));
includeContextVar3512.set("unit",context.get("unit"));
sb.append(new Timelimit().get(includeContextVar3512));
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List sysItemcharacterList488 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof List) sysItemcharacterList488=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList").getClass().isArray()) sysItemcharacterList488=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
}
sysItemcharacterList488.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("    		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList206 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList206=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList206=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList206.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
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
List sysItempackages793 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof List) sysItempackages793=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages").getClass().isArray()) sysItempackages793=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
}
sysItempackages793.forEach(item->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource214 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof List) sysItemresource214=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource").getClass().isArray()) sysItemresource214=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
}
sysItemresource214.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources8 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources8=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources8=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources8.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources20 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources20=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources20=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources20.forEach(resource->{
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
List sysItemresources166 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources166=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources166=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources166.forEach(resource->{
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
sb.append("	quantity=");
sb.append(context.get("unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}