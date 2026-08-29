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
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"sender"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"subject"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"content"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"createdDateStr"));
sb.append("\", ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("message"),"haveAttached"),"!=","N")) ){
sb.append("		attachment = { ");
sb.append("\r\n");
sb.append("			id=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"playerItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			time_left=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"playerItem.timeLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"playerItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"playerItem.playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("message"),"playerItem.quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List sicharacterList473 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof List) sicharacterList473=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof int[]) sicharacterList473=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof long[]) sicharacterList473=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof float[]) sicharacterList473=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof double[]) sicharacterList473=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof byte[]) sicharacterList473=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList") instanceof String[]) sicharacterList473=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"characterList").getClass().isArray()) sicharacterList473=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"characterList")).collect(Collectors.toList());
}
sicharacterList473.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"iId"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"isPopular"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_web=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"isStrengthen"),"==",0)){
sb.append("						-1 , ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"strengthLevel"));
sb.append("  , ");
sb.append("\r\n");
}
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"damange"));
sb.append(", ");
sb.append("\r\n");
sb.append("				speed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"shootSpeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"damangeAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"shootSpeedAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"wAmmoCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List sigunPropertypropertyList70 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof List) sigunPropertypropertyList70=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof int[]) sigunPropertypropertyList70=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof long[]) sigunPropertypropertyList70=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof float[]) sigunPropertypropertyList70=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof double[]) sigunPropertypropertyList70=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof byte[]) sigunPropertypropertyList70=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList") instanceof String[]) sigunPropertypropertyList70=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList").getClass().isArray()) sigunPropertypropertyList70=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sigunPropertypropertyList70.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("						1, ");
sb.append("\r\n");
}
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List sipackages767 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof List) sipackages767=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof int[]) sipackages767=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof long[]) sipackages767=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof float[]) sipackages767=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof double[]) sipackages767=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof byte[]) sipackages767=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages") instanceof String[]) sipackages767=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"packages").getClass().isArray()) sipackages767=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"packages")).collect(Collectors.toList());
}
sipackages767.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List sigpPricesList392 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof List) sigpPricesList392=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof int[]) sigpPricesList392=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof long[]) sigpPricesList392=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof float[]) sigpPricesList392=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof double[]) sigpPricesList392=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof byte[]) sigpPricesList392=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList") instanceof String[]) sigpPricesList392=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList").getClass().isArray()) sigpPricesList392=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"gpPricesList")).collect(Collectors.toList());
}
sigpPricesList392.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List sicrPricesList772 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof List) sicrPricesList772=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof int[]) sicrPricesList772=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof long[]) sicrPricesList772=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof float[]) sicrPricesList772=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof double[]) sicrPricesList772=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof byte[]) sicrPricesList772=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList") instanceof String[]) sicrPricesList772=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList").getClass().isArray()) sicrPricesList772=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"crPricesList")).collect(Collectors.toList());
}
sicrPricesList772.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List sivoucherPricesList756 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof List) sivoucherPricesList756=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof int[]) sivoucherPricesList756=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof long[]) sivoucherPricesList756=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof float[]) sivoucherPricesList756=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof double[]) sivoucherPricesList756=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof byte[]) sivoucherPricesList756=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList") instanceof String[]) sivoucherPricesList756=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList").getClass().isArray()) sivoucherPricesList756=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"voucherPricesList")).collect(Collectors.toList());
}
sivoucherPricesList756.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List siresource246 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof List) siresource246=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof int[]) siresource246=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof long[]) siresource246=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof float[]) siresource246=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof double[]) siresource246=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof byte[]) siresource246=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource") instanceof String[]) siresource246=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resource").getClass().isArray()) siresource246=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"resource")).collect(Collectors.toList());
}
siresource246.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cId"));
sb.append(", ");
sb.append("\r\n");
List siresources971 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources971=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof int[]) siresources971=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof long[]) siresources971=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof float[]) siresources971=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof double[]) siresources971=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof byte[]) siresources971=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof String[]) siresources971=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources971=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources971.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("si"),"cId"));
sb.append(", ");
sb.append("\r\n");
List siresources64 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources64=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof int[]) siresources64=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof long[]) siresources64=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof float[]) siresources64=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof double[]) siresources64=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof byte[]) siresources64=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof String[]) siresources64=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources64=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources64.forEach(resource->{
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
List siresources900 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("si"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof List) siresources900=(List<?>)O2oUtil.getSmarty4jProperty(context.get("si"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof int[]) siresources900=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof long[]) siresources900=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof float[]) siresources900=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof double[]) siresources900=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof byte[]) siresources900=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources") instanceof String[]) siresources900=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("si"),"resources").getClass().isArray()) siresources900=Stream.of(O2oUtil.getSmarty4jProperty(context.get("si"),"resources")).collect(Collectors.toList());
}
siresources900.forEach(resource->{
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
sb.append("		attachment = nil , ");
sb.append("\r\n");
}
sb.append("	num = ");
sb.append(context.get("num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	items = { ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("message"),"haveAttached"),"==","N")) ){
List messageitemList421 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")){
if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof List) messageitemList421=(List<?>)O2oUtil.getSmarty4jProperty(context.get("message"),"itemList");
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof int[]) messageitemList421=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof long[]) messageitemList421=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof float[]) messageitemList421=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof double[]) messageitemList421=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof byte[]) messageitemList421=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList") instanceof String[]) messageitemList421=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("message"),"itemList").getClass().isArray()) messageitemList421=Stream.of(O2oUtil.getSmarty4jProperty(context.get("message"),"itemList")).collect(Collectors.toList());
}
messageitemList421.forEach(sysItem->{
try{
sb.append("			{ ");
sb.append("\r\n");
sb.append("				name = \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				display_name = \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				color = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit_type = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"unitType"));
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