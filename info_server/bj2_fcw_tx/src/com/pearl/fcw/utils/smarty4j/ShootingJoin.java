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

public class ShootingJoin implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("bout_num=");
sb.append(context.get("boutNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_num=");
sb.append(context.get("dartleNum"));
sb.append("    ");
sb.append("\r\n");
sb.append("ammo_num=");
sb.append(context.get("ammoNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("needDartleNum=");
sb.append(context.get("needDartleNum"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("look_item = { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List shootingLookcharacterList292 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof List) shootingLookcharacterList292=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof int[]) shootingLookcharacterList292=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof long[]) shootingLookcharacterList292=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof float[]) shootingLookcharacterList292=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof double[]) shootingLookcharacterList292=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof byte[]) shootingLookcharacterList292=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList") instanceof String[]) shootingLookcharacterList292=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList").getClass().isArray()) shootingLookcharacterList292=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"characterList")).collect(Collectors.toList());
}
shootingLookcharacterList292.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	i_value = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"iValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List shootingLookgunPropertypropertyList604 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof List) shootingLookgunPropertypropertyList604=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof int[]) shootingLookgunPropertypropertyList604=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof long[]) shootingLookgunPropertypropertyList604=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof float[]) shootingLookgunPropertypropertyList604=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof double[]) shootingLookgunPropertypropertyList604=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof byte[]) shootingLookgunPropertypropertyList604=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList") instanceof String[]) shootingLookgunPropertypropertyList604=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList").getClass().isArray()) shootingLookgunPropertypropertyList604=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.propertyList")).collect(Collectors.toList());
}
shootingLookgunPropertypropertyList604.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List shootingLookpackages438 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof List) shootingLookpackages438=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof int[]) shootingLookpackages438=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof long[]) shootingLookpackages438=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof float[]) shootingLookpackages438=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof double[]) shootingLookpackages438=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof byte[]) shootingLookpackages438=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages") instanceof String[]) shootingLookpackages438=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages").getClass().isArray()) shootingLookpackages438=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"packages")).collect(Collectors.toList());
}
shootingLookpackages438.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List shootingLookgpPricesList408 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof List) shootingLookgpPricesList408=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof int[]) shootingLookgpPricesList408=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof long[]) shootingLookgpPricesList408=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof float[]) shootingLookgpPricesList408=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof double[]) shootingLookgpPricesList408=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof byte[]) shootingLookgpPricesList408=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList") instanceof String[]) shootingLookgpPricesList408=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList").getClass().isArray()) shootingLookgpPricesList408=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"gpPricesList")).collect(Collectors.toList());
}
shootingLookgpPricesList408.forEach(pay->{
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
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List shootingLookcrPricesList810 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof List) shootingLookcrPricesList810=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof int[]) shootingLookcrPricesList810=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof long[]) shootingLookcrPricesList810=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof float[]) shootingLookcrPricesList810=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof double[]) shootingLookcrPricesList810=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof byte[]) shootingLookcrPricesList810=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList") instanceof String[]) shootingLookcrPricesList810=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList").getClass().isArray()) shootingLookcrPricesList810=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"crPricesList")).collect(Collectors.toList());
}
shootingLookcrPricesList810.forEach(pay->{
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
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List shootingLookvoucherPricesList567 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof List) shootingLookvoucherPricesList567=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof int[]) shootingLookvoucherPricesList567=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof long[]) shootingLookvoucherPricesList567=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof float[]) shootingLookvoucherPricesList567=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof double[]) shootingLookvoucherPricesList567=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof byte[]) shootingLookvoucherPricesList567=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList") instanceof String[]) shootingLookvoucherPricesList567=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList").getClass().isArray()) shootingLookvoucherPricesList567=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"voucherPricesList")).collect(Collectors.toList());
}
shootingLookvoucherPricesList567.forEach(pay->{
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
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List shootingLookresource743 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof List) shootingLookresource743=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof int[]) shootingLookresource743=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof long[]) shootingLookresource743=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof float[]) shootingLookresource743=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof double[]) shootingLookresource743=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof byte[]) shootingLookresource743=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource") instanceof String[]) shootingLookresource743=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource").getClass().isArray()) shootingLookresource743=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resource")).collect(Collectors.toList());
}
shootingLookresource743.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e7){
logger.error(e7.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cId"));
sb.append(", ");
sb.append("\r\n");
List shootingLookresources133 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources133=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof int[]) shootingLookresources133=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof long[]) shootingLookresources133=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof float[]) shootingLookresources133=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof double[]) shootingLookresources133=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof byte[]) shootingLookresources133=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof String[]) shootingLookresources133=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources133=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources133.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"cId"));
sb.append(", ");
sb.append("\r\n");
List shootingLookresources231 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources231=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof int[]) shootingLookresources231=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof long[]) shootingLookresources231=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof float[]) shootingLookresources231=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof double[]) shootingLookresources231=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof byte[]) shootingLookresources231=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof String[]) shootingLookresources231=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources231=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources231.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
} else {
List shootingLookresources433 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")){
if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof List) shootingLookresources433=(List<?>)O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources");
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof int[]) shootingLookresources433=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof long[]) shootingLookresources433=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof float[]) shootingLookresources433=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof double[]) shootingLookresources433=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof byte[]) shootingLookresources433=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources") instanceof String[]) shootingLookresources433=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources").getClass().isArray()) shootingLookresources433=Stream.of(O2oUtil.getSmarty4jProperty(context.get("shootingLook"),"resources")).collect(Collectors.toList());
}
shootingLookresources433.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("gold_gift =  { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List goldGiftsysItemcharacterList146 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof List) goldGiftsysItemcharacterList146=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof int[]) goldGiftsysItemcharacterList146=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof long[]) goldGiftsysItemcharacterList146=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof float[]) goldGiftsysItemcharacterList146=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof double[]) goldGiftsysItemcharacterList146=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof byte[]) goldGiftsysItemcharacterList146=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList") instanceof String[]) goldGiftsysItemcharacterList146=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList").getClass().isArray()) goldGiftsysItemcharacterList146=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.characterList")).collect(Collectors.toList());
}
goldGiftsysItemcharacterList146.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List goldGiftsysItemgunPropertypropertyList18 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof List) goldGiftsysItemgunPropertypropertyList18=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof int[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof long[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof float[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof double[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof byte[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList") instanceof String[]) goldGiftsysItemgunPropertypropertyList18=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) goldGiftsysItemgunPropertypropertyList18=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
goldGiftsysItemgunPropertypropertyList18.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List goldGiftsysItempackages390 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof List) goldGiftsysItempackages390=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof int[]) goldGiftsysItempackages390=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof long[]) goldGiftsysItempackages390=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof float[]) goldGiftsysItempackages390=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof double[]) goldGiftsysItempackages390=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof byte[]) goldGiftsysItempackages390=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages") instanceof String[]) goldGiftsysItempackages390=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages").getClass().isArray()) goldGiftsysItempackages390=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.packages")).collect(Collectors.toList());
}
goldGiftsysItempackages390.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List goldGiftsysItemgpPricesList848 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof List) goldGiftsysItemgpPricesList848=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof int[]) goldGiftsysItemgpPricesList848=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof long[]) goldGiftsysItemgpPricesList848=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof float[]) goldGiftsysItemgpPricesList848=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof double[]) goldGiftsysItemgpPricesList848=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof byte[]) goldGiftsysItemgpPricesList848=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList") instanceof String[]) goldGiftsysItemgpPricesList848=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList").getClass().isArray()) goldGiftsysItemgpPricesList848=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
goldGiftsysItemgpPricesList848.forEach(pay->{
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
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List goldGiftsysItemcrPricesList110 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof List) goldGiftsysItemcrPricesList110=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof int[]) goldGiftsysItemcrPricesList110=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof long[]) goldGiftsysItemcrPricesList110=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof float[]) goldGiftsysItemcrPricesList110=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof double[]) goldGiftsysItemcrPricesList110=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof byte[]) goldGiftsysItemcrPricesList110=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList") instanceof String[]) goldGiftsysItemcrPricesList110=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList").getClass().isArray()) goldGiftsysItemcrPricesList110=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
goldGiftsysItemcrPricesList110.forEach(pay->{
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
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List goldGiftsysItemvoucherPricesList920 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof List) goldGiftsysItemvoucherPricesList920=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof int[]) goldGiftsysItemvoucherPricesList920=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof long[]) goldGiftsysItemvoucherPricesList920=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof float[]) goldGiftsysItemvoucherPricesList920=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof double[]) goldGiftsysItemvoucherPricesList920=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof byte[]) goldGiftsysItemvoucherPricesList920=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList") instanceof String[]) goldGiftsysItemvoucherPricesList920=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList").getClass().isArray()) goldGiftsysItemvoucherPricesList920=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
goldGiftsysItemvoucherPricesList920.forEach(pay->{
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
}catch(Exception e16){
logger.error(e16.toString());
}
});
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List goldGiftsysItemresource566 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof List) goldGiftsysItemresource566=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof int[]) goldGiftsysItemresource566=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof long[]) goldGiftsysItemresource566=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof float[]) goldGiftsysItemresource566=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof double[]) goldGiftsysItemresource566=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof byte[]) goldGiftsysItemresource566=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource") instanceof String[]) goldGiftsysItemresource566=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource").getClass().isArray()) goldGiftsysItemresource566=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resource")).collect(Collectors.toList());
}
goldGiftsysItemresource566.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e17){
logger.error(e17.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List goldGiftsysItemresources160 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources160=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof int[]) goldGiftsysItemresources160=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof long[]) goldGiftsysItemresources160=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof float[]) goldGiftsysItemresources160=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof double[]) goldGiftsysItemresources160=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof byte[]) goldGiftsysItemresources160=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof String[]) goldGiftsysItemresources160=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources160=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources160.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e18){
logger.error(e18.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List goldGiftsysItemresources927 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources927=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof int[]) goldGiftsysItemresources927=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof long[]) goldGiftsysItemresources927=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof float[]) goldGiftsysItemresources927=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof double[]) goldGiftsysItemresources927=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof byte[]) goldGiftsysItemresources927=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof String[]) goldGiftsysItemresources927=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources927=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources927.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
} else {
List goldGiftsysItemresources937 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof List) goldGiftsysItemresources937=(List<?>)O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof int[]) goldGiftsysItemresources937=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof long[]) goldGiftsysItemresources937=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof float[]) goldGiftsysItemresources937=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof double[]) goldGiftsysItemresources937=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof byte[]) goldGiftsysItemresources937=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources") instanceof String[]) goldGiftsysItemresources937=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources").getClass().isArray()) goldGiftsysItemresources937=Stream.of(O2oUtil.getSmarty4jProperty(context.get("goldGift"),"sysItem.resources")).collect(Collectors.toList());
}
goldGiftsysItemresources937.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("silver_gift = { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List silverGiftsysItemcharacterList782 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof List) silverGiftsysItemcharacterList782=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof int[]) silverGiftsysItemcharacterList782=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof long[]) silverGiftsysItemcharacterList782=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof float[]) silverGiftsysItemcharacterList782=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof double[]) silverGiftsysItemcharacterList782=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof byte[]) silverGiftsysItemcharacterList782=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList") instanceof String[]) silverGiftsysItemcharacterList782=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList").getClass().isArray()) silverGiftsysItemcharacterList782=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.characterList")).collect(Collectors.toList());
}
silverGiftsysItemcharacterList782.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List silverGiftsysItemgunPropertypropertyList760 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof List) silverGiftsysItemgunPropertypropertyList760=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof int[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof long[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof float[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof double[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof byte[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList") instanceof String[]) silverGiftsysItemgunPropertypropertyList760=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) silverGiftsysItemgunPropertypropertyList760=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
silverGiftsysItemgunPropertypropertyList760.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("	    		\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List silverGiftsysItempackages647 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof List) silverGiftsysItempackages647=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof int[]) silverGiftsysItempackages647=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof long[]) silverGiftsysItempackages647=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof float[]) silverGiftsysItempackages647=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof double[]) silverGiftsysItempackages647=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof byte[]) silverGiftsysItempackages647=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages") instanceof String[]) silverGiftsysItempackages647=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages").getClass().isArray()) silverGiftsysItempackages647=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.packages")).collect(Collectors.toList());
}
silverGiftsysItempackages647.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List silverGiftsysItemgpPricesList430 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof List) silverGiftsysItemgpPricesList430=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof int[]) silverGiftsysItemgpPricesList430=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof long[]) silverGiftsysItemgpPricesList430=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof float[]) silverGiftsysItemgpPricesList430=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof double[]) silverGiftsysItemgpPricesList430=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof byte[]) silverGiftsysItemgpPricesList430=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList") instanceof String[]) silverGiftsysItemgpPricesList430=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList").getClass().isArray()) silverGiftsysItemgpPricesList430=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
silverGiftsysItemgpPricesList430.forEach(pay->{
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
}catch(Exception e24){
logger.error(e24.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List silverGiftsysItemcrPricesList616 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof List) silverGiftsysItemcrPricesList616=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof int[]) silverGiftsysItemcrPricesList616=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof long[]) silverGiftsysItemcrPricesList616=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof float[]) silverGiftsysItemcrPricesList616=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof double[]) silverGiftsysItemcrPricesList616=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof byte[]) silverGiftsysItemcrPricesList616=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList") instanceof String[]) silverGiftsysItemcrPricesList616=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList").getClass().isArray()) silverGiftsysItemcrPricesList616=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
silverGiftsysItemcrPricesList616.forEach(pay->{
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
}catch(Exception e25){
logger.error(e25.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List silverGiftsysItemvoucherPricesList891 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof List) silverGiftsysItemvoucherPricesList891=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof int[]) silverGiftsysItemvoucherPricesList891=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof long[]) silverGiftsysItemvoucherPricesList891=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof float[]) silverGiftsysItemvoucherPricesList891=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof double[]) silverGiftsysItemvoucherPricesList891=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof byte[]) silverGiftsysItemvoucherPricesList891=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList") instanceof String[]) silverGiftsysItemvoucherPricesList891=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList").getClass().isArray()) silverGiftsysItemvoucherPricesList891=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
silverGiftsysItemvoucherPricesList891.forEach(pay->{
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
}catch(Exception e26){
logger.error(e26.toString());
}
});
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List silverGiftsysItemresource55 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof List) silverGiftsysItemresource55=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof int[]) silverGiftsysItemresource55=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof long[]) silverGiftsysItemresource55=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof float[]) silverGiftsysItemresource55=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof double[]) silverGiftsysItemresource55=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof byte[]) silverGiftsysItemresource55=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource") instanceof String[]) silverGiftsysItemresource55=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource").getClass().isArray()) silverGiftsysItemresource55=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resource")).collect(Collectors.toList());
}
silverGiftsysItemresource55.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e27){
logger.error(e27.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List silverGiftsysItemresources564 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources564=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof int[]) silverGiftsysItemresources564=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof long[]) silverGiftsysItemresources564=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof float[]) silverGiftsysItemresources564=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof double[]) silverGiftsysItemresources564=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof byte[]) silverGiftsysItemresources564=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof String[]) silverGiftsysItemresources564=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources564=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources564.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e28){
logger.error(e28.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List silverGiftsysItemresources742 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources742=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof int[]) silverGiftsysItemresources742=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof long[]) silverGiftsysItemresources742=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof float[]) silverGiftsysItemresources742=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof double[]) silverGiftsysItemresources742=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof byte[]) silverGiftsysItemresources742=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof String[]) silverGiftsysItemresources742=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources742=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources742.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
} else {
List silverGiftsysItemresources782 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof List) silverGiftsysItemresources782=(List<?>)O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof int[]) silverGiftsysItemresources782=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof long[]) silverGiftsysItemresources782=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof float[]) silverGiftsysItemresources782=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof double[]) silverGiftsysItemresources782=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof byte[]) silverGiftsysItemresources782=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources") instanceof String[]) silverGiftsysItemresources782=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources").getClass().isArray()) silverGiftsysItemresources782=Stream.of(O2oUtil.getSmarty4jProperty(context.get("silverGift"),"sysItem.resources")).collect(Collectors.toList());
}
silverGiftsysItemresources782.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e30){
logger.error(e30.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("common_gift = { ");
sb.append("\r\n");
List commonGifts100 = new ArrayList<>();
if (null!=context.get("commonGifts")){
if (context.get("commonGifts") instanceof List) commonGifts100=(List<?>)context.get("commonGifts");
else if (context.get("commonGifts") instanceof int[]) commonGifts100=Stream.of((int[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts") instanceof long[]) commonGifts100=Stream.of((long[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts") instanceof float[]) commonGifts100=Stream.of((float[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts") instanceof double[]) commonGifts100=Stream.of((double[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts") instanceof byte[]) commonGifts100=Stream.of((byte[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts") instanceof String[]) commonGifts100=Stream.of((String[])context.get("commonGifts")).collect(Collectors.toList());
else if (context.get("commonGifts").getClass().isArray()) commonGifts100=Stream.of(context.get("commonGifts")).collect(Collectors.toList());
}
commonGifts100.forEach(onlineAward->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		sid=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		display=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		characters={ ");
sb.append("\r\n");
List onlineAwardsysItemcharacterList956 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof List) onlineAwardsysItemcharacterList956=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof int[]) onlineAwardsysItemcharacterList956=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof long[]) onlineAwardsysItemcharacterList956=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof float[]) onlineAwardsysItemcharacterList956=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof double[]) onlineAwardsysItemcharacterList956=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof byte[]) onlineAwardsysItemcharacterList956=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList") instanceof String[]) onlineAwardsysItemcharacterList956=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList").getClass().isArray()) onlineAwardsysItemcharacterList956=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.characterList")).collect(Collectors.toList());
}
onlineAwardsysItemcharacterList956.forEach(ids->{
try{
sb.append("				");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		description =\"");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"),"==",4)){
sb.append("			wid = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		common={ ");
sb.append("\r\n");
sb.append("			level = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("			type = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("			subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				wid=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			seq=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_new=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("			is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("			star=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.isStrengthen"),"==",0)){
sb.append("					-1 , ");
sb.append("\r\n");
} else {
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("			cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("			cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		performance = { ");
sb.append("\r\n");
sb.append("			damange = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("			speed =");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("			damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("			ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
sb.append("		color=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gunproperty={ ");
sb.append("\r\n");
List onlineAwardsysItemgunPropertypropertyList569 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof List) onlineAwardsysItemgunPropertypropertyList569=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof int[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof long[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof float[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof double[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof byte[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList") instanceof String[]) onlineAwardsysItemgunPropertypropertyList569=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList").getClass().isArray()) onlineAwardsysItemgunPropertypropertyList569=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
onlineAwardsysItemgunPropertypropertyList569.forEach(property->{
try{
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"),"!=",1)){
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gunProperty.color"));
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
}catch(Exception e33){
logger.error(e33.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		package = { ");
sb.append("\r\n");
List onlineAwardsysItempackages279 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof List) onlineAwardsysItempackages279=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof int[]) onlineAwardsysItempackages279=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof long[]) onlineAwardsysItempackages279=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof float[]) onlineAwardsysItempackages279=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof double[]) onlineAwardsysItempackages279=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof byte[]) onlineAwardsysItempackages279=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages") instanceof String[]) onlineAwardsysItempackages279=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages").getClass().isArray()) onlineAwardsysItempackages279=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.packages")).collect(Collectors.toList());
}
onlineAwardsysItempackages279.forEach(item->{
try{
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e34){
logger.error(e34.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		gpprices={ ");
sb.append("\r\n");
List onlineAwardsysItemgpPricesList280 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof List) onlineAwardsysItemgpPricesList280=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof int[]) onlineAwardsysItemgpPricesList280=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof long[]) onlineAwardsysItemgpPricesList280=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof float[]) onlineAwardsysItemgpPricesList280=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof double[]) onlineAwardsysItemgpPricesList280=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof byte[]) onlineAwardsysItemgpPricesList280=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList") instanceof String[]) onlineAwardsysItemgpPricesList280=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList").getClass().isArray()) onlineAwardsysItemgpPricesList280=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.gpPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemgpPricesList280.forEach(pay->{
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
}catch(Exception e35){
logger.error(e35.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		crprices={ ");
sb.append("\r\n");
List onlineAwardsysItemcrPricesList532 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof List) onlineAwardsysItemcrPricesList532=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof int[]) onlineAwardsysItemcrPricesList532=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof long[]) onlineAwardsysItemcrPricesList532=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof float[]) onlineAwardsysItemcrPricesList532=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof double[]) onlineAwardsysItemcrPricesList532=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof byte[]) onlineAwardsysItemcrPricesList532=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList") instanceof String[]) onlineAwardsysItemcrPricesList532=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList").getClass().isArray()) onlineAwardsysItemcrPricesList532=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.crPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemcrPricesList532.forEach(pay->{
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
}catch(Exception e36){
logger.error(e36.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		voucherprices={ ");
sb.append("\r\n");
List onlineAwardsysItemvoucherPricesList554 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof List) onlineAwardsysItemvoucherPricesList554=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof int[]) onlineAwardsysItemvoucherPricesList554=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof long[]) onlineAwardsysItemvoucherPricesList554=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof float[]) onlineAwardsysItemvoucherPricesList554=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof double[]) onlineAwardsysItemvoucherPricesList554=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof byte[]) onlineAwardsysItemvoucherPricesList554=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList") instanceof String[]) onlineAwardsysItemvoucherPricesList554=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList").getClass().isArray()) onlineAwardsysItemvoucherPricesList554=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
onlineAwardsysItemvoucherPricesList554.forEach(pay->{
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
}catch(Exception e37){
logger.error(e37.toString());
}
});
sb.append("		},	 ");
sb.append("\r\n");
sb.append("		resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",1)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List onlineAwardsysItemresource996 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof List) onlineAwardsysItemresource996=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof int[]) onlineAwardsysItemresource996=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof long[]) onlineAwardsysItemresource996=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof float[]) onlineAwardsysItemresource996=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof double[]) onlineAwardsysItemresource996=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof byte[]) onlineAwardsysItemresource996=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource") instanceof String[]) onlineAwardsysItemresource996=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource").getClass().isArray()) onlineAwardsysItemresource996=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resource")).collect(Collectors.toList());
}
onlineAwardsysItemresource996.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("						\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e38){
logger.error(e38.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",2)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources970 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources970=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources970=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources970=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources970=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources970=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources970=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources970=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources970=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources970.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e39){
logger.error(e39.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.type"),"==",3)){
sb.append("				type=");
sb.append(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List onlineAwardsysItemresources109 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources109=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources109=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources109=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources109=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources109=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources109=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources109=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources109=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources109.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e40){
logger.error(e40.toString());
}
});
} else {
List onlineAwardsysItemresources333 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof List) onlineAwardsysItemresources333=(List<?>)O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof int[]) onlineAwardsysItemresources333=Stream.of((int[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof long[]) onlineAwardsysItemresources333=Stream.of((long[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof float[]) onlineAwardsysItemresources333=Stream.of((float[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof double[]) onlineAwardsysItemresources333=Stream.of((double[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof byte[]) onlineAwardsysItemresources333=Stream.of((byte[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources") instanceof String[]) onlineAwardsysItemresources333=Stream.of((String[])O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources").getClass().isArray()) onlineAwardsysItemresources333=Stream.of(O2oUtil.getSmarty4jProperty(onlineAward,"sysItem.resources")).collect(Collectors.toList());
}
onlineAwardsysItemresources333.forEach(resource->{
try{
sb.append("					\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e41){
logger.error(e41.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e41){
logger.error(e41.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("dartle_gift =  { ");
sb.append("\r\n");
sb.append("	sid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	display=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List dartleGiftsysItemcharacterList637 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof List) dartleGiftsysItemcharacterList637=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof int[]) dartleGiftsysItemcharacterList637=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof long[]) dartleGiftsysItemcharacterList637=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof float[]) dartleGiftsysItemcharacterList637=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof double[]) dartleGiftsysItemcharacterList637=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof byte[]) dartleGiftsysItemcharacterList637=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList") instanceof String[]) dartleGiftsysItemcharacterList637=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList").getClass().isArray()) dartleGiftsysItemcharacterList637=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.characterList")).collect(Collectors.toList());
}
dartleGiftsysItemcharacterList637.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e42){
logger.error(e42.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	description =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	unit=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("	sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"),"==",4)){
sb.append("		wid = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("	common={ ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			wid=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		seq=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_new=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		star=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.figthNumOutput"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.isStrengthen"),"==",0)){
sb.append("				-1 , ");
sb.append("\r\n");
} else {
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.strengthLevel"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("		cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("		cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("		cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("		rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	performance = { ");
sb.append("\r\n");
sb.append("		damange = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("		speed =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.damange"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))) - O2oUtil.parseFloat(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.shootSpeed"))));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	gunproperty={ ");
sb.append("\r\n");
List dartleGiftsysItemgunPropertypropertyList62 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof List) dartleGiftsysItemgunPropertypropertyList62=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof int[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof long[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof float[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof double[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof byte[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList") instanceof String[]) dartleGiftsysItemgunPropertypropertyList62=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList").getClass().isArray()) dartleGiftsysItemgunPropertypropertyList62=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
dartleGiftsysItemgunPropertypropertyList62.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"),"!=",1)){
sb.append("				");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("				1, ");
sb.append("\r\n");
}
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e43){
logger.error(e43.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	package = { ");
sb.append("\r\n");
List dartleGiftsysItempackages185 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof List) dartleGiftsysItempackages185=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof int[]) dartleGiftsysItempackages185=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof long[]) dartleGiftsysItempackages185=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof float[]) dartleGiftsysItempackages185=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof double[]) dartleGiftsysItempackages185=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof byte[]) dartleGiftsysItempackages185=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages") instanceof String[]) dartleGiftsysItempackages185=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages").getClass().isArray()) dartleGiftsysItempackages185=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.packages")).collect(Collectors.toList());
}
dartleGiftsysItempackages185.forEach(item->{
try{
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e44){
logger.error(e44.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	gpprices={ ");
sb.append("\r\n");
List dartleGiftsysItemgpPricesList151 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof List) dartleGiftsysItemgpPricesList151=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof int[]) dartleGiftsysItemgpPricesList151=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof long[]) dartleGiftsysItemgpPricesList151=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof float[]) dartleGiftsysItemgpPricesList151=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof double[]) dartleGiftsysItemgpPricesList151=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof byte[]) dartleGiftsysItemgpPricesList151=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList") instanceof String[]) dartleGiftsysItemgpPricesList151=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList").getClass().isArray()) dartleGiftsysItemgpPricesList151=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.gpPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemgpPricesList151.forEach(pay->{
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
}catch(Exception e45){
logger.error(e45.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	crprices={ ");
sb.append("\r\n");
List dartleGiftsysItemcrPricesList303 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof List) dartleGiftsysItemcrPricesList303=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof int[]) dartleGiftsysItemcrPricesList303=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof long[]) dartleGiftsysItemcrPricesList303=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof float[]) dartleGiftsysItemcrPricesList303=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof double[]) dartleGiftsysItemcrPricesList303=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof byte[]) dartleGiftsysItemcrPricesList303=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList") instanceof String[]) dartleGiftsysItemcrPricesList303=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList").getClass().isArray()) dartleGiftsysItemcrPricesList303=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.crPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemcrPricesList303.forEach(pay->{
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
}catch(Exception e46){
logger.error(e46.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	voucherprices={ ");
sb.append("\r\n");
List dartleGiftsysItemvoucherPricesList381 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof List) dartleGiftsysItemvoucherPricesList381=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof int[]) dartleGiftsysItemvoucherPricesList381=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof long[]) dartleGiftsysItemvoucherPricesList381=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof float[]) dartleGiftsysItemvoucherPricesList381=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof double[]) dartleGiftsysItemvoucherPricesList381=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof byte[]) dartleGiftsysItemvoucherPricesList381=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList") instanceof String[]) dartleGiftsysItemvoucherPricesList381=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList").getClass().isArray()) dartleGiftsysItemvoucherPricesList381=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.voucherPricesList")).collect(Collectors.toList());
}
dartleGiftsysItemvoucherPricesList381.forEach(pay->{
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
}catch(Exception e47){
logger.error(e47.toString());
}
});
sb.append("	},		 ");
sb.append("\r\n");
sb.append("	resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",1)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List dartleGiftsysItemresource523 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof List) dartleGiftsysItemresource523=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof int[]) dartleGiftsysItemresource523=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof long[]) dartleGiftsysItemresource523=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof float[]) dartleGiftsysItemresource523=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof double[]) dartleGiftsysItemresource523=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof byte[]) dartleGiftsysItemresource523=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource") instanceof String[]) dartleGiftsysItemresource523=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource").getClass().isArray()) dartleGiftsysItemresource523=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resource")).collect(Collectors.toList());
}
dartleGiftsysItemresource523.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("					\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e48){
logger.error(e48.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",2)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources169 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources169=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources169=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources169=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources169=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources169=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources169=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources169=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources169=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources169.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e49){
logger.error(e49.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.type"),"==",3)){
sb.append("			type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List dartleGiftsysItemresources795 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources795=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources795=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources795=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources795=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources795=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources795=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources795=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources795=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources795.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e50){
logger.error(e50.toString());
}
});
} else {
List dartleGiftsysItemresources205 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof List) dartleGiftsysItemresources205=(List<?>)O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof int[]) dartleGiftsysItemresources205=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof long[]) dartleGiftsysItemresources205=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof float[]) dartleGiftsysItemresources205=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof double[]) dartleGiftsysItemresources205=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof byte[]) dartleGiftsysItemresources205=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources") instanceof String[]) dartleGiftsysItemresources205=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources").getClass().isArray()) dartleGiftsysItemresources205=Stream.of(O2oUtil.getSmarty4jProperty(context.get("dartleGift"),"sysItem.resources")).collect(Collectors.toList());
}
dartleGiftsysItemresources205.forEach(resource->{
try{
sb.append("				\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e51){
logger.error(e51.toString());
}
});
}
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append("dartle_top_list ={ ");
sb.append("\r\n");
List dartleTopList705 = new ArrayList<>();
if (null!=context.get("dartleTopList")){
if (context.get("dartleTopList") instanceof List) dartleTopList705=(List<?>)context.get("dartleTopList");
else if (context.get("dartleTopList") instanceof int[]) dartleTopList705=Stream.of((int[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof long[]) dartleTopList705=Stream.of((long[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof float[]) dartleTopList705=Stream.of((float[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof double[]) dartleTopList705=Stream.of((double[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof byte[]) dartleTopList705=Stream.of((byte[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList") instanceof String[]) dartleTopList705=Stream.of((String[])context.get("dartleTopList")).collect(Collectors.toList());
else if (context.get("dartleTopList").getClass().isArray()) dartleTopList705=Stream.of(context.get("dartleTopList")).collect(Collectors.toList());
}
dartleTopList705.forEach(entry->{
try{
sb.append("		");
sb.append(entry);
sb.append(" ");
sb.append("\r\n");
}catch(Exception e52){
logger.error(e52.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("dartle_awards_list ={ ");
sb.append("\r\n");
List awardsList247 = new ArrayList<>();
if (null!=context.get("awardsList")){
if (context.get("awardsList") instanceof List) awardsList247=(List<?>)context.get("awardsList");
else if (context.get("awardsList") instanceof int[]) awardsList247=Stream.of((int[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof long[]) awardsList247=Stream.of((long[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof float[]) awardsList247=Stream.of((float[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof double[]) awardsList247=Stream.of((double[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof byte[]) awardsList247=Stream.of((byte[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList") instanceof String[]) awardsList247=Stream.of((String[])context.get("awardsList")).collect(Collectors.toList());
else if (context.get("awardsList").getClass().isArray()) awardsList247=Stream.of(context.get("awardsList")).collect(Collectors.toList());
}
awardsList247.forEach(theItem->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"player.rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		{ ");
sb.append("\r\n");
sb.append("			item_num=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("			sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList22 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList22=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList22=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList22=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList22=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList22=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList22=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList22=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList22=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList22.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e54){
logger.error(e54.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("				star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("				strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("						-1 ,	 ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append(" ,	 ");
sb.append("\r\n");
}
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("				damange_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"damange")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				speed_add = ");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"shootSpeed")) - O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed")));
sb.append(",			 ");
sb.append("\r\n");
sb.append("				ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList353 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList353=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList353=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList353=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList353=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList353=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList353=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList353=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList353=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList353.forEach(property->{
try{
sb.append("				{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("						1, ");
sb.append("\r\n");
}
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("				},  ");
sb.append("\r\n");
}catch(Exception e55){
logger.error(e55.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItemsysItempackages369 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages369=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages369=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages369=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages369=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages369=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages369=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages369=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages369=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages369.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e56){
logger.error(e56.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList400 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList400=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList400=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList400=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList400=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList400=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList400=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList400=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList400=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList400.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e57){
logger.error(e57.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList992 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList992=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList992=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList992=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList992=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList992=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList992=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList992=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList992=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList992.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e58){
logger.error(e58.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList560 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList560=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList560=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList560=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList560=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList560=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList560=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList560=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList560=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList560.forEach(pay->{
try{
sb.append("				{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e59){
logger.error(e59.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource587 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource587=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource587=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource587=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource587=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource587=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource587=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource587=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource587=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource587.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e60){
logger.error(e60.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources694 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources694=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources694=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources694=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources694=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources694=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources694=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources694=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources694=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources694.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e61){
logger.error(e61.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources571 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources571=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources571=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources571=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources571=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources571=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources571=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources571=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources571=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources571.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e62){
logger.error(e62.toString());
}
});
} else {
List theItemsysItemresources370 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources370=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources370=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources370=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources370=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources370=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources370=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources370=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources370=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources370.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e63){
logger.error(e63.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},   ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e63){
logger.error(e63.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}