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

public class FontPlayerInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"id"));
sb.append(" ");
sb.append("\r\n");
sb.append("newGP =");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"gPoint"));
sb.append(" ");
sb.append("\r\n");
sb.append("newCR = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("playerInfo"),"xunleiPoint"));
sb.append(" ");
sb.append("\r\n");
sb.append("newTicket=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"voucher"));
sb.append(" ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"name"));
sb.append("\" ");
sb.append("\r\n");
sb.append("level = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"rank"));
sb.append(" ");
sb.append("\r\n");
sb.append("exp = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"exp"));
sb.append(" ");
sb.append("\r\n");
sb.append("newTeamReq=");
sb.append(context.get("teamRequestNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("newEmailNum=");
sb.append(context.get("emailNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("isvip=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"isVip"));
sb.append(" ");
sb.append("\r\n");
sb.append("vipExp=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"vipExp"));
sb.append(" ");
sb.append("\r\n");
sb.append("vipLeftMins=");
sb.append(context.get("vipLeftMins"));
sb.append(" ");
sb.append("\r\n");
sb.append("leftMinites=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"leftMinites"));
sb.append(" ");
sb.append("\r\n");
sb.append("internetCafe=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"internetCafe"));
sb.append(" ");
sb.append("\r\n");
sb.append("card=");
sb.append(context.get("card"));
sb.append(" ");
sb.append("\r\n");
sb.append("head_image=\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"icon"));
sb.append("\" ");
sb.append("\r\n");
sb.append("title =\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("player"),"title"));
sb.append("\" ");
sb.append("\r\n");
sb.append("top=");
sb.append(context.get("top"));
sb.append(" ");
sb.append("\r\n");
sb.append("winRate=");
sb.append(context.get("winRate"));
sb.append(" ");
sb.append("\r\n");
sb.append("maxFightNum=");
sb.append(context.get("maxFightNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("team=\"");
sb.append(context.get("team"));
sb.append("\" ");
sb.append("\r\n");
sb.append("maxLevel=");
sb.append(context.get("maxLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("currentyEXPItem=");
sb.append(context.get("currentyEXPItem"));
sb.append(" ");
sb.append("\r\n");
sb.append("buff_list ={ ");
sb.append("\r\n");
List buffList683 = new ArrayList<>();
if (null!=context.get("buffList")){
if (context.get("buffList") instanceof List) buffList683=(List<?>)context.get("buffList");
else if (context.get("buffList") instanceof int[]) buffList683=Stream.of((int[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList") instanceof long[]) buffList683=Stream.of((long[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList") instanceof float[]) buffList683=Stream.of((float[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList") instanceof double[]) buffList683=Stream.of((double[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList") instanceof byte[]) buffList683=Stream.of((byte[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList") instanceof String[]) buffList683=Stream.of((String[])context.get("buffList")).collect(Collectors.toList());
else if (context.get("buffList").getClass().isArray()) buffList683=Stream.of(context.get("buffList")).collect(Collectors.toList());
}
buffList683.forEach(buff->{
try{
sb.append("	{\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(buff,"timeLeft"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(buff,"sysItem.iBuffId"));
sb.append(",}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
if( (O2oUtil.compare(context.get("erroMsg"),"!=",null))){
sb.append("	error = \"");
sb.append(context.get("erroMsg"));
sb.append("\"  ");
sb.append("\r\n");
}
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}