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

public class MissionItemAwardEntity implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{    ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar1732=new Context();
includeContextVar1732.set("unitType",context.get("unitType"));
includeContextVar1732.set("unit",context.get("unit"));
sb.append(new Timelimit().get(includeContextVar1732));
sb.append("	isBind=\"N\", ");
sb.append("\r\n");
sb.append("	isVip=");
sb.append(context.get("isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",1))){
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("unitType"),"==",1)){
sb.append("				quantity = ");
sb.append(context.get("unit"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				quantity = 1, ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList648 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList648=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof int[]) sysItemgunPropertypropertyList648=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof long[]) sysItemgunPropertypropertyList648=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof float[]) sysItemgunPropertypropertyList648=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof double[]) sysItemgunPropertypropertyList648=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof byte[]) sysItemgunPropertypropertyList648=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList") instanceof String[]) sysItemgunPropertypropertyList648=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList648=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList648.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunProperty.color"));
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
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("    		}, ");
sb.append("\r\n");
} else if ( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",2) || O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"),"==",3))){
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("unitType"),"==",1)){
sb.append("				quantity = ");
sb.append(context.get("unit"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				quantity = 1, ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
} else {
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("unitType"),"==",1)){
sb.append("				quantity = ");
sb.append(context.get("unit"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				quantity = 1, ");
sb.append("\r\n");
}
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}