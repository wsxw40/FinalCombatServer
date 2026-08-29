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

public class StageClear implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"rid"));
sb.append(" ");
sb.append("\r\n");
sb.append("type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"type"));
sb.append(" ");
sb.append("\r\n");
sb.append("levelName = \"<LD#stageClear.levelId#^0>\" ");
sb.append("\r\n");
sb.append("lct = ");
sb.append(context.get("lct"));
sb.append(" ");
sb.append("\r\n");
sb.append("winner = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"winner"));
sb.append(" ");
sb.append("\r\n");
sb.append("terrorist_score = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"terroristScore"));
sb.append(" ");
sb.append("\r\n");
sb.append("police_score = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"policeScore"));
sb.append(" ");
sb.append("\r\n");
sb.append("activity=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"onlineActivityOffset"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",5)){
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0771 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0771=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0771=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0771.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"costReliveCoin"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList756 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList756=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList756=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList756.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		},			 ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={} ");
sb.append("\r\n");
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",10)){
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0108 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0108=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0108=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0108.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"costReliveCoin"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList899 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList899=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList899=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList899.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			awards_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"boss2AwardsNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			awards_list ={ ");
sb.append("\r\n");
List playerInfoboss2Awards661 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof List) playerInfoboss2Awards661=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards").getClass().isArray()) playerInfoboss2Awards661=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
}
playerInfoboss2Awards661.forEach(theItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					characters={}, ");
sb.append("\r\n");
sb.append("					description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar4426=new Context();
includeContextVar4426.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"unitType"));
includeContextVar4426.set("unit",O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(new Timelimit().get(includeContextVar4426));
sb.append("					sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item_num=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("					total_rare_level=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"unitType"),"==",1)){
sb.append("							");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"))*O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"unit")));
sb.append("  , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append("  , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("						wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					common={ ");
sb.append("\r\n");
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("						star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("								-1 , ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("						cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("						rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList741 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList741=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList741=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList741.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("						},  ");
sb.append("\r\n");
}catch(Exception e7){
logger.error(e7.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					package = { ");
sb.append("\r\n");
List theItemsysItempackages874 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages874=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages874=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages874.forEach(item->{
try{
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList320 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList320=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList320=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList320.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList292 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList292=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList292=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList292.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e10){
logger.error(e10.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList257 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList257=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList257=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList257.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e11){
logger.error(e11.toString());
}
});
sb.append("					},		 ");
sb.append("\r\n");
sb.append("					resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource105 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource105=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource105=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource105.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("									\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e12){
logger.error(e12.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources86 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources86=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources86=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources86.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e13){
logger.error(e13.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources697 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources697=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources697=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources697.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e14){
logger.error(e14.toString());
}
});
} else {
List theItemsysItemresources21 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources21=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources21=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources21.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e15){
logger.error(e15.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			brands_list ={ ");
sb.append("\r\n");
List playerInfobrands386 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"brands")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof List) playerInfobrands386=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"brands");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands").getClass().isArray()) playerInfobrands386=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
}
playerInfobrands386.forEach(theItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList855 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList855=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList855=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList855.forEach(ids->{
try{
sb.append("							");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e17){
logger.error(e17.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar5622=new Context();
includeContextVar5622.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"unitType"));
includeContextVar5622.set("unit",O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(new Timelimit().get(includeContextVar5622));
sb.append("					sendperson = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item_num=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("					total_rare_level=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"unitType"),"==",1)){
sb.append("							");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"))*O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(theItem,"unit")));
sb.append("  , ");
sb.append("\r\n");
} else {
sb.append("							");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append("  , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("						wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					common={ ");
sb.append("\r\n");
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_hot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("						star=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("								-1 , ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceFire_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceBlast_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceBullet_add=0, ");
sb.append("\r\n");
sb.append("						cResistanceKnife_add=0, ");
sb.append("\r\n");
sb.append("						cBloodAdd_add=0, ");
sb.append("\r\n");
sb.append("						rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList937 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList937=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList937=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList937.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(property,"basePropertyStr"));
sb.append("\" ");
sb.append("\r\n");
sb.append("						},  ");
sb.append("\r\n");
}catch(Exception e19){
logger.error(e19.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					package = { ");
sb.append("\r\n");
List theItemsysItempackages942 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages942=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages942=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages942.forEach(item->{
try{
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e20){
logger.error(e20.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList839 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList839=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList839=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList839.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e21){
logger.error(e21.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList153 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList153=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList153=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList153.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e22){
logger.error(e22.toString());
}
});
sb.append("					}, ");
sb.append("\r\n");
sb.append("					voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList752 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList752=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList752=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList752.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e23){
logger.error(e23.toString());
}
});
sb.append("					},	 ");
sb.append("\r\n");
sb.append("					resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource28 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource28=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource28=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource28.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("									\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e24){
logger.error(e24.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources758 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources758=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources758=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources758.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e25){
logger.error(e25.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("							type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources497 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources497=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources497=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources497.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e26){
logger.error(e26.toString());
}
});
} else {
List theItemsysItemresources812 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources812=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources812=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources812.forEach(resource->{
try{
sb.append("								\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e27){
logger.error(e27.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={} ");
sb.append("\r\n");
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",15)){
sb.append("	winnerRes =");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"winnerRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("	loserRes=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("stageClear"),"loserRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("	team0costItems={ ");
sb.append("\r\n");
List stageClearteamaCostItems87 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof List) stageClearteamaCostItems87=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems").getClass().isArray()) stageClearteamaCostItems87=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
}
stageClearteamaCostItems87.forEach(theItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			costNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("				iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			createtime=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"createTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList451 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList451=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList451=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList451.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e29){
logger.error(e29.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("			isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("			mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				isAsset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",13)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						1,  ");
sb.append("\r\n");
}
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			parts = { ");
sb.append("\r\n");
List theItemparts101 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts101=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts101=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts101.forEach(part->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e30){
logger.error(e30.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList837 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList837=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList837=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList837.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e31){
logger.error(e31.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList346 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList346=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList346=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList346.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e32){
logger.error(e32.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList428 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList428=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList428=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList428.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e33){
logger.error(e33.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItempackages881 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages881=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages881=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages881.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e34){
logger.error(e34.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource772 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource772=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource772=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource772.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e35){
logger.error(e35.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources34 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources34=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources34=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources34.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e36){
logger.error(e36.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources780 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources780=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources780=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources780.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e37){
logger.error(e37.toString());
}
});
} else {
List theItemresources741 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources741=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources741=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources741.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e38){
logger.error(e38.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e38){
logger.error(e38.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1costItems={ ");
sb.append("\r\n");
List stageClearteambCostItems446 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof List) stageClearteambCostItems446=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems").getClass().isArray()) stageClearteambCostItems446=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
}
stageClearteambCostItems446.forEach(theItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			costNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("				iid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			createtime=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"createTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList877 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList877=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList877=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList877.forEach(ids->{
try{
sb.append("					");
sb.append(ids);
sb.append(",  ");
sb.append("\r\n");
}catch(Exception e40){
logger.error(e40.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			isDefault =   ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"isDefault"),"==","Y")){
sb.append("				0 , ");
sb.append("\r\n");
} else {
sb.append("				1 , ");
sb.append("\r\n");
}
sb.append("			mType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			mValue = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				isAsset = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isAsset"));
sb.append(", ");
sb.append("\r\n");
sb.append("				isOpenQuality= ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",13)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						1,  ");
sb.append("\r\n");
}
sb.append("				level = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				materialNeed = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"materialNeed"));
sb.append(", ");
sb.append("\r\n");
sb.append("				type = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("				subtype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"timeLeft"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				seq=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_vip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				holeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			performance = { ");
sb.append("\r\n");
sb.append("				damange = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("				speed =");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.shootSpeed"));
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
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("				ammo_count=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			color=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			parts = { ");
sb.append("\r\n");
List theItemparts635 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts635=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts635=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts635.forEach(part->{
try{
sb.append("				{");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(part,"id"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e41){
logger.error(e41.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			gpprices={ ");
sb.append("\r\n");
List theItemsysItemgpPricesList6 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList6=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList6=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList6.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e42){
logger.error(e42.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList567 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList567=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList567=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList567.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e43){
logger.error(e43.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			voucherprices={ ");
sb.append("\r\n");
List theItemsysItemvoucherPricesList267 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList267=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList267=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList267.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
sb.append("\r\n");
}catch(Exception e44){
logger.error(e44.toString());
}
});
sb.append("			},	 ");
sb.append("\r\n");
sb.append("			package = { ");
sb.append("\r\n");
List theItempackages507 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages507=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages507=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages507.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e45){
logger.error(e45.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			resource = { ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource83 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource83=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource83=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource83.forEach(res->{
try{
if((O2oUtil.compare(res,"!=",""))){
sb.append("							\"");
sb.append(res);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e46){
logger.error(e46.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",2)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources439 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources439=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources439=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources439.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e47){
logger.error(e47.toString());
}
});
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",3)){
sb.append("					type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources351 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources351=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources351=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources351.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e48){
logger.error(e48.toString());
}
});
} else {
List theItemresources569 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources569=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources569=Stream.of((Object[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources569.forEach(resource->{
try{
sb.append("						\"");
sb.append(resource);
sb.append("\",  ");
sb.append("\r\n");
}catch(Exception e49){
logger.error(e49.toString());
}
});
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e49){
logger.error(e49.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0108 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0108=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0108=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0108.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			baseRes=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"earnRobRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			captainRes=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"captainAddRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e50){
logger.error(e50.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam1717 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1717=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1717=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1717.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			baseRes=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"earnRobRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			captainRes=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"captainAddRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e51){
logger.error(e51.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
} else if ( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"isMatch"),"==",7)){
sb.append("	items={ ");
sb.append("\r\n");
List stageClearitems712 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof List) stageClearitems712=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items").getClass().isArray()) stageClearitems712=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
}
stageClearitems712.forEach(thisItem->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"name"));
sb.append("\"}, ");
sb.append("\r\n");
}catch(Exception e52){
logger.error(e52.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0661 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0661=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0661=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0661.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			item_list={ ");
sb.append("\r\n");
List playerInfoitems84 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"items")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof List) playerInfoitems84=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"items");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items").getClass().isArray()) playerInfoitems84=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
}
playerInfoitems84.forEach(thisItem->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"sysItem.id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"unit"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e54){
logger.error(e54.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList253 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList253=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList253=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList253.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e55){
logger.error(e55.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e55){
logger.error(e55.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam1720 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1720=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1720=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1720.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			item_list={ ");
sb.append("\r\n");
List playerInfoitems78 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"items")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof List) playerInfoitems78=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"items");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items").getClass().isArray()) playerInfoitems78=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
}
playerInfoitems78.forEach(thisItem->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"sysItem.id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(thisItem,"unit"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e57){
logger.error(e57.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList611 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList611=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList611=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList611.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e58){
logger.error(e58.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e58){
logger.error(e58.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
} else {
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0975 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0975=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0975=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0975.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList251 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList251=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList251=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList251.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e60){
logger.error(e60.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e60){
logger.error(e60.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam1510 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1510=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1510=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1510.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList673 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList673=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList673=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList673.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e62){
logger.error(e62.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e62){
logger.error(e62.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("mvp ={	 ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("mvpPlayer"),"!=",null)){
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpPlayer"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpPlayer"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpPlayer"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpWeapon"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpWeapon"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpPlayer"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpWeapon"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"sysItem.isStrengthen"),"==",0)){
sb.append("			-1, ");
sb.append("\r\n");
} else {
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("mvpWeapon"),"level"));
sb.append(", ");
sb.append("\r\n");
}
} else {
sb.append("		\"\",1,\"\",\"\",\"\",1,-1,-1, ");
sb.append("\r\n");
}
sb.append("}	 ");
sb.append("\r\n");
return sb.toString();
}

}