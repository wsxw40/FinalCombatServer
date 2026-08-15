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

public class misticPinTu implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( (O2oUtil.compare(context.get("erroCode"),"!=",null))){
sb.append("	error=\"");
sb.append(context.get("erroCode"));
sb.append("\"  ");
sb.append("\r\n");
} else {
sb.append("	error={}  ");
sb.append("\r\n");
}
sb.append("misticAward={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("misticAward"),"!=",null)){
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List misticAwardsysItemcharacterList664 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof List) misticAwardsysItemcharacterList664=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList").getClass().isArray()) misticAwardsysItemcharacterList664=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
}
misticAwardsysItemcharacterList664.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List misticAwardsysItemgunPropertypropertyList396 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof List) misticAwardsysItemgunPropertypropertyList396=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList").getClass().isArray()) misticAwardsysItemgunPropertypropertyList396=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
misticAwardsysItemgunPropertypropertyList396.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.gunProperty.color"));
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
List misticAwardsysItempackages985 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof List) misticAwardsysItempackages985=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages").getClass().isArray()) misticAwardsysItempackages985=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
}
misticAwardsysItempackages985.forEach(item->{
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
List misticAwardsysItemgpPricesList863 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof List) misticAwardsysItemgpPricesList863=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList").getClass().isArray()) misticAwardsysItemgpPricesList863=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
misticAwardsysItemgpPricesList863.forEach(pay->{
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
List misticAwardsysItemcrPricesList189 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof List) misticAwardsysItemcrPricesList189=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList").getClass().isArray()) misticAwardsysItemcrPricesList189=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
}
misticAwardsysItemcrPricesList189.forEach(pay->{
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
List misticAwardsysItemvoucherPricesList198 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof List) misticAwardsysItemvoucherPricesList198=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList").getClass().isArray()) misticAwardsysItemvoucherPricesList198=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
misticAwardsysItemvoucherPricesList198.forEach(pay->{
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
sb.append("		},				 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List misticAwardsysItemresource525 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof List) misticAwardsysItemresource525=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource").getClass().isArray()) misticAwardsysItemresource525=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
}
misticAwardsysItemresource525.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources976 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources976=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources976=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources976.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources426 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources426=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources426=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources426.forEach(resource->{
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
List misticAwardsysItemresources93 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources93=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources93=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources93.forEach(resource->{
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
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}