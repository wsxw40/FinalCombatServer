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
List prices439 = new ArrayList<>();
if (null!=context.get("prices")){
if (context.get("prices") instanceof List) prices439=(List<?>)context.get("prices");
else if (context.get("prices").getClass().isArray()) prices439=Stream.of((Object[])context.get("prices")).collect(Collectors.toList());
}
prices439.forEach(price->{
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
List items508 = new ArrayList<>();
if (null!=context.get("items")){
if (context.get("items") instanceof List) items508=(List<?>)context.get("items");
else if (context.get("items").getClass().isArray()) items508=Stream.of((Object[])context.get("items")).collect(Collectors.toList());
}
items508.forEach(sysItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
if( O2oUtil.compare(sysItem,"!=",null) ){
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List sysItemcharacterList240 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof List) sysItemcharacterList240=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList").getClass().isArray()) sysItemcharacterList240=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
}
sysItemcharacterList240.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=0, ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = {	 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList884 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList884=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList884=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList884.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List sysItempackages205 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"packages")){
if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof List) sysItempackages205=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"packages");
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages").getClass().isArray()) sysItempackages205=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
}
sysItempackages205.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
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
List sysItemgpPricesList936 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof List) sysItemgpPricesList936=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList").getClass().isArray()) sysItemgpPricesList936=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
}
sysItemgpPricesList936.forEach(pay->{
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
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List sysItemcrPricesList963 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof List) sysItemcrPricesList963=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList").getClass().isArray()) sysItemcrPricesList963=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
}
sysItemcrPricesList963.forEach(pay->{
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List sysItemvoucherPricesList122 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof List) sysItemvoucherPricesList122=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList").getClass().isArray()) sysItemvoucherPricesList122=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
}
sysItemvoucherPricesList122.forEach(pay->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource427 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resource")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof List) sysItemresource427=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resource");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource").getClass().isArray()) sysItemresource427=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
}
sysItemresource427.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources369 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources369=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources369=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources369.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources371 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources371=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources371=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources371.forEach(resource->{
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
List sysItemresources851 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources851=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources851=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources851.forEach(resource->{
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