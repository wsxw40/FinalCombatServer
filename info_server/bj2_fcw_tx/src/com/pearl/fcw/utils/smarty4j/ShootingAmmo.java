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

public class ShootingAmmo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list= { ");
sb.append("\r\n");
List playerItems139 = new ArrayList<>();
if (null!=context.get("playerItems")){
if (context.get("playerItems") instanceof List) playerItems139=(List<?>)context.get("playerItems");
else if (context.get("playerItems") instanceof int[]) playerItems139=Stream.of((int[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof long[]) playerItems139=Stream.of((long[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof float[]) playerItems139=Stream.of((float[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof double[]) playerItems139=Stream.of((double[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof byte[]) playerItems139=Stream.of((byte[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof String[]) playerItems139=Stream.of((String[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems").getClass().isArray()) playerItems139=Stream.of(context.get("playerItems")).collect(Collectors.toList());
}
playerItems139.forEach(sysItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List sysItemcharacterList402 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof List) sysItemcharacterList402=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof int[]) sysItemcharacterList402=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof long[]) sysItemcharacterList402=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof float[]) sysItemcharacterList402=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof double[]) sysItemcharacterList402=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof byte[]) sysItemcharacterList402=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof String[]) sysItemcharacterList402=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList").getClass().isArray()) sysItemcharacterList402=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
}
sysItemcharacterList402.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		i_value = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList322 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList322=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof int[]) sysItemgunPropertypropertyList322=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof long[]) sysItemgunPropertypropertyList322=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof float[]) sysItemgunPropertypropertyList322=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof double[]) sysItemgunPropertypropertyList322=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof byte[]) sysItemgunPropertypropertyList322=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof String[]) sysItemgunPropertypropertyList322=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList322=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList322.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("	    			\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List sysItempackages912 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"packages")){
if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof List) sysItempackages912=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"packages");
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof int[]) sysItempackages912=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof long[]) sysItempackages912=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof float[]) sysItempackages912=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof double[]) sysItempackages912=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof byte[]) sysItempackages912=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof String[]) sysItempackages912=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages").getClass().isArray()) sysItempackages912=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
}
sysItempackages912.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List sysItemgpPricesList922 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof List) sysItemgpPricesList922=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof int[]) sysItemgpPricesList922=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof long[]) sysItemgpPricesList922=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof float[]) sysItemgpPricesList922=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof double[]) sysItemgpPricesList922=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof byte[]) sysItemgpPricesList922=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof String[]) sysItemgpPricesList922=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList").getClass().isArray()) sysItemgpPricesList922=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
}
sysItemgpPricesList922.forEach(pay->{
try{
sb.append("	    		{id=");
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List sysItemcrPricesList357 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof List) sysItemcrPricesList357=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof int[]) sysItemcrPricesList357=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof long[]) sysItemcrPricesList357=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof float[]) sysItemcrPricesList357=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof double[]) sysItemcrPricesList357=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof byte[]) sysItemcrPricesList357=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof String[]) sysItemcrPricesList357=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList").getClass().isArray()) sysItemcrPricesList357=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
}
sysItemcrPricesList357.forEach(pay->{
try{
sb.append("	    		{id=");
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List sysItemvoucherPricesList292 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof List) sysItemvoucherPricesList292=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof int[]) sysItemvoucherPricesList292=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof long[]) sysItemvoucherPricesList292=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof float[]) sysItemvoucherPricesList292=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof double[]) sysItemvoucherPricesList292=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof byte[]) sysItemvoucherPricesList292=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof String[]) sysItemvoucherPricesList292=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList").getClass().isArray()) sysItemvoucherPricesList292=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
}
sysItemvoucherPricesList292.forEach(pay->{
try{
sb.append("	    		{id=");
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
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource897 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resource")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof List) sysItemresource897=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resource");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof int[]) sysItemresource897=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof long[]) sysItemresource897=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof float[]) sysItemresource897=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof double[]) sysItemresource897=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof byte[]) sysItemresource897=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof String[]) sysItemresource897=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource").getClass().isArray()) sysItemresource897=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
}
sysItemresource897.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources16 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources16=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources16=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources16=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources16=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources16=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources16=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources16=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources16=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources16.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources187 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources187=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources187=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources187=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources187=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources187=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources187=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources187=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources187=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources187.forEach(resource->{
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
List sysItemresources520 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources520=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof int[]) sysItemresources520=Stream.of((int[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof long[]) sysItemresources520=Stream.of((long[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof float[]) sysItemresources520=Stream.of((float[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof double[]) sysItemresources520=Stream.of((double[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof byte[]) sysItemresources520=Stream.of((byte[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof String[]) sysItemresources520=Stream.of((String[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources520=Stream.of(O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources520.forEach(resource->{
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
return sb.toString();
}

}