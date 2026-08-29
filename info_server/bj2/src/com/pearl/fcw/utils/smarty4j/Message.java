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

public class Message implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("mail = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"sender"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"subject"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"content"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"createdDateStr"));
sb.append("\", ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("message"),"haveAttached"),"!=","N")) ){
sb.append("		attachment = { ");
sb.append("\r\n");
sb.append("			id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"playerItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			time_left=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"playerItem.timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"playerItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"playerItem.playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("message"),"playerItem.quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List sicharacterList423 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof List) sicharacterList423=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList").getClass().isArray()) sicharacterList423=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
}
sicharacterList423.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"iId"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"isStrengthen"),"==",0)){
sb.append("						-1 , ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"damange"));
sb.append(", ");
sb.append("\r\n");
sb.append("				speed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"shootSpeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"damangeAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"shootSpeedAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"wAmmoCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List sigunPropertypropertyList839 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof List) sigunPropertypropertyList839=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList").getClass().isArray()) sigunPropertypropertyList839=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sigunPropertypropertyList839.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"gunProperty.color"));
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
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List sipackages237 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof List) sipackages237=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages").getClass().isArray()) sipackages237=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
}
sipackages237.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List sigpPricesList946 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof List) sigpPricesList946=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList").getClass().isArray()) sigpPricesList946=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
}
sigpPricesList946.forEach(pay->{
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List sicrPricesList776 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof List) sicrPricesList776=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList").getClass().isArray()) sicrPricesList776=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
}
sicrPricesList776.forEach(pay->{
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
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List sivoucherPricesList557 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof List) sivoucherPricesList557=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList").getClass().isArray()) sivoucherPricesList557=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
}
sivoucherPricesList557.forEach(pay->{
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
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List siresource195 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof List) siresource195=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource").getClass().isArray()) siresource195=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
}
siresource195.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cId"));
sb.append(", ");
sb.append("\r\n");
List siresources373 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources373=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources373=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources373.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("si"),"cId"));
sb.append(", ");
sb.append("\r\n");
List siresources498 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources498=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources498=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources498.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else {
List siresources843 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources843=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources843=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources843.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
} else {
sb.append("		attachment = {} , ");
sb.append("\r\n");
}
sb.append("	num = ");
sb.append(context.get("num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	items = { ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("message"),"haveAttached"),"==","N")) ){
List messageitemList995 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")){
if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof List) messageitemList995=(List<?>)O2oUtil.getSmarty4jProperty(context.get("message"),"itemList");
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList").getClass().isArray()) messageitemList995=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
}
messageitemList995.forEach(sysItem->{
try{
sb.append("			{ ");
sb.append("\r\n");
sb.append("				name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				display_name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				color = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit_type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"unitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}