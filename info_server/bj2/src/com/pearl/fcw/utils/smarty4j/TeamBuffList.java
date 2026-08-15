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
List list667 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list667=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list667=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list667.forEach(teamBuffView->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		teambuffid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysTeamBuff.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iBuffId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.iBuffId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iValue=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		status = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("		payType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysTeamBuff.payType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysTeamBuff.cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBuff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isBuff"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar2541=new Context();
includeContextVar2541.set("unitType",O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.unitType"));
includeContextVar2541.set("unit",O2oUtil.getSmarty4jProperty(teamBuffView,"sysTeamBuff.unit"));
sb.append(new Timelimit().get(includeContextVar2541));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List teamBuffViewsysItemcharacterList947 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList") instanceof List) teamBuffViewsysItemcharacterList947=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList").getClass().isArray()) teamBuffViewsysItemcharacterList947=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.characterList")).collect(Collectors.toList());
}
teamBuffViewsysItemcharacterList947.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"expireMinutesLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List teamBuffViewsysItemgunPropertypropertyList543 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList") instanceof List) teamBuffViewsysItemgunPropertypropertyList543=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList").getClass().isArray()) teamBuffViewsysItemgunPropertypropertyList543=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
teamBuffViewsysItemgunPropertypropertyList543.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("	    			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
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
List teamBuffViewsysItempackages711 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages") instanceof List) teamBuffViewsysItempackages711=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages").getClass().isArray()) teamBuffViewsysItempackages711=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.packages")).collect(Collectors.toList());
}
teamBuffViewsysItempackages711.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List teamBuffViewsysItemresource812 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource") instanceof List) teamBuffViewsysItemresource812=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource").getClass().isArray()) teamBuffViewsysItemresource812=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resource")).collect(Collectors.toList());
}
teamBuffViewsysItemresource812.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List teamBuffViewsysItemresources100 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources100=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources100=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources100.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List teamBuffViewsysItemresources511 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources511=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources511=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources511.forEach(resource->{
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
List teamBuffViewsysItemresources602 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources") instanceof List) teamBuffViewsysItemresources602=(List<?>)O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources").getClass().isArray()) teamBuffViewsysItemresources602=Stream.of((Object[])O2oUtil.getSmarty4jProperty(teamBuffView,"sysItem.resources")).collect(Collectors.toList());
}
teamBuffViewsysItemresources602.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(teamBuffView,"sysTeamBuff.unit"));
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