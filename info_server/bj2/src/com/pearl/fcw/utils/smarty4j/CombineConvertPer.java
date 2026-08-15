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

public class CombineConvertPer implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rate=");
sb.append(context.get("rate"));
sb.append(" ");
sb.append("\r\n");
sb.append("from_rate=");
sb.append(context.get("rateFrom"));
sb.append(" ");
sb.append("\r\n");
sb.append("to_rate=");
sb.append(context.get("rateTo"));
sb.append(" ");
sb.append("\r\n");
sb.append("from=  ");
sb.append("{ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("fromItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List fromItemsysItemcharacterList912 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof List) fromItemsysItemcharacterList912=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList").getClass().isArray()) fromItemsysItemcharacterList912=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
}
fromItemsysItemcharacterList912.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List fromItemgunPropertypropertyList460 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof List) fromItemgunPropertypropertyList460=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList").getClass().isArray()) fromItemgunPropertypropertyList460=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
fromItemgunPropertypropertyList460.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("				\"");
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
List fromItemgunPropertystrPropertyList359 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof List) fromItemgunPropertystrPropertyList359=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList").getClass().isArray()) fromItemgunPropertystrPropertyList359=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
fromItemgunPropertystrPropertyList359.forEach(property->{
try{
sb.append("			{index= ");
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
List fromItemparts641 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof List) fromItemparts641=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts").getClass().isArray()) fromItemparts641=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
}
fromItemparts641.forEach(part->{
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
List fromItemsysItemgpPricesList930 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof List) fromItemsysItemgpPricesList930=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList").getClass().isArray()) fromItemsysItemgpPricesList930=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
fromItemsysItemgpPricesList930.forEach(pay->{
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
sb.append("		 crprices={ ");
sb.append("\r\n");
List fromItemsysItemcrPricesList684 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof List) fromItemsysItemcrPricesList684=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList").getClass().isArray()) fromItemsysItemcrPricesList684=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
fromItemsysItemcrPricesList684.forEach(pay->{
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
List fromItemsysItemvoucherPricesList238 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof List) fromItemsysItemvoucherPricesList238=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList").getClass().isArray()) fromItemsysItemvoucherPricesList238=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
fromItemsysItemvoucherPricesList238.forEach(pay->{
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
List fromItempackages95 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof List) fromItempackages95=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages").getClass().isArray()) fromItempackages95=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
}
fromItempackages95.forEach(item->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List fromItemresource13 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof List) fromItemresource13=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource").getClass().isArray()) fromItemresource13=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
}
fromItemresource13.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List fromItemresources927 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources927=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources927=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources927.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("fromItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List fromItemresources117 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources117=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources117=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources117.forEach(resource->{
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
List fromItemresources473 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources473=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources473=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources473.forEach(resource->{
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
}
sb.append("} ");
sb.append("\r\n");
sb.append("to=  ");
sb.append("{ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("toItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List toItemsysItemcharacterList234 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof List) toItemsysItemcharacterList234=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList").getClass().isArray()) toItemsysItemcharacterList234=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
}
toItemsysItemcharacterList234.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"),"==",13)){
sb.append("					0 , ");
sb.append("\r\n");
} else {
sb.append("					1 , ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List toItemgunPropertypropertyList901 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof List) toItemgunPropertypropertyList901=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList").getClass().isArray()) toItemgunPropertypropertyList901=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
toItemgunPropertypropertyList901.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("			},  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		combineDetail = { ");
sb.append("\r\n");
List toItemgunPropertystrPropertyList524 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof List) toItemgunPropertystrPropertyList524=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList").getClass().isArray()) toItemgunPropertystrPropertyList524=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
toItemgunPropertystrPropertyList524.forEach(property->{
try{
sb.append("			{index= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\"},  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		 parts = { ");
sb.append("\r\n");
List toItemparts769 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof List) toItemparts769=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts").getClass().isArray()) toItemparts769=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
}
toItemparts769.forEach(part->{
try{
sb.append("			 {");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("		 }, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List toItemsysItemgpPricesList911 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof List) toItemsysItemgpPricesList911=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList").getClass().isArray()) toItemsysItemgpPricesList911=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
toItemsysItemgpPricesList911.forEach(pay->{
try{
sb.append("			{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List toItemsysItemcrPricesList258 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof List) toItemsysItemcrPricesList258=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList").getClass().isArray()) toItemsysItemcrPricesList258=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
toItemsysItemcrPricesList258.forEach(pay->{
try{
sb.append("			{id=");
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
sb.append("		voucherprices={ ");
sb.append("\r\n");
List toItemsysItemvoucherPricesList384 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof List) toItemsysItemvoucherPricesList384=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList").getClass().isArray()) toItemsysItemvoucherPricesList384=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
toItemsysItemvoucherPricesList384.forEach(pay->{
try{
sb.append("		{id=");
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
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		 package = { ");
sb.append("\r\n");
List toItempackages10 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof List) toItempackages10=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages").getClass().isArray()) toItempackages10=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
}
toItempackages10.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("		 }, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List toItemresource506 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof List) toItemresource506=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource").getClass().isArray()) toItemresource506=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
}
toItemresource506.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources822 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources822=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources822=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources822.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources74 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources74=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources74=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources74.forEach(resource->{
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
List toItemresources315 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources315=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources315=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources315.forEach(resource->{
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
return sb.toString();
}

}