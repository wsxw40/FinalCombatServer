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

public class ShootingJoin implements Ctx {
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
sb.append("needDartleNum=");
sb.append(context.get("needDartleNum"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("look_item = { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List shootingLookcharacterList618 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof List) shootingLookcharacterList618=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList").getClass().isArray()) shootingLookcharacterList618=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
}
shootingLookcharacterList618.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	i_value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List shootingLookgunPropertypropertyList653 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof List) shootingLookgunPropertypropertyList653=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList").getClass().isArray()) shootingLookgunPropertypropertyList653=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
}
shootingLookgunPropertypropertyList653.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"gunProperty.color"));
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
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List shootingLookpackages53 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof List) shootingLookpackages53=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages").getClass().isArray()) shootingLookpackages53=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
}
shootingLookpackages53.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List shootingLookgpPricesList472 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof List) shootingLookgpPricesList472=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList").getClass().isArray()) shootingLookgpPricesList472=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
}
shootingLookgpPricesList472.forEach(pay->{
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List shootingLookcrPricesList82 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof List) shootingLookcrPricesList82=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList").getClass().isArray()) shootingLookcrPricesList82=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
}
shootingLookcrPricesList82.forEach(pay->{
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List shootingLookvoucherPricesList872 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof List) shootingLookvoucherPricesList872=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList").getClass().isArray()) shootingLookvoucherPricesList872=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
}
shootingLookvoucherPricesList872.forEach(pay->{
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
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List shootingLookresource932 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof List) shootingLookresource932=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource").getClass().isArray()) shootingLookresource932=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
}
shootingLookresource932.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cId"));
sb.append(", ");
sb.append("\r\n");
List shootingLookresources765 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources765=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources765=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources765.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("shootingLook"),"cId"));
sb.append(", ");
sb.append("\r\n");
List shootingLookresources909 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources909=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources909=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources909.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else {
List shootingLookresources849 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources849=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources849=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources849.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("gold_gift =  { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List goldGiftsysItemcharacterList118 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof List) goldGiftsysItemcharacterList118=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList").getClass().isArray()) goldGiftsysItemcharacterList118=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
}
goldGiftsysItemcharacterList118.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List goldGiftsysItemgunPropertypropertyList920 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof List) goldGiftsysItemgunPropertypropertyList920=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) goldGiftsysItemgunPropertypropertyList920=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
goldGiftsysItemgunPropertypropertyList920.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.gunProperty.color"));
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
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List goldGiftsysItempackages728 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof List) goldGiftsysItempackages728=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages").getClass().isArray()) goldGiftsysItempackages728=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
}
goldGiftsysItempackages728.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List goldGiftsysItemgpPricesList210 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof List) goldGiftsysItemgpPricesList210=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList").getClass().isArray()) goldGiftsysItemgpPricesList210=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
goldGiftsysItemgpPricesList210.forEach(pay->{
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
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List goldGiftsysItemcrPricesList576 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof List) goldGiftsysItemcrPricesList576=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList").getClass().isArray()) goldGiftsysItemcrPricesList576=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
goldGiftsysItemcrPricesList576.forEach(pay->{
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
sb.append("	voucherprices={ ");
sb.append("\r\n");
List goldGiftsysItemvoucherPricesList211 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof List) goldGiftsysItemvoucherPricesList211=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList").getClass().isArray()) goldGiftsysItemvoucherPricesList211=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
goldGiftsysItemvoucherPricesList211.forEach(pay->{
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
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List goldGiftsysItemresource346 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof List) goldGiftsysItemresource346=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource").getClass().isArray()) goldGiftsysItemresource346=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
}
goldGiftsysItemresource346.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e17){
logger.error(e17.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List goldGiftsysItemresources31 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources31=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources31=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources31.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("goldGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List goldGiftsysItemresources660 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources660=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources660=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources660.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
} else {
List goldGiftsysItemresources382 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources382=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources382=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources382.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("silver_gift = { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List silverGiftsysItemcharacterList524 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof List) silverGiftsysItemcharacterList524=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList").getClass().isArray()) silverGiftsysItemcharacterList524=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
}
silverGiftsysItemcharacterList524.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List silverGiftsysItemgunPropertypropertyList14 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof List) silverGiftsysItemgunPropertypropertyList14=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) silverGiftsysItemgunPropertypropertyList14=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
silverGiftsysItemgunPropertypropertyList14.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.gunProperty.color"));
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
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List silverGiftsysItempackages357 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof List) silverGiftsysItempackages357=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages").getClass().isArray()) silverGiftsysItempackages357=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
}
silverGiftsysItempackages357.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List silverGiftsysItemgpPricesList596 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof List) silverGiftsysItemgpPricesList596=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList").getClass().isArray()) silverGiftsysItemgpPricesList596=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
silverGiftsysItemgpPricesList596.forEach(pay->{
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
}catch(Exception e24){
logger.error(e24.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List silverGiftsysItemcrPricesList235 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof List) silverGiftsysItemcrPricesList235=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList").getClass().isArray()) silverGiftsysItemcrPricesList235=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
silverGiftsysItemcrPricesList235.forEach(pay->{
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
}catch(Exception e25){
logger.error(e25.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List silverGiftsysItemvoucherPricesList985 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof List) silverGiftsysItemvoucherPricesList985=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList").getClass().isArray()) silverGiftsysItemvoucherPricesList985=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
silverGiftsysItemvoucherPricesList985.forEach(pay->{
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
}catch(Exception e26){
logger.error(e26.toString());
}
});
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List silverGiftsysItemresource157 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof List) silverGiftsysItemresource157=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource").getClass().isArray()) silverGiftsysItemresource157=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
}
silverGiftsysItemresource157.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e27){
logger.error(e27.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List silverGiftsysItemresources495 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources495=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources495=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources495.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e28){
logger.error(e28.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("silverGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List silverGiftsysItemresources843 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources843=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources843=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources843.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
} else {
List silverGiftsysItemresources611 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources611=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources611=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources611.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e30){
logger.error(e30.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("common_gift = { ");
sb.append("\r\n");
List commonGifts222 = new ArrayList<>();
if (null!=context.get("commonGifts")){
if (context.get("commonGifts") instanceof List) commonGifts222=(List<?>)context.get("commonGifts");
else if (context.get("commonGifts").getClass().isArray()) commonGifts222=Stream.of((Object[])context.get("commonGifts")).collect(Collectors.toList());
}
commonGifts222.forEach(onlineAward->{
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
List onlineAwardsysItemcharacterList414 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList414=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList414=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList414.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
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
List onlineAwardsysItemgunPropertypropertyList763 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList763=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList763=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList763.forEach(property->{
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
}catch(Exception e33){
logger.error(e33.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List onlineAwardsysItempackages284 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof List) onlineAwardsysItempackages284=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages284=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages284.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e34){
logger.error(e34.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List onlineAwardsysItemgpPricesList288 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList288=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList288=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList288.forEach(pay->{
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
}catch(Exception e35){
logger.error(e35.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List onlineAwardsysItemcrPricesList74 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList74=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList74=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList74.forEach(pay->{
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
}catch(Exception e36){
logger.error(e36.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List onlineAwardsysItemvoucherPricesList107 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList107=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList107=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList107.forEach(pay->{
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
}catch(Exception e37){
logger.error(e37.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource326 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof List) onlineAwardsysItemresource326=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource326=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource326.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e38){
logger.error(e38.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources197 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources197=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources197=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources197.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e39){
logger.error(e39.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources330 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources330=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources330=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources330.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e40){
logger.error(e40.toString());
}
});
} else {
List onlineAwardsysItemresources653 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources653=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources653=Stream.of((Object[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources653.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e41){
logger.error(e41.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e41){
logger.error(e41.toString());
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
List dartleGiftsysItemcharacterList860 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof List) dartleGiftsysItemcharacterList860=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList").getClass().isArray()) dartleGiftsysItemcharacterList860=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
}
dartleGiftsysItemcharacterList860.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e42){
logger.error(e42.toString());
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
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.strengthLevel"));
sb.append(",  ");
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
List dartleGiftsysItemgunPropertypropertyList86 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof List) dartleGiftsysItemgunPropertypropertyList86=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) dartleGiftsysItemgunPropertypropertyList86=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
dartleGiftsysItemgunPropertypropertyList86.forEach(property->{
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
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e43){
logger.error(e43.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List dartleGiftsysItempackages797 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof List) dartleGiftsysItempackages797=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages").getClass().isArray()) dartleGiftsysItempackages797=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
}
dartleGiftsysItempackages797.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e44){
logger.error(e44.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List dartleGiftsysItemgpPricesList705 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof List) dartleGiftsysItemgpPricesList705=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList").getClass().isArray()) dartleGiftsysItemgpPricesList705=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemgpPricesList705.forEach(pay->{
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
}catch(Exception e45){
logger.error(e45.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List dartleGiftsysItemcrPricesList490 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof List) dartleGiftsysItemcrPricesList490=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList").getClass().isArray()) dartleGiftsysItemcrPricesList490=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemcrPricesList490.forEach(pay->{
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
}catch(Exception e46){
logger.error(e46.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List dartleGiftsysItemvoucherPricesList911 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof List) dartleGiftsysItemvoucherPricesList911=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList").getClass().isArray()) dartleGiftsysItemvoucherPricesList911=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemvoucherPricesList911.forEach(pay->{
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
}catch(Exception e47){
logger.error(e47.toString());
}
});
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List dartleGiftsysItemresource581 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof List) dartleGiftsysItemresource581=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource").getClass().isArray()) dartleGiftsysItemresource581=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
}
dartleGiftsysItemresource581.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e48){
logger.error(e48.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources105 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources105=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources105=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources105.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e49){
logger.error(e49.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources882 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources882=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources882=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources882.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e50){
logger.error(e50.toString());
}
});
} else {
List dartleGiftsysItemresources395 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources395=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources395=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources395.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e51){
logger.error(e51.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("dartle_top_list ={ ");
sb.append("\r\n");
List dartleTopList421 = new ArrayList<>();
if (null!=context.get("dartleTopList")){
if (context.get("dartleTopList") instanceof List) dartleTopList421=(List<?>)context.get("dartleTopList");
else if (context.get("dartleTopList").getClass().isArray()) dartleTopList421=Stream.of((Object[])context.get("dartleTopList")).collect(Collectors.toList());
}
dartleTopList421.forEach(entry->{
try{
sb.append("		");
sb.append(entry);
sb.append(" ");
sb.append("\r\n");
}catch(Exception e52){
logger.error(e52.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("dartle_awards_list ={ ");
sb.append("\r\n");
List awardsList193 = new ArrayList<>();
if (null!=context.get("awardsList")){
if (context.get("awardsList") instanceof List) awardsList193=(List<?>)context.get("awardsList");
else if (context.get("awardsList").getClass().isArray()) awardsList193=Stream.of((Object[])context.get("awardsList")).collect(Collectors.toList());
}
awardsList193.forEach(theItem->{
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
List theItemsysItemcharacterList695 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList695=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList695=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList695.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e54){
logger.error(e54.toString());
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
sb.append("						-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.strengthLevel"));
sb.append(" ,	 ");
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
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
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
List theItemsysItemgunPropertypropertyList355 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList355=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList355=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList355.forEach(property->{
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
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("				},  ");
sb.append("\r\n");
}catch(Exception e55){
logger.error(e55.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItemsysItempackages449 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages449=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages449=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages449.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e56){
logger.error(e56.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList831 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList831=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList831=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList831.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e57){
logger.error(e57.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList125 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList125=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList125=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList125.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e58){
logger.error(e58.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList388 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList388=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList388=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList388.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e59){
logger.error(e59.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource857 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource857=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource857=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource857.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e60){
logger.error(e60.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources625 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources625=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources625=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources625.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e61){
logger.error(e61.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources96 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources96=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources96=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources96.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e62){
logger.error(e62.toString());
}
});
} else {
List theItemsysItemresources33 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources33=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources33=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources33.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e63){
logger.error(e63.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},   ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e63){
logger.error(e63.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}