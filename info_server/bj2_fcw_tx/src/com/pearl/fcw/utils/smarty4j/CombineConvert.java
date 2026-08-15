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

public class CombineConvert implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rate=");
sb.append(context.get("rate"));
sb.append(" ");
sb.append("\r\n");
sb.append("result = ");
sb.append(context.get("result"));
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
List fromItemsysItemcharacterList855 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof List) fromItemsysItemcharacterList855=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof int[]) fromItemsysItemcharacterList855=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof long[]) fromItemsysItemcharacterList855=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof float[]) fromItemsysItemcharacterList855=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof double[]) fromItemsysItemcharacterList855=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof byte[]) fromItemsysItemcharacterList855=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList") instanceof String[]) fromItemsysItemcharacterList855=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList").getClass().isArray()) fromItemsysItemcharacterList855=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.characterList")).collect(Collectors.toList());
}
fromItemsysItemcharacterList855.forEach(ids->{
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
sb.append(",       		 ");
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
sb.append("		 performance = { ");
sb.append("\r\n");
sb.append("    			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("    			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	    		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    		ammo_count=");
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
List fromItemgunPropertypropertyList134 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof List) fromItemgunPropertypropertyList134=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof int[]) fromItemgunPropertypropertyList134=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof long[]) fromItemgunPropertypropertyList134=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof float[]) fromItemgunPropertypropertyList134=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof double[]) fromItemgunPropertypropertyList134=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof byte[]) fromItemgunPropertypropertyList134=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList") instanceof String[]) fromItemgunPropertypropertyList134=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList").getClass().isArray()) fromItemgunPropertypropertyList134=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
fromItemgunPropertypropertyList134.forEach(property->{
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
sb.append("		    		\"");
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
List fromItemgunPropertystrPropertyList489 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof List) fromItemgunPropertystrPropertyList489=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof int[]) fromItemgunPropertystrPropertyList489=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof long[]) fromItemgunPropertystrPropertyList489=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof float[]) fromItemgunPropertystrPropertyList489=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof double[]) fromItemgunPropertystrPropertyList489=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof byte[]) fromItemgunPropertystrPropertyList489=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList") instanceof String[]) fromItemgunPropertystrPropertyList489=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList").getClass().isArray()) fromItemgunPropertystrPropertyList489=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
fromItemgunPropertystrPropertyList489.forEach(property->{
try{
sb.append("				{index= ");
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
List fromItemparts699 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof List) fromItemparts699=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof int[]) fromItemparts699=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof long[]) fromItemparts699=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof float[]) fromItemparts699=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof double[]) fromItemparts699=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof byte[]) fromItemparts699=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts") instanceof String[]) fromItemparts699=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts").getClass().isArray()) fromItemparts699=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"parts")).collect(Collectors.toList());
}
fromItemparts699.forEach(part->{
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
List fromItemsysItemgpPricesList146 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof List) fromItemsysItemgpPricesList146=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof int[]) fromItemsysItemgpPricesList146=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof long[]) fromItemsysItemgpPricesList146=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof float[]) fromItemsysItemgpPricesList146=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof double[]) fromItemsysItemgpPricesList146=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof byte[]) fromItemsysItemgpPricesList146=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList") instanceof String[]) fromItemsysItemgpPricesList146=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList").getClass().isArray()) fromItemsysItemgpPricesList146=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
fromItemsysItemgpPricesList146.forEach(pay->{
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
List fromItemsysItemcrPricesList731 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof List) fromItemsysItemcrPricesList731=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof int[]) fromItemsysItemcrPricesList731=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof long[]) fromItemsysItemcrPricesList731=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof float[]) fromItemsysItemcrPricesList731=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof double[]) fromItemsysItemcrPricesList731=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof byte[]) fromItemsysItemcrPricesList731=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList") instanceof String[]) fromItemsysItemcrPricesList731=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList").getClass().isArray()) fromItemsysItemcrPricesList731=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
fromItemsysItemcrPricesList731.forEach(pay->{
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
sb.append("}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List fromItemsysItemvoucherPricesList953 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof List) fromItemsysItemvoucherPricesList953=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof int[]) fromItemsysItemvoucherPricesList953=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof long[]) fromItemsysItemvoucherPricesList953=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof float[]) fromItemsysItemvoucherPricesList953=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof double[]) fromItemsysItemvoucherPricesList953=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof byte[]) fromItemsysItemvoucherPricesList953=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList") instanceof String[]) fromItemsysItemvoucherPricesList953=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList").getClass().isArray()) fromItemsysItemvoucherPricesList953=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
fromItemsysItemvoucherPricesList953.forEach(pay->{
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
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List fromItempackages290 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof List) fromItempackages290=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof int[]) fromItempackages290=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof long[]) fromItempackages290=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof float[]) fromItempackages290=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof double[]) fromItempackages290=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof byte[]) fromItempackages290=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages") instanceof String[]) fromItempackages290=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages").getClass().isArray()) fromItempackages290=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"packages")).collect(Collectors.toList());
}
fromItempackages290.forEach(item->{
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
List fromItemresource801 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof List) fromItemresource801=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof int[]) fromItemresource801=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof long[]) fromItemresource801=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof float[]) fromItemresource801=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof double[]) fromItemresource801=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof byte[]) fromItemresource801=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource") instanceof String[]) fromItemresource801=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource").getClass().isArray()) fromItemresource801=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resource")).collect(Collectors.toList());
}
fromItemresource801.forEach(res->{
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
List fromItemresources154 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources154=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources154=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources154=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources154=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources154=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources154=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources154=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources154=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources154.forEach(resource->{
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
List fromItemresources267 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources267=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources267=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources267=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources267=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources267=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources267=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources267=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources267=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources267.forEach(resource->{
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
List fromItemresources947 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof List) fromItemresources947=(List<?>)O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof int[]) fromItemresources947=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof long[]) fromItemresources947=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof float[]) fromItemresources947=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof double[]) fromItemresources947=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof byte[]) fromItemresources947=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources") instanceof String[]) fromItemresources947=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources").getClass().isArray()) fromItemresources947=Stream.of(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"resources")).collect(Collectors.toList());
}
fromItemresources947.forEach(resource->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("fromItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
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
List toItemsysItemcharacterList733 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof List) toItemsysItemcharacterList733=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof int[]) toItemsysItemcharacterList733=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof long[]) toItemsysItemcharacterList733=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof float[]) toItemsysItemcharacterList733=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof double[]) toItemsysItemcharacterList733=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof byte[]) toItemsysItemcharacterList733=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList") instanceof String[]) toItemsysItemcharacterList733=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList").getClass().isArray()) toItemsysItemcharacterList733=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.characterList")).collect(Collectors.toList());
}
toItemsysItemcharacterList733.forEach(ids->{
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
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
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
sb.append(",       		 ");
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
List toItemgunPropertypropertyList393 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof List) toItemgunPropertypropertyList393=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof int[]) toItemgunPropertypropertyList393=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof long[]) toItemgunPropertypropertyList393=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof float[]) toItemgunPropertypropertyList393=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof double[]) toItemgunPropertypropertyList393=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof byte[]) toItemgunPropertypropertyList393=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList") instanceof String[]) toItemgunPropertypropertyList393=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList").getClass().isArray()) toItemgunPropertypropertyList393=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
toItemgunPropertypropertyList393.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("		    	\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	combineDetail = { ");
sb.append("\r\n");
List toItemgunPropertystrPropertyList532 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof List) toItemgunPropertystrPropertyList532=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof int[]) toItemgunPropertystrPropertyList532=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof long[]) toItemgunPropertystrPropertyList532=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof float[]) toItemgunPropertystrPropertyList532=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof double[]) toItemgunPropertystrPropertyList532=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof byte[]) toItemgunPropertystrPropertyList532=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList") instanceof String[]) toItemgunPropertystrPropertyList532=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList").getClass().isArray()) toItemgunPropertystrPropertyList532=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
toItemgunPropertystrPropertyList532.forEach(property->{
try{
sb.append("	    		{index= ");
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	parts = { ");
sb.append("\r\n");
List toItemparts961 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof List) toItemparts961=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof int[]) toItemparts961=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof long[]) toItemparts961=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof float[]) toItemparts961=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof double[]) toItemparts961=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof byte[]) toItemparts961=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts") instanceof String[]) toItemparts961=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts").getClass().isArray()) toItemparts961=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"parts")).collect(Collectors.toList());
}
toItemparts961.forEach(part->{
try{
sb.append("		{");
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
sb.append("}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List toItemsysItemgpPricesList500 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof List) toItemsysItemgpPricesList500=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof int[]) toItemsysItemgpPricesList500=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof long[]) toItemsysItemgpPricesList500=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof float[]) toItemsysItemgpPricesList500=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof double[]) toItemsysItemgpPricesList500=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof byte[]) toItemsysItemgpPricesList500=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList") instanceof String[]) toItemsysItemgpPricesList500=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList").getClass().isArray()) toItemsysItemgpPricesList500=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
toItemsysItemgpPricesList500.forEach(pay->{
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List toItemsysItemcrPricesList543 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof List) toItemsysItemcrPricesList543=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof int[]) toItemsysItemcrPricesList543=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof long[]) toItemsysItemcrPricesList543=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof float[]) toItemsysItemcrPricesList543=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof double[]) toItemsysItemcrPricesList543=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof byte[]) toItemsysItemcrPricesList543=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList") instanceof String[]) toItemsysItemcrPricesList543=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList").getClass().isArray()) toItemsysItemcrPricesList543=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
toItemsysItemcrPricesList543.forEach(pay->{
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List toItemsysItemvoucherPricesList518 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof List) toItemsysItemvoucherPricesList518=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof int[]) toItemsysItemvoucherPricesList518=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof long[]) toItemsysItemvoucherPricesList518=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof float[]) toItemsysItemvoucherPricesList518=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof double[]) toItemsysItemvoucherPricesList518=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof byte[]) toItemsysItemvoucherPricesList518=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList") instanceof String[]) toItemsysItemvoucherPricesList518=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList").getClass().isArray()) toItemsysItemvoucherPricesList518=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
toItemsysItemvoucherPricesList518.forEach(pay->{
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
sb.append("},	 ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List toItempackages203 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof List) toItempackages203=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof int[]) toItempackages203=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof long[]) toItempackages203=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof float[]) toItempackages203=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof double[]) toItempackages203=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof byte[]) toItempackages203=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages") instanceof String[]) toItempackages203=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages").getClass().isArray()) toItempackages203=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"packages")).collect(Collectors.toList());
}
toItempackages203.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List toItemresource989 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof List) toItemresource989=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof int[]) toItemresource989=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof long[]) toItemresource989=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof float[]) toItemresource989=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof double[]) toItemresource989=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof byte[]) toItemresource989=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource") instanceof String[]) toItemresource989=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource").getClass().isArray()) toItemresource989=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resource")).collect(Collectors.toList());
}
toItemresource989.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e21){
logger.error(e21.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources156 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources156=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources156=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources156=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources156=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources156=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources156=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources156=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources156=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources156.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List toItemresources245 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources245=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources245=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources245=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources245=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources245=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources245=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources245=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources245=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources245.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
} else {
List toItemresources582 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof List) toItemresources582=(List<?>)O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof int[]) toItemresources582=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof long[]) toItemresources582=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof float[]) toItemresources582=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof double[]) toItemresources582=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof byte[]) toItemresources582=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources") instanceof String[]) toItemresources582=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources").getClass().isArray()) toItemresources582=Stream.of(O2oUtil.getSmarty4jProperty(context.get("toItem"),"resources")).collect(Collectors.toList());
}
toItemresources582.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e24){
logger.error(e24.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gstLevel = ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gst_level"),"==",null)){
sb.append("			0,  ");
sb.append("\r\n");
} else {
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("	gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gst_level_exp"),"==",null)){
sb.append("			0,  ");
sb.append("\r\n");
} else {
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(context.get("toItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}