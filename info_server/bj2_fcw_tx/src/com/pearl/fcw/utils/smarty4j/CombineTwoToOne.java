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

public class CombineTwoToOne implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("mainItem= { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("mainItem"),"!=",null)){
sb.append("		playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List mainItemsysItemcharacterList952 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof List) mainItemsysItemcharacterList952=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof int[]) mainItemsysItemcharacterList952=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof long[]) mainItemsysItemcharacterList952=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof float[]) mainItemsysItemcharacterList952=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof double[]) mainItemsysItemcharacterList952=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof byte[]) mainItemsysItemcharacterList952=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList") instanceof String[]) mainItemsysItemcharacterList952=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList").getClass().isArray()) mainItemsysItemcharacterList952=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.characterList")).collect(Collectors.toList());
}
mainItemsysItemcharacterList952.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("		mType = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"),"==",13)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					1,  ");
sb.append("\r\n");
}
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"fightNum"));
sb.append(",       		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1,  ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"level"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List mainItemgunPropertypropertyList548 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof List) mainItemgunPropertypropertyList548=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof int[]) mainItemgunPropertypropertyList548=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof long[]) mainItemgunPropertypropertyList548=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof float[]) mainItemgunPropertypropertyList548=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof double[]) mainItemgunPropertypropertyList548=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof byte[]) mainItemgunPropertypropertyList548=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList") instanceof String[]) mainItemgunPropertypropertyList548=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList").getClass().isArray()) mainItemgunPropertypropertyList548=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
mainItemgunPropertypropertyList548.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.color"));
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
List mainItemgunPropertystrPropertyList693 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof List) mainItemgunPropertystrPropertyList693=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof int[]) mainItemgunPropertystrPropertyList693=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof long[]) mainItemgunPropertystrPropertyList693=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof float[]) mainItemgunPropertystrPropertyList693=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof double[]) mainItemgunPropertystrPropertyList693=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof byte[]) mainItemgunPropertystrPropertyList693=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList") instanceof String[]) mainItemgunPropertystrPropertyList693=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList").getClass().isArray()) mainItemgunPropertystrPropertyList693=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
mainItemgunPropertystrPropertyList693.forEach(property->{
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
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		parts = { ");
sb.append("\r\n");
List mainItemparts331 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof List) mainItemparts331=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof int[]) mainItemparts331=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof long[]) mainItemparts331=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof float[]) mainItemparts331=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof double[]) mainItemparts331=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof byte[]) mainItemparts331=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts") instanceof String[]) mainItemparts331=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts").getClass().isArray()) mainItemparts331=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"parts")).collect(Collectors.toList());
}
mainItemparts331.forEach(part->{
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
List mainItemsysItemgpPricesList76 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof List) mainItemsysItemgpPricesList76=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof int[]) mainItemsysItemgpPricesList76=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof long[]) mainItemsysItemgpPricesList76=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof float[]) mainItemsysItemgpPricesList76=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof double[]) mainItemsysItemgpPricesList76=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof byte[]) mainItemsysItemgpPricesList76=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList") instanceof String[]) mainItemsysItemgpPricesList76=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList").getClass().isArray()) mainItemsysItemgpPricesList76=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
mainItemsysItemgpPricesList76.forEach(pay->{
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
List mainItemsysItemcrPricesList852 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof List) mainItemsysItemcrPricesList852=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof int[]) mainItemsysItemcrPricesList852=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof long[]) mainItemsysItemcrPricesList852=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof float[]) mainItemsysItemcrPricesList852=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof double[]) mainItemsysItemcrPricesList852=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof byte[]) mainItemsysItemcrPricesList852=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList") instanceof String[]) mainItemsysItemcrPricesList852=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList").getClass().isArray()) mainItemsysItemcrPricesList852=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
mainItemsysItemcrPricesList852.forEach(pay->{
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
List mainItemsysItemvoucherPricesList993 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof List) mainItemsysItemvoucherPricesList993=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof int[]) mainItemsysItemvoucherPricesList993=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof long[]) mainItemsysItemvoucherPricesList993=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof float[]) mainItemsysItemvoucherPricesList993=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof double[]) mainItemsysItemvoucherPricesList993=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof byte[]) mainItemsysItemvoucherPricesList993=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList") instanceof String[]) mainItemsysItemvoucherPricesList993=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList").getClass().isArray()) mainItemsysItemvoucherPricesList993=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
mainItemsysItemvoucherPricesList993.forEach(pay->{
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
List mainItempackages425 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof List) mainItempackages425=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof int[]) mainItempackages425=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof long[]) mainItempackages425=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof float[]) mainItempackages425=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof double[]) mainItempackages425=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof byte[]) mainItempackages425=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages") instanceof String[]) mainItempackages425=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages").getClass().isArray()) mainItempackages425=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"packages")).collect(Collectors.toList());
}
mainItempackages425.forEach(item->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List mainItemresource74 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof List) mainItemresource74=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof int[]) mainItemresource74=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof long[]) mainItemresource74=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof float[]) mainItemresource74=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof double[]) mainItemresource74=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof byte[]) mainItemresource74=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource") instanceof String[]) mainItemresource74=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource").getClass().isArray()) mainItemresource74=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resource")).collect(Collectors.toList());
}
mainItemresource74.forEach(res->{
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
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List mainItemresources704 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources704=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof int[]) mainItemresources704=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof long[]) mainItemresources704=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof float[]) mainItemresources704=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof double[]) mainItemresources704=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof byte[]) mainItemresources704=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof String[]) mainItemresources704=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources704=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources704.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List mainItemresources344 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources344=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof int[]) mainItemresources344=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof long[]) mainItemresources344=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof float[]) mainItemresources344=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof double[]) mainItemresources344=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof byte[]) mainItemresources344=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof String[]) mainItemresources344=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources344=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources344.forEach(resource->{
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
List mainItemresources373 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof List) mainItemresources373=(List<?>)O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof int[]) mainItemresources373=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof long[]) mainItemresources373=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof float[]) mainItemresources373=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof double[]) mainItemresources373=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof byte[]) mainItemresources373=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources") instanceof String[]) mainItemresources373=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources").getClass().isArray()) mainItemresources373=Stream.of(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"resources")).collect(Collectors.toList());
}
mainItemresources373.forEach(resource->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mainItem"),"gst_level_exp"));
sb.append(",  ");
sb.append("\r\n");
}
}
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}