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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"!=",null)){
sb.append("			iid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List playerItemsysItemcharacterList542 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof List) playerItemsysItemcharacterList542=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof int[]) playerItemsysItemcharacterList542=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof long[]) playerItemsysItemcharacterList542=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof float[]) playerItemsysItemcharacterList542=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof double[]) playerItemsysItemcharacterList542=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof byte[]) playerItemsysItemcharacterList542=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList") instanceof String[]) playerItemsysItemcharacterList542=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList").getClass().isArray()) playerItemsysItemcharacterList542=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.characterList")).collect(Collectors.toList());
}
playerItemsysItemcharacterList542.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("		repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"==",1)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.iId"),"==",5)){
sb.append("			buff = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"buff"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gpNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gpNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"timeLeft"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=1, ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	    		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		nextLevelDetail = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"),"<=",0)){
sb.append("					0, ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"durableInt"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			level =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"level"),">=",17)){
sb.append("					17 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"level")) + O2oUtil.parseFloat(1));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextCResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextCResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextCResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextCResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			holesNum = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextHoleNum"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	    		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextColor"));
sb.append(", ");
sb.append("\r\n");
sb.append("	    		cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"nextBloodAdd"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List playerItemgunPropertypropertyList3 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof List) playerItemgunPropertypropertyList3=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof int[]) playerItemgunPropertypropertyList3=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof long[]) playerItemgunPropertypropertyList3=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof float[]) playerItemgunPropertypropertyList3=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof double[]) playerItemgunPropertypropertyList3=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof byte[]) playerItemgunPropertypropertyList3=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList") instanceof String[]) playerItemgunPropertypropertyList3=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList").getClass().isArray()) playerItemgunPropertypropertyList3=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.propertyList")).collect(Collectors.toList());
}
playerItemgunPropertypropertyList3.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.color"));
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
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		combineDetail = { ");
sb.append("\r\n");
List playerItemgunPropertystrPropertyList519 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof List) playerItemgunPropertystrPropertyList519=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof int[]) playerItemgunPropertystrPropertyList519=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof long[]) playerItemgunPropertystrPropertyList519=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof float[]) playerItemgunPropertystrPropertyList519=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof double[]) playerItemgunPropertystrPropertyList519=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof byte[]) playerItemgunPropertystrPropertyList519=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList") instanceof String[]) playerItemgunPropertystrPropertyList519=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList").getClass().isArray()) playerItemgunPropertystrPropertyList519=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunProperty.strPropertyList")).collect(Collectors.toList());
}
playerItemgunPropertystrPropertyList519.forEach(property->{
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
List playerItemparts588 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof List) playerItemparts588=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof int[]) playerItemparts588=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof long[]) playerItemparts588=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof float[]) playerItemparts588=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof double[]) playerItemparts588=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof byte[]) playerItemparts588=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts") instanceof String[]) playerItemparts588=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts").getClass().isArray()) playerItemparts588=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"parts")).collect(Collectors.toList());
}
playerItemparts588.forEach(part->{
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
List playerItemsysItemgpPricesList542 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof List) playerItemsysItemgpPricesList542=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof int[]) playerItemsysItemgpPricesList542=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof long[]) playerItemsysItemgpPricesList542=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof float[]) playerItemsysItemgpPricesList542=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof double[]) playerItemsysItemgpPricesList542=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof byte[]) playerItemsysItemgpPricesList542=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList") instanceof String[]) playerItemsysItemgpPricesList542=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList").getClass().isArray()) playerItemsysItemgpPricesList542=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
playerItemsysItemgpPricesList542.forEach(pay->{
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
List playerItemsysItemcrPricesList460 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof List) playerItemsysItemcrPricesList460=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof int[]) playerItemsysItemcrPricesList460=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof long[]) playerItemsysItemcrPricesList460=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof float[]) playerItemsysItemcrPricesList460=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof double[]) playerItemsysItemcrPricesList460=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof byte[]) playerItemsysItemcrPricesList460=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList") instanceof String[]) playerItemsysItemcrPricesList460=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList").getClass().isArray()) playerItemsysItemcrPricesList460=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.crPricesList")).collect(Collectors.toList());
}
playerItemsysItemcrPricesList460.forEach(pay->{
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
List playerItemsysItemvoucherPricesList198 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof List) playerItemsysItemvoucherPricesList198=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof int[]) playerItemsysItemvoucherPricesList198=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof long[]) playerItemsysItemvoucherPricesList198=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof float[]) playerItemsysItemvoucherPricesList198=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof double[]) playerItemsysItemvoucherPricesList198=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof byte[]) playerItemsysItemvoucherPricesList198=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList") instanceof String[]) playerItemsysItemvoucherPricesList198=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList").getClass().isArray()) playerItemsysItemvoucherPricesList198=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
playerItemsysItemvoucherPricesList198.forEach(pay->{
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
List playerItempackages569 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof List) playerItempackages569=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof int[]) playerItempackages569=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof long[]) playerItempackages569=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof float[]) playerItempackages569=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof double[]) playerItempackages569=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof byte[]) playerItempackages569=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages") instanceof String[]) playerItempackages569=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages").getClass().isArray()) playerItempackages569=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packages")).collect(Collectors.toList());
}
playerItempackages569.forEach(item->{
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List playerItemresource438 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof List) playerItemresource438=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof int[]) playerItemresource438=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof long[]) playerItemresource438=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof float[]) playerItemresource438=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof double[]) playerItemresource438=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof byte[]) playerItemresource438=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource") instanceof String[]) playerItemresource438=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource").getClass().isArray()) playerItemresource438=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource")).collect(Collectors.toList());
}
playerItemresource438.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List playerItemresources628 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources628=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof int[]) playerItemresources628=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof long[]) playerItemresources628=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof float[]) playerItemresources628=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof double[]) playerItemresources628=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof byte[]) playerItemresources628=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof String[]) playerItemresources628=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources628=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources628.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List playerItemresources52 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources52=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof int[]) playerItemresources52=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof long[]) playerItemresources52=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof float[]) playerItemresources52=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof double[]) playerItemresources52=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof byte[]) playerItemresources52=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof String[]) playerItemresources52=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources52=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources52.forEach(resource->{
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
List playerItemresources87 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof List) playerItemresources87=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof int[]) playerItemresources87=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof long[]) playerItemresources87=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof float[]) playerItemresources87=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof double[]) playerItemresources87=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof byte[]) playerItemresources87=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources") instanceof String[]) playerItemresources87=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources").getClass().isArray()) playerItemresources87=Stream.of(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resources")).collect(Collectors.toList());
}
playerItemresources87.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gst_level"));
sb.append(",   ");
sb.append("\r\n");
}
sb.append("		gstExp= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gst_level_exp"),"==",null)){
sb.append("				0,  ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gst_level_exp"));
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
List itemList227 = new ArrayList<>();
if (null!=context.get("itemList")){
if (context.get("itemList") instanceof List) itemList227=(List<?>)context.get("itemList");
else if (context.get("itemList") instanceof int[]) itemList227=Stream.of((int[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof long[]) itemList227=Stream.of((long[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof float[]) itemList227=Stream.of((float[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof double[]) itemList227=Stream.of((double[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof byte[]) itemList227=Stream.of((byte[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList") instanceof String[]) itemList227=Stream.of((String[])context.get("itemList")).collect(Collectors.toList());
else if (context.get("itemList").getClass().isArray()) itemList227=Stream.of(context.get("itemList")).collect(Collectors.toList());
}
itemList227.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
if( O2oUtil.compare(theItem,"!=",null)){
sb.append("				playeritemid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("				sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"itemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isBind = \"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isBind"));
sb.append("\", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("					iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList942 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList942=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList942=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList942=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList942=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList942=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList942=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList942=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList942=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList942.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("				repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("					wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				common={ ");
sb.append("\r\n");
sb.append("					level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("					type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("						wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("					minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_new=1, ");
sb.append("\r\n");
sb.append("					star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("					strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("							-1 , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("					holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("					rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				performance = { ");
sb.append("\r\n");
sb.append("					damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("					speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList60 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList60=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList60=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList60=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList60=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList60=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList60=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList60=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList60=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList60.forEach(property->{
try{
sb.append("					{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("							1, ");
sb.append("\r\n");
}
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List theItemparts794 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts794=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts794=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts794=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts794=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts794=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts794=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts794=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts794=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts794.forEach(part->{
try{
sb.append("					{");
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
sb.append("				}, ");
sb.append("\r\n");
sb.append("				gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList211 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList211=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList211=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList211=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList211=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList211=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList211=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList211=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList211=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList211.forEach(pay->{
try{
sb.append("					{id=");
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
sb.append("				}, ");
sb.append("\r\n");
sb.append("				crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList961 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList961=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList961=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList961=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList961=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList961=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList961=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList961=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList961=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList961.forEach(pay->{
try{
sb.append("					{id=");
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
sb.append("				}, ");
sb.append("\r\n");
sb.append("				voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList159 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList159=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList159=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList159=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList159=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList159=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList159=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList159=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList159=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList159.forEach(pay->{
try{
sb.append("					{id=");
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
sb.append("				},	 ");
sb.append("\r\n");
sb.append("				package = { ");
sb.append("\r\n");
List theItempackages510 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages510=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages510=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages510=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages510=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages510=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages510=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages510=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages510=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages510.forEach(item->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource204 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource204=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource204=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource204=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource204=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource204=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource204=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource204=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource204=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource204.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources352 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources352=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources352=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources352=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources352=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources352=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources352=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources352=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources352=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources352.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources93 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources93=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources93=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources93=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources93=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources93=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources93=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources93=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources93=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources93.forEach(resource->{
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
List theItemresources776 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources776=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources776=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources776=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources776=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources776=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources776=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources776=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources776=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources776.forEach(resource->{
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
List failAwards981 = new ArrayList<>();
if (null!=context.get("failAwards")){
if (context.get("failAwards") instanceof List) failAwards981=(List<?>)context.get("failAwards");
else if (context.get("failAwards") instanceof int[]) failAwards981=Stream.of((int[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards") instanceof long[]) failAwards981=Stream.of((long[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards") instanceof float[]) failAwards981=Stream.of((float[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards") instanceof double[]) failAwards981=Stream.of((double[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards") instanceof byte[]) failAwards981=Stream.of((byte[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards") instanceof String[]) failAwards981=Stream.of((String[])context.get("failAwards")).collect(Collectors.toList());
else if (context.get("failAwards").getClass().isArray()) failAwards981=Stream.of(context.get("failAwards")).collect(Collectors.toList());
}
failAwards981.forEach(theItem->{
try{
sb.append("		{    ");
sb.append("\r\n");
if( O2oUtil.compare(theItem,"!=",null)){
sb.append("				sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"Id"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"!=",null)){
sb.append("					iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				item_num =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("				unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"unitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				characters={ ");
sb.append("\r\n");
List theItemcharacterList148 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof List) theItemcharacterList148=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof int[]) theItemcharacterList148=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof long[]) theItemcharacterList148=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof float[]) theItemcharacterList148=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof double[]) theItemcharacterList148=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof byte[]) theItemcharacterList148=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList") instanceof String[]) theItemcharacterList148=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"characterList").getClass().isArray()) theItemcharacterList148=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"characterList")).collect(Collectors.toList());
}
theItemcharacterList148.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("				pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("				repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"wId"),"==",4)){
sb.append("					wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"==",1)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"iId"),"==",5)){
sb.append("					buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("				common={ ");
sb.append("\r\n");
sb.append("					level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("					materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("					type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("						wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("					minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("							0, ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					is_new=1, ");
sb.append("\r\n");
sb.append("					star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("					strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isStrengthen"),"==",0)){
sb.append("							-1 , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("					holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("					cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("					rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				performance = { ");
sb.append("\r\n");
sb.append("					damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("					speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("					ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
sb.append("				color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("				gunproperty={ ");
sb.append("\r\n");
List theItemgunPropertypropertyList53 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof List) theItemgunPropertypropertyList53=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof int[]) theItemgunPropertypropertyList53=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof long[]) theItemgunPropertypropertyList53=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof float[]) theItemgunPropertypropertyList53=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof double[]) theItemgunPropertypropertyList53=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof byte[]) theItemgunPropertypropertyList53=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList") instanceof String[]) theItemgunPropertypropertyList53=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList").getClass().isArray()) theItemgunPropertypropertyList53=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.propertyList")).collect(Collectors.toList());
}
theItemgunPropertypropertyList53.forEach(property->{
try{
sb.append("					{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"),"!=",1)){
sb.append("							");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("							1, ");
sb.append("\r\n");
}
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List theItemparts407 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts407=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts407=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts407=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts407=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts407=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts407=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts407=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts407=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts407.forEach(part->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
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
List theItemgpPricesList895 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof List) theItemgpPricesList895=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof int[]) theItemgpPricesList895=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof long[]) theItemgpPricesList895=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof float[]) theItemgpPricesList895=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof double[]) theItemgpPricesList895=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof byte[]) theItemgpPricesList895=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList") instanceof String[]) theItemgpPricesList895=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"gpPricesList").getClass().isArray()) theItemgpPricesList895=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"gpPricesList")).collect(Collectors.toList());
}
theItemgpPricesList895.forEach(pay->{
try{
sb.append("					{id=");
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
sb.append("				}, ");
sb.append("\r\n");
sb.append("				crprices={ ");
sb.append("\r\n");
List theItemcrPricesList8 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof List) theItemcrPricesList8=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof int[]) theItemcrPricesList8=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof long[]) theItemcrPricesList8=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof float[]) theItemcrPricesList8=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof double[]) theItemcrPricesList8=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof byte[]) theItemcrPricesList8=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList") instanceof String[]) theItemcrPricesList8=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"crPricesList").getClass().isArray()) theItemcrPricesList8=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"crPricesList")).collect(Collectors.toList());
}
theItemcrPricesList8.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemvoucherPricesList362 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof List) theItemvoucherPricesList362=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof int[]) theItemvoucherPricesList362=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof long[]) theItemvoucherPricesList362=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof float[]) theItemvoucherPricesList362=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof double[]) theItemvoucherPricesList362=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof byte[]) theItemvoucherPricesList362=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList") instanceof String[]) theItemvoucherPricesList362=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList").getClass().isArray()) theItemvoucherPricesList362=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"voucherPricesList")).collect(Collectors.toList());
}
theItemvoucherPricesList362.forEach(pay->{
try{
sb.append("					{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItempackages856 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages856=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages856=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages856=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages856=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages856=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages856=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages856=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages856=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages856.forEach(item->{
try{
sb.append("						\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource366 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource366=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource366=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource366=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource366=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource366=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource366=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource366=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource366=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource366.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources39 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources39=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources39=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources39=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources39=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources39=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources39=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources39=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources39=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources39.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources724 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources724=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources724=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources724=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources724=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources724=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources724=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources724=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources724=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources724.forEach(resource->{
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
List theItemresources401 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources401=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources401=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources401=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources401=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources401=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources401=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources401=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources401=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources401.forEach(resource->{
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