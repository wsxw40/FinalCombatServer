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

public class CombineInsert implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(context.get("result"));
sb.append(" ");
sb.append("\r\n");
sb.append("msg = \"");
sb.append(context.get("msg"));
sb.append("\" ");
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
List theItemsysItemcharacterList402 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof List) theItemsysItemcharacterList402=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList402=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList402=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList402=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList402=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList402=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList402=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList402=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList402.forEach(ids->{
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
sb.append(",   		 ");
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
List theItemgunPropertypropertyList971 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList971=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList971=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList971=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList971=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList971=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList971=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList971=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList971=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList971.forEach(property->{
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
List theItemgunPropertystrPropertyList471 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList471=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList471=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList471=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList471=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList471=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList471=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList471=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList471=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList471.forEach(property->{
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
List theItemparts939 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof List) theItemparts939=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof int[]) theItemparts939=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof long[]) theItemparts939=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof float[]) theItemparts939=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof double[]) theItemparts939=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof byte[]) theItemparts939=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof String[]) theItemparts939=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts").getClass().isArray()) theItemparts939=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
}
theItemparts939.forEach(part->{
try{
sb.append("		{");
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
List theItemsysItemgpPricesList788 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList788=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList788=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList788=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList788=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList788=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList788=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList788=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList788=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList788.forEach(pay->{
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList318 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList318=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList318=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList318=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList318=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList318=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList318=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList318=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList318=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList318.forEach(pay->{
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
List theItemsysItemvoucherPricesList125 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList125=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList125=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList125=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList125=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList125=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList125=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList125=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList125=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList125.forEach(pay->{
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
List theItempackages490 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof List) theItempackages490=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof int[]) theItempackages490=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof long[]) theItempackages490=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof float[]) theItempackages490=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof double[]) theItempackages490=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof byte[]) theItempackages490=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof String[]) theItempackages490=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages").getClass().isArray()) theItempackages490=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
}
theItempackages490.forEach(item->{
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
List theItemresource309 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource309=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof int[]) theItemresource309=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof long[]) theItemresource309=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof float[]) theItemresource309=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof double[]) theItemresource309=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof byte[]) theItemresource309=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof String[]) theItemresource309=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource309=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource309.forEach(res->{
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
List theItemresources351 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources351=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources351=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources351=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources351=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources351=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources351=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources351=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources351=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources351.forEach(resource->{
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
List theItemresources717 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources717=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources717=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources717=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources717=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources717=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources717=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources717=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources717=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources717.forEach(resource->{
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
List theItemresources467 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources467=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources467=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources467=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources467=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources467=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources467=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources467=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources467=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources467.forEach(resource->{
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