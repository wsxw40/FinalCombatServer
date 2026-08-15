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

public class PlayerPack implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("enable=1 ");
sb.append("\r\n");
sb.append("player={ ");
sb.append("\r\n");
sb.append("	name= \"");
sb.append(context.get("name"));
sb.append("\",matchRank=");
sb.append(context.get("matchRank"));
sb.append(", ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("fightnum=");
sb.append(context.get("fightnum"));
sb.append(" ");
sb.append("\r\n");
sb.append("items=	{ ");
sb.append("\r\n");
sb.append("	resourcename=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	avatar={ ");
sb.append("\r\n");
List charactersysCharactercostume811 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof List) charactersysCharactercostume811=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof int[]) charactersysCharactercostume811=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof long[]) charactersysCharactercostume811=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof float[]) charactersysCharactercostume811=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof double[]) charactersysCharactercostume811=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof byte[]) charactersysCharactercostume811=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof String[]) charactersysCharactercostume811=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume").getClass().isArray()) charactersysCharactercostume811=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
}
charactersysCharactercostume811.forEach(res->{
try{
if( (O2oUtil.compare(res,"!=",null))){
sb.append("				\"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}
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
List characterweaponList846 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof List) characterweaponList846=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof int[]) characterweaponList846=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof long[]) characterweaponList846=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof float[]) characterweaponList846=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof double[]) characterweaponList846=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof byte[]) characterweaponList846=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof String[]) characterweaponList846=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList").getClass().isArray()) characterweaponList846=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
}
characterweaponList846.forEach(theItem->{
try{
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("			{ ");
sb.append("\r\n");
sb.append("				playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("				sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList900 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList900=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList900=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList900=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList900=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList900=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList900=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList900=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList900=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList900.forEach(ids->{
try{
sb.append("						");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("					baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("				strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("				repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isDefault =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("						0 , ");
sb.append("\r\n");
} else {
sb.append("						1 , ");
sb.append("\r\n");
}
sb.append("				provisional_item_day=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("				provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_flag"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("					wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				common={			 ");
sb.append("\r\n");
sb.append("					isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("					isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",13)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							1,  ");
sb.append("\r\n");
}
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durable"),"<=",0)){
sb.append("								0, ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("						p_suitable = { ");
sb.append("\r\n");
List theItempSuits860 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"pSuits")){
if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof List) theItempSuits860=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"pSuits");
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof int[]) theItempSuits860=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof long[]) theItempSuits860=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof float[]) theItempSuits860=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof double[]) theItempSuits860=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof byte[]) theItempSuits860=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits") instanceof String[]) theItempSuits860=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"pSuits").getClass().isArray()) theItempSuits860=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"pSuits")).collect(Collectors.toList());
}
theItempSuits860.forEach(pSuit->{
try{
sb.append("								\"");
sb.append(pSuit);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("						}, ");
sb.append("\r\n");
sb.append("						minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("								0, ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=1, ");
sb.append("\r\n");
sb.append("						star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("						speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList92 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList92=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList92=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList92=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList92=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList92=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList92=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList92=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList92=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList92.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("						},  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("						suitId=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.suitId"));
sb.append(", 		   ");
sb.append("\r\n");
sb.append("						suitDetail={ ");
sb.append("\r\n");
sb.append("							des4={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros458 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros458=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) theItemsysItembelongSuitallSpec4Pros458=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros458=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros458.forEach(property->{
try{
sb.append("								\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("							}, ");
sb.append("\r\n");
sb.append("							des6={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec6Pros7 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros7=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) theItemsysItembelongSuitallSpec6Pros7=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros7=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros7.forEach(property->{
try{
sb.append("								\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("							}, ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
}
sb.append("					combineDetail = { ");
sb.append("\r\n");
List theItemgunPropertystrPropertyList36 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList36=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList36=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList36=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList36=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList36=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList36=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList36=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList36=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList36.forEach(property->{
try{
sb.append("						{index= ");
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
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("		   			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList566 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList566=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList566=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList566=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList566=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList566=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList566=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList566=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList566=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList566.forEach(pay->{
try{
sb.append("	    					{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList225 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList225=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList225=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList225=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList225=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList225=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList225=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList225=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList225=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList225.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList560 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList560=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList560=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList560=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList560=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList560=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList560=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList560=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList560=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList560.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					parts = { ");
sb.append("\r\n");
List theItemparts657 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts657=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts657=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts657=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts657=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts657=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts657=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts657=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts657=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts657.forEach(part->{
try{
sb.append("						{");
sb.append(O2oUtil.getSmarty4jProperty(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource866 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource866=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource866=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource866=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource866=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource866=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource866=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource866=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource866=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource866.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("									\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e13){
logger.error(e13.toString());
}
});
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("					gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level"),"==",null)){
sb.append("							0,  ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("					gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level_exp"),"==",null)){
sb.append("							0,  ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
} else {
sb.append("				{}, ");
sb.append("\r\n");
}
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	costume={ ");
sb.append("\r\n");
List charactercostumeList340 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof List) charactercostumeList340=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof int[]) charactercostumeList340=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof long[]) charactercostumeList340=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof float[]) charactercostumeList340=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof double[]) charactercostumeList340=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof byte[]) charactercostumeList340=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof String[]) charactercostumeList340=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList").getClass().isArray()) charactercostumeList340=Stream.of(O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
}
charactercostumeList340.forEach(theItem->{
try{
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("				{ ");
sb.append("\r\n");
sb.append("					playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("					isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("					modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList807 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList807=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList807=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList807=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList807=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList807=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList807=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList807=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList807=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList807.forEach(ids->{
try{
sb.append("							");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("					repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("					baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					isDefault =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("							0 , ");
sb.append("\r\n");
} else {
sb.append("							1 , ");
sb.append("\r\n");
}
sb.append("					provisional_item_day=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("					provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"provisional_item_flag"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("						wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					common={ ");
sb.append("\r\n");
sb.append("						isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						durable = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("						quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("						minutes_left = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=1, ");
sb.append("\r\n");
sb.append("						star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					 performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("						speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList860 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList860=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList860=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList860=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList860=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList860=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList860=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList860=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList860=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList860.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("						},  ");
sb.append("\r\n");
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("						  suitId=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("						  suitDetail={ ");
sb.append("\r\n");
sb.append("							des4={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros28 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros28=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof int[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof long[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof float[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof double[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof byte[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof String[]) theItemsysItembelongSuitallSpec4Pros28=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros28=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros28.forEach(property->{
try{
sb.append("									\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("							}, ");
sb.append("\r\n");
sb.append("							des6={ ");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec6Pros356 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros356=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof int[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof long[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof float[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof double[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof byte[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof String[]) theItemsysItembelongSuitallSpec6Pros356=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros356=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros356.forEach(property->{
try{
sb.append("									\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("							}, ");
sb.append("\r\n");
sb.append("						}, ");
sb.append("\r\n");
}
sb.append("					combineDetail = { ");
sb.append("\r\n");
List theItemgunPropertystrPropertyList657 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList657=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList657=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList657=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList657=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList657=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList657=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList657=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList657=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList657.forEach(property->{
try{
sb.append("						{index= ");
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
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList424 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList424=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList424=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList424=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList424=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList424=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList424=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList424=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList424=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList424.forEach(pay->{
try{
sb.append("						{id=");
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
sb.append("					}, ");
sb.append("\r\n");
sb.append("					crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList40 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList40=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList40=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList40=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList40=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList40=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList40=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList40=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList40=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList40.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList782 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList782=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList782=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList782=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList782=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList782=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList782=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList782=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList782=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList782.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					 parts = { ");
sb.append("\r\n");
List theItemparts804 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts804=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts804=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts804=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts804=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts804=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts804=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts804=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts804=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts804.forEach(part->{
try{
sb.append("						{");
sb.append(O2oUtil.getSmarty4jProperty(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresources349 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources349=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources349=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources349=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources349=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources349=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources349=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources349=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources349=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources349.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("									\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresources781 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources781=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources781=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources781=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources781=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources781=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources781=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources781=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources781=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources781.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("									\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e25){
logger.error(e25.toString());
}
});
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("					gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level"),"==",null)){
sb.append("							0,  ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("					gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_level_exp"),"==",null)){
sb.append("							0,  ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				}, ");
sb.append("\r\n");
} else {
sb.append("				{},  ");
sb.append("\r\n");
}
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
return sb.toString();
}

}