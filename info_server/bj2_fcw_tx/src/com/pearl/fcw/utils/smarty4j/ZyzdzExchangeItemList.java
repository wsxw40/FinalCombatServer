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

public class ZyzdzExchangeItemList implements Ctx {
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
sb.append("teamLevel = ");
sb.append(context.get("teamLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("pRes=");
sb.append(context.get("pRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List list491 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list491=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list491=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list491=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list491=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list491=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list491=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list491=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list491=Stream.of(context.get("list")).collect(Collectors.toList());
}
list491.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"iId"));
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
List theItemcharacterList314 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList314=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof int[]) theItemcharacterList314=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof long[]) theItemcharacterList314=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof float[]) theItemcharacterList314=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof double[]) theItemcharacterList314=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof byte[]) theItemcharacterList314=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof String[]) theItemcharacterList314=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList314=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList314.forEach(ids->{
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
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		buyRecordTimes=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buyRecordTimes"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
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
sb.append("			is_exchange=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isExchange"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"figthNumOutput"));
sb.append(",    		 ");
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
List theItemgunPropertypropertyList36 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList36=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList36=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList36=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList36=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList36=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList36=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList36=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList36=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList36.forEach(property->{
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
List theItempackages228 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages228=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages228=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages228=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages228=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages228=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages228=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages228=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages228=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages228.forEach(item->{
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
sb.append("		resPricesList={ ");
sb.append("\r\n");
List theItemallResPricesList58 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof List) theItemallResPricesList58=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"allResPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof int[]) theItemallResPricesList58=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof long[]) theItemallResPricesList58=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof float[]) theItemallResPricesList58=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof double[]) theItemallResPricesList58=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof byte[]) theItemallResPricesList58=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList") instanceof String[]) theItemallResPricesList58=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResPricesList").getClass().isArray()) theItemallResPricesList58=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"allResPricesList")).collect(Collectors.toList());
}
theItemallResPricesList58.forEach(pay->{
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
sb.append("		resDisPricesList={ ");
sb.append("\r\n");
List theItemallResDisPricesList697 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof List) theItemallResDisPricesList697=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof int[]) theItemallResDisPricesList697=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof long[]) theItemallResDisPricesList697=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof float[]) theItemallResDisPricesList697=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof double[]) theItemallResDisPricesList697=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof byte[]) theItemallResDisPricesList697=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList") instanceof String[]) theItemallResDisPricesList697=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList").getClass().isArray()) theItemallResDisPricesList697=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"allResDisPricesList")).collect(Collectors.toList());
}
theItemallResDisPricesList697.forEach(pay->{
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
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource535 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource535=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource535=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource535=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource535=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource535=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource535=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource535=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource535=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource535.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources345 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources345=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources345=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources345=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources345=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources345=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources345=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources345=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources345=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources345.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources630 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources630=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources630=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources630=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources630=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources630=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources630=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources630=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources630=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources630.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else {
List theItemresources740 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources740=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources740=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources740=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources740=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources740=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources740=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources740=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources740=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources740.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}