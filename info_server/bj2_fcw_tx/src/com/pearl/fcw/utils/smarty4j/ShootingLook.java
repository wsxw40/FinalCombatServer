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

public class ShootingLook implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("bout_num=");
sb.append(context.get("boutNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_num=");
sb.append(context.get("dartleNum"));
sb.append("    ");
sb.append("\r\n");
sb.append("ammo_num=");
sb.append(context.get("ammoNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("look_num = ");
sb.append(context.get("lookNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("needDartleNum=");
sb.append(context.get("needDartleNum"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("list ={ ");
sb.append("\r\n");
List gifts986 = new ArrayList<>();
if (null!=context.get("gifts")){
if (context.get("gifts") instanceof List) gifts986=(List<?>)context.get("gifts");
else if (context.get("gifts") instanceof int[]) gifts986=Stream.of((int[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts") instanceof long[]) gifts986=Stream.of((long[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts") instanceof float[]) gifts986=Stream.of((float[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts") instanceof double[]) gifts986=Stream.of((double[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts") instanceof byte[]) gifts986=Stream.of((byte[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts") instanceof String[]) gifts986=Stream.of((String[])context.get("gifts")).collect(Collectors.toList());
else if (context.get("gifts").getClass().isArray()) gifts986=Stream.of(context.get("gifts")).collect(Collectors.toList());
}
gifts986.forEach(onlineAward->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List onlineAwardsysItemcharacterList763 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList763=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof int[]) onlineAwardsysItemcharacterList763=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof long[]) onlineAwardsysItemcharacterList763=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof float[]) onlineAwardsysItemcharacterList763=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof double[]) onlineAwardsysItemcharacterList763=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof byte[]) onlineAwardsysItemcharacterList763=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof String[]) onlineAwardsysItemcharacterList763=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList763=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList763.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List onlineAwardsysItemgunPropertypropertyList10 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList10=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof int[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof long[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof float[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof double[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof byte[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof String[]) onlineAwardsysItemgunPropertypropertyList10=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList10=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList10.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"));
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
List onlineAwardsysItempackages423 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof List) onlineAwardsysItempackages423=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof int[]) onlineAwardsysItempackages423=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof long[]) onlineAwardsysItempackages423=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof float[]) onlineAwardsysItempackages423=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof double[]) onlineAwardsysItempackages423=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof byte[]) onlineAwardsysItempackages423=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof String[]) onlineAwardsysItempackages423=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages423=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages423.forEach(item->{
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
List onlineAwardsysItemgpPricesList425 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList425=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof int[]) onlineAwardsysItemgpPricesList425=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof long[]) onlineAwardsysItemgpPricesList425=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof float[]) onlineAwardsysItemgpPricesList425=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof double[]) onlineAwardsysItemgpPricesList425=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof byte[]) onlineAwardsysItemgpPricesList425=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof String[]) onlineAwardsysItemgpPricesList425=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList425=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList425.forEach(pay->{
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
List onlineAwardsysItemcrPricesList142 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList142=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof int[]) onlineAwardsysItemcrPricesList142=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof long[]) onlineAwardsysItemcrPricesList142=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof float[]) onlineAwardsysItemcrPricesList142=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof double[]) onlineAwardsysItemcrPricesList142=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof byte[]) onlineAwardsysItemcrPricesList142=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof String[]) onlineAwardsysItemcrPricesList142=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList142=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList142.forEach(pay->{
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
List onlineAwardsysItemvoucherPricesList174 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList174=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof int[]) onlineAwardsysItemvoucherPricesList174=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof long[]) onlineAwardsysItemvoucherPricesList174=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof float[]) onlineAwardsysItemvoucherPricesList174=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof double[]) onlineAwardsysItemvoucherPricesList174=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof byte[]) onlineAwardsysItemvoucherPricesList174=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof String[]) onlineAwardsysItemvoucherPricesList174=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList174=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList174.forEach(pay->{
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
sb.append("		},		 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource115 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof List) onlineAwardsysItemresource115=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof int[]) onlineAwardsysItemresource115=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof long[]) onlineAwardsysItemresource115=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof float[]) onlineAwardsysItemresource115=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof double[]) onlineAwardsysItemresource115=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof byte[]) onlineAwardsysItemresource115=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof String[]) onlineAwardsysItemresource115=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource115=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource115.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources418 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources418=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources418=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources418=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources418=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources418=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources418=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources418=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources418=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources418.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources466 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources466=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources466=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources466=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources466=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources466=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources466=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources466=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources466=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources466.forEach(resource->{
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
List onlineAwardsysItemresources125 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources125=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources125=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources125=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources125=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources125=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources125=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources125=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources125=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources125.forEach(resource->{
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
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_gift =  { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List dartleGiftsysItemcharacterList82 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof List) dartleGiftsysItemcharacterList82=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof int[]) dartleGiftsysItemcharacterList82=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof long[]) dartleGiftsysItemcharacterList82=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof float[]) dartleGiftsysItemcharacterList82=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof double[]) dartleGiftsysItemcharacterList82=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof byte[]) dartleGiftsysItemcharacterList82=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof String[]) dartleGiftsysItemcharacterList82=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList").getClass().isArray()) dartleGiftsysItemcharacterList82=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
}
dartleGiftsysItemcharacterList82.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List dartleGiftsysItemgunPropertypropertyList568 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof List) dartleGiftsysItemgunPropertypropertyList568=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof int[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof long[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof float[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof double[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof byte[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof String[]) dartleGiftsysItemgunPropertypropertyList568=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) dartleGiftsysItemgunPropertypropertyList568=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
dartleGiftsysItemgunPropertypropertyList568.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"));
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
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List dartleGiftsysItempackages830 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof List) dartleGiftsysItempackages830=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof int[]) dartleGiftsysItempackages830=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof long[]) dartleGiftsysItempackages830=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof float[]) dartleGiftsysItempackages830=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof double[]) dartleGiftsysItempackages830=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof byte[]) dartleGiftsysItempackages830=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof String[]) dartleGiftsysItempackages830=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages").getClass().isArray()) dartleGiftsysItempackages830=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
}
dartleGiftsysItempackages830.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List dartleGiftsysItemgpPricesList850 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof List) dartleGiftsysItemgpPricesList850=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof int[]) dartleGiftsysItemgpPricesList850=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof long[]) dartleGiftsysItemgpPricesList850=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof float[]) dartleGiftsysItemgpPricesList850=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof double[]) dartleGiftsysItemgpPricesList850=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof byte[]) dartleGiftsysItemgpPricesList850=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof String[]) dartleGiftsysItemgpPricesList850=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList").getClass().isArray()) dartleGiftsysItemgpPricesList850=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemgpPricesList850.forEach(pay->{
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
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List dartleGiftsysItemcrPricesList851 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof List) dartleGiftsysItemcrPricesList851=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof int[]) dartleGiftsysItemcrPricesList851=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof long[]) dartleGiftsysItemcrPricesList851=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof float[]) dartleGiftsysItemcrPricesList851=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof double[]) dartleGiftsysItemcrPricesList851=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof byte[]) dartleGiftsysItemcrPricesList851=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof String[]) dartleGiftsysItemcrPricesList851=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList").getClass().isArray()) dartleGiftsysItemcrPricesList851=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemcrPricesList851.forEach(pay->{
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List dartleGiftsysItemvoucherPricesList791 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof List) dartleGiftsysItemvoucherPricesList791=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof int[]) dartleGiftsysItemvoucherPricesList791=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof long[]) dartleGiftsysItemvoucherPricesList791=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof float[]) dartleGiftsysItemvoucherPricesList791=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof double[]) dartleGiftsysItemvoucherPricesList791=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof byte[]) dartleGiftsysItemvoucherPricesList791=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof String[]) dartleGiftsysItemvoucherPricesList791=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList").getClass().isArray()) dartleGiftsysItemvoucherPricesList791=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemvoucherPricesList791.forEach(pay->{
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
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("	},	 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List dartleGiftsysItemresource224 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof List) dartleGiftsysItemresource224=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof int[]) dartleGiftsysItemresource224=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof long[]) dartleGiftsysItemresource224=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof float[]) dartleGiftsysItemresource224=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof double[]) dartleGiftsysItemresource224=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof byte[]) dartleGiftsysItemresource224=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof String[]) dartleGiftsysItemresource224=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource").getClass().isArray()) dartleGiftsysItemresource224=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
}
dartleGiftsysItemresource224.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e18){
logger.error(e18.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources3 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources3=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources3=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources3=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources3=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources3=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources3=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources3=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources3=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources3.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources161 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources161=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources161=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources161=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources161=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources161=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources161=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources161=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources161=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources161.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
} else {
List dartleGiftsysItemresources856 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources856=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources856=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources856=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources856=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources856=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources856=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources856=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources856=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources856.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_top_list = { ");
sb.append("\r\n");
List dartleTopList937 = new ArrayList<>();
if (null!=context.get("dartleTopList")){
if (context.get("dartleTopList") instanceof List) dartleTopList937=(List<?>)context.get("dartleTopList");
else if (context.get("dartleTopList") instanceof int[]) dartleTopList937=Stream.of((int[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof long[]) dartleTopList937=Stream.of((long[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof float[]) dartleTopList937=Stream.of((float[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof double[]) dartleTopList937=Stream.of((double[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof byte[]) dartleTopList937=Stream.of((byte[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof String[]) dartleTopList937=Stream.of((String[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList").getClass().isArray()) dartleTopList937=Stream.of(context.get("dartleTopList")).collect(Collectors.toList());
}
dartleTopList937.forEach(entry->{
try{
sb.append("		");
sb.append(entry);
sb.append(" ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_awards_list ={ ");
sb.append("\r\n");
List awardsList868 = new ArrayList<>();
if (null!=context.get("awardsList")){
if (context.get("awardsList") instanceof List) awardsList868=(List<?>)context.get("awardsList");
else if (context.get("awardsList") instanceof int[]) awardsList868=Stream.of((int[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof long[]) awardsList868=Stream.of((long[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof float[]) awardsList868=Stream.of((float[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof double[]) awardsList868=Stream.of((double[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof byte[]) awardsList868=Stream.of((byte[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof String[]) awardsList868=Stream.of((String[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList").getClass().isArray()) awardsList868=Stream.of(context.get("awardsList")).collect(Collectors.toList());
}
awardsList868.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		{ ");
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
List theItemsysItemcharacterList301 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList301=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList301=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList301=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList301=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList301=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList301=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList301=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList301=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList301.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
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
sb.append("				strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("						-1 , ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
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
sb.append("		    		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			    	speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			    	damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		    		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			    	ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			    	ammo_count=");
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
List theItemsysItemgunPropertypropertyList26 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList26=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList26=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList26=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList26=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList26=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList26=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList26=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList26=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList26.forEach(property->{
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
sb.append("	    				\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("				},  ");
sb.append("\r\n");
}catch(Exception e25){
logger.error(e25.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItemsysItempackages11 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages11=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages11=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages11=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages11=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages11=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages11=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages11=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages11=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages11.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e26){
logger.error(e26.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList871 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList871=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList871=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList871=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList871=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList871=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList871=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList871=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList871=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList871.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList711 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList711=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList711=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList711=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList711=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList711=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList711=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList711=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList711=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList711.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e28){
logger.error(e28.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList336 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList336=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList336=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList336=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList336=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList336=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList336=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList336=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList336=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList336.forEach(pay->{
try{
sb.append("	    			{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource793 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource793=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource793=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource793=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource793=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource793=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource793=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource793=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource793=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource793.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e30){
logger.error(e30.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources496 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources496=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources496=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources496=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources496=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources496=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources496=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources496=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources496=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources496.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e31){
logger.error(e31.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources579 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources579=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources579=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources579=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources579=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources579=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources579=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources579=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources579=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources579.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
}
});
} else {
List theItemsysItemresources127 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources127=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources127=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources127=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources127=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources127=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources127=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources127=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources127=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources127.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e33){
logger.error(e33.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},   ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e33){
logger.error(e33.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}