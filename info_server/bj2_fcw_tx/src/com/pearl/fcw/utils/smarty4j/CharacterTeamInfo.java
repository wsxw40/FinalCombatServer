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

public class CharacterTeamInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("player={ ");
sb.append("\r\n");
sb.append("	id = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"id"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name= \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	rank = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isvip = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	rankintop = ");
sb.append(context.get("rankInTop"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("team ={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("team"),"!=",null)){
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("team"),"name"));
sb.append("\", ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
sb.append("items={ ");
sb.append("\r\n");
sb.append("	enable=1, ");
sb.append("\r\n");
sb.append("	fightnum=");
sb.append(context.get("fightnum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	resourcename=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	avatar={");
sb.append("\r\n");
List charactersysCharactercostume734 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof List) charactersysCharactercostume734=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof int[]) charactersysCharactercostume734=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof long[]) charactersysCharactercostume734=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof float[]) charactersysCharactercostume734=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof double[]) charactersysCharactercostume734=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof byte[]) charactersysCharactercostume734=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof String[]) charactersysCharactercostume734=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume").getClass().isArray()) charactersysCharactercostume734=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
}
charactersysCharactercostume734.forEach(res->{
try{
if( (O2oUtil.compare(res,"!=",null))){
}
sb.append("\"");
sb.append(res);
sb.append("\",");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	putSuitId=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"suitId"));
sb.append(", ");
sb.append("\r\n");
sb.append("	suitCount=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"suitNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	weapons={ ");
sb.append("\r\n");
List characterweaponList337 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof List) characterweaponList337=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof int[]) characterweaponList337=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof long[]) characterweaponList337=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof float[]) characterweaponList337=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof double[]) characterweaponList337=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof byte[]) characterweaponList337=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof String[]) characterweaponList337=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList").getClass().isArray()) characterweaponList337=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
}
characterweaponList337.forEach(theItem->{
try{
sb.append("		 ");
sb.append("\r\n");
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("			{ ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={");
sb.append("\r\n");
List theItemsysItemcharacterList540 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList540=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList540=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList540=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList540=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList540=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList540=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList540=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList540=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList540.forEach(ids->{
try{
sb.append(" ");
sb.append(ids);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("			strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isDefault = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("0,");
sb.append("\r\n");
} else {
sb.append("1,");
sb.append("\r\n");
}
sb.append("			provisional_item_day=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("			provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_flag"));
sb.append(",	 ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
}
sb.append(" wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("			isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality=");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",13)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" 1, ");
sb.append("\r\n");
}
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				 ");
sb.append("\r\n");
sb.append("				durable = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durable"),"<=",0)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				p_suitable = {");
sb.append("\r\n");
List theItemsysItempSuits77 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof List) theItemsysItempSuits77=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof int[]) theItemsysItempSuits77=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof long[]) theItemsysItempSuits77=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof float[]) theItemsysItempSuits77=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof double[]) theItemsysItempSuits77=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof byte[]) theItemsysItempSuits77=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof String[]) theItemsysItempSuits77=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits").getClass().isArray()) theItemsysItempSuits77=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
}
theItemsysItempSuits77.forEach(pSuit->{
try{
sb.append(" \"");
sb.append(pSuit);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("				minutes_left = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("		    performance = { ");
sb.append("\r\n");
sb.append("		    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			    	damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		      color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList362 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList362=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList362=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList362=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList362=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList362=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList362=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList362=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList362=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList362.forEach(property->{
try{
sb.append(" { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(",");
sb.append("\r\n");
}
sb.append(" 1,");
sb.append("\r\n");
sb.append("	    	\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("	    }, ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("	     combineDetail = { ");
sb.append("\r\n");
List theItemgunPropertystrPropertyList403 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList403=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList403=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList403=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList403=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList403=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList403=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList403=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList403=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList403.forEach(property->{
try{
sb.append("	    		{index= ");
sb.append(O2oUtil.getSmarty4jProperty(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jProperty(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jProperty(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jProperty(property,"open"));
sb.append("},  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	    }, ");
sb.append("\r\n");
sb.append("		 ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("		  suitId=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		  suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros797 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros797=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) theItemsysItembelongSuitallSpec4Pros797=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros797=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros797.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				des6={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec6Pros141 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros141=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) theItemsysItembelongSuitallSpec6Pros141=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros141=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros141.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("		  }, ");
sb.append("\r\n");
}
sb.append("		   	gpprices={");
sb.append("\r\n");
List theItemsysItemgpPricesList906 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList906=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList906=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList906=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList906=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList906=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList906=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList906=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList906=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList906.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	    	crprices={");
sb.append("\r\n");
List theItemsysItemcrPricesList49 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList49=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList49=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList49=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList49=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList49=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList49=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList49=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList49=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList49.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	    	voucherprices={");
sb.append("\r\n");
List theItemsysItemvoucherPricesList95 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList95=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList95=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList95=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList95=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList95=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList95=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList95=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList95=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList95.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("		    parts = {");
sb.append("\r\n");
List theItemparts486 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts486=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts486=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts486=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts486=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts486=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts486=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts486=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts486=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts486.forEach(part->{
try{
sb.append(" {");
sb.append(O2oUtil.getSmarty4jProperty(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
List theItemresource280 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource280=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource280=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource280=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource280=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource280=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource280=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource280=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource280=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource280.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
if((O2oUtil.compare(res,"!=",""))){
}
sb.append(" \"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
}
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		gstLevel =");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		gstExp=");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("		    }, ");
sb.append("\r\n");
} else {
sb.append("		 {}, ");
sb.append("\r\n");
}
sb.append("		  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	costume={ ");
sb.append("\r\n");
List charactercostumeList138 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof List) charactercostumeList138=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof int[]) charactercostumeList138=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof long[]) charactercostumeList138=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof float[]) charactercostumeList138=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof double[]) charactercostumeList138=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof byte[]) charactercostumeList138=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof String[]) charactercostumeList138=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList").getClass().isArray()) charactercostumeList138=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
}
charactercostumeList138.forEach(theItem->{
try{
sb.append("		 ");
sb.append("\r\n");
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("		{ ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			currency=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.currency"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={");
sb.append("\r\n");
List theItemsysItemcharacterList657 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList657=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList657=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList657=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList657=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList657=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList657=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList657=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList657=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList657.forEach(ids->{
try{
sb.append(" ");
sb.append(ids);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("			strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isDefault = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("0,");
sb.append("\r\n");
} else {
sb.append("1,");
sb.append("\r\n");
}
sb.append("			provisional_item_day=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("			provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_flag"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("			isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
}
sb.append(" wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				durable = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("		    performance = { ");
sb.append("\r\n");
sb.append("		    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			    	damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		    color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		    gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList713 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList713=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList713=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList713=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList713=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList713=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList713=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList713=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList713=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList713.forEach(property->{
try{
sb.append(" { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(",");
sb.append("\r\n");
}
sb.append(" 1,");
sb.append("\r\n");
sb.append("	    	\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("	    }, ");
sb.append("\r\n");
sb.append("		    combineDetail = { ");
sb.append("\r\n");
List theItemgunPropertystrPropertyList686 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList686=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList686=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList686=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList686=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList686=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList686=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList686=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList686=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList686.forEach(property->{
try{
sb.append("	    		{index= ");
sb.append(O2oUtil.getSmarty4jProperty(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jProperty(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jProperty(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jProperty(property,"open"));
sb.append("},  ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("	    	}, ");
sb.append("\r\n");
sb.append("	 ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("		  suitId=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		  suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros23 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros23=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) theItemsysItembelongSuitallSpec4Pros23=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros23=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros23.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				des6={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec6Pros664 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros664=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) theItemsysItembelongSuitallSpec6Pros664=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros664=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros664.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("		  }, ");
sb.append("\r\n");
}
sb.append("		   	gpprices={");
sb.append("\r\n");
List theItemsysItemgpPricesList856 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList856=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList856=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList856=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList856=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList856=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList856=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList856=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList856=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList856.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	    	crprices={");
sb.append("\r\n");
List theItemsysItemcrPricesList761 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList761=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList761=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList761=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList761=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList761=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList761=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList761=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList761=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList761.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	    	voucherprices={");
sb.append("\r\n");
List theItemsysItemvoucherPricesList983 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList983=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList983=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList983=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList983=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList983=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList983=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList983=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList983=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList983.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("		    parts = {");
sb.append("\r\n");
List theItemparts478 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts478=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts478=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts478=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts478=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts478=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts478=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts478=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts478=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts478.forEach(part->{
try{
sb.append(" {");
sb.append(O2oUtil.getSmarty4jProperty(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
List theItemresources847 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources847=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources847=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources847=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources847=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources847=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources847=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources847=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources847=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources847.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
if((O2oUtil.compare(res,"!=",""))){
}
sb.append(" \"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
List theItemresources428 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources428=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources428=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources428=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources428=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources428=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources428=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources428=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources428=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources428.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
if((O2oUtil.compare(res,"!=",""))){
}
sb.append(" \"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e25){
logger.error(e25.toString());
}
});
}
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		gstLevel =");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		gstExp=");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("		  }, ");
sb.append("\r\n");
} else {
sb.append("		  {},  ");
sb.append("\r\n");
}
sb.append("		 ");
sb.append("\r\n");
}catch(Exception e25){
logger.error(e25.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}