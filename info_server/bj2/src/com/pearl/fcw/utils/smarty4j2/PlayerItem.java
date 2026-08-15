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

public class PlayerItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("playeritemid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"playeritemid"));
sb.append(", ");
sb.append("\r\n");
sb.append("sid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sid"));
sb.append(", ");
sb.append("\r\n");
sb.append("isBind = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"isBind"));
sb.append("\", ");
sb.append("\r\n");
sb.append("iid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"iid"));
sb.append(",  ");
sb.append("\r\n");
sb.append("wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"wid"));
sb.append(", ");
sb.append("\r\n");
sb.append("display = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"display"));
sb.append("\", ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("createtime = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"createtime"));
sb.append(", ");
sb.append("\r\n");
sb.append("unit_type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"unitType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("modified_desc = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("characters={ ");
sb.append("\r\n");
List playerItemcharactersList14 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"charactersList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"charactersList") instanceof List) playerItemcharactersList14=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"charactersList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"charactersList").getClass().isArray()) playerItemcharactersList14=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"charactersList")).collect(Collectors.toList());
}
playerItemcharactersList14.forEach(id->{
try{
sb.append("		");
sb.append(id);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("description = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"packId"));
sb.append(", ");
sb.append("\r\n");
sb.append("repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"repairCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"buff"));
sb.append(", ");
sb.append("\r\n");
sb.append("isDefault = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"isDefault"));
sb.append(", ");
sb.append("\r\n");
sb.append("mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("provisional_item_day = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"provisionalItemDay"));
sb.append(", ");
sb.append("\r\n");
sb.append("provisional_item_flag = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"provisionalItemFlag"));
sb.append(", ");
sb.append("\r\n");
sb.append("baseItemFightNum = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"baseItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("strengthenItemFightNum = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"strengthenItemFightNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("common={ ");
sb.append("\r\n");
sb.append("	isAsset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("	isOpenQuality = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.isOpenQuality"));
sb.append(", ");
sb.append("\r\n");
sb.append("	level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.type.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("	subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.subtype"));
sb.append(", ");
sb.append("\r\n");
sb.append("	wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.wid.number"));
sb.append(",  ");
sb.append("\r\n");
sb.append("	durable = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("	quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("	minutes_left = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.minutesLeft"));
sb.append(", ");
sb.append("\r\n");
sb.append("	seq = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_vip = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("	is_new = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("	star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.star"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("	strength = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.strength"));
sb.append(",  ");
sb.append("\r\n");
sb.append("	holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceFire = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBlast = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBullet = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceKnife = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cBloodAdd = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceFire_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceFireAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBlast_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceBlastAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBullet_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceBulletAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceKnife_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cResistanceKnifeAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cBloodAdd_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.cBloodAddAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	rareLevel = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	evaluateRmb = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.evaluateRmb"));
sb.append(", ");
sb.append("\r\n");
sb.append("	needTeamPlaceLevel = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"common.needTeamPlaceLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("	p_suitable = { ");
sb.append("\r\n");
List playerItemcommonpSuitableList929 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"common.pSuitableList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"common.pSuitableList") instanceof List) playerItemcommonpSuitableList929=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"common.pSuitableList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"common.pSuitableList").getClass().isArray()) playerItemcommonpSuitableList929=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"common.pSuitableList")).collect(Collectors.toList());
}
playerItemcommonpSuitableList929.forEach(pSuit->{
try{
sb.append("			\"");
sb.append(pSuit);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("performance = { ");
sb.append("\r\n");
sb.append("	damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.damage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.speed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.ammos"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.ammoCount"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	damange_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.damageAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("	speed_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"performance.speedAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("nextLevelDetail = { ");
sb.append("\r\n");
sb.append("	damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.damage"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("	speed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.speed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	durable =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.durable"));
sb.append(", ");
sb.append("\r\n");
sb.append("	damange_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.damageAdd"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("	speed_add = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.speedAdd"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.star"));
sb.append(", ");
sb.append("\r\n");
sb.append("	level =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("	holesNum = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.holesNum"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("	color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("	cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"nextLevelDetail.cBloodAddAdd"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"color.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("gunproperty={ ");
sb.append("\r\n");
List playerItemgunpropertyList818 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunpropertyList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunpropertyList") instanceof List) playerItemgunpropertyList818=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunpropertyList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunpropertyList").getClass().isArray()) playerItemgunpropertyList818=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gunpropertyList")).collect(Collectors.toList());
}
playerItemgunpropertyList818.forEach(property->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"color.number"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\"},  ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("combineDetail = { ");
sb.append("\r\n");
List playerItemcombineDetailList955 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"combineDetailList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"combineDetailList") instanceof List) playerItemcombineDetailList955=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"combineDetailList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"combineDetailList").getClass().isArray()) playerItemcombineDetailList955=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"combineDetailList")).collect(Collectors.toList());
}
playerItemcombineDetailList955.forEach(property->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("		index = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"index"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		state = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"state"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"level"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		desc = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"desc"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("		open = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"open"));
sb.append(", ");
sb.append("\r\n");
sb.append("		},  ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("suitId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"suitId"));
sb.append(",  ");
sb.append("\r\n");
sb.append("suitDetail={ ");
sb.append("\r\n");
sb.append("	des4={ ");
sb.append("\r\n");
List playerItemsuitDetaildes4List237 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des4List")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des4List") instanceof List) playerItemsuitDetaildes4List237=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des4List");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des4List").getClass().isArray()) playerItemsuitDetaildes4List237=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des4List")).collect(Collectors.toList());
}
playerItemsuitDetaildes4List237.forEach(des4->{
try{
sb.append("			\"");
sb.append(des4);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	des6={ ");
sb.append("\r\n");
List playerItemsuitDetaildes6List35 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des6List")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des6List") instanceof List) playerItemsuitDetaildes6List35=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des6List");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des6List").getClass().isArray()) playerItemsuitDetaildes6List35=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"suitDetail.des6List")).collect(Collectors.toList());
}
playerItemsuitDetaildes6List35.forEach(des6->{
try{
sb.append("			\"");
sb.append(des6);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("}, ");
sb.append("\r\n");
sb.append("parts = { ");
sb.append("\r\n");
List theItempartsList496 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("theItem"),"partsList")){
if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"partsList") instanceof List) theItempartsList496=(List<?>)O2oUtil.getSmarty4jProperty(context.get("theItem"),"partsList");
else if (O2oUtil.getSmarty4jProperty(context.get("theItem"),"partsList").getClass().isArray()) theItempartsList496=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("theItem"),"partsList")).collect(Collectors.toList());
}
theItempartsList496.forEach(part->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
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
sb.append("gpprices={ ");
sb.append("\r\n");
List playerItemgppricesList127 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gppricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gppricesList") instanceof List) playerItemgppricesList127=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gppricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gppricesList").getClass().isArray()) playerItemgppricesList127=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"gppricesList")).collect(Collectors.toList());
}
playerItemgppricesList127.forEach(pay->{
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
sb.append("crprices={ ");
sb.append("\r\n");
List playerItemcrpricesList474 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"crpricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"crpricesList") instanceof List) playerItemcrpricesList474=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"crpricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"crpricesList").getClass().isArray()) playerItemcrpricesList474=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"crpricesList")).collect(Collectors.toList());
}
playerItemcrpricesList474.forEach(pay->{
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
sb.append("voucherprices={ ");
sb.append("\r\n");
List playerItemvoucherpricesList662 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"voucherpricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"voucherpricesList") instanceof List) playerItemvoucherpricesList662=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"voucherpricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"voucherpricesList").getClass().isArray()) playerItemvoucherpricesList662=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"voucherpricesList")).collect(Collectors.toList());
}
playerItemvoucherpricesList662.forEach(pay->{
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
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("package = { ");
sb.append("\r\n");
List playerItempackageStrList458 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packageStrList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packageStrList") instanceof List) playerItempackageStrList458=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packageStrList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packageStrList").getClass().isArray()) playerItempackageStrList458=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"packageStrList")).collect(Collectors.toList());
}
playerItempackageStrList458.forEach(str->{
try{
sb.append("		\"");
sb.append(str);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("resource = { ");
sb.append("\r\n");
sb.append("	type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"resource.type"));
sb.append(", ");
sb.append("\r\n");
List playerItemresourceresList879 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource.resList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource.resList") instanceof List) playerItemresourceresList879=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource.resList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource.resList").getClass().isArray()) playerItemresourceresList879=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"resource.resList")).collect(Collectors.toList());
}
playerItemresourceresList879.forEach(res->{
try{
sb.append("		\"");
sb.append(res);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e12){
logger.error(e12.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("gstLevel = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gstLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("gstExp= ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"gstExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("items={ ");
sb.append("\r\n");
List playerItemmeltingItemList468 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingItemList") instanceof List) playerItemmeltingItemList468=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingItemList").getClass().isArray()) playerItemmeltingItemList468=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingItemList")).collect(Collectors.toList());
}
playerItemmeltingItemList468.forEach(meltingItem->{
try{
sb.append("		{");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingItem,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingItem,"displayName"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
sb.append("result={ ");
sb.append("\r\n");
List playerItemmeltingResultList582 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingResultList")){
if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingResultList") instanceof List) playerItemmeltingResultList582=(List<?>)O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingResultList");
else if (O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingResultList").getClass().isArray()) playerItemmeltingResultList582=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("playerItem"),"meltingResultList")).collect(Collectors.toList());
}
playerItemmeltingResultList582.forEach(meltingResult->{
try{
sb.append("		{");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingResult,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingResult,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingResult,"displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingResult,"unit"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(meltingResult,"unitType.number"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
sb.append("}, ");
sb.append("\r\n");
return sb.toString();
}

}