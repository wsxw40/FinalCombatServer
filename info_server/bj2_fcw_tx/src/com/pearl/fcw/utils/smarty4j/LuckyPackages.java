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
List random689 = new ArrayList<>();
if (null!=context.get("random")){
if (context.get("random") instanceof List) random689=(List<?>)context.get("random");
else if (context.get("random") instanceof int[]) random689=Stream.of((int[])context.get("random")).collect(Collectors.toList());
else if (context.get("random") instanceof long[]) random689=Stream.of((long[])context.get("random")).collect(Collectors.toList());
else if (context.get("random") instanceof float[]) random689=Stream.of((float[])context.get("random")).collect(Collectors.toList());
else if (context.get("random") instanceof double[]) random689=Stream.of((double[])context.get("random")).collect(Collectors.toList());
else if (context.get("random") instanceof byte[]) random689=Stream.of((byte[])context.get("random")).collect(Collectors.toList());
else if (context.get("random") instanceof String[]) random689=Stream.of((String[])context.get("random")).collect(Collectors.toList());
else if (context.get("random").getClass().isArray()) random689=Stream.of(context.get("random")).collect(Collectors.toList());
}
random689.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar8264=new Context();
includeContextVar8264.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"useType"));
includeContextVar8264.set("unit",O2oUtil.getSmarty4jProperty(theItem,"number"));
sb.append(new Timelimit().get(includeContextVar8264));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList244 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList244=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList244=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList244=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList244=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList244=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList244=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList244=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList244=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList244.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.showDamage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.showShootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList820 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList820=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList820=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList820=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList820=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList820=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList820=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList820=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList820=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList820.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("			suitId=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros683 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros683=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) theItemsysItembelongSuitallSpec4Pros683=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros683=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros683.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
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
List theItemsysItembelongSuitallSpec6Pros853 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros853=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) theItemsysItembelongSuitallSpec6Pros853=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros853=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros853.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
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
List theItemsysItempackages363 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages363=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages363=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages363=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages363=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages363=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages363=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages363=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages363=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages363.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource287 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource287=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource287=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource287=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource287=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource287=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource287=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource287=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource287=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource287.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources732 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources732=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources732=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources732=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources732=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources732=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources732=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources732=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources732=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources732.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources112 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources112=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources112=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources112=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources112=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources112=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources112=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources112=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources112=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources112.forEach(resource->{
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
List theItemsysItemresources115 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources115=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources115=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources115=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources115=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources115=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources115=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources115=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources115=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources115.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"number"));
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