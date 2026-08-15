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

public class SysItemList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("newGP = ");
sb.append(context.get("gp"));
sb.append(" ");
sb.append("\r\n");
sb.append("newCR = ");
sb.append(context.get("cr"));
sb.append(" ");
sb.append("\r\n");
sb.append("player_rank = ");
sb.append(context.get("rank"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List list646 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list646=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list646=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list646=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list646=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list646=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list646=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list646=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list646=Stream.of(context.get("list")).collect(Collectors.toList());
}
list646.forEach(theItem->{
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
List theItemcharacterList811 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList811=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof int[]) theItemcharacterList811=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof long[]) theItemcharacterList811=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof float[]) theItemcharacterList811=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof double[]) theItemcharacterList811=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof byte[]) theItemcharacterList811=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof String[]) theItemcharacterList811=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList811=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList811.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(", ");
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
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"iId"));
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
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isPopular"));
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
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"strengthLevel"));
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
sb.append(", ");
sb.append("\r\n");
sb.append("			speed = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"damangeAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"shootSpeedAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList431 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList431=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList431=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList431=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList431=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList431=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList431=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList431=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList431=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList431.forEach(property->{
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
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List theItempackages831 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages831=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages831=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages831=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages831=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages831=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages831=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages831=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages831=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages831.forEach(item->{
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
List theItemgpPricesList243 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList243=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof int[]) theItemgpPricesList243=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof long[]) theItemgpPricesList243=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof float[]) theItemgpPricesList243=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof double[]) theItemgpPricesList243=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof byte[]) theItemgpPricesList243=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof String[]) theItemgpPricesList243=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList243=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList243.forEach(pay->{
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
List theItemcrPricesList759 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList759=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof int[]) theItemcrPricesList759=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof long[]) theItemcrPricesList759=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof float[]) theItemcrPricesList759=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof double[]) theItemcrPricesList759=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof byte[]) theItemcrPricesList759=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof String[]) theItemcrPricesList759=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList759=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList759.forEach(pay->{
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
List theItemvoucherPricesList669 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList669=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof int[]) theItemvoucherPricesList669=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof long[]) theItemvoucherPricesList669=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof float[]) theItemvoucherPricesList669=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof double[]) theItemvoucherPricesList669=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof byte[]) theItemvoucherPricesList669=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof String[]) theItemvoucherPricesList669=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList669=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList669.forEach(pay->{
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
sb.append("		},						 ");
sb.append("\r\n");
sb.append("		resprices={ ");
sb.append("\r\n");
List theItemresPricesList313 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof List) theItemresPricesList313=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof int[]) theItemresPricesList313=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof long[]) theItemresPricesList313=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof float[]) theItemresPricesList313=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof double[]) theItemresPricesList313=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof byte[]) theItemresPricesList313=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList") instanceof String[]) theItemresPricesList313=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resPricesList").getClass().isArray()) theItemresPricesList313=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resPricesList")).collect(Collectors.toList());
}
theItemresPricesList313.forEach(pay->{
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
sb.append("		},						 ");
sb.append("\r\n");
sb.append("		resteamprices={ ");
sb.append("\r\n");
List theItemresTeamPricesList36 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof List) theItemresTeamPricesList36=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof int[]) theItemresTeamPricesList36=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof long[]) theItemresTeamPricesList36=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof float[]) theItemresTeamPricesList36=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof double[]) theItemresTeamPricesList36=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof byte[]) theItemresTeamPricesList36=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList") instanceof String[]) theItemresTeamPricesList36=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList").getClass().isArray()) theItemresTeamPricesList36=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resTeamPricesList")).collect(Collectors.toList());
}
theItemresTeamPricesList36.forEach(pay->{
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
}catch(Exception e9){
logger.error(e9.toString());
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
List theItemresource939 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource939=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource939=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource939=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource939=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource939=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource939=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource939=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource939=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource939.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources89 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources89=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources89=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources89=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources89=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources89=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources89=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources89=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources89=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources89.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources101 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources101=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources101=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources101=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources101=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources101=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources101=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources101=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources101=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources101.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
} else {
List theItemresources115 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources115=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources115=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources115=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources115=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources115=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources115=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources115=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources115=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources115.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}