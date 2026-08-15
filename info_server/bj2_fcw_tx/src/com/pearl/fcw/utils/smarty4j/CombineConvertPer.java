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

public class CombineConvertPer implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rate=");
sb.append(context.get("rate"));
sb.append(" ");
sb.append("\r\n");
sb.append("from_rate=");
sb.append(context.get("rateFrom"));
sb.append(" ");
sb.append("\r\n");
sb.append("to_rate=");
sb.append(context.get("rateTo"));
sb.append(" ");
sb.append("\r\n");
sb.append("from=  ");
sb.append("{ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("fromItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List fromItemsysItemcharacterList813 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof List) fromItemsysItemcharacterList813=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof int[]) fromItemsysItemcharacterList813=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof long[]) fromItemsysItemcharacterList813=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof float[]) fromItemsysItemcharacterList813=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof double[]) fromItemsysItemcharacterList813=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof byte[]) fromItemsysItemcharacterList813=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof String[]) fromItemsysItemcharacterList813=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList").getClass().isArray()) fromItemsysItemcharacterList813=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
}
fromItemsysItemcharacterList813.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List fromItemgunPropertypropertyList844 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof List) fromItemgunPropertypropertyList844=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof int[]) fromItemgunPropertypropertyList844=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof long[]) fromItemgunPropertypropertyList844=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof float[]) fromItemgunPropertypropertyList844=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof double[]) fromItemgunPropertypropertyList844=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof byte[]) fromItemgunPropertypropertyList844=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof String[]) fromItemgunPropertypropertyList844=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList").getClass().isArray()) fromItemgunPropertypropertyList844=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
fromItemgunPropertypropertyList844.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.color"));
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
List fromItemgunPropertystrPropertyList469 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof List) fromItemgunPropertystrPropertyList469=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof int[]) fromItemgunPropertystrPropertyList469=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof long[]) fromItemgunPropertystrPropertyList469=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof float[]) fromItemgunPropertystrPropertyList469=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof double[]) fromItemgunPropertystrPropertyList469=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof byte[]) fromItemgunPropertystrPropertyList469=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof String[]) fromItemgunPropertystrPropertyList469=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList").getClass().isArray()) fromItemgunPropertystrPropertyList469=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
fromItemgunPropertystrPropertyList469.forEach(property->{
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
List fromItemparts109 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof List) fromItemparts109=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof int[]) fromItemparts109=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof long[]) fromItemparts109=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof float[]) fromItemparts109=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof double[]) fromItemparts109=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof byte[]) fromItemparts109=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof String[]) fromItemparts109=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts").getClass().isArray()) fromItemparts109=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
}
fromItemparts109.forEach(part->{
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
List fromItemsysItemgpPricesList425 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof List) fromItemsysItemgpPricesList425=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof int[]) fromItemsysItemgpPricesList425=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof long[]) fromItemsysItemgpPricesList425=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof float[]) fromItemsysItemgpPricesList425=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof double[]) fromItemsysItemgpPricesList425=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof byte[]) fromItemsysItemgpPricesList425=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof String[]) fromItemsysItemgpPricesList425=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList").getClass().isArray()) fromItemsysItemgpPricesList425=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
fromItemsysItemgpPricesList425.forEach(pay->{
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
sb.append("		 crprices={ ");
sb.append("\r\n");
List fromItemsysItemcrPricesList613 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof List) fromItemsysItemcrPricesList613=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof int[]) fromItemsysItemcrPricesList613=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof long[]) fromItemsysItemcrPricesList613=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof float[]) fromItemsysItemcrPricesList613=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof double[]) fromItemsysItemcrPricesList613=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof byte[]) fromItemsysItemcrPricesList613=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof String[]) fromItemsysItemcrPricesList613=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList").getClass().isArray()) fromItemsysItemcrPricesList613=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
fromItemsysItemcrPricesList613.forEach(pay->{
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
List fromItemsysItemvoucherPricesList618 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof List) fromItemsysItemvoucherPricesList618=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof int[]) fromItemsysItemvoucherPricesList618=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof long[]) fromItemsysItemvoucherPricesList618=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof float[]) fromItemsysItemvoucherPricesList618=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof double[]) fromItemsysItemvoucherPricesList618=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof byte[]) fromItemsysItemvoucherPricesList618=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof String[]) fromItemsysItemvoucherPricesList618=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList").getClass().isArray()) fromItemsysItemvoucherPricesList618=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
fromItemsysItemvoucherPricesList618.forEach(pay->{
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
List fromItempackages187 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof List) fromItempackages187=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof int[]) fromItempackages187=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof long[]) fromItempackages187=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof float[]) fromItempackages187=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof double[]) fromItempackages187=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof byte[]) fromItempackages187=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof String[]) fromItempackages187=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages").getClass().isArray()) fromItempackages187=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
}
fromItempackages187.forEach(item->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List fromItemresource314 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof List) fromItemresource314=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof int[]) fromItemresource314=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof long[]) fromItemresource314=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof float[]) fromItemresource314=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof double[]) fromItemresource314=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof byte[]) fromItemresource314=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof String[]) fromItemresource314=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource").getClass().isArray()) fromItemresource314=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
}
fromItemresource314.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List fromItemresources135 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources135=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources135=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources135=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources135=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources135=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources135=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources135=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources135=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources135.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List fromItemresources819 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources819=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources819=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources819=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources819=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources819=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources819=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources819=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources819=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources819.forEach(resource->{
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
List fromItemresources0 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources0=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources0=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources0=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources0=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources0=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources0=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources0=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources0=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources0.forEach(resource->{
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
}
sb.append("} ");
sb.append("\r\n");
sb.append("to=  ");
sb.append("{ ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("toItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List toItemsysItemcharacterList479 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof List) toItemsysItemcharacterList479=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof int[]) toItemsysItemcharacterList479=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof long[]) toItemsysItemcharacterList479=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof float[]) toItemsysItemcharacterList479=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof double[]) toItemsysItemcharacterList479=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof byte[]) toItemsysItemcharacterList479=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof String[]) toItemsysItemcharacterList479=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList").getClass().isArray()) toItemsysItemcharacterList479=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
}
toItemsysItemcharacterList479.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"),"==",13)){
sb.append("					0 , ");
sb.append("\r\n");
} else {
sb.append("					1 , ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List toItemgunPropertypropertyList17 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof List) toItemgunPropertypropertyList17=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof int[]) toItemgunPropertypropertyList17=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof long[]) toItemgunPropertypropertyList17=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof float[]) toItemgunPropertypropertyList17=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof double[]) toItemgunPropertypropertyList17=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof byte[]) toItemgunPropertypropertyList17=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof String[]) toItemgunPropertypropertyList17=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList").getClass().isArray()) toItemgunPropertypropertyList17=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
toItemgunPropertypropertyList17.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"));
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
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		combineDetail = { ");
sb.append("\r\n");
List toItemgunPropertystrPropertyList879 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof List) toItemgunPropertystrPropertyList879=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof int[]) toItemgunPropertystrPropertyList879=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof long[]) toItemgunPropertystrPropertyList879=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof float[]) toItemgunPropertystrPropertyList879=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof double[]) toItemgunPropertystrPropertyList879=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof byte[]) toItemgunPropertystrPropertyList879=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof String[]) toItemgunPropertystrPropertyList879=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList").getClass().isArray()) toItemgunPropertystrPropertyList879=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
toItemgunPropertystrPropertyList879.forEach(property->{
try{
sb.append("			{index= ");
sb.append(O2oUtil.getSmarty4jProperty(property,"index"));
sb.append(", state=");
sb.append(O2oUtil.getSmarty4jProperty(property,"state"));
sb.append(", level=");
sb.append(O2oUtil.getSmarty4jProperty(property,"level"));
sb.append(", desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"desc"));
sb.append("\"},  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		 parts = { ");
sb.append("\r\n");
List toItemparts627 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof List) toItemparts627=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof int[]) toItemparts627=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof long[]) toItemparts627=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof float[]) toItemparts627=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof double[]) toItemparts627=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof byte[]) toItemparts627=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof String[]) toItemparts627=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts").getClass().isArray()) toItemparts627=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
}
toItemparts627.forEach(part->{
try{
sb.append("			 {");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("		 }, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List toItemsysItemgpPricesList487 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof List) toItemsysItemgpPricesList487=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof int[]) toItemsysItemgpPricesList487=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof long[]) toItemsysItemgpPricesList487=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof float[]) toItemsysItemgpPricesList487=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof double[]) toItemsysItemgpPricesList487=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof byte[]) toItemsysItemgpPricesList487=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof String[]) toItemsysItemgpPricesList487=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList").getClass().isArray()) toItemsysItemgpPricesList487=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
toItemsysItemgpPricesList487.forEach(pay->{
try{
sb.append("			{id=");
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List toItemsysItemcrPricesList318 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof List) toItemsysItemcrPricesList318=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof int[]) toItemsysItemcrPricesList318=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof long[]) toItemsysItemcrPricesList318=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof float[]) toItemsysItemcrPricesList318=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof double[]) toItemsysItemcrPricesList318=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof byte[]) toItemsysItemcrPricesList318=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof String[]) toItemsysItemcrPricesList318=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList").getClass().isArray()) toItemsysItemcrPricesList318=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
toItemsysItemcrPricesList318.forEach(pay->{
try{
sb.append("			{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List toItemsysItemvoucherPricesList32 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof List) toItemsysItemvoucherPricesList32=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof int[]) toItemsysItemvoucherPricesList32=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof long[]) toItemsysItemvoucherPricesList32=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof float[]) toItemsysItemvoucherPricesList32=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof double[]) toItemsysItemvoucherPricesList32=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof byte[]) toItemsysItemvoucherPricesList32=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof String[]) toItemsysItemvoucherPricesList32=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList").getClass().isArray()) toItemsysItemvoucherPricesList32=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
toItemsysItemvoucherPricesList32.forEach(pay->{
try{
sb.append("		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		 package = { ");
sb.append("\r\n");
List toItempackages287 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof List) toItempackages287=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof int[]) toItempackages287=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof long[]) toItempackages287=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof float[]) toItempackages287=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof double[]) toItempackages287=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof byte[]) toItempackages287=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof String[]) toItempackages287=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages").getClass().isArray()) toItempackages287=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
}
toItempackages287.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("		 }, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List toItemresource832 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof List) toItemresource832=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof int[]) toItemresource832=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof long[]) toItemresource832=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof float[]) toItemresource832=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof double[]) toItemresource832=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof byte[]) toItemresource832=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof String[]) toItemresource832=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource").getClass().isArray()) toItemresource832=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
}
toItemresource832.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e21){
logger.error(e21.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources717 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources717=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources717=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources717=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources717=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources717=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources717=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources717=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources717=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources717.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources709 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources709=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources709=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources709=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources709=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources709=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources709=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources709=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources709=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources709.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
} else {
List toItemresources578 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources578=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources578=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources578=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources578=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources578=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources578=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources578=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources578=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources578.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
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