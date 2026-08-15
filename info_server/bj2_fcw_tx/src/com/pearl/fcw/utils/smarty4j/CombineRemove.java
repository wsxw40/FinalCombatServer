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

public class CombineRemove implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(context.get("result"));
sb.append(" ");
sb.append("\r\n");
sb.append("item= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("theItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList862 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof List) theItemsysItemcharacterList862=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList862=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList862=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList862=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList862=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList862=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList862=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList862=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList862.forEach(ids->{
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
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"materialNeed"));
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"timeLeft"));
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
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"fightNum"));
sb.append(",       		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"cResistanceKnife"));
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
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		 color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList322 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList322=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList322=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList322=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList322=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList322=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList322=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList322=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList322=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList322.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("					1, ");
sb.append("\r\n");
}
sb.append("				\"");
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
sb.append("		combineDetail = { ");
sb.append("\r\n");
List theItemgunPropertystrPropertyList528 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList528=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList528=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList528=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList528=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList528=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList528=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList528=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList528=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList528.forEach(property->{
try{
sb.append("			{index= ");
sb.append(O2oUtil.getSmarty4jProperty(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jProperty(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jProperty(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\", open=");
sb.append(O2oUtil.getSmarty4jProperty(property,"open"));
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
List theItemparts217 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof List) theItemparts217=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof int[]) theItemparts217=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof long[]) theItemparts217=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof float[]) theItemparts217=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof double[]) theItemparts217=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof byte[]) theItemparts217=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof String[]) theItemparts217=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts").getClass().isArray()) theItemparts217=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
}
theItemparts217.forEach(part->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
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
List theItemsysItemgpPricesList97 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList97=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList97=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList97=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList97=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList97=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList97=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList97=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList97=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList97.forEach(pay->{
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
List theItemsysItemcrPricesList386 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList386=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList386=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList386=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList386=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList386=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList386=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList386=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList386=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList386.forEach(pay->{
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
List theItemsysItemvoucherPricesList921 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList921=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList921=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList921=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList921=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList921=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList921=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList921=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList921=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList921.forEach(pay->{
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
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List theItempackages780 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof List) theItempackages780=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof int[]) theItempackages780=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof long[]) theItempackages780=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof float[]) theItempackages780=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof double[]) theItempackages780=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof byte[]) theItempackages780=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof String[]) theItempackages780=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages").getClass().isArray()) theItempackages780=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
}
theItempackages780.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource534 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource534=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof int[]) theItemresource534=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof long[]) theItemresource534=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof float[]) theItemresource534=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof double[]) theItemresource534=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof byte[]) theItemresource534=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof String[]) theItemresource534=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource534=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource534.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources679 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources679=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources679=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources679=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources679=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources679=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources679=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources679=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources679=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources679.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources9 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources9=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources9=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources9=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources9=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources9=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources9=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources9=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources9=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources9.forEach(resource->{
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
List theItemresources737 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources737=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources737=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources737=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources737=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources737=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources737=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources737=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources737=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources737.forEach(resource->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}