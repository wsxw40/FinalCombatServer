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

public class ShootingLook implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("bout_num=");
sb.append(context.get("boutNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_num=");
sb.append(context.get("dartleNum"));
sb.append("    ");
sb.append("\r\n");
sb.append("ammo_num=");
sb.append(context.get("ammoNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("look_num = ");
sb.append(context.get("lookNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("needDartleNum=");
sb.append(context.get("needDartleNum"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("list ={ ");
sb.append("\r\n");
List gifts787 = new ArrayList<>();
if (null!=context.get("gifts")){
if (context.get("gifts") instanceof List) gifts787=(List<?>)context.get("gifts");
else if (context.get("gifts").getClass().isArray()) gifts787=Stream.of((Object[])context.get("gifts")).collect(Collectors.toList());
}
gifts787.forEach(onlineAward->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List onlineAwardsysItemcharacterList240 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList240=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList240=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList240.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List onlineAwardsysItemgunPropertypropertyList444 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList444=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList444=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList444.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.gunProperty.color"));
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List onlineAwardsysItempackages745 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof List) onlineAwardsysItempackages745=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages745=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages745.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List onlineAwardsysItemgpPricesList393 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList393=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList393=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList393.forEach(pay->{
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
List onlineAwardsysItemcrPricesList918 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList918=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList918=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList918.forEach(pay->{
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
List onlineAwardsysItemvoucherPricesList678 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList678=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList678=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList678.forEach(pay->{
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
sb.append("		},		 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource3 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof List) onlineAwardsysItemresource3=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource3=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource3.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources99 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources99=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources99=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources99.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources321 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources321=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources321=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources321.forEach(resource->{
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
List onlineAwardsysItemresources998 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources998=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources998=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources998.forEach(resource->{
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
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_gift =  { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List dartleGiftsysItemcharacterList439 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof List) dartleGiftsysItemcharacterList439=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList").getClass().isArray()) dartleGiftsysItemcharacterList439=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
}
dartleGiftsysItemcharacterList439.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List dartleGiftsysItemgunPropertypropertyList853 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof List) dartleGiftsysItemgunPropertypropertyList853=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) dartleGiftsysItemgunPropertypropertyList853=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
dartleGiftsysItemgunPropertypropertyList853.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List dartleGiftsysItempackages903 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof List) dartleGiftsysItempackages903=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages").getClass().isArray()) dartleGiftsysItempackages903=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
}
dartleGiftsysItempackages903.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List dartleGiftsysItemgpPricesList38 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof List) dartleGiftsysItemgpPricesList38=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList").getClass().isArray()) dartleGiftsysItemgpPricesList38=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemgpPricesList38.forEach(pay->{
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
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List dartleGiftsysItemcrPricesList540 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof List) dartleGiftsysItemcrPricesList540=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList").getClass().isArray()) dartleGiftsysItemcrPricesList540=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemcrPricesList540.forEach(pay->{
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List dartleGiftsysItemvoucherPricesList388 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof List) dartleGiftsysItemvoucherPricesList388=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList").getClass().isArray()) dartleGiftsysItemvoucherPricesList388=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemvoucherPricesList388.forEach(pay->{
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
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("	},	 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List dartleGiftsysItemresource374 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof List) dartleGiftsysItemresource374=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource").getClass().isArray()) dartleGiftsysItemresource374=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
}
dartleGiftsysItemresource374.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e18){
logger.error(e18.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources837 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources837=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources837=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources837.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources459 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources459=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources459=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources459.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
} else {
List dartleGiftsysItemresources365 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources365=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources365=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources365.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_top_list = { ");
sb.append("\r\n");
List dartleTopList527 = new ArrayList<>();
if (null!=context.get("dartleTopList")){
if (context.get("dartleTopList") instanceof List) dartleTopList527=(List<?>)context.get("dartleTopList");
else if (context.get("dartleTopList").getClass().isArray()) dartleTopList527=Stream.of((Object[])context.get("dartleTopList")).collect(Collectors.toList());
}
dartleTopList527.forEach(entry->{
try{
sb.append("		");
sb.append(entry);
sb.append(" ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_awards_list ={ ");
sb.append("\r\n");
List awardsList369 = new ArrayList<>();
if (null!=context.get("awardsList")){
if (context.get("awardsList") instanceof List) awardsList369=(List<?>)context.get("awardsList");
else if (context.get("awardsList").getClass().isArray()) awardsList369=Stream.of((Object[])context.get("awardsList")).collect(Collectors.toList());
}
awardsList369.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"player.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"player.rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		{ ");
sb.append("\r\n");
sb.append("			item_num=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList948 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList948=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList948=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList948.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			common={ ");
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
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
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
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("						-1 , ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
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
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList410 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList410=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList410=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList410.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("						1, ");
sb.append("\r\n");
}
sb.append("	    				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("				},  ");
sb.append("\r\n");
}catch(Exception e25){
logger.error(e25.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItemsysItempackages929 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages929=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages929=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages929.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e26){
logger.error(e26.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList86 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList86=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList86=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList86.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList754 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList754=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList754=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList754.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e28){
logger.error(e28.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList554 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList554=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList554=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList554.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource462 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource462=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource462=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource462.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e30){
logger.error(e30.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources816 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources816=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources816=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources816.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e31){
logger.error(e31.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources65 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources65=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources65=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources65.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
}
});
} else {
List theItemsysItemresources84 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources84=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources84=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources84.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e33){
logger.error(e33.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},   ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e33){
logger.error(e33.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}