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

public class TeamMember implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("team = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	member = { ");
sb.append("\r\n");
List teammemberList969 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")){
if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof List) teammemberList969=(List<?>)O2oUtil.getSmarty4jProperty(context.get("team"),"memberList");
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList").getClass().isArray()) teammemberList969=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
}
teammemberList969.forEach(member->{
try{
sb.append("     		{	 ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"playerId"));
sb.append(" ,");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"job"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"rank"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"nickName"));
sb.append("\",  ");
sb.append("\r\n");
sb.append("     			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"online"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"score"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"killNum"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"deadNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("     			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"assist"));
sb.append(",  ");
sb.append("\r\n");
sb.append("     			");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(member,"killNum"))-O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(member,"deadNum")));
sb.append(", ");
sb.append("\r\n");
sb.append("     			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"exp"));
sb.append(", ");
sb.append("\r\n");
sb.append("     			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("     			");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"contribution"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"times"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"creatDate"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"isNew"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"fight"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(member,"player.icon"));
sb.append("\" ");
sb.append("\r\n");
sb.append("     		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("   	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}