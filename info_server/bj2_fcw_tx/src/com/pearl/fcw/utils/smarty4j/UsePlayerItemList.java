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

public class UsePlayerItemList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("gp = ");
sb.append(context.get("cal"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List sysitemList976 = new ArrayList<>();
if (null!=context.get("sysitemList")){
if (context.get("sysitemList") instanceof List) sysitemList976=(List<?>)context.get("sysitemList");
else if (context.get("sysitemList") instanceof int[]) sysitemList976=Stream.of((int[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList") instanceof long[]) sysitemList976=Stream.of((long[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList") instanceof float[]) sysitemList976=Stream.of((float[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList") instanceof double[]) sysitemList976=Stream.of((double[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList") instanceof byte[]) sysitemList976=Stream.of((byte[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList") instanceof String[]) sysitemList976=Stream.of((String[])context.get("sysitemList")).collect(Collectors.toList());
else if (context.get("sysitemList").getClass().isArray()) sysitemList976=Stream.of(context.get("sysitemList")).collect(Collectors.toList());
}
sysitemList976.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List theItemcharacterList108 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList108=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof int[]) theItemcharacterList108=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof long[]) theItemcharacterList108=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof float[]) theItemcharacterList108=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof double[]) theItemcharacterList108=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof byte[]) theItemcharacterList108=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof String[]) theItemcharacterList108=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList108=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList108.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar4959=new Context();
includeContextVar4959.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"unitType"));
includeContextVar4959.set("unit",O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(new Timelimit().get(includeContextVar4959));
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			quantity = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_web=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"figthNumOutput"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					0 , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList295 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList295=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList295=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList295=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList295=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList295=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList295=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList295=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList295=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList295.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List theItempackages267 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages267=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages267=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages267=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages267=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages267=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages267=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages267=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages267=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages267.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List theItemgpPricesList717 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList717=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof int[]) theItemgpPricesList717=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof long[]) theItemgpPricesList717=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof float[]) theItemgpPricesList717=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof double[]) theItemgpPricesList717=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof byte[]) theItemgpPricesList717=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof String[]) theItemgpPricesList717=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList717=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList717.forEach(pay->{
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
sb.append("		crprices={ ");
sb.append("\r\n");
List theItemcrPricesList812 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList812=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof int[]) theItemcrPricesList812=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof long[]) theItemcrPricesList812=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof float[]) theItemcrPricesList812=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof double[]) theItemcrPricesList812=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof byte[]) theItemcrPricesList812=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof String[]) theItemcrPricesList812=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList812=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList812.forEach(pay->{
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List theItemvoucherPricesList426 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList426=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof int[]) theItemvoucherPricesList426=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof long[]) theItemvoucherPricesList426=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof float[]) theItemvoucherPricesList426=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof double[]) theItemvoucherPricesList426=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof byte[]) theItemvoucherPricesList426=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof String[]) theItemvoucherPricesList426=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList426=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList426.forEach(pay->{
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
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource48 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource48=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource48=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource48=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource48=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource48=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource48=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource48=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource48=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource48.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources947 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources947=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources947=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources947=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources947=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources947=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources947=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources947=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources947=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources947.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources936 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources936=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources936=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources936=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources936=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources936=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources936=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources936=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources936=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources936.forEach(resource->{
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
List theItemresources830 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources830=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources830=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources830=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources830=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources830=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources830=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources830=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources830=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources830.forEach(resource->{
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