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

public class CombineStrength implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(context.get("result"));
sb.append(" ");
sb.append("\r\n");
sb.append("items= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("playerItem"),"!=",null)){
sb.append("	{    ");
sb.append("\r\n");
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List playerItemsysItemcharacterList958 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof List) playerItemsysItemcharacterList958=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList").getClass().isArray()) playerItemsysItemcharacterList958=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
}
playerItemsysItemcharacterList958.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gpNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gpNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    		speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    		ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    		ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		nextLevelDetail = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextDamange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextShootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.shootSpeed")));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			level =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"level"),">=",18)){
sb.append("					18 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"level")) + O2oUtil.parseFloat(1));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextCResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextCResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextCResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextCResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			holesNum = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextHoleNum"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextColor"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    		cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextBloodAdd"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List playerItemgunPropertypropertyList860 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof List) playerItemgunPropertypropertyList860=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList").getClass().isArray()) playerItemgunPropertypropertyList860=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
playerItemgunPropertypropertyList860.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gunProperty.color"));
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
sb.append("		combineDetail = { ");
sb.append("\r\n");
List playerItemgunPropertystrPropertyList744 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof List) playerItemgunPropertystrPropertyList744=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList").getClass().isArray()) playerItemgunPropertystrPropertyList744=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
playerItemgunPropertystrPropertyList744.forEach(property->{
try{
sb.append("	    		{index= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"open"));
sb.append("},  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List playerItemparts875 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof List) playerItemparts875=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts").getClass().isArray()) playerItemparts875=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
}
playerItemparts875.forEach(part->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List playerItemsysItemgpPricesList600 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof List) playerItemsysItemgpPricesList600=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList").getClass().isArray()) playerItemsysItemgpPricesList600=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
playerItemsysItemgpPricesList600.forEach(pay->{
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
List playerItemsysItemcrPricesList228 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof List) playerItemsysItemcrPricesList228=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList").getClass().isArray()) playerItemsysItemcrPricesList228=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
playerItemsysItemcrPricesList228.forEach(pay->{
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
List playerItemsysItemvoucherPricesList949 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof List) playerItemsysItemvoucherPricesList949=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList").getClass().isArray()) playerItemsysItemvoucherPricesList949=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
playerItemsysItemvoucherPricesList949.forEach(pay->{
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
sb.append("		package = { ");
sb.append("\r\n");
List playerItempackages656 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof List) playerItempackages656=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages").getClass().isArray()) playerItempackages656=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
}
playerItempackages656.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerItemresource696 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof List) playerItemresource696=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource").getClass().isArray()) playerItemresource696=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
}
playerItemresource696.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List playerItemresources528 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources528=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources528=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources528.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List playerItemresources244 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources244=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources244=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources244.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
} else {
List playerItemresources115 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources115=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources115=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources115.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	}, ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
sb.append("items2= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("itemList"),"!=",null)){
List itemList756 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList756=(List<?>)context.get("itemList");
else if (context.get("itemList").getClass().isArray()) itemList756=Stream.of((Object[])context.get("itemList")).collect(Collectors.toList());
}
itemList756.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
if( O2oUtil.compare(theItem,"!=",null)){
sb.append("				playeritemid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("				sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("					iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList283 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList283=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList283=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList283.forEach(ids->{
try{
sb.append("						");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("				repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("					wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("						0 , ");
sb.append("\r\n");
} else {
sb.append("						1 , ");
sb.append("\r\n");
}
sb.append("				mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				common={ ");
sb.append("\r\n");
sb.append("					level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("					type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("						wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("					minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_new=1, ");
sb.append("\r\n");
sb.append("					star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("					strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("							-1 , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("					holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("					rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				performance = { ");
sb.append("\r\n");
sb.append("					damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("					speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("					damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("					speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("					ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList204 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList204=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList204=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList204.forEach(property->{
try{
sb.append("					{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("							1, ");
sb.append("\r\n");
}
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("					},  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				parts = { ");
sb.append("\r\n");
List theItemparts47 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts47=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts47=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts47.forEach(part->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList697 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList697=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList697=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList697.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList996 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList996=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList996=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList996.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList128 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList128=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList128=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList128.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("				},	 ");
sb.append("\r\n");
sb.append("				package = { ");
sb.append("\r\n");
List theItempackages700 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages700=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages700=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages700.forEach(item->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource360 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource360=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource360=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource360.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("								\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e21){
logger.error(e21.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources768 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources768=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources768=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources768.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources700 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources700=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources700=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources700.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
} else {
List theItemresources988 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources988=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources988=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources988.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
sb.append("				}, ");
sb.append("\r\n");
}
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
sb.append("} ");
sb.append("\r\n");
sb.append("failAwards={ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("failAwards"),"!=",null)){
List failAwards114 = new ArrayList<>();
if (null!=context.get("failAwards")){
if (context.get("failAwards") instanceof List) failAwards114=(List<?>)context.get("failAwards");
else if (context.get("failAwards").getClass().isArray()) failAwards114=Stream.of((Object[])context.get("failAwards")).collect(Collectors.toList());
}
failAwards114.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
if( O2oUtil.compare(theItem,"!=",null)){
sb.append("				sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"Id"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"!=",null)){
sb.append("					iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				item_num =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"unitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				characters={ ");
sb.append("\r\n");
List theItemcharacterList962 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList962=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList962=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList962.forEach(ids->{
try{
sb.append("						");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e26){
logger.error(e26.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("				repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"wId"),"==",4)){
sb.append("					wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"==",1)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"==",5)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("						0 , ");
sb.append("\r\n");
} else {
sb.append("						1 , ");
sb.append("\r\n");
}
sb.append("				mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				common={ ");
sb.append("\r\n");
sb.append("					level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("					type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("						wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("					minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_new=1, ");
sb.append("\r\n");
sb.append("					star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("					strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isStrengthen"),"==",0)){
sb.append("							-1 , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("					holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("					rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				performance = { ");
sb.append("\r\n");
sb.append("					damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("					speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("					damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("					speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("					ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList766 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList766=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList766=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList766.forEach(property->{
try{
sb.append("					{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("							1, ");
sb.append("\r\n");
}
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("					},  ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				parts = { ");
sb.append("\r\n");
List theItemparts26 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts26=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts26=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts26.forEach(part->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e28){
logger.error(e28.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				gpprices={ ");
sb.append("\r\n");
List theItemgpPricesList514 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList514=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList514=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList514.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				crprices={ ");
sb.append("\r\n");
List theItemcrPricesList71 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList71=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList71=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList71.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e30){
logger.error(e30.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				voucherprices={ ");
sb.append("\r\n");
List theItemvoucherPricesList903 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList903=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList903=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList903.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e31){
logger.error(e31.toString());
}
});
sb.append("				},	 ");
sb.append("\r\n");
sb.append("				package = { ");
sb.append("\r\n");
List theItempackages795 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages795=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages795=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages795.forEach(item->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
}
});
sb.append("				}, ");
sb.append("\r\n");
sb.append("				resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource978 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource978=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource978=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource978.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("								\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e33){
logger.error(e33.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",2)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources799 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources799=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources799=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources799.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e34){
logger.error(e34.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",3)){
sb.append("						type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources943 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources943=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources943=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources943.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e35){
logger.error(e35.toString());
}
});
} else {
List theItemresources524 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources524=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources524=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources524.forEach(resource->{
try{
sb.append("							\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e36){
logger.error(e36.toString());
}
});
}
sb.append("				}, ");
sb.append("\r\n");
}
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e36){
logger.error(e36.toString());
}
});
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}