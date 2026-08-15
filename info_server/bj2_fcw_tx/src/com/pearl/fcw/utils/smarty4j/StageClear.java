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
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"rid"));
sb.append(" ");
sb.append("\r\n");
sb.append("type = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"));
sb.append(" ");
sb.append("\r\n");
sb.append("lct = ");
sb.append(context.get("lct"));
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
sb.append("activity=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"onlineActivityOffset"));
sb.append(" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"type"),"==",5)){
sb.append("	team0={ ");
sb.append("\r\n");
List stageClearstageClearPlayerInfoTeam0132 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0132=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0132=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0132=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0132.forEach(playerInfo->{
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
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"costReliveCoin"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList899 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList899=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList899=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList899=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList899=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList899=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList899=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList899=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList899=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList899.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam0542 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0542=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0542=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0542=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0542.forEach(playerInfo->{
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
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"costReliveCoin"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			},		 ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList267 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList267=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList267=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList267=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList267=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList267=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList267=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList267=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList267=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList267.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
sb.append("\",}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("			}, ");
sb.append("\r\n");
sb.append("			awards_count=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"boss2AwardsNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("			awards_list ={ ");
sb.append("\r\n");
List playerInfoboss2Awards719 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof List) playerInfoboss2Awards719=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof int[]) playerInfoboss2Awards719=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof long[]) playerInfoboss2Awards719=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof float[]) playerInfoboss2Awards719=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof double[]) playerInfoboss2Awards719=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof byte[]) playerInfoboss2Awards719=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards") instanceof String[]) playerInfoboss2Awards719=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards").getClass().isArray()) playerInfoboss2Awards719=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"boss2Awards")).collect(Collectors.toList());
}
playerInfoboss2Awards719.forEach(theItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					characters={}, ");
sb.append("\r\n");
sb.append("					description =\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar6958=new Context();
includeContextVar6958.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"unitType"));
includeContextVar6958.set("unit",O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(new Timelimit().get(includeContextVar6958));
sb.append("					sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item_num=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append("  , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("						wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					common={ ");
sb.append("\r\n");
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("						star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("								-1 , ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList490 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList490=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList490=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList490=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList490=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList490=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList490=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList490=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList490=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList490.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List theItemsysItempackages10 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages10=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages10=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages10=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages10=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages10=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages10=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages10=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages10=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages10.forEach(item->{
try{
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List theItemsysItemgpPricesList314 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList314=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList314=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList314=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList314=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList314=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList314=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList314=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList314=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList314.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemcrPricesList960 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList960=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList960=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList960=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList960=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList960=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList960=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList960=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList960=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList960.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemvoucherPricesList34 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList34=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList34=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList34=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList34=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList34=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList34=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList34=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList34=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList34.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource154 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource154=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource154=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource154=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource154=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource154=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource154=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource154=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource154=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource154.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources607 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources607=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources607=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources607=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources607=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources607=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources607=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources607=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources607=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources607.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources251 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources251=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources251=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources251=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources251=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources251=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources251=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources251=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources251=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources251.forEach(resource->{
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
List theItemsysItemresources85 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources85=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources85=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources85=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources85=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources85=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources85=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources85=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources85=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources85.forEach(resource->{
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
List playerInfobrands656 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"brands")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof List) playerInfobrands656=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"brands");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof int[]) playerInfobrands656=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof long[]) playerInfobrands656=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof float[]) playerInfobrands656=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof double[]) playerInfobrands656=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof byte[]) playerInfobrands656=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands") instanceof String[]) playerInfobrands656=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"brands").getClass().isArray()) playerInfobrands656=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"brands")).collect(Collectors.toList());
}
playerInfobrands656.forEach(theItem->{
try{
sb.append("				{ ");
sb.append("\r\n");
sb.append("					sid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("					display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("					characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList630 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList630=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList630=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList630=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList630=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList630=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList630=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList630=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList630=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList630.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
Context includeContextVar7568=new Context();
includeContextVar7568.set("unitType",O2oUtil.getSmarty4jProperty(theItem,"unitType"));
includeContextVar7568.set("unit",O2oUtil.getSmarty4jProperty(theItem,"unit"));
sb.append(new Timelimit().get(includeContextVar7568));
sb.append("					sendperson = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("					item_num=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append("  , ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("						wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("					common={ ");
sb.append("\r\n");
sb.append("						level = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.level"));
sb.append(", ");
sb.append("\r\n");
sb.append("						type = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("						subtype = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.subType"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.type"),"==",1)){
sb.append("							wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("						seq=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.seq"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_vip=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_new=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isNew"));
sb.append(", ");
sb.append("\r\n");
sb.append("						is_hot=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isHot"));
sb.append(", ");
sb.append("\r\n");
sb.append("						star=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.figthNumOutput"));
sb.append(",    		 ");
sb.append("\r\n");
sb.append("						strength=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isStrengthen"),"==",0)){
sb.append("								-1 , ");
sb.append("\r\n");
} else {
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.strengthLevel"));
sb.append(" , ");
sb.append("\r\n");
}
sb.append("						cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("						cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					performance = { ");
sb.append("\r\n");
sb.append("						damange = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.damange"));
sb.append(",					 ");
sb.append("\r\n");
sb.append("						speed =");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.shootSpeed"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("						ammos = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoOneClip"));
sb.append(", ");
sb.append("\r\n");
sb.append("						ammo_count=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wAmmoCount"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("					}, ");
sb.append("\r\n");
sb.append("					color=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("					gunproperty={ ");
sb.append("\r\n");
List theItemsysItemgunPropertypropertyList269 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof List) theItemsysItemgunPropertypropertyList269=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof int[]) theItemsysItemgunPropertypropertyList269=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof long[]) theItemsysItemgunPropertypropertyList269=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof float[]) theItemsysItemgunPropertypropertyList269=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof double[]) theItemsysItemgunPropertypropertyList269=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof byte[]) theItemsysItemgunPropertypropertyList269=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList") instanceof String[]) theItemsysItemgunPropertypropertyList269=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList").getClass().isArray()) theItemsysItemgunPropertypropertyList269=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.propertyList")).collect(Collectors.toList());
}
theItemsysItemgunPropertypropertyList269.forEach(property->{
try{
sb.append("						{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"),"!=",1)){
sb.append("								");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
} else {
sb.append("								1, ");
sb.append("\r\n");
}
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(property,"basePropertyStr"));
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
List theItemsysItempackages263 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof List) theItemsysItempackages263=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof int[]) theItemsysItempackages263=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof long[]) theItemsysItempackages263=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof float[]) theItemsysItempackages263=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof double[]) theItemsysItempackages263=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof byte[]) theItemsysItempackages263=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages") instanceof String[]) theItemsysItempackages263=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages").getClass().isArray()) theItemsysItempackages263=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.packages")).collect(Collectors.toList());
}
theItemsysItempackages263.forEach(item->{
try{
sb.append("							\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"displayName"));
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
List theItemsysItemgpPricesList191 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList191=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList191=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList191=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList191=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList191=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList191=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList191=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList191=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList191.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemcrPricesList849 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList849=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList849=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList849=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList849=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList849=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList849=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList849=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList849=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList849.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemvoucherPricesList757 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList757=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList757=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList757=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList757=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList757=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList757=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList757=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList757=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList757.forEach(pay->{
try{
sb.append("						{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemsysItemresource913 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof List) theItemsysItemresource913=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof int[]) theItemsysItemresource913=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof long[]) theItemsysItemresource913=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof float[]) theItemsysItemresource913=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof double[]) theItemsysItemresource913=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof byte[]) theItemsysItemresource913=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource") instanceof String[]) theItemsysItemresource913=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource").getClass().isArray()) theItemsysItemresource913=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resource")).collect(Collectors.toList());
}
theItemsysItemresource913.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources678 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources678=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources678=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources678=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources678=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources678=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources678=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources678=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources678=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources678.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemsysItemresources127 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources127=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources127=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources127=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources127=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources127=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources127=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources127=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources127=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources127.forEach(resource->{
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
List theItemsysItemresources149 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof List) theItemsysItemresources149=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof int[]) theItemsysItemresources149=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof long[]) theItemsysItemresources149=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof float[]) theItemsysItemresources149=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof double[]) theItemsysItemresources149=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof byte[]) theItemsysItemresources149=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources") instanceof String[]) theItemsysItemresources149=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources").getClass().isArray()) theItemsysItemresources149=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.resources")).collect(Collectors.toList());
}
theItemsysItemresources149.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"winnerRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("	loserRes=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"loserRes"));
sb.append(" ");
sb.append("\r\n");
sb.append("	team0costItems={ ");
sb.append("\r\n");
List stageClearteamaCostItems479 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof List) stageClearteamaCostItems479=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof int[]) stageClearteamaCostItems479=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof long[]) stageClearteamaCostItems479=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof float[]) stageClearteamaCostItems479=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof double[]) stageClearteamaCostItems479=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof byte[]) stageClearteamaCostItems479=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems") instanceof String[]) stageClearteamaCostItems479=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems").getClass().isArray()) stageClearteamaCostItems479=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teamaCostItems")).collect(Collectors.toList());
}
stageClearteamaCostItems479.forEach(theItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			costNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("				iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			createtime=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"createTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList457 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList457=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList457=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList457=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList457=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList457=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList457=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList457=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList457=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList457.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"materialNeed"));
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
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
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				rareLevel=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.rareLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			 performance = { ");
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			parts = { ");
sb.append("\r\n");
List theItemparts813 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts813=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts813=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts813=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts813=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts813=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts813=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts813=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts813=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts813.forEach(part->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
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
List theItemsysItemgpPricesList674 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList674=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList674=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList674=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList674=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList674=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList674=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList674=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList674=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList674.forEach(pay->{
try{
sb.append("			    		{id=");
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
sb.append("			}, ");
sb.append("\r\n");
sb.append("			crprices={ ");
sb.append("\r\n");
List theItemsysItemcrPricesList139 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList139=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList139=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList139=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList139=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList139=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList139=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList139=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList139=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList139.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemvoucherPricesList118 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList118=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList118=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList118=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList118=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList118=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList118=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList118=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList118=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList118.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItempackages2 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages2=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages2=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages2=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages2=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages2=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages2=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages2=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages2=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages2.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource32 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource32=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource32=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource32=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource32=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource32=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource32=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource32=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource32=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource32.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources252 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources252=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources252=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources252=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources252=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources252=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources252=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources252=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources252=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources252.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources920 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources920=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources920=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources920=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources920=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources920=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources920=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources920=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources920=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources920.forEach(resource->{
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
List theItemresources774 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources774=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources774=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources774=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources774=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources774=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources774=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources774=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources774=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources774.forEach(resource->{
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
List stageClearteambCostItems307 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof List) stageClearteambCostItems307=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof int[]) stageClearteambCostItems307=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof long[]) stageClearteambCostItems307=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof float[]) stageClearteambCostItems307=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof double[]) stageClearteambCostItems307=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof byte[]) stageClearteambCostItems307=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems") instanceof String[]) stageClearteambCostItems307=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems").getClass().isArray()) stageClearteambCostItems307=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"teambCostItems")).collect(Collectors.toList());
}
stageClearteambCostItems307.forEach(theItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			costNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"!=",null)){
sb.append("				iid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("			display=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			name=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			createtime=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"createTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("			unit_type=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"playerItemUnitType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			modified_desc=\"");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"modifiedDesc"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			characters={ ");
sb.append("\r\n");
List theItemsysItemcharacterList685 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof List) theItemsysItemcharacterList685=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof int[]) theItemsysItemcharacterList685=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof long[]) theItemsysItemcharacterList685=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof float[]) theItemsysItemcharacterList685=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof double[]) theItemsysItemcharacterList685=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof byte[]) theItemsysItemcharacterList685=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList") instanceof String[]) theItemsysItemcharacterList685=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList").getClass().isArray()) theItemsysItemcharacterList685=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.characterList")).collect(Collectors.toList());
}
theItemsysItemcharacterList685.forEach(ids->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.description"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			pack_id = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"pack"));
sb.append(", ");
sb.append("\r\n");
sb.append("			repair_cost = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"repairCost"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"),"==",4)){
sb.append("				wid = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",1)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
sb.append(",  ");
sb.append("\r\n");
}
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"sysItem.iId"),"==",5)){
sb.append("				buff = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"buff"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.mType"));
sb.append(", ");
sb.append("\r\n");
sb.append("			mValue = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.mValue"));
sb.append(", ");
sb.append("\r\n");
sb.append("			common={ ");
sb.append("\r\n");
sb.append("				isAsset = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.isAsset"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				materialNeed = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"materialNeed"));
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
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"type"),"==",1)){
sb.append("					wid=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				durable =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"durableInt"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"durableInt"));
sb.append(",  ");
sb.append("\r\n");
}
sb.append("				quantity =  ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"quantity"));
sb.append(", ");
sb.append("\r\n");
sb.append("				minutes_left =  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"),"<=",0)){
sb.append("						0, ");
sb.append("\r\n");
} else {
sb.append("						");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"timeLeft"));
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
sb.append("				is_new=1, ");
sb.append("\r\n");
sb.append("				star = ");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"fightNum"));
sb.append(",   		 ");
sb.append("\r\n");
sb.append("				strength=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("				holeNum=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"holeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cBloodAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceFire_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceFire"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBlast_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBlast"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceBullet_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceBullet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cResistanceKnife_add=");
sb.append(O2oUtil.getSmarty4jProperty(theItem,"cResistanceKnife"));
sb.append(", ");
sb.append("\r\n");
sb.append("				cBloodAdd_add=");
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
sb.append("			parts = { ");
sb.append("\r\n");
List theItemparts579 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"parts")){
if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof List) theItemparts579=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"parts");
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof int[]) theItemparts579=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof long[]) theItemparts579=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof float[]) theItemparts579=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof double[]) theItemparts579=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof byte[]) theItemparts579=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts") instanceof String[]) theItemparts579=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"parts").getClass().isArray()) theItemparts579=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"parts")).collect(Collectors.toList());
}
theItemparts579.forEach(part->{
try{
sb.append("				{");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.subType"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(part,"sysItem.displayName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(part,"id"));
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
List theItemsysItemgpPricesList565 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof List) theItemsysItemgpPricesList565=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof int[]) theItemsysItemgpPricesList565=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof long[]) theItemsysItemgpPricesList565=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof float[]) theItemsysItemgpPricesList565=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof double[]) theItemsysItemgpPricesList565=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof byte[]) theItemsysItemgpPricesList565=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList") instanceof String[]) theItemsysItemgpPricesList565=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList").getClass().isArray()) theItemsysItemgpPricesList565=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.gpPricesList")).collect(Collectors.toList());
}
theItemsysItemgpPricesList565.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemcrPricesList494 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof List) theItemsysItemcrPricesList494=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof int[]) theItemsysItemcrPricesList494=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof long[]) theItemsysItemcrPricesList494=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof float[]) theItemsysItemcrPricesList494=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof double[]) theItemsysItemcrPricesList494=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof byte[]) theItemsysItemcrPricesList494=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList") instanceof String[]) theItemsysItemcrPricesList494=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList").getClass().isArray()) theItemsysItemcrPricesList494=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.crPricesList")).collect(Collectors.toList());
}
theItemsysItemcrPricesList494.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItemsysItemvoucherPricesList299 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")){
if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof List) theItemsysItemvoucherPricesList299=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList");
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof int[]) theItemsysItemvoucherPricesList299=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof long[]) theItemsysItemvoucherPricesList299=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof float[]) theItemsysItemvoucherPricesList299=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof double[]) theItemsysItemvoucherPricesList299=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof byte[]) theItemsysItemvoucherPricesList299=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList") instanceof String[]) theItemsysItemvoucherPricesList299=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList").getClass().isArray()) theItemsysItemvoucherPricesList299=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"sysItem.voucherPricesList")).collect(Collectors.toList());
}
theItemsysItemvoucherPricesList299.forEach(pay->{
try{
sb.append("			    		{id=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jProperty(pay,"unit"));
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
List theItempackages816 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"packages")){
if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof List) theItempackages816=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"packages");
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof int[]) theItempackages816=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof long[]) theItempackages816=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof float[]) theItempackages816=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof double[]) theItempackages816=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof byte[]) theItempackages816=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages") instanceof String[]) theItempackages816=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"packages").getClass().isArray()) theItempackages816=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"packages")).collect(Collectors.toList());
}
theItempackages816.forEach(item->{
try{
sb.append("					\"");
sb.append(O2oUtil.getSmarty4jProperty(item,"sysItem.displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List theItemresource874 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resource")){
if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof List) theItemresource874=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resource");
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof int[]) theItemresource874=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof long[]) theItemresource874=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof float[]) theItemresource874=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof double[]) theItemresource874=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof byte[]) theItemresource874=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource") instanceof String[]) theItemresource874=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resource").getClass().isArray()) theItemresource874=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resource")).collect(Collectors.toList());
}
theItemresource874.forEach(res->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources236 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources236=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources236=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources236=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources236=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources236=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources236=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources236=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources236=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources236.forEach(resource->{
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
sb.append(O2oUtil.getSmarty4jProperty(theItem,"sysItem.cId"));
sb.append(", ");
sb.append("\r\n");
List theItemresources320 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources320=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources320=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources320=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources320=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources320=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources320=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources320=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources320=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources320.forEach(resource->{
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
List theItemresources486 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(theItem,"resources")){
if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof List) theItemresources486=(List<?>)O2oUtil.getSmarty4jProperty(theItem,"resources");
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof int[]) theItemresources486=Stream.of((int[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof long[]) theItemresources486=Stream.of((long[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof float[]) theItemresources486=Stream.of((float[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof double[]) theItemresources486=Stream.of((double[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof byte[]) theItemresources486=Stream.of((byte[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources") instanceof String[]) theItemresources486=Stream.of((String[])O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(theItem,"resources").getClass().isArray()) theItemresources486=Stream.of(O2oUtil.getSmarty4jProperty(theItem,"resources")).collect(Collectors.toList());
}
theItemresources486.forEach(resource->{
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
List stageClearstageClearPlayerInfoTeam0887 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0887=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0887=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0887=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0887.forEach(playerInfo->{
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
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			baseRes=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"earnRobRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			captainRes=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"captainAddRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
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
List stageClearstageClearPlayerInfoTeam1509 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1509=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof int[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof long[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof float[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof double[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof byte[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof String[]) stageClearstageClearPlayerInfoTeam1509=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1509=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1509.forEach(playerInfo->{
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
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"rank"));
sb.append(",  ");
sb.append("\r\n");
sb.append("			isvip=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			baseRes=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"earnRobRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			captainRes=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"captainAddRes"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
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
List stageClearitems661 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof List) stageClearitems661=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof int[]) stageClearitems661=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof long[]) stageClearitems661=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof float[]) stageClearitems661=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof double[]) stageClearitems661=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof byte[]) stageClearitems661=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items") instanceof String[]) stageClearitems661=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items").getClass().isArray()) stageClearitems661=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"items")).collect(Collectors.toList());
}
stageClearitems661.forEach(thisItem->{
try{
sb.append("			{");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"name"));
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
List stageClearstageClearPlayerInfoTeam0674 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0674=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0674=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0674=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0674.forEach(playerInfo->{
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
sb.append(", ");
sb.append("\r\n");
sb.append("			");
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
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			item_list={ ");
sb.append("\r\n");
List playerInfoitems517 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"items")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof List) playerInfoitems517=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"items");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof int[]) playerInfoitems517=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof long[]) playerInfoitems517=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof float[]) playerInfoitems517=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof double[]) playerInfoitems517=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof byte[]) playerInfoitems517=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof String[]) playerInfoitems517=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items").getClass().isArray()) playerInfoitems517=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
}
playerInfoitems517.forEach(thisItem->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"sysItem.id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"unit"));
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
List playerInfobuffList331 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList331=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList331=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList331=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList331=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList331=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList331=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList331=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList331=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList331.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam1426 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1426=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof int[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof long[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof float[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof double[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof byte[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof String[]) stageClearstageClearPlayerInfoTeam1426=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1426=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1426.forEach(playerInfo->{
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
sb.append(", ");
sb.append("\r\n");
sb.append("			");
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
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			item_list={ ");
sb.append("\r\n");
List playerInfoitems757 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"items")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof List) playerInfoitems757=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"items");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof int[]) playerInfoitems757=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof long[]) playerInfoitems757=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof float[]) playerInfoitems757=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof double[]) playerInfoitems757=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof byte[]) playerInfoitems757=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items") instanceof String[]) playerInfoitems757=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"items").getClass().isArray()) playerInfoitems757=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"items")).collect(Collectors.toList());
}
playerInfoitems757.forEach(thisItem->{
try{
sb.append("					{");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"sysItem.id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(thisItem,"unit"));
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
List playerInfobuffList320 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList320=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList320=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList320=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList320=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList320=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList320=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList320=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList320=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList320.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam0906 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof List) stageClearstageClearPlayerInfoTeam0906=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof int[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof long[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof float[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof double[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof byte[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0") instanceof String[]) stageClearstageClearPlayerInfoTeam0906=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0").getClass().isArray()) stageClearstageClearPlayerInfoTeam0906=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam0")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam0906.forEach(playerInfo->{
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
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList878 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList878=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList878=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList878=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList878=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList878=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList878=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList878=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList878=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList878.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
List stageClearstageClearPlayerInfoTeam1105 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")){
if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof List) stageClearstageClearPlayerInfoTeam1105=(List<?>)O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1");
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof int[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof long[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof float[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof double[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof byte[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1") instanceof String[]) stageClearstageClearPlayerInfoTeam1105=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1").getClass().isArray()) stageClearstageClearPlayerInfoTeam1105=Stream.of(O2oUtil.getSmarty4jProperty(context.get("stageClear"),"stageClearPlayerInfoTeam1")).collect(Collectors.toList());
}
stageClearstageClearPlayerInfoTeam1105.forEach(playerInfo->{
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
sb.append("			addedVipExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"vipExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("			icon=\"");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"headGif"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			playerTeamExp=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerTeamExp"));
sb.append(", ");
sb.append("\r\n");
sb.append("			playerContribution=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"playerContribution"));
sb.append(", ");
sb.append("\r\n");
sb.append("			isTeamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"isTeamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			get_exps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getExps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_gps={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				itemAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.itemAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("				teamAdd=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getGps.teamAdd"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			get_chances={ ");
sb.append("\r\n");
sb.append("				gameGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.gameGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				vipGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.vipGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				netBarGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.netBarGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				activityGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.activityGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("				totalGet=");
sb.append(O2oUtil.getSmarty4jProperty(playerInfo,"getChances.totalGet"));
sb.append(", ");
sb.append("\r\n");
sb.append("			}, ");
sb.append("\r\n");
sb.append("			buff_list ={ ");
sb.append("\r\n");
List playerInfobuffList959 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(playerInfo,"buffList")){
if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof List) playerInfobuffList959=(List<?>)O2oUtil.getSmarty4jProperty(playerInfo,"buffList");
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof int[]) playerInfobuffList959=Stream.of((int[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof long[]) playerInfobuffList959=Stream.of((long[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof float[]) playerInfobuffList959=Stream.of((float[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof double[]) playerInfobuffList959=Stream.of((double[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof byte[]) playerInfobuffList959=Stream.of((byte[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList") instanceof String[]) playerInfobuffList959=Stream.of((String[])O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(playerInfo,"buffList").getClass().isArray()) playerInfobuffList959=Stream.of(O2oUtil.getSmarty4jProperty(playerInfo,"buffList")).collect(Collectors.toList());
}
playerInfobuffList959.forEach(buff->{
try{
sb.append("					{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
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
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpPlayer"),"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpPlayer"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpPlayer"),"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"sysItem.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"sysItem.displayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpPlayer"),"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"gunProperty.color"));
sb.append(", ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"sysItem.isStrengthen"),"==",0)){
sb.append("			-1, ");
sb.append("\r\n");
} else {
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(context.get("mvpWeapon"),"level"));
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