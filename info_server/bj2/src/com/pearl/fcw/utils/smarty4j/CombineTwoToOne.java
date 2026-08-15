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

public class CombineTwoToOne implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("mainItem= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("mainItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List mainItemsysItemcharacterList100 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof List) mainItemsysItemcharacterList100=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList").getClass().isArray()) mainItemsysItemcharacterList100=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
}
mainItemsysItemcharacterList100.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"fightNum"));
sb.append(",       		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1,  ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"level"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List mainItemgunPropertypropertyList397 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof List) mainItemgunPropertypropertyList397=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList").getClass().isArray()) mainItemgunPropertypropertyList397=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
mainItemgunPropertypropertyList397.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("		    		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("			},  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		combineDetail = { ");
sb.append("\r\n");
List mainItemgunPropertystrPropertyList396 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof List) mainItemgunPropertystrPropertyList396=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList").getClass().isArray()) mainItemgunPropertystrPropertyList396=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
mainItemgunPropertystrPropertyList396.forEach(property->{
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
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List mainItemparts37 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof List) mainItemparts37=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts").getClass().isArray()) mainItemparts37=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
}
mainItemparts37.forEach(part->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List mainItemsysItemgpPricesList547 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof List) mainItemsysItemgpPricesList547=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList").getClass().isArray()) mainItemsysItemgpPricesList547=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
mainItemsysItemgpPricesList547.forEach(pay->{
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
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List mainItemsysItemcrPricesList566 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof List) mainItemsysItemcrPricesList566=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList").getClass().isArray()) mainItemsysItemcrPricesList566=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
mainItemsysItemcrPricesList566.forEach(pay->{
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
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List mainItemsysItemvoucherPricesList857 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof List) mainItemsysItemvoucherPricesList857=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList").getClass().isArray()) mainItemsysItemvoucherPricesList857=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
mainItemsysItemvoucherPricesList857.forEach(pay->{
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List mainItempackages425 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof List) mainItempackages425=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages").getClass().isArray()) mainItempackages425=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
}
mainItempackages425.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List mainItemresource634 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof List) mainItemresource634=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource").getClass().isArray()) mainItemresource634=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
}
mainItemresource634.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List mainItemresources359 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources359=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources359=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources359.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List mainItemresources231 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources231=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources231=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources231.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
} else {
List mainItemresources750 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources750=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources750=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources750.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mainItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
}
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}