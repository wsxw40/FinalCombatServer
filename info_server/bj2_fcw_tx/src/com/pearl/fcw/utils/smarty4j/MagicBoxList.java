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

public class MagicBoxList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("box_num=");
sb.append(context.get("boxNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("hummer_num=");
sb.append(context.get("hummerNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("need=");
sb.append(context.get("needNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("hummer = {		 ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List hummercharacterList990 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof List) hummercharacterList990=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof int[]) hummercharacterList990=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof long[]) hummercharacterList990=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof float[]) hummercharacterList990=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof double[]) hummercharacterList990=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof byte[]) hummercharacterList990=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList") instanceof String[]) hummercharacterList990=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList").getClass().isArray()) hummercharacterList990=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"characterList")).collect(Collectors.toList());
}
hummercharacterList990.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("hummer"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List hummergunPropertypropertyList320 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof List) hummergunPropertypropertyList320=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof int[]) hummergunPropertypropertyList320=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof long[]) hummergunPropertypropertyList320=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof float[]) hummergunPropertypropertyList320=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof double[]) hummergunPropertypropertyList320=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof byte[]) hummergunPropertypropertyList320=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList") instanceof String[]) hummergunPropertypropertyList320=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList").getClass().isArray()) hummergunPropertypropertyList320=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.propertyList")).collect(Collectors.toList());
}
hummergunPropertypropertyList320.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List hummerpackages853 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof List) hummerpackages853=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof int[]) hummerpackages853=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof long[]) hummerpackages853=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof float[]) hummerpackages853=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof double[]) hummerpackages853=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof byte[]) hummerpackages853=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages") instanceof String[]) hummerpackages853=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages").getClass().isArray()) hummerpackages853=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"packages")).collect(Collectors.toList());
}
hummerpackages853.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List hummergpPricesList798 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof List) hummergpPricesList798=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof int[]) hummergpPricesList798=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof long[]) hummergpPricesList798=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof float[]) hummergpPricesList798=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof double[]) hummergpPricesList798=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof byte[]) hummergpPricesList798=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList") instanceof String[]) hummergpPricesList798=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList").getClass().isArray()) hummergpPricesList798=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"gpPricesList")).collect(Collectors.toList());
}
hummergpPricesList798.forEach(pay->{
try{
sb.append("	    	{id=");
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List hummercrPricesList955 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof List) hummercrPricesList955=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof int[]) hummercrPricesList955=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof long[]) hummercrPricesList955=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof float[]) hummercrPricesList955=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof double[]) hummercrPricesList955=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof byte[]) hummercrPricesList955=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList") instanceof String[]) hummercrPricesList955=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList").getClass().isArray()) hummercrPricesList955=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"crPricesList")).collect(Collectors.toList());
}
hummercrPricesList955.forEach(pay->{
try{
sb.append("	    	{id=");
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List hummervoucherPricesList568 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof List) hummervoucherPricesList568=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof int[]) hummervoucherPricesList568=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof long[]) hummervoucherPricesList568=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof float[]) hummervoucherPricesList568=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof double[]) hummervoucherPricesList568=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof byte[]) hummervoucherPricesList568=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList") instanceof String[]) hummervoucherPricesList568=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList").getClass().isArray()) hummervoucherPricesList568=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"voucherPricesList")).collect(Collectors.toList());
}
hummervoucherPricesList568.forEach(pay->{
try{
sb.append("	    	{id=");
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
sb.append("	},	 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("hummer"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List hummerresource758 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof List) hummerresource758=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof int[]) hummerresource758=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof long[]) hummerresource758=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof float[]) hummerresource758=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof double[]) hummerresource758=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof byte[]) hummerresource758=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource") instanceof String[]) hummerresource758=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource").getClass().isArray()) hummerresource758=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"resource")).collect(Collectors.toList());
}
hummerresource758.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("hummer"),"type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cId"));
sb.append(", ");
sb.append("\r\n");
List hummerresources315 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof List) hummerresources315=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof int[]) hummerresources315=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof long[]) hummerresources315=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof float[]) hummerresources315=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof double[]) hummerresources315=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof byte[]) hummerresources315=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof String[]) hummerresources315=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources").getClass().isArray()) hummerresources315=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
}
hummerresources315.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("hummer"),"type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("hummer"),"cId"));
sb.append(", ");
sb.append("\r\n");
List hummerresources188 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof List) hummerresources188=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof int[]) hummerresources188=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof long[]) hummerresources188=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof float[]) hummerresources188=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof double[]) hummerresources188=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof byte[]) hummerresources188=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof String[]) hummerresources188=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources").getClass().isArray()) hummerresources188=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
}
hummerresources188.forEach(resource->{
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
List hummerresources716 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof List) hummerresources716=(List<?>)O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof int[]) hummerresources716=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof long[]) hummerresources716=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof float[]) hummerresources716=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof double[]) hummerresources716=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof byte[]) hummerresources716=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources") instanceof String[]) hummerresources716=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources").getClass().isArray()) hummerresources716=Stream.of(O2oUtil.getSmarty4jProperty(context.get("hummer"),"resources")).collect(Collectors.toList());
}
hummerresources716.forEach(resource->{
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
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
List playerItems298 = new ArrayList<>();
if (null!=context.get("playerItems")){
if (context.get("playerItems") instanceof List) playerItems298=(List<?>)context.get("playerItems");
else if (context.get("playerItems") instanceof int[]) playerItems298=Stream.of((int[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof long[]) playerItems298=Stream.of((long[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof float[]) playerItems298=Stream.of((float[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof double[]) playerItems298=Stream.of((double[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof byte[]) playerItems298=Stream.of((byte[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems") instanceof String[]) playerItems298=Stream.of((String[])context.get("playerItems")).collect(Collectors.toList());
else if (context.get("playerItems").getClass().isArray()) playerItems298=Stream.of(context.get("playerItems")).collect(Collectors.toList());
}
playerItems298.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		rank=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		exp = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		item={ ");
sb.append("\r\n");
sb.append("			item_num=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList850 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList850=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList850=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList850=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList850=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList850=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList850=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList850=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList850=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList850.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar6176=new Context();
includeContextVar6176.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"sysItem.defaultPayment.unitType"));
includeContextVar6176.set("unit",O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(new Timelimit().get(includeContextVar6176));
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList181 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList181=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList181=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList181=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList181=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList181=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList181=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList181=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList181=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList181.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
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
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItemsysItempackages30 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages30=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages30=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages30=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages30=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages30=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages30=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages30=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages30=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages30.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList432 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList432=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList432=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList432=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList432=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList432=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList432=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList432=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList432=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList432.forEach(pay->{
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList583 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList583=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList583=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList583=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList583=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList583=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList583=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList583=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList583=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList583.forEach(pay->{
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
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList191 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList191=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList191=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList191=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList191=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList191=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList191=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList191=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList191=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList191.forEach(pay->{
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
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource884 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource884=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource884=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource884=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource884=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource884=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource884=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource884=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource884=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource884.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e19){
logger.error(e19.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources329 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources329=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources329=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources329=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources329=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources329=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources329=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources329=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources329=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources329.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources862 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources862=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources862=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources862=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources862=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources862=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources862=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources862=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources862=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources862.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
} else {
List theItemsysItemresources110 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources110=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources110=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources110=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources110=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources110=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources110=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources110=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources110=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources110.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},   ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}