package com.pearl.fcw.utils.smarty4j2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;

public class SysItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"sid"));
sb.append(", ");
sb.append("\r\n");
sb.append("display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"display"));
sb.append("\", ");
sb.append("\r\n");
sb.append("name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("modified_desc = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("timelimit = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"timelimit"));
sb.append("\", ");
sb.append("\r\n");
sb.append("color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"color.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"type.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"sendperson"));
sb.append(", ");
sb.append("\r\n");
sb.append("wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"wid.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("iid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"iid.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("quantity = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("price={ ");
sb.append("\r\n");
List sysItempriceList26 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"priceList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"priceList") instanceof List) sysItempriceList26=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"priceList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"priceList").getClass().isArray()) sysItempriceList26=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"priceList")).collect(Collectors.toList());
}
sysItempriceList26.forEach(sysItemPrice->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar8899=new HashMap<>();
includeContextVar8899.put("sysItemPrice",sysItemPrice);
sb.append(new SysItemPrice().get(includeContextVar8899));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("characters={ ");
sb.append("\r\n");
List sysItemcharactersList665 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"charactersList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"charactersList") instanceof List) sysItemcharactersList665=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"charactersList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"charactersList").getClass().isArray()) sysItemcharactersList665=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"charactersList")).collect(Collectors.toList());
}
sysItemcharactersList665.forEach(ids->{
try{
sb.append("		");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("common={ ");
sb.append("\r\n");
sb.append("	level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isOpenQuality = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isOpenQuality"));
sb.append(", ");
sb.append("\r\n");
sb.append("	modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	characters={ ");
sb.append("\r\n");
List sysItemcommoncharactersList707 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"common.charactersList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"common.charactersList") instanceof List) sysItemcommoncharactersList707=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"common.charactersList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"common.charactersList").getClass().isArray()) sysItemcommoncharactersList707=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"common.charactersList")).collect(Collectors.toList());
}
sysItemcommoncharactersList707.forEach(ids->{
try{
sb.append("			");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.type.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("	subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.subtype"));
sb.append(", ");
sb.append("\r\n");
sb.append("	wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.wid.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("	seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_web=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isWeb"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isFlashSale = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.isFlashSale"));
sb.append(", ");
sb.append("\r\n");
sb.append("	mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.mType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("	star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.star"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("	strength= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.strength"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceFireAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceBlastAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceBulletAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cResistanceKnifeAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.cBloodAddAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"common.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("performance = { ");
sb.append("\r\n");
sb.append("	damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.damage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.speed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.ammos"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.ammoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	damange_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.damageAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	speed_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"performance.speedAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("gunproperty={ ");
sb.append("\r\n");
List sysItemgunpropertyList137 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunpropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunpropertyList") instanceof List) sysItemgunpropertyList137=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunpropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunpropertyList").getClass().isArray()) sysItemgunpropertyList137=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gunpropertyList")).collect(Collectors.toList());
}
sysItemgunpropertyList137.forEach(property->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"color.number"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\"},  ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("package = { ");
sb.append("\r\n");
List sysItempackageStrList194 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packageStrList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packageStrList") instanceof List) sysItempackageStrList194=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packageStrList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packageStrList").getClass().isArray()) sysItempackageStrList194=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"packageStrList")).collect(Collectors.toList());
}
sysItempackageStrList194.forEach(str->{
try{
sb.append("		\"");
sb.append(str);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("gpprices={ ");
sb.append("\r\n");
List sysItemgppricesList657 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gppricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gppricesList") instanceof List) sysItemgppricesList657=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gppricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gppricesList").getClass().isArray()) sysItemgppricesList657=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"gppricesList")).collect(Collectors.toList());
}
sysItemgppricesList657.forEach(pay->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unittype.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("crprices={ ");
sb.append("\r\n");
List sysItemcrpricesList363 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"crpricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"crpricesList") instanceof List) sysItemcrpricesList363=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"crpricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"crpricesList").getClass().isArray()) sysItemcrpricesList363=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"crpricesList")).collect(Collectors.toList());
}
sysItemcrpricesList363.forEach(pay->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unittype.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("voucherprices={ ");
sb.append("\r\n");
List sysItemvoucherpricesList442 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"voucherpricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"voucherpricesList") instanceof List) sysItemvoucherpricesList442=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"voucherpricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"voucherpricesList").getClass().isArray()) sysItemvoucherpricesList442=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"voucherpricesList")).collect(Collectors.toList());
}
sysItemvoucherpricesList442.forEach(pay->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unittype.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("		cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("resource = { ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"resource.type"));
sb.append(", ");
sb.append("\r\n");
List sysItemresourceresList119 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource.resList")){
if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource.resList") instanceof List) sysItemresourceresList119=(List<?>)O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource.resList");
else if (O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource.resList").getClass().isArray()) sysItemresourceresList119=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("sysItem"),"resource.resList")).collect(Collectors.toList());
}
sysItemresourceresList119.forEach(res->{
try{
sb.append("		\"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("suitId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItem"),"suitId"));
sb.append(", ");
sb.append("\r\n");
sb.append("suitDetail={ ");
sb.append("\r\n");
sb.append("	des4={ ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	des6={ ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}