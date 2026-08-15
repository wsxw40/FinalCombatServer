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

public class CombineGetPrice implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("prices = { ");
sb.append("\r\n");
List prices289 = new ArrayList<>();
if (null!=context.get("prices")){
if (context.get("prices") instanceof List) prices289=(List<?>)context.get("prices");
else if (context.get("prices") instanceof int[]) prices289=Stream.of((int[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices") instanceof long[]) prices289=Stream.of((long[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices") instanceof float[]) prices289=Stream.of((float[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices") instanceof double[]) prices289=Stream.of((double[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices") instanceof byte[]) prices289=Stream.of((byte[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices") instanceof String[]) prices289=Stream.of((String[])context.get("prices")).collect(Collectors.toList());
else if (context.get("prices").getClass().isArray()) prices289=Stream.of(context.get("prices")).collect(Collectors.toList());
}
prices289.forEach(price->{
try{
sb.append("		");
sb.append(price);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List items645 = new ArrayList<>();
if (null!=context.get("items")){
if (context.get("items") instanceof List) items645=(List<?>)context.get("items");
else if (context.get("items") instanceof int[]) items645=Stream.of((int[])context.get("items")).collect(Collectors.toList());
else if (context.get("items") instanceof long[]) items645=Stream.of((long[])context.get("items")).collect(Collectors.toList());
else if (context.get("items") instanceof float[]) items645=Stream.of((float[])context.get("items")).collect(Collectors.toList());
else if (context.get("items") instanceof double[]) items645=Stream.of((double[])context.get("items")).collect(Collectors.toList());
else if (context.get("items") instanceof byte[]) items645=Stream.of((byte[])context.get("items")).collect(Collectors.toList());
else if (context.get("items") instanceof String[]) items645=Stream.of((String[])context.get("items")).collect(Collectors.toList());
else if (context.get("items").getClass().isArray()) items645=Stream.of(context.get("items")).collect(Collectors.toList());
}
items645.forEach(sysItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
if( O2oUtil.compare(sysItem,"!=",null) ){
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List sysItemcharacterList961 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof List) sysItemcharacterList961=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof int[]) sysItemcharacterList961=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof long[]) sysItemcharacterList961=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof float[]) sysItemcharacterList961=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof double[]) sysItemcharacterList961=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof byte[]) sysItemcharacterList961=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof String[]) sysItemcharacterList961=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList").getClass().isArray()) sysItemcharacterList961=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
}
sysItemcharacterList961.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=0, ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList575 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList575=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof int[]) sysItemgunPropertypropertyList575=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof long[]) sysItemgunPropertypropertyList575=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof float[]) sysItemgunPropertypropertyList575=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof double[]) sysItemgunPropertypropertyList575=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof byte[]) sysItemgunPropertypropertyList575=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof String[]) sysItemgunPropertypropertyList575=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList575=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList575.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("						1, ");
sb.append("\r\n");
}
sb.append("	    				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("				},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List sysItempackages657 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"packages")){
if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof List) sysItempackages657=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"packages");
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof int[]) sysItempackages657=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof long[]) sysItempackages657=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof float[]) sysItempackages657=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof double[]) sysItempackages657=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof byte[]) sysItempackages657=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof String[]) sysItempackages657=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages").getClass().isArray()) sysItempackages657=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
}
sysItempackages657.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List sysItemgpPricesList610 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof List) sysItemgpPricesList610=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof int[]) sysItemgpPricesList610=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof long[]) sysItemgpPricesList610=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof float[]) sysItemgpPricesList610=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof double[]) sysItemgpPricesList610=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof byte[]) sysItemgpPricesList610=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof String[]) sysItemgpPricesList610=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList").getClass().isArray()) sysItemgpPricesList610=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
}
sysItemgpPricesList610.forEach(pay->{
try{
sb.append("	    			{id=");
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
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List sysItemcrPricesList410 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof List) sysItemcrPricesList410=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof int[]) sysItemcrPricesList410=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof long[]) sysItemcrPricesList410=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof float[]) sysItemcrPricesList410=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof double[]) sysItemcrPricesList410=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof byte[]) sysItemcrPricesList410=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof String[]) sysItemcrPricesList410=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList").getClass().isArray()) sysItemcrPricesList410=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
}
sysItemcrPricesList410.forEach(pay->{
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List sysItemvoucherPricesList894 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof List) sysItemvoucherPricesList894=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof int[]) sysItemvoucherPricesList894=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof long[]) sysItemvoucherPricesList894=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof float[]) sysItemvoucherPricesList894=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof double[]) sysItemvoucherPricesList894=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof byte[]) sysItemvoucherPricesList894=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof String[]) sysItemvoucherPricesList894=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList").getClass().isArray()) sysItemvoucherPricesList894=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
}
sysItemvoucherPricesList894.forEach(pay->{
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
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource521 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resource")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof List) sysItemresource521=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resource");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof int[]) sysItemresource521=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof long[]) sysItemresource521=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof float[]) sysItemresource521=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof double[]) sysItemresource521=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof byte[]) sysItemresource521=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof String[]) sysItemresource521=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource").getClass().isArray()) sysItemresource521=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
}
sysItemresource521.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources809 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources809=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources809=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources809=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources809=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources809=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources809=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources809=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources809=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources809.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources882 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources882=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources882=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources882=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources882=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources882=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources882=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources882=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources882=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources882.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
} else {
List sysItemresources635 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources635=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources635=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources635=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources635=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources635=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources635=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources635=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources635=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources635.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
}
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}