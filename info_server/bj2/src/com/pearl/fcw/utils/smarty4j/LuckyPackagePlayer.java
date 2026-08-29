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

public class LuckyPackagePlayer implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
sb.append("	");
sb.append(context.get("randoms"));
sb.append(" ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("fix={ ");
sb.append("\r\n");
List fix635 = new ArrayList<>();
if (null!=context.get("fix")){
if (context.get("fix") instanceof List) fix635=(List<?>)context.get("fix");
else if (context.get("fix").getClass().isArray()) fix635=Stream.of((Object[])context.get("fix")).collect(Collectors.toList());
}
fix635.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		quantity = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.id"));
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
sb.append("		characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList201 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList201=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList201=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList201.forEach(ids->{
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
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
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
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={{ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList542 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList542=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList542=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList542.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
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
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("random={ ");
sb.append("\r\n");
List multiRandoms245 = new ArrayList<>();
if (null!=context.get("multiRandoms")){
if (context.get("multiRandoms") instanceof List) multiRandoms245=(List<?>)context.get("multiRandoms");
else if (context.get("multiRandoms").getClass().isArray()) multiRandoms245=Stream.of((Object[])context.get("multiRandoms")).collect(Collectors.toList());
}
multiRandoms245.forEach(random->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		quantity=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar9995=new Context();
includeContextVar9995.set("unitType",O2oUtil.getSmarty4jProperty(random,"useType"));
includeContextVar9995.set("unit",O2oUtil.getSmarty4jProperty(random,"number"));
sb.append(new Timelimit().get(includeContextVar9995));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List randomsysItemcharacterList938 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof List) randomsysItemcharacterList938=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList").getClass().isArray()) randomsysItemcharacterList938=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
}
randomsysItemcharacterList938.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceFire_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceBlast_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceBullet_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cResistanceKnife_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cBloodAdd_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.showDamage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.showShootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List randomsysItemgunPropertypropertyList798 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof List) randomsysItemgunPropertypropertyList798=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList").getClass().isArray()) randomsysItemgunPropertypropertyList798=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
randomsysItemgunPropertypropertyList798.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.gunProperty.color"));
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("			suitId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={ ");
sb.append("\r\n");
List randomsysItembelongSuitallSpec4Pros351 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof List) randomsysItembelongSuitallSpec4Pros351=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) randomsysItembelongSuitallSpec4Pros351=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
randomsysItembelongSuitallSpec4Pros351.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				des6={ ");
sb.append("\r\n");
List randomsysItembelongSuitallSpec6Pros126 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof List) randomsysItembelongSuitallSpec6Pros126=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) randomsysItembelongSuitallSpec6Pros126=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
randomsysItembelongSuitallSpec6Pros126.forEach(property->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
}
sb.append("		package = { ");
sb.append("\r\n");
List randomsysItempackages392 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof List) randomsysItempackages392=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages").getClass().isArray()) randomsysItempackages392=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
}
randomsysItempackages392.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List randomsysItemresource735 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof List) randomsysItemresource735=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource").getClass().isArray()) randomsysItemresource735=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
}
randomsysItemresource735.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e11){
logger.error(e11.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List randomsysItemresources907 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources907=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources907=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources907.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(random,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List randomsysItemresources952 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources952=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources952=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources952.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
} else {
List randomsysItemresources266 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources266=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources266=Stream.of((Object[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources266.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("onlineAward={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("onlineAward"),"!=",null)){
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List onlineAwardsysItemcharacterList219 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList219=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList219=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList219.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List onlineAwardsysItemgunPropertypropertyList956 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList956=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList956=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList956.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.gunProperty.color"));
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List onlineAwardsysItempackages157 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof List) onlineAwardsysItempackages157=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages157=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages157.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List onlineAwardsysItemgpPricesList73 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList73=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList73=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList73.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List onlineAwardsysItemcrPricesList77 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList77=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList77=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList77.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List onlineAwardsysItemvoucherPricesList38 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList38=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList38=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList38.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource934 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof List) onlineAwardsysItemresource934=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource934=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource934.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e21){
logger.error(e21.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources190 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources190=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources190=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources190.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("onlineAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources81 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources81=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources81=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources81.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
} else {
List onlineAwardsysItemresources681 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources681=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources681=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources681.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("erroMsg"),"!=",null))){
sb.append("	error=\"");
sb.append(context.get("erroMsg"));
sb.append("\"  ");
sb.append("\r\n");
} else {
sb.append("	error={}  ");
sb.append("\r\n");
}
sb.append("indexs={ ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("indexs"),"!=",null))){
sb.append("		");
sb.append(context.get("indexs"));
sb.append(" ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
sb.append("flags ={ ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("flags"),"!=",null))){
sb.append("		");
sb.append(context.get("flags"));
sb.append(" ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}