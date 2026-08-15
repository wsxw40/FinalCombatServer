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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar8655=new Context();
includeContextVar8655.set("unitType",context.get("unitType"));
includeContextVar8655.set("unit",context.get("unit"));
sb.append(new Timelimit().get(includeContextVar8655));
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List sysItemcharacterList378 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof List) sysItemcharacterList378=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof int[]) sysItemcharacterList378=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof long[]) sysItemcharacterList378=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof float[]) sysItemcharacterList378=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof double[]) sysItemcharacterList378=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof byte[]) sysItemcharacterList378=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList") instanceof String[]) sysItemcharacterList378=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList").getClass().isArray()) sysItemcharacterList378=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"characterList")).collect(Collectors.toList());
}
sysItemcharacterList378.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_web=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList605 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList605=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof int[]) sysItemgunPropertypropertyList605=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof long[]) sysItemgunPropertypropertyList605=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof float[]) sysItemgunPropertypropertyList605=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof double[]) sysItemgunPropertypropertyList605=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof byte[]) sysItemgunPropertypropertyList605=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof String[]) sysItemgunPropertypropertyList605=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList605=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList605.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List sysItempackages584 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof List) sysItempackages584=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof int[]) sysItempackages584=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof long[]) sysItempackages584=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof float[]) sysItempackages584=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof double[]) sysItempackages584=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof byte[]) sysItempackages584=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages") instanceof String[]) sysItempackages584=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages").getClass().isArray()) sysItempackages584=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packages")).collect(Collectors.toList());
}
sysItempackages584.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource735 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof List) sysItemresource735=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof int[]) sysItemresource735=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof long[]) sysItemresource735=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof float[]) sysItemresource735=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof double[]) sysItemresource735=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof byte[]) sysItemresource735=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource") instanceof String[]) sysItemresource735=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource").getClass().isArray()) sysItemresource735=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource")).collect(Collectors.toList());
}
sysItemresource735.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources114 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources114=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof int[]) sysItemresources114=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof long[]) sysItemresources114=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof float[]) sysItemresources114=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof double[]) sysItemresources114=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof byte[]) sysItemresources114=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof String[]) sysItemresources114=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources114=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources114.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources536 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources536=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof int[]) sysItemresources536=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof long[]) sysItemresources536=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof float[]) sysItemresources536=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof double[]) sysItemresources536=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof byte[]) sysItemresources536=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof String[]) sysItemresources536=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources536=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources536.forEach(resource->{
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
List sysItemresources361 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof List) sysItemresources361=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof int[]) sysItemresources361=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof long[]) sysItemresources361=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof float[]) sysItemresources361=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof double[]) sysItemresources361=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof byte[]) sysItemresources361=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources") instanceof String[]) sysItemresources361=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources").getClass().isArray()) sysItemresources361=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resources")).collect(Collectors.toList());
}
sysItemresources361.forEach(resource->{
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