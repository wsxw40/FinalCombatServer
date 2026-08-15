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

public class ShootingAmmo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list= { ");
sb.append("\r\n");
List playerItems855 = new ArrayList<>();
if (null!=context.get("playerItems")){
if (context.get("playerItems") instanceof List) playerItems855=(List<?>)context.get("playerItems");
else if (context.get("playerItems").getClass().isArray()) playerItems855=Stream.of((Object[])context.get("playerItems")).collect(Collectors.toList());
}
playerItems855.forEach(sysItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List sysItemcharacterList55 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"characterList") instanceof List) sysItemcharacterList55=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"characterList").getClass().isArray()) sysItemcharacterList55=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"characterList")).collect(Collectors.toList());
}
sysItemcharacterList55.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		i_value = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(sysItem,"shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List sysItemgunPropertypropertyList120 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList") instanceof List) sysItemgunPropertypropertyList120=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList").getClass().isArray()) sysItemgunPropertypropertyList120=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
sysItemgunPropertypropertyList120.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"gunProperty.color"));
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
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List sysItempackages712 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"packages")){
if (O2oUtil.getSmarty4jProperty(sysItem,"packages") instanceof List) sysItempackages712=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"packages");
else if (O2oUtil.getSmarty4jProperty(sysItem,"packages").getClass().isArray()) sysItempackages712=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"packages")).collect(Collectors.toList());
}
sysItempackages712.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
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
List sysItemgpPricesList358 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList") instanceof List) sysItemgpPricesList358=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList").getClass().isArray()) sysItemgpPricesList358=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"gpPricesList")).collect(Collectors.toList());
}
sysItemgpPricesList358.forEach(pay->{
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
sb.append("		crprices={ ");
sb.append("\r\n");
List sysItemcrPricesList984 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList") instanceof List) sysItemcrPricesList984=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"crPricesList").getClass().isArray()) sysItemcrPricesList984=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"crPricesList")).collect(Collectors.toList());
}
sysItemcrPricesList984.forEach(pay->{
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List sysItemvoucherPricesList96 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList") instanceof List) sysItemvoucherPricesList96=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList").getClass().isArray()) sysItemvoucherPricesList96=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"voucherPricesList")).collect(Collectors.toList());
}
sysItemvoucherPricesList96.forEach(pay->{
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List sysItemresource596 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resource")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resource") instanceof List) sysItemresource596=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resource");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resource").getClass().isArray()) sysItemresource596=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resource")).collect(Collectors.toList());
}
sysItemresource596.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources132 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources132=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources132=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources132.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(sysItem,"type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List sysItemresources538 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources538=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources538=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources538.forEach(resource->{
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
List sysItemresources831 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(sysItem,"resources")){
if (O2oUtil.getSmarty4jProperty(sysItem,"resources") instanceof List) sysItemresources831=(List<?>)O2oUtil.getSmarty4jProperty(sysItem,"resources");
else if (O2oUtil.getSmarty4jProperty(sysItem,"resources").getClass().isArray()) sysItemresources831=Stream.of((Object[])O2oUtil.getSmarty4jProperty(sysItem,"resources")).collect(Collectors.toList());
}
sysItemresources831.forEach(resource->{
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
return sb.toString();
}

}