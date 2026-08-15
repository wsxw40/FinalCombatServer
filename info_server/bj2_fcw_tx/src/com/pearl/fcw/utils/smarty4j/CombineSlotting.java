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

public class CombineSlotting implements Ctx {
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
List theItemsysItemcharacterList45 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof List) theItemsysItemcharacterList45=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList45=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList45=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList45=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList45=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList45=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList45=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList45=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList45.forEach(ids->{
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
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList695 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList695=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList695=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList695=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList695=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList695=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList695=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList695=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList695=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList695.forEach(property->{
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
List theItemgunPropertystrPropertyList512 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof List) theItemgunPropertystrPropertyList512=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof int[]) theItemgunPropertystrPropertyList512=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof long[]) theItemgunPropertystrPropertyList512=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof float[]) theItemgunPropertystrPropertyList512=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof double[]) theItemgunPropertystrPropertyList512=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof byte[]) theItemgunPropertystrPropertyList512=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList") instanceof String[]) theItemgunPropertystrPropertyList512=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList").getClass().isArray()) theItemgunPropertystrPropertyList512=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
theItemgunPropertystrPropertyList512.forEach(property->{
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
List theItemparts210 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof List) theItemparts210=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof int[]) theItemparts210=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof long[]) theItemparts210=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof float[]) theItemparts210=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof double[]) theItemparts210=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof byte[]) theItemparts210=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts") instanceof String[]) theItemparts210=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts").getClass().isArray()) theItemparts210=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"parts")).collect(Collectors.toList());
}
theItemparts210.forEach(part->{
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
List theItemsysItemgpPricesList374 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList374=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList374=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList374=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList374=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList374=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList374=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList374=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList374=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList374.forEach(pay->{
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
List theItemsysItemcrPricesList957 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList957=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList957=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList957=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList957=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList957=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList957=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList957=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList957=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList957.forEach(pay->{
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
List theItemsysItemvoucherPricesList870 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList870=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList870=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList870=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList870=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList870=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList870=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList870=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList870=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList870.forEach(pay->{
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
List theItempackages566 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof List) theItempackages566=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof int[]) theItempackages566=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof long[]) theItempackages566=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof float[]) theItempackages566=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof double[]) theItempackages566=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof byte[]) theItempackages566=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages") instanceof String[]) theItempackages566=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages").getClass().isArray()) theItempackages566=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"packages")).collect(Collectors.toList());
}
theItempackages566.forEach(item->{
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
List theItemresource582 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof List) theItemresource582=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof int[]) theItemresource582=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof long[]) theItemresource582=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof float[]) theItemresource582=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof double[]) theItemresource582=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof byte[]) theItemresource582=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource") instanceof String[]) theItemresource582=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource").getClass().isArray()) theItemresource582=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resource")).collect(Collectors.toList());
}
theItemresource582.forEach(res->{
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
List theItemresources815 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources815=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources815=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources815=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources815=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources815=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources815=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources815=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources815=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources815.forEach(resource->{
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
List theItemresources187 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources187=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources187=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources187=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources187=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources187=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources187=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources187=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources187=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources187.forEach(resource->{
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
List theItemresources614 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof List) theItemresources614=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof int[]) theItemresources614=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof long[]) theItemresources614=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof float[]) theItemresources614=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof double[]) theItemresources614=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof byte[]) theItemresources614=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources") instanceof String[]) theItemresources614=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources").getClass().isArray()) theItemresources614=Stream.of(O2oUtil.getSmarty4jProperty(context.get("theItem"),"resources")).collect(Collectors.toList());
}
theItemresources614.forEach(resource->{
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
sb.append("sloterItem= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("sloterItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List sloterItemsysItemcharacterList325 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof List) sloterItemsysItemcharacterList325=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof int[]) sloterItemsysItemcharacterList325=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof long[]) sloterItemsysItemcharacterList325=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof float[]) sloterItemsysItemcharacterList325=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof double[]) sloterItemsysItemcharacterList325=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof byte[]) sloterItemsysItemcharacterList325=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList") instanceof String[]) sloterItemsysItemcharacterList325=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList").getClass().isArray()) sloterItemsysItemcharacterList325=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.characterList")).collect(Collectors.toList());
}
sloterItemsysItemcharacterList325.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List sloterItemgunPropertypropertyList674 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof List) sloterItemgunPropertypropertyList674=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof int[]) sloterItemgunPropertypropertyList674=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof long[]) sloterItemgunPropertypropertyList674=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof float[]) sloterItemgunPropertypropertyList674=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof double[]) sloterItemgunPropertypropertyList674=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof byte[]) sloterItemgunPropertypropertyList674=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList") instanceof String[]) sloterItemgunPropertypropertyList674=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList").getClass().isArray()) sloterItemgunPropertypropertyList674=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
sloterItemgunPropertypropertyList674.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.color"));
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
List sloterItemgunPropertystrPropertyList807 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof List) sloterItemgunPropertystrPropertyList807=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof int[]) sloterItemgunPropertystrPropertyList807=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof long[]) sloterItemgunPropertystrPropertyList807=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof float[]) sloterItemgunPropertystrPropertyList807=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof double[]) sloterItemgunPropertystrPropertyList807=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof byte[]) sloterItemgunPropertystrPropertyList807=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList") instanceof String[]) sloterItemgunPropertystrPropertyList807=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList").getClass().isArray()) sloterItemgunPropertystrPropertyList807=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
sloterItemgunPropertystrPropertyList807.forEach(property->{
try{
sb.append("	    		{index= ");
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
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List sloterItemparts68 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof List) sloterItemparts68=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof int[]) sloterItemparts68=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof long[]) sloterItemparts68=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof float[]) sloterItemparts68=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof double[]) sloterItemparts68=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof byte[]) sloterItemparts68=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts") instanceof String[]) sloterItemparts68=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts").getClass().isArray()) sloterItemparts68=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"parts")).collect(Collectors.toList());
}
sloterItemparts68.forEach(part->{
try{
sb.append("			{");
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List sloterItemsysItemgpPricesList483 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof List) sloterItemsysItemgpPricesList483=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof int[]) sloterItemsysItemgpPricesList483=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof long[]) sloterItemsysItemgpPricesList483=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof float[]) sloterItemsysItemgpPricesList483=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof double[]) sloterItemsysItemgpPricesList483=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof byte[]) sloterItemsysItemgpPricesList483=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList") instanceof String[]) sloterItemsysItemgpPricesList483=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList").getClass().isArray()) sloterItemsysItemgpPricesList483=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
sloterItemsysItemgpPricesList483.forEach(pay->{
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
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List sloterItemsysItemcrPricesList250 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof List) sloterItemsysItemcrPricesList250=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof int[]) sloterItemsysItemcrPricesList250=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof long[]) sloterItemsysItemcrPricesList250=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof float[]) sloterItemsysItemcrPricesList250=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof double[]) sloterItemsysItemcrPricesList250=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof byte[]) sloterItemsysItemcrPricesList250=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList") instanceof String[]) sloterItemsysItemcrPricesList250=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList").getClass().isArray()) sloterItemsysItemcrPricesList250=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
sloterItemsysItemcrPricesList250.forEach(pay->{
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
}catch(Exception e18){
logger.error(e18.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List sloterItemsysItemvoucherPricesList606 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof List) sloterItemsysItemvoucherPricesList606=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof int[]) sloterItemsysItemvoucherPricesList606=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof long[]) sloterItemsysItemvoucherPricesList606=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof float[]) sloterItemsysItemvoucherPricesList606=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof double[]) sloterItemsysItemvoucherPricesList606=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof byte[]) sloterItemsysItemvoucherPricesList606=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList") instanceof String[]) sloterItemsysItemvoucherPricesList606=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList").getClass().isArray()) sloterItemsysItemvoucherPricesList606=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
sloterItemsysItemvoucherPricesList606.forEach(pay->{
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
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List sloterItempackages99 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof List) sloterItempackages99=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof int[]) sloterItempackages99=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof long[]) sloterItempackages99=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof float[]) sloterItempackages99=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof double[]) sloterItempackages99=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof byte[]) sloterItempackages99=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages") instanceof String[]) sloterItempackages99=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages").getClass().isArray()) sloterItempackages99=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"packages")).collect(Collectors.toList());
}
sloterItempackages99.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List sloterItemresource559 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof List) sloterItemresource559=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof int[]) sloterItemresource559=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof long[]) sloterItemresource559=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof float[]) sloterItemresource559=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof double[]) sloterItemresource559=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof byte[]) sloterItemresource559=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource") instanceof String[]) sloterItemresource559=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource").getClass().isArray()) sloterItemresource559=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resource")).collect(Collectors.toList());
}
sloterItemresource559.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List sloterItemresources343 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof List) sloterItemresources343=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof int[]) sloterItemresources343=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof long[]) sloterItemresources343=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof float[]) sloterItemresources343=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof double[]) sloterItemresources343=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof byte[]) sloterItemresources343=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof String[]) sloterItemresources343=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources").getClass().isArray()) sloterItemresources343=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
}
sloterItemresources343.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List sloterItemresources847 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof List) sloterItemresources847=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof int[]) sloterItemresources847=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof long[]) sloterItemresources847=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof float[]) sloterItemresources847=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof double[]) sloterItemresources847=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof byte[]) sloterItemresources847=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof String[]) sloterItemresources847=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources").getClass().isArray()) sloterItemresources847=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
}
sloterItemresources847.forEach(resource->{
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
List sloterItemresources17 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof List) sloterItemresources17=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof int[]) sloterItemresources17=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof long[]) sloterItemresources17=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof float[]) sloterItemresources17=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof double[]) sloterItemresources17=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof byte[]) sloterItemresources17=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources") instanceof String[]) sloterItemresources17=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources").getClass().isArray()) sloterItemresources17=Stream.of(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"resources")).collect(Collectors.toList());
}
sloterItemresources17.forEach(resource->{
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
sb.append("		gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sloterItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}