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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList432 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof List) theItemsysItemcharacterList432=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList432=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList432.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("		    	damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.damange"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.shootSpeed"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    	ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		    	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wAmmoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList871 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList871=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList871=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList871.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("	    			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
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
List theItemsysItempackages994 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages") instanceof List) theItemsysItempackages994=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages").getClass().isArray()) theItemsysItempackages994=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages994.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
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
List theItemsysItemgpPricesList240 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList240=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList240=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList240.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemcrPricesList295 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList295=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList295=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList295.forEach(pay->{
try{
sb.append("	    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
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
List theItemsysItemvoucherPricesList197 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList197=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList197=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList197.forEach(pay->{
try{
sb.append("	    		{id=");
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
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource266 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource") instanceof List) theItemsysItemresource266=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource").getClass().isArray()) theItemsysItemresource266=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource266.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources357 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources357=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources357=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources357.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources816 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources816=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources816=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources816.forEach(resource->{
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
List theItemsysItemresources672 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources") instanceof List) theItemsysItemresources672=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources").getClass().isArray()) theItemsysItemresources672=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources672.forEach(resource->{
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