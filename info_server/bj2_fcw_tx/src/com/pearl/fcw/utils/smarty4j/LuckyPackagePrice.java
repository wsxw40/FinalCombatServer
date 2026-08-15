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

public class LuckyPackagePrice implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("chipNum=");
sb.append(context.get("chipNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("copperyKeyNum=");
sb.append(context.get("copperyKeyNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("silverKeyNum=");
sb.append(context.get("silverKeyNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("goldKeyNum=");
sb.append(context.get("goldKeyNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("fix={ ");
sb.append("\r\n");
List fix102 = new ArrayList<>();
if (null!=context.get("fix")){
if (context.get("fix") instanceof List) fix102=(List<?>)context.get("fix");
else if (context.get("fix") instanceof int[]) fix102=Stream.of((int[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof long[]) fix102=Stream.of((long[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof float[]) fix102=Stream.of((float[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof double[]) fix102=Stream.of((double[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof byte[]) fix102=Stream.of((byte[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix") instanceof String[]) fix102=Stream.of((String[])context.get("fix")).collect(Collectors.toList());
else if (context.get("fix").getClass().isArray()) fix102=Stream.of(context.get("fix")).collect(Collectors.toList());
}
fix102.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(",							 ");
sb.append("\r\n");
sb.append("		payFC = 1,							 ");
sb.append("\r\n");
sb.append("		payChip = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"chipPrice"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("luckyPackages={ ");
sb.append("\r\n");
List luckyPackages10 = new ArrayList<>();
if (null!=context.get("luckyPackages")){
if (context.get("luckyPackages") instanceof List) luckyPackages10=(List<?>)context.get("luckyPackages");
else if (context.get("luckyPackages") instanceof int[]) luckyPackages10=Stream.of((int[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages") instanceof long[]) luckyPackages10=Stream.of((long[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages") instanceof float[]) luckyPackages10=Stream.of((float[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages") instanceof double[]) luckyPackages10=Stream.of((double[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages") instanceof byte[]) luckyPackages10=Stream.of((byte[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages") instanceof String[]) luckyPackages10=Stream.of((String[])context.get("luckyPackages")).collect(Collectors.toList());
else if (context.get("luckyPackages").getClass().isArray()) luckyPackages10=Stream.of(context.get("luckyPackages")).collect(Collectors.toList());
}
luckyPackages10.forEach(theItem->{
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
List theItemcharacterList252 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList252=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof int[]) theItemcharacterList252=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof long[]) theItemcharacterList252=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof float[]) theItemcharacterList252=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof double[]) theItemcharacterList252=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof byte[]) theItemcharacterList252=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof String[]) theItemcharacterList252=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList252=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList252.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ivalue=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"iValue"));
sb.append("\", ");
sb.append("\r\n");
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
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append("  , ");
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
List theItemgunPropertypropertyList821 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList821=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList821=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList821=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList821=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList821=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList821=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList821=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList821=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList821.forEach(property->{
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
sb.append("				\"");
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
List theItempackages555 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages555=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages555=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages555=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages555=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages555=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages555=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages555=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages555=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages555.forEach(item->{
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
List theItemgpPricesList455 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList455=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof int[]) theItemgpPricesList455=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof long[]) theItemgpPricesList455=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof float[]) theItemgpPricesList455=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof double[]) theItemgpPricesList455=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof byte[]) theItemgpPricesList455=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof String[]) theItemgpPricesList455=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList455=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList455.forEach(pay->{
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
List theItemcrPricesList616 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList616=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof int[]) theItemcrPricesList616=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof long[]) theItemcrPricesList616=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof float[]) theItemcrPricesList616=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof double[]) theItemcrPricesList616=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof byte[]) theItemcrPricesList616=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof String[]) theItemcrPricesList616=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList616=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList616.forEach(pay->{
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
List theItemvoucherPricesList106 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList106=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof int[]) theItemvoucherPricesList106=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof long[]) theItemvoucherPricesList106=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof float[]) theItemvoucherPricesList106=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof double[]) theItemvoucherPricesList106=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof byte[]) theItemvoucherPricesList106=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof String[]) theItemvoucherPricesList106=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList106=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList106.forEach(pay->{
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
sb.append("		},	     ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource619 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource619=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource619=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource619=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource619=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource619=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource619=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource619=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource619=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource619.forEach(res->{
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
List theItemresources421 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources421=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources421=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources421=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources421=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources421=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources421=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources421=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources421=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources421.forEach(resource->{
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
List theItemresources271 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources271=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources271=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources271=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources271=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources271=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources271=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources271=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources271=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources271.forEach(resource->{
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
List theItemresources931 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources931=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources931=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources931=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources931=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources931=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources931=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources931=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources931=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources931.forEach(resource->{
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