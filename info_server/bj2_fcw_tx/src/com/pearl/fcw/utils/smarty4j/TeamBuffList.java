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

public class TeamBuffList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("num = ");
sb.append(context.get("num"));
sb.append(" ");
sb.append("\r\n");
sb.append("contribution = ");
sb.append(context.get("contribution"));
sb.append(" ");
sb.append("\r\n");
sb.append("contributionLimit = ");
sb.append(context.get("contributionLimit"));
sb.append("	 ");
sb.append("\r\n");
sb.append("items = { ");
sb.append("\r\n");
List list64 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list64=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list64=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list64=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list64=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list64=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list64=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list64=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list64=Stream.of(context.get("list")).collect(Collectors.toList());
}
list64.forEach(teamBuffView->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		teambuffid=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iBuffId=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.iBuffId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iValue=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		status = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("		payType = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.payType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cost = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBuff = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isBuff"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar4592=new Context();
includeContextVar4592.set("unitType",O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.unitType"));
includeContextVar4592.set("unit",O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.unit"));
sb.append(new Timelimit().get(includeContextVar4592));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List teamBuffViewsysItemcharacterList389 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof List) teamBuffViewsysItemcharacterList389=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof int[]) teamBuffViewsysItemcharacterList389=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof long[]) teamBuffViewsysItemcharacterList389=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof float[]) teamBuffViewsysItemcharacterList389=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof double[]) teamBuffViewsysItemcharacterList389=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof byte[]) teamBuffViewsysItemcharacterList389=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof String[]) teamBuffViewsysItemcharacterList389=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList").getClass().isArray()) teamBuffViewsysItemcharacterList389=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
}
teamBuffViewsysItemcharacterList389.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"expireMinutesLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List teamBuffViewsysItemgunPropertypropertyList992 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof List) teamBuffViewsysItemgunPropertypropertyList992=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof int[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof long[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof float[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof double[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof byte[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof String[]) teamBuffViewsysItemgunPropertypropertyList992=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList").getClass().isArray()) teamBuffViewsysItemgunPropertypropertyList992=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
teamBuffViewsysItemgunPropertypropertyList992.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("	    			\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("			},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List teamBuffViewsysItempackages643 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof List) teamBuffViewsysItempackages643=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof int[]) teamBuffViewsysItempackages643=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof long[]) teamBuffViewsysItempackages643=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof float[]) teamBuffViewsysItempackages643=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof double[]) teamBuffViewsysItempackages643=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof byte[]) teamBuffViewsysItempackages643=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof String[]) teamBuffViewsysItempackages643=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages").getClass().isArray()) teamBuffViewsysItempackages643=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
}
teamBuffViewsysItempackages643.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"),"==",1)){
sb.append("		    		type=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List teamBuffViewsysItemresource669 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof List) teamBuffViewsysItemresource669=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof int[]) teamBuffViewsysItemresource669=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof long[]) teamBuffViewsysItemresource669=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof float[]) teamBuffViewsysItemresource669=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof double[]) teamBuffViewsysItemresource669=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof byte[]) teamBuffViewsysItemresource669=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof String[]) teamBuffViewsysItemresource669=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource").getClass().isArray()) teamBuffViewsysItemresource669=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
}
teamBuffViewsysItemresource669.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e6){
logger.error(e6.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"),"==",2)){
sb.append("		    		type=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List teamBuffViewsysItemresources223 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources223=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof int[]) teamBuffViewsysItemresources223=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof long[]) teamBuffViewsysItemresources223=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof float[]) teamBuffViewsysItemresources223=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof double[]) teamBuffViewsysItemresources223=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof byte[]) teamBuffViewsysItemresources223=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof String[]) teamBuffViewsysItemresources223=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources223=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources223.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"),"==",3)){
sb.append("		    		type=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List teamBuffViewsysItemresources195 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources195=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof int[]) teamBuffViewsysItemresources195=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof long[]) teamBuffViewsysItemresources195=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof float[]) teamBuffViewsysItemresources195=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof double[]) teamBuffViewsysItemresources195=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof byte[]) teamBuffViewsysItemresources195=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof String[]) teamBuffViewsysItemresources195=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources195=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources195.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else {
List teamBuffViewsysItemresources427 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources427=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof int[]) teamBuffViewsysItemresources427=Stream.of((int[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof long[]) teamBuffViewsysItemresources427=Stream.of((long[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof float[]) teamBuffViewsysItemresources427=Stream.of((float[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof double[]) teamBuffViewsysItemresources427=Stream.of((double[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof byte[]) teamBuffViewsysItemresources427=Stream.of((byte[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof String[]) teamBuffViewsysItemresources427=Stream.of((String[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources427=Stream.of(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources427.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("		quantity=");
sb.append(O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}