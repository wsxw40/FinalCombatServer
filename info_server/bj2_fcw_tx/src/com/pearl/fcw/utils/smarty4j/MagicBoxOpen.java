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

public class MagicBoxOpen implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("box_num = ");
sb.append(context.get("magicBoxNum"));
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
sb.append("item={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("theItem"),"!=",null)){
sb.append("		item_num=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList853 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof List) theItemsysItemcharacterList853=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList853=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList853=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList853=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList853=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList853=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList853=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList853=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList853.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("		    	damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		    	ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList649 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList649=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList649=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList649=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList649=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList649=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList649=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList649=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList649=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList649.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.color"));
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
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List theItemsysItempackages808 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof List) theItemsysItempackages808=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof int[]) theItemsysItempackages808=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof long[]) theItemsysItempackages808=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof float[]) theItemsysItempackages808=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof double[]) theItemsysItempackages808=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof byte[]) theItemsysItempackages808=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof String[]) theItemsysItempackages808=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages").getClass().isArray()) theItemsysItempackages808=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages808.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList28 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList28=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList28=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList28=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList28=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList28=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList28=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList28=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList28=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList28.forEach(pay->{
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList946 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList946=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList946=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList946=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList946=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList946=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList946=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList946=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList946=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList946.forEach(pay->{
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
sb.append("		voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList795 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList795=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList795=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList795=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList795=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList795=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList795=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList795=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList795=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList795.forEach(pay->{
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
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource943 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof List) theItemsysItemresource943=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof int[]) theItemsysItemresource943=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof long[]) theItemsysItemresource943=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof float[]) theItemsysItemresource943=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof double[]) theItemsysItemresource943=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof byte[]) theItemsysItemresource943=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof String[]) theItemsysItemresource943=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource").getClass().isArray()) theItemsysItemresource943=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource943.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources736 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources736=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof int[]) theItemsysItemresources736=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof long[]) theItemsysItemresources736=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof float[]) theItemsysItemresources736=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof double[]) theItemsysItemresources736=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof byte[]) theItemsysItemresources736=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof String[]) theItemsysItemresources736=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources736=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources736.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources651 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources651=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof int[]) theItemsysItemresources651=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof long[]) theItemsysItemresources651=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof float[]) theItemsysItemresources651=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof double[]) theItemsysItemresources651=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof byte[]) theItemsysItemresources651=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof String[]) theItemsysItemresources651=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources651=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources651.forEach(resource->{
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
List theItemsysItemresources429 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources429=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof int[]) theItemsysItemresources429=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof long[]) theItemsysItemresources429=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof float[]) theItemsysItemresources429=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof double[]) theItemsysItemresources429=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof byte[]) theItemsysItemresources429=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof String[]) theItemsysItemresources429=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources429=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources429.forEach(resource->{
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
sb.append("		} ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}