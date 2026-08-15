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

public class GetBattlefieldView implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("t_logo=\"");
sb.append(context.get("t_logo"));
sb.append("\" ");
sb.append("\r\n");
sb.append("t_teamName=\"");
sb.append(context.get("t_teamName"));
sb.append("\"  ");
sb.append("\r\n");
sb.append("t_teamLeader=\"");
sb.append(context.get("t_teamLeader"));
sb.append("\"  ");
sb.append("\r\n");
sb.append("t_teamLevel=");
sb.append(context.get("t_teamLevel"));
sb.append("   ");
sb.append("\r\n");
sb.append("t_exp=");
sb.append(context.get("t_exp"));
sb.append("  ");
sb.append("\r\n");
sb.append("t_totalExp=");
sb.append(context.get("t_totalExp"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_teamFightNum=");
sb.append(context.get("t_teamFightNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_recoreRankingCurr=");
sb.append(context.get("t_recoreRankingCurr"));
sb.append("  ");
sb.append("\r\n");
sb.append("t_resourceRank=");
sb.append(context.get("t_resourceRank"));
sb.append("  ");
sb.append("\r\n");
sb.append("t_teamSpaceLevel=");
sb.append(context.get("t_teamSpaceLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_transitionResource=");
sb.append(context.get("t_transitionResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_addTransition=");
sb.append(context.get("t_addTransition"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_unusableResource=");
sb.append(context.get("t_unusableResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_unusableResourceMAX=");
sb.append(context.get("t_unusableResourceMAX"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_usableResource=");
sb.append(context.get("t_usableResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_usableResourceMAX=");
sb.append(context.get("t_usableResourceMAX"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_teamMemberAcount=");
sb.append(context.get("t_teamMemberAcount"));
sb.append(" ");
sb.append("\r\n");
sb.append("t_teamSpaceActive=");
sb.append(context.get("t_teamSpaceActive"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_Id=");
sb.append(context.get("p_Id"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_TransitionResource=");
sb.append(context.get("p_TransitionResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_AddTransition=");
sb.append(context.get("p_AddTransition"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_UnusableResource=");
sb.append(context.get("p_UnusableResource"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_UnusableResourceMAX=");
sb.append(context.get("p_UnusableResourceMAX"));
sb.append(" ");
sb.append("\r\n");
sb.append("p_UsableResource=");
sb.append(context.get("p_UsableResource"));
sb.append("	 ");
sb.append("\r\n");
sb.append("p_UsableResourceMAX=");
sb.append(context.get("p_UsableResourceMAX"));
sb.append(" ");
sb.append("\r\n");
sb.append("challenge=");
sb.append(context.get("challenge"));
sb.append(" ");
sb.append("\r\n");
sb.append("challengeSDate=\"");
sb.append(context.get("challengeSDate"));
sb.append("\" ");
sb.append("\r\n");
sb.append("challengeEDate=\"");
sb.append(context.get("challengeEDate"));
sb.append("\" ");
sb.append("\r\n");
sb.append("Res_ratio_status=\"");
sb.append(context.get("Res_ratio_status"));
sb.append("\" ");
sb.append("\r\n");
sb.append("player_Res_ratio_status=\"");
sb.append(context.get("player_Res_ratio_status"));
sb.append("\" ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("preZYZDZRankList"),"!=",null)){
sb.append("	preZYZDZRankList = { ");
sb.append("\r\n");
List preZYZDZRankList769 = new ArrayList<>();
if (null!=context.get("preZYZDZRankList")){
if (context.get("preZYZDZRankList") instanceof List) preZYZDZRankList769=(List<?>)context.get("preZYZDZRankList");
else if (context.get("preZYZDZRankList") instanceof int[]) preZYZDZRankList769=Stream.of((int[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList") instanceof long[]) preZYZDZRankList769=Stream.of((long[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList") instanceof float[]) preZYZDZRankList769=Stream.of((float[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList") instanceof double[]) preZYZDZRankList769=Stream.of((double[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList") instanceof byte[]) preZYZDZRankList769=Stream.of((byte[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList") instanceof String[]) preZYZDZRankList769=Stream.of((String[])context.get("preZYZDZRankList")).collect(Collectors.toList());
else if (context.get("preZYZDZRankList").getClass().isArray()) preZYZDZRankList769=Stream.of(context.get("preZYZDZRankList")).collect(Collectors.toList());
}
preZYZDZRankList769.forEach(topTeam->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"team.id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"team.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"team.teamSpaceLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"ableBeChallenge"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"leftTime"));
sb.append(",     ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"challengeHillStatus.stones"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"challengeHillStatus.canBeRob"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"challengeCost"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"leaderMaxReturn"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(topTeam,"memberMaxDisReturn"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
sb.append("SHOP_PersonalTransform={ ");
sb.append("\r\n");
sb.append("	type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	costs={ ");
sb.append("\r\n");
List SHOP_PersonalTransformcosts723 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")){
if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof List) SHOP_PersonalTransformcosts723=(List<?>)O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs");
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof int[]) SHOP_PersonalTransformcosts723=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof long[]) SHOP_PersonalTransformcosts723=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof float[]) SHOP_PersonalTransformcosts723=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof double[]) SHOP_PersonalTransformcosts723=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof byte[]) SHOP_PersonalTransformcosts723=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs") instanceof String[]) SHOP_PersonalTransformcosts723=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs").getClass().isArray()) SHOP_PersonalTransformcosts723=Stream.of(O2oUtil.getSmarty4jProperty(context.get("SHOP_PersonalTransform"),"costs")).collect(Collectors.toList());
}
SHOP_PersonalTransformcosts723.forEach(cost->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(cost,"[0]"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(cost,"[1]"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(cost,"[2]"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(cost,"[3]"));
sb.append(" ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("SHOP_TeamTransofrm={ ");
sb.append("\r\n");
sb.append("	type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	costs={ ");
sb.append("\r\n");
List SHOP_TeamTransofrmcosts735 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")){
if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof List) SHOP_TeamTransofrmcosts735=(List<?>)O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs");
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof int[]) SHOP_TeamTransofrmcosts735=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof long[]) SHOP_TeamTransofrmcosts735=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof float[]) SHOP_TeamTransofrmcosts735=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof double[]) SHOP_TeamTransofrmcosts735=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof byte[]) SHOP_TeamTransofrmcosts735=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs") instanceof String[]) SHOP_TeamTransofrmcosts735=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs").getClass().isArray()) SHOP_TeamTransofrmcosts735=Stream.of(O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamTransofrm"),"costs")).collect(Collectors.toList());
}
SHOP_TeamTransofrmcosts735.forEach(cost->{
try{
sb.append("		{ ");
sb.append("\r\n");
List cost983 = new ArrayList<>();
if (null!=cost){
if (cost instanceof List) cost983=(List<?>)cost;
else if (cost instanceof int[]) cost983=Stream.of((int[])cost).collect(Collectors.toList());
else if (cost instanceof long[]) cost983=Stream.of((long[])cost).collect(Collectors.toList());
else if (cost instanceof float[]) cost983=Stream.of((float[])cost).collect(Collectors.toList());
else if (cost instanceof double[]) cost983=Stream.of((double[])cost).collect(Collectors.toList());
else if (cost instanceof byte[]) cost983=Stream.of((byte[])cost).collect(Collectors.toList());
else if (cost instanceof String[]) cost983=Stream.of((String[])cost).collect(Collectors.toList());
else if (cost.getClass().isArray()) cost983=Stream.of(cost).collect(Collectors.toList());
}
cost983.forEach(cost1->{
try{
sb.append("				");
sb.append(cost1);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e4){
logger.error(e4.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
sb.append("SHOP_TeamBuy={ ");
sb.append("\r\n");
sb.append("	type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("	costs={ ");
sb.append("\r\n");
List SHOP_TeamBuycosts63 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")){
if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof List) SHOP_TeamBuycosts63=(List<?>)O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs");
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof int[]) SHOP_TeamBuycosts63=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof long[]) SHOP_TeamBuycosts63=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof float[]) SHOP_TeamBuycosts63=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof double[]) SHOP_TeamBuycosts63=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof byte[]) SHOP_TeamBuycosts63=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs") instanceof String[]) SHOP_TeamBuycosts63=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs").getClass().isArray()) SHOP_TeamBuycosts63=Stream.of(O2oUtil.getSmarty4jProperty(context.get("SHOP_TeamBuy"),"costs")).collect(Collectors.toList());
}
SHOP_TeamBuycosts63.forEach(cost->{
try{
sb.append("		{ ");
sb.append("\r\n");
List cost151 = new ArrayList<>();
if (null!=cost){
if (cost instanceof List) cost151=(List<?>)cost;
else if (cost instanceof int[]) cost151=Stream.of((int[])cost).collect(Collectors.toList());
else if (cost instanceof long[]) cost151=Stream.of((long[])cost).collect(Collectors.toList());
else if (cost instanceof float[]) cost151=Stream.of((float[])cost).collect(Collectors.toList());
else if (cost instanceof double[]) cost151=Stream.of((double[])cost).collect(Collectors.toList());
else if (cost instanceof byte[]) cost151=Stream.of((byte[])cost).collect(Collectors.toList());
else if (cost instanceof String[]) cost151=Stream.of((String[])cost).collect(Collectors.toList());
else if (cost.getClass().isArray()) cost151=Stream.of(cost).collect(Collectors.toList());
}
cost151.forEach(cost1->{
try{
sb.append("				");
sb.append(cost1);
sb.append(", ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e6){
logger.error(e6.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}