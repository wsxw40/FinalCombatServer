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
sb.append("	error=nil  ");
sb.append("\r\n");
}
sb.append("misticAward={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("misticAward"),"!=",null)){
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List misticAwardsysItemcharacterList58 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof List) misticAwardsysItemcharacterList58=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof int[]) misticAwardsysItemcharacterList58=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof long[]) misticAwardsysItemcharacterList58=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof float[]) misticAwardsysItemcharacterList58=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof double[]) misticAwardsysItemcharacterList58=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof byte[]) misticAwardsysItemcharacterList58=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof String[]) misticAwardsysItemcharacterList58=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList").getClass().isArray()) misticAwardsysItemcharacterList58=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
}
misticAwardsysItemcharacterList58.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List misticAwardsysItemgunPropertypropertyList949 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof List) misticAwardsysItemgunPropertypropertyList949=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof int[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof long[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof float[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof double[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof byte[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof String[]) misticAwardsysItemgunPropertypropertyList949=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList").getClass().isArray()) misticAwardsysItemgunPropertypropertyList949=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
misticAwardsysItemgunPropertypropertyList949.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.color"));
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
List misticAwardsysItempackages123 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof List) misticAwardsysItempackages123=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof int[]) misticAwardsysItempackages123=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof long[]) misticAwardsysItempackages123=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof float[]) misticAwardsysItempackages123=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof double[]) misticAwardsysItempackages123=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof byte[]) misticAwardsysItempackages123=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof String[]) misticAwardsysItempackages123=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages").getClass().isArray()) misticAwardsysItempackages123=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
}
misticAwardsysItempackages123.forEach(item->{
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
List misticAwardsysItemgpPricesList606 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof List) misticAwardsysItemgpPricesList606=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof int[]) misticAwardsysItemgpPricesList606=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof long[]) misticAwardsysItemgpPricesList606=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof float[]) misticAwardsysItemgpPricesList606=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof double[]) misticAwardsysItemgpPricesList606=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof byte[]) misticAwardsysItemgpPricesList606=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList") instanceof String[]) misticAwardsysItemgpPricesList606=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList").getClass().isArray()) misticAwardsysItemgpPricesList606=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
misticAwardsysItemgpPricesList606.forEach(pay->{
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
List misticAwardsysItemcrPricesList323 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof List) misticAwardsysItemcrPricesList323=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof int[]) misticAwardsysItemcrPricesList323=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof long[]) misticAwardsysItemcrPricesList323=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof float[]) misticAwardsysItemcrPricesList323=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof double[]) misticAwardsysItemcrPricesList323=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof byte[]) misticAwardsysItemcrPricesList323=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList") instanceof String[]) misticAwardsysItemcrPricesList323=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList").getClass().isArray()) misticAwardsysItemcrPricesList323=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.crPricesList")).collect(Collectors.toList());
}
misticAwardsysItemcrPricesList323.forEach(pay->{
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
List misticAwardsysItemvoucherPricesList213 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof List) misticAwardsysItemvoucherPricesList213=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof int[]) misticAwardsysItemvoucherPricesList213=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof long[]) misticAwardsysItemvoucherPricesList213=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof float[]) misticAwardsysItemvoucherPricesList213=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof double[]) misticAwardsysItemvoucherPricesList213=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof byte[]) misticAwardsysItemvoucherPricesList213=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList") instanceof String[]) misticAwardsysItemvoucherPricesList213=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList").getClass().isArray()) misticAwardsysItemvoucherPricesList213=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
misticAwardsysItemvoucherPricesList213.forEach(pay->{
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
sb.append("		},				 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List misticAwardsysItemresource483 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof List) misticAwardsysItemresource483=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof int[]) misticAwardsysItemresource483=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof long[]) misticAwardsysItemresource483=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof float[]) misticAwardsysItemresource483=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof double[]) misticAwardsysItemresource483=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof byte[]) misticAwardsysItemresource483=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof String[]) misticAwardsysItemresource483=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource").getClass().isArray()) misticAwardsysItemresource483=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
}
misticAwardsysItemresource483.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources252 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources252=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof int[]) misticAwardsysItemresources252=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof long[]) misticAwardsysItemresources252=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof float[]) misticAwardsysItemresources252=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof double[]) misticAwardsysItemresources252=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof byte[]) misticAwardsysItemresources252=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof String[]) misticAwardsysItemresources252=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources252=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources252.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources671 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources671=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof int[]) misticAwardsysItemresources671=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof long[]) misticAwardsysItemresources671=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof float[]) misticAwardsysItemresources671=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof double[]) misticAwardsysItemresources671=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof byte[]) misticAwardsysItemresources671=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof String[]) misticAwardsysItemresources671=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources671=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources671.forEach(resource->{
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
List misticAwardsysItemresources43 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources43=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof int[]) misticAwardsysItemresources43=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof long[]) misticAwardsysItemresources43=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof float[]) misticAwardsysItemresources43=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof double[]) misticAwardsysItemresources43=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof byte[]) misticAwardsysItemresources43=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof String[]) misticAwardsysItemresources43=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources43=Stream.of(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources43.forEach(resource->{
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