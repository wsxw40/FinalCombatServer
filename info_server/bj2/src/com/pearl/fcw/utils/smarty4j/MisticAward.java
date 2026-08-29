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

public class MisticAward implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
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
List misticAwardsysItemcharacterList784 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList") instanceof List) misticAwardsysItemcharacterList784=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList").getClass().isArray()) misticAwardsysItemcharacterList784=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.characterList")).collect(Collectors.toList());
}
misticAwardsysItemcharacterList784.forEach(ids->{
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
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.level"));
sb.append(",			 ");
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
sb.append("					-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
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
sb.append("		 performance = { ");
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
List misticAwardsysItemgunPropertypropertyList324 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList") instanceof List) misticAwardsysItemgunPropertypropertyList324=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList").getClass().isArray()) misticAwardsysItemgunPropertypropertyList324=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
misticAwardsysItemgunPropertypropertyList324.forEach(property->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.needTeamPlaceLevel"),">",99)){
sb.append("			suitId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.belongSuit.suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			suitDetail={ ");
sb.append("\r\n");
sb.append("				des4={ ");
sb.append("\r\n");
List misticAwardsysItembelongSuitallSpec4Pros204 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec4Pros")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec4Pros") instanceof List) misticAwardsysItembelongSuitallSpec4Pros204=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec4Pros");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec4Pros").getClass().isArray()) misticAwardsysItembelongSuitallSpec4Pros204=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec4Pros")).collect(Collectors.toList());
}
misticAwardsysItembelongSuitallSpec4Pros204.forEach(property->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				des6={ ");
sb.append("\r\n");
List misticAwardsysItembelongSuitallSpec6Pros576 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec6Pros")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec6Pros") instanceof List) misticAwardsysItembelongSuitallSpec6Pros576=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec6Pros");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec6Pros").getClass().isArray()) misticAwardsysItembelongSuitallSpec6Pros576=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.belongSuit.allSpec6Pros")).collect(Collectors.toList());
}
misticAwardsysItembelongSuitallSpec6Pros576.forEach(property->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
}
sb.append("		package = { ");
sb.append("\r\n");
List misticAwardsysItempackages779 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages") instanceof List) misticAwardsysItempackages779=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages").getClass().isArray()) misticAwardsysItempackages779=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.packages")).collect(Collectors.toList());
}
misticAwardsysItempackages779.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List misticAwardsysItemresource104 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource") instanceof List) misticAwardsysItemresource104=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource").getClass().isArray()) misticAwardsysItemresource104=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resource")).collect(Collectors.toList());
}
misticAwardsysItemresource104.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e6){
logger.error(e6.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources448 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources448=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources448=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources448.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",3)) && (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"),"==",2))){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources889 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources889=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources889=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources889.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.type"),"==",3)) && (O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"),"==",3)) ){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("misticAward"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List misticAwardsysItemresources85 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources85=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources85=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources85.forEach(resource->{
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
List misticAwardsysItemresources264 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources") instanceof List) misticAwardsysItemresources264=(List<?>)O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources").getClass().isArray()) misticAwardsysItemresources264=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("misticAward"),"sysItem.resources")).collect(Collectors.toList());
}
misticAwardsysItemresources264.forEach(resource->{
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
sb.append("	flags={");
sb.append(context.get("flags"));
sb.append("} ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}