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

public class StageQuit implements Ctx {
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",5)){
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfo977 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof List) stageClearstageClearPlayerInfo977=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo").getClass().isArray()) stageClearstageClearPlayerInfo977=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfo977.forEach(playerInfo->{
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
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList700 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList700=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList700=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList700.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("				 ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list =			{ ");
sb.append("\r\n");
List playerInfobuffList984 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList984=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList984=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList984.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={} ");
sb.append("\r\n");
} else {
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0347 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0347=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0347=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0347.forEach(playerInfo->{
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
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList873 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList873=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList873=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList873.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e5){
logger.error(e5.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList535 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList535=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList535=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList535.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("	team1={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam1564 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1564=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1564=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1564.forEach(playerInfo->{
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
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList988 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList988=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList988=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList988.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(box,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
}
sb.append("					}, ");
sb.append("\r\n");
sb.append("				}, ");
sb.append("\r\n");
}catch(Exception e8){
logger.error(e8.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list = { ");
sb.append("\r\n");
List playerInfobuffList107 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList107=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList107=Stream.of((Object[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList107.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e9){
logger.error(e9.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
sb.append("mvp ={\"\",1,\"\",\"\",\"\",1,-1,}	 ");
sb.append("\r\n");
return sb.toString();
}

}