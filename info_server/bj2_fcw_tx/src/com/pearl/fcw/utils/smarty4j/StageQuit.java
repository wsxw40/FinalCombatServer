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
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"rid"));
sb.append(" ");
sb.append("\r\n");
sb.append("type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"));
sb.append(" ");
sb.append("\r\n");
sb.append("winner = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"winner"));
sb.append(" ");
sb.append("\r\n");
sb.append("terrorist_score = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"terroristScore"));
sb.append(" ");
sb.append("\r\n");
sb.append("police_score = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"policeScore"));
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",5)){
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfo424 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof List) stageClearstageClearPlayerInfo424=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof int[]) stageClearstageClearPlayerInfo424=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof long[]) stageClearstageClearPlayerInfo424=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof float[]) stageClearstageClearPlayerInfo424=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof double[]) stageClearstageClearPlayerInfo424=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof byte[]) stageClearstageClearPlayerInfo424=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo") instanceof String[]) stageClearstageClearPlayerInfo424=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo").getClass().isArray()) stageClearstageClearPlayerInfo424=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfo")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfo424.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList686 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList686=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof int[]) playerInfostageClearBoxList686=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof long[]) playerInfostageClearBoxList686=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof float[]) playerInfostageClearBoxList686=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof double[]) playerInfostageClearBoxList686=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof byte[]) playerInfostageClearBoxList686=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof String[]) playerInfostageClearBoxList686=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList686=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList686.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.gunProperty.color"));
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
List playerInfobuffList687 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList687=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList687=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList687=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList687=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList687=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList687=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList687=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList687=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList687.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam0329 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0329=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0329=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0329=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0329.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList756 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList756=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof int[]) playerInfostageClearBoxList756=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof long[]) playerInfostageClearBoxList756=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof float[]) playerInfostageClearBoxList756=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof double[]) playerInfostageClearBoxList756=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof byte[]) playerInfostageClearBoxList756=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof String[]) playerInfostageClearBoxList756=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList756=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList756.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.gunProperty.color"));
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
List playerInfobuffList892 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList892=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList892=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList892=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList892=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList892=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList892=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList892=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList892=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList892.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam1936 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1936=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof int[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof long[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof float[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof double[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof byte[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof String[]) stageClearstageClearPlayerInfoTeam1936=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1936=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1936.forEach(playerInfo->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"side"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currRank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currExp"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"currGp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"exp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"gp"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"score"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"dead"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"controlNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"revengeNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"assistNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"knifeKill"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHeadshotCharacter"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNum"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxKillNumCharacter"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxDamageNumCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNum"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxHealthNumCharacter"));
sb.append(",  ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTime"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"maxLiveTimeCharacter"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			box={ ");
sb.append("\r\n");
List playerInfostageClearBoxList894 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof List) playerInfostageClearBoxList894=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof int[]) playerInfostageClearBoxList894=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof long[]) playerInfostageClearBoxList894=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof float[]) playerInfostageClearBoxList894=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof double[]) playerInfostageClearBoxList894=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof byte[]) playerInfostageClearBoxList894=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList") instanceof String[]) playerInfostageClearBoxList894=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList").getClass().isArray()) playerInfostageClearBoxList894=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"stageClearBoxList")).collect(Collectors.toList());
}
playerInfostageClearBoxList894.forEach(box->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("					");
sb.append(O2oUtil.getSmarty4jProperty(box,"num"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item={ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(box,"sysItem"),"!=",null)){
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.displayName"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(box,"sysItem.gunProperty.color"));
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
List playerInfobuffList380 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList380=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList380=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList380=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList380=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList380=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList380=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList380=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList380=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList380.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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