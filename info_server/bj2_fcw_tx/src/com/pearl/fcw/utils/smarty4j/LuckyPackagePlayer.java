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
List fix513 = new ArrayList<>();
if (null!=context.get("fix")){
if (context.get("fix") instanceof List) fix513=(List<?>)context.get("fix");
else if (context.get("fix") instanceof int[]) fix513=Stream.of((int[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof long[]) fix513=Stream.of((long[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof float[]) fix513=Stream.of((float[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof double[]) fix513=Stream.of((double[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof byte[]) fix513=Stream.of((byte[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof String[]) fix513=Stream.of((String[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix").getClass().isArray()) fix513=Stream.of(context.get("fix")).collect(Collectors.toList());
}
fix513.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		quantity = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"number"));
sb.append(", ");
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
sb.append("		characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList100 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList100=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList100=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList100=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList100=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList100=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList100=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList100=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList100=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList100.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
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
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
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
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={{ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList399 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList399=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList399=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList399=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList399=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList399=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList399=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList399=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList399=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList399.forEach(property->{
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
List multiRandoms863 = new ArrayList<>();
if (null!=context.get("multiRandoms")){
if (context.get("multiRandoms") instanceof List) multiRandoms863=(List<?>)context.get("multiRandoms");
else if (context.get("multiRandoms") instanceof int[]) multiRandoms863=Stream.of((int[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms") instanceof long[]) multiRandoms863=Stream.of((long[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms") instanceof float[]) multiRandoms863=Stream.of((float[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms") instanceof double[]) multiRandoms863=Stream.of((double[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms") instanceof byte[]) multiRandoms863=Stream.of((byte[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms") instanceof String[]) multiRandoms863=Stream.of((String[])context.get("multiRandoms")).collect(Collectors.toList());
else if (context.get("multiRandoms").getClass().isArray()) multiRandoms863=Stream.of(context.get("multiRandoms")).collect(Collectors.toList());
}
multiRandoms863.forEach(random->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		quantity=");
sb.append(O2oUtil.getSmarty4jProperty(random,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar6481=new Context();
includeContextVar6481.set("unitType",O2oUtil.getSmarty4jProperty(random,"useType"));
includeContextVar6481.set("unit",O2oUtil.getSmarty4jProperty(random,"number"));
sb.append(new Timelimit().get(includeContextVar6481));
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List randomsysItemcharacterList641 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof List) randomsysItemcharacterList641=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof int[]) randomsysItemcharacterList641=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof long[]) randomsysItemcharacterList641=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof float[]) randomsysItemcharacterList641=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof double[]) randomsysItemcharacterList641=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof byte[]) randomsysItemcharacterList641=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList") instanceof String[]) randomsysItemcharacterList641=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.characterList").getClass().isArray()) randomsysItemcharacterList641=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.characterList")).collect(Collectors.toList());
}
randomsysItemcharacterList641.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceFire_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceBlast_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceBullet_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cResistanceKnife_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cBloodAdd_add"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.showDamage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.showShootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List randomsysItemgunPropertypropertyList75 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof List) randomsysItemgunPropertypropertyList75=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof int[]) randomsysItemgunPropertypropertyList75=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof long[]) randomsysItemgunPropertypropertyList75=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof float[]) randomsysItemgunPropertypropertyList75=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof double[]) randomsysItemgunPropertypropertyList75=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof byte[]) randomsysItemgunPropertypropertyList75=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList") instanceof String[]) randomsysItemgunPropertypropertyList75=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList").getClass().isArray()) randomsysItemgunPropertypropertyList75=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
randomsysItemgunPropertypropertyList75.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.gunProperty.color"));
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(random,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("			suitId=");
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={ ");
sb.append("\r\n");
List randomsysItembelongSuitallSpec4Pros822 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof List) randomsysItembelongSuitallSpec4Pros822=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) randomsysItembelongSuitallSpec4Pros822=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) randomsysItembelongSuitallSpec4Pros822=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
randomsysItembelongSuitallSpec4Pros822.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
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
List randomsysItembelongSuitallSpec6Pros403 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof List) randomsysItembelongSuitallSpec6Pros403=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) randomsysItembelongSuitallSpec6Pros403=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) randomsysItembelongSuitallSpec6Pros403=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
randomsysItembelongSuitallSpec6Pros403.forEach(property->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
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
List randomsysItempackages649 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof List) randomsysItempackages649=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof int[]) randomsysItempackages649=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof long[]) randomsysItempackages649=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof float[]) randomsysItempackages649=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof double[]) randomsysItempackages649=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof byte[]) randomsysItempackages649=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages") instanceof String[]) randomsysItempackages649=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.packages").getClass().isArray()) randomsysItempackages649=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.packages")).collect(Collectors.toList());
}
randomsysItempackages649.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List randomsysItemresource243 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof List) randomsysItemresource243=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof int[]) randomsysItemresource243=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof long[]) randomsysItemresource243=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof float[]) randomsysItemresource243=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof double[]) randomsysItemresource243=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof byte[]) randomsysItemresource243=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource") instanceof String[]) randomsysItemresource243=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resource").getClass().isArray()) randomsysItemresource243=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.resource")).collect(Collectors.toList());
}
randomsysItemresource243.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List randomsysItemresources162 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources162=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof int[]) randomsysItemresources162=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof long[]) randomsysItemresources162=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof float[]) randomsysItemresources162=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof double[]) randomsysItemresources162=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof byte[]) randomsysItemresources162=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof String[]) randomsysItemresources162=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources162=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources162.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(random,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List randomsysItemresources699 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources699=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof int[]) randomsysItemresources699=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof long[]) randomsysItemresources699=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof float[]) randomsysItemresources699=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof double[]) randomsysItemresources699=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof byte[]) randomsysItemresources699=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof String[]) randomsysItemresources699=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources699=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources699.forEach(resource->{
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
List randomsysItemresources92 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(random,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof List) randomsysItemresources92=(List<?>)O2oUtil.getSmarty4jProperty(random,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof int[]) randomsysItemresources92=Stream.of((int[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof long[]) randomsysItemresources92=Stream.of((long[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof float[]) randomsysItemresources92=Stream.of((float[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof double[]) randomsysItemresources92=Stream.of((double[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof byte[]) randomsysItemresources92=Stream.of((byte[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources") instanceof String[]) randomsysItemresources92=Stream.of((String[])O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(random,"sysItem.resources").getClass().isArray()) randomsysItemresources92=Stream.of(O2oUtil.getSmarty4jProperty(random,"sysItem.resources")).collect(Collectors.toList());
}
randomsysItemresources92.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List onlineAwardsysItemcharacterList332 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList332=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof int[]) onlineAwardsysItemcharacterList332=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof long[]) onlineAwardsysItemcharacterList332=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof float[]) onlineAwardsysItemcharacterList332=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof double[]) onlineAwardsysItemcharacterList332=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof byte[]) onlineAwardsysItemcharacterList332=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList") instanceof String[]) onlineAwardsysItemcharacterList332=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList332=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList332.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List onlineAwardsysItemgunPropertypropertyList370 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList370=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof int[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof long[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof float[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof double[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof byte[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList") instanceof String[]) onlineAwardsysItemgunPropertypropertyList370=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList370=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList370.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gunProperty.color"));
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List onlineAwardsysItempackages708 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof List) onlineAwardsysItempackages708=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof int[]) onlineAwardsysItempackages708=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof long[]) onlineAwardsysItempackages708=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof float[]) onlineAwardsysItempackages708=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof double[]) onlineAwardsysItempackages708=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof byte[]) onlineAwardsysItempackages708=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages") instanceof String[]) onlineAwardsysItempackages708=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages708=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages708.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List onlineAwardsysItemgpPricesList610 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList610=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof int[]) onlineAwardsysItemgpPricesList610=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof long[]) onlineAwardsysItemgpPricesList610=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof float[]) onlineAwardsysItemgpPricesList610=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof double[]) onlineAwardsysItemgpPricesList610=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof byte[]) onlineAwardsysItemgpPricesList610=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList") instanceof String[]) onlineAwardsysItemgpPricesList610=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList610=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList610.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List onlineAwardsysItemcrPricesList845 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList845=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof int[]) onlineAwardsysItemcrPricesList845=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof long[]) onlineAwardsysItemcrPricesList845=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof float[]) onlineAwardsysItemcrPricesList845=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof double[]) onlineAwardsysItemcrPricesList845=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof byte[]) onlineAwardsysItemcrPricesList845=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList") instanceof String[]) onlineAwardsysItemcrPricesList845=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList845=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList845.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List onlineAwardsysItemvoucherPricesList839 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList839=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof int[]) onlineAwardsysItemvoucherPricesList839=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof long[]) onlineAwardsysItemvoucherPricesList839=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof float[]) onlineAwardsysItemvoucherPricesList839=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof double[]) onlineAwardsysItemvoucherPricesList839=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof byte[]) onlineAwardsysItemvoucherPricesList839=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList") instanceof String[]) onlineAwardsysItemvoucherPricesList839=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList839=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList839.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource916 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof List) onlineAwardsysItemresource916=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof int[]) onlineAwardsysItemresource916=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof long[]) onlineAwardsysItemresource916=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof float[]) onlineAwardsysItemresource916=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof double[]) onlineAwardsysItemresource916=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof byte[]) onlineAwardsysItemresource916=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource") instanceof String[]) onlineAwardsysItemresource916=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource916=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource916.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources150 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources150=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof int[]) onlineAwardsysItemresources150=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof long[]) onlineAwardsysItemresources150=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof float[]) onlineAwardsysItemresources150=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof double[]) onlineAwardsysItemresources150=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources150=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof String[]) onlineAwardsysItemresources150=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources150=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources150.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources305 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources305=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof int[]) onlineAwardsysItemresources305=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof long[]) onlineAwardsysItemresources305=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof float[]) onlineAwardsysItemresources305=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof double[]) onlineAwardsysItemresources305=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources305=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof String[]) onlineAwardsysItemresources305=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources305=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources305.forEach(resource->{
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
List onlineAwardsysItemresources503 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof List) onlineAwardsysItemresources503=(List<?>)O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof int[]) onlineAwardsysItemresources503=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof long[]) onlineAwardsysItemresources503=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof float[]) onlineAwardsysItemresources503=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof double[]) onlineAwardsysItemresources503=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources503=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources") instanceof String[]) onlineAwardsysItemresources503=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources503=Stream.of(O2oUtil.getSmarty4jProperty(context.get("onlineAward"),"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources503.forEach(resource->{
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
sb.append("	error=nil  ");
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