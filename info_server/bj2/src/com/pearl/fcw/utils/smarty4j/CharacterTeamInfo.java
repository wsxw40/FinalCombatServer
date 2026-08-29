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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"id"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name= \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	rank = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("	exp = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isvip = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("player"),"isVip"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"name"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("character"),"sysCharacter.resourceName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("character"),"sysCharacter.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	avatar={");
sb.append("\r\n");
List charactersysCharactercostume116 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume") instanceof List) charactersysCharactercostume116=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume").getClass().isArray()) charactersysCharactercostume116=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("character"),"sysCharacter.costume")).collect(Collectors.toList());
}
charactersysCharactercostume116.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("character"),"suitId"));
sb.append(", ");
sb.append("\r\n");
sb.append("	suitCount=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("character"),"suitNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	weapons={ ");
sb.append("\r\n");
List characterweaponList969 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList") instanceof List) characterweaponList969=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList").getClass().isArray()) characterweaponList969=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("character"),"weaponList")).collect(Collectors.toList());
}
characterweaponList969.forEach(theItem->{
try{
sb.append("		 ");
sb.append("\r\n");
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("			{ ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={");
sb.append("\r\n");
List theItemsysItemcharacterList538 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList538=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList538=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList538.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("			strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("			provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"provisional_item_flag"));
sb.append(",	 ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
}
sb.append(" wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("			isAsset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isAsset"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				p_suitable = {");
sb.append("\r\n");
List theItemsysItempSuits99 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits") instanceof List) theItemsysItempSuits99=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits").getClass().isArray()) theItemsysItempSuits99=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.pSuits")).collect(Collectors.toList());
}
theItemsysItempSuits99.forEach(pSuit->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("		    performance = { ");
sb.append("\r\n");
sb.append("		    		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			    	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			    	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		      color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList104 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList104=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList104=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList104.forEach(property->{
try{
sb.append(" { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(",");
sb.append("\r\n");
}
sb.append(" 1,");
sb.append("\r\n");
sb.append("	    	\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
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
List theItemgunPropertystrPropertyList139 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList139=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList139=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList139.forEach(property->{
try{
sb.append("	    		{index= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"open"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		  suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros87 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros87=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros87=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros87.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
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
List theItemsysItembelongSuitallSpec6Pros136 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros136=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros136=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros136.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
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
List theItemsysItemgpPricesList883 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList883=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList883=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList883.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemcrPricesList983 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList983=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList983=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList983.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemvoucherPricesList218 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList218=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList218=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList218.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemparts476 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts476=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts476=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts476.forEach(part->{
try{
sb.append(" {");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
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
List theItemresource974 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource974=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource974=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource974.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gst_level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		gstExp=");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gst_exp"));
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
List charactercostumeList0 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")){
if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList") instanceof List) charactercostumeList0=(List<?>)O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList");
else if (O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList").getClass().isArray()) charactercostumeList0=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("character"),"costumeList")).collect(Collectors.toList());
}
charactercostumeList0.forEach(theItem->{
try{
sb.append("		 ");
sb.append("\r\n");
if( (O2oUtil.compare(theItem,"!=",null))){
sb.append("		{ ");
sb.append("\r\n");
sb.append("			playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			currency=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.currency"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={");
sb.append("\r\n");
List theItemsysItemcharacterList266 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList266=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList266=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList266.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				baseItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("			strengthenItemFightNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"provisional_item_day"));
sb.append(", ");
sb.append("\r\n");
sb.append("			provisional_item_flag=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"provisional_item_flag"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("			isAsset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
}
sb.append(" wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				durable = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 ");
sb.append("\r\n");
sb.append("		    performance = { ");
sb.append("\r\n");
sb.append("		    		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			    	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			    	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		    }, ");
sb.append("\r\n");
sb.append("		    color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		    gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList120 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList120=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList120=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList120.forEach(property->{
try{
sb.append(" { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(",");
sb.append("\r\n");
}
sb.append(" 1,");
sb.append("\r\n");
sb.append("	    	\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
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
List theItemgunPropertystrPropertyList452 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList452=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList452=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList452.forEach(property->{
try{
sb.append("	    		{index= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"open"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		  suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={");
sb.append("\r\n");
List theItemsysItembelongSuitallSpec4Pros328 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros") instanceof List) theItemsysItembelongSuitallSpec4Pros328=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) theItemsysItembelongSuitallSpec4Pros328=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec4Pros328.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
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
List theItemsysItembelongSuitallSpec6Pros152 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros") instanceof List) theItemsysItembelongSuitallSpec6Pros152=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) theItemsysItembelongSuitallSpec6Pros152=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
theItemsysItembelongSuitallSpec6Pros152.forEach(property->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
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
List theItemsysItemgpPricesList689 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList689=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList689=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList689.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemcrPricesList420 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList420=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList420=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList420.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemvoucherPricesList837 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList837=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList837=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList837.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemparts365 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts365=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts365=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts365.forEach(part->{
try{
sb.append(" {");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
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
List theItemresources782 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources782=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources782=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources782.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
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
List theItemresources404 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources404=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources404=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources404.forEach(res->{
try{
sb.append(" type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gst_level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		gstExp=");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gst_exp"),"==",null)){
sb.append(" 0,");
sb.append("\r\n");
} else {
sb.append(" ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gst_exp"));
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