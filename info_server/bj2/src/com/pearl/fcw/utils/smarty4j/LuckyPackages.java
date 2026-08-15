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

public class LuckyPackages implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("pageCount = ");
sb.append(context.get("pageCount"));
sb.append(" ");
sb.append("\r\n");
sb.append("pageNo = ");
sb.append(context.get("pageNo"));
sb.append(" ");
sb.append("\r\n");
sb.append("random = { ");
sb.append("\r\n");
List random419 = new ArrayList<>();
if (null!=context.get("random")){
if (context.get("random") instanceof List) random419=(List<?>)context.get("random");
else if (context.get("random").getClass().isArray()) random419=Stream.of((Object[])context.get("random")).collect(Collectors.toList());
}
random419.forEach(theItem->{
try{
sb.append("	{ ");
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
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar1822=new Context();
includeContextVar1822.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"useType"));
includeContextVar1822.set("unit",O2oUtil.getSmarty4jProperty(theItem,"number"));
sb.append(new Timelimit().get(includeContextVar1822));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
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
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList528 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList528=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList528=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList528.forEach(ids->{
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
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd_add"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.showDamage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.showShootSpeed"));
sb.append(",	 ");
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
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList922 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList922=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList922=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList922.forEach(property->{
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("			suitId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros10 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros10=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros10=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros10.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				des6={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec6Pros817 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros817=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros817=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros817.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
}
sb.append("		package = { ");
sb.append("\r\n");
List theItemsysItempackages618 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages618=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages618=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages618.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource773 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource773=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource773=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource773.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources370 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources370=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources370=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources370.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources888 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources888=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources888=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources888.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else {
List theItemsysItemresources449 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources449=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources449=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources449.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("		quantity=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}