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

public class TeamMemberAll implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("team = { ");
sb.append("\r\n");
sb.append("	");
sb.append(O2oUtil.getSmarty4jProperty(context.get("team"),"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("team"),"name"));
sb.append("\",");
sb.append(context.get("num"));
sb.append(", ");
sb.append("\r\n");
sb.append("	member = { ");
sb.append("\r\n");
List teammemberList609 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")){
if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof List) teammemberList609=(List<?>)O2oUtil.getSmarty4jProperty(context.get("team"),"memberList");
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof int[]) teammemberList609=Stream.of((int[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof long[]) teammemberList609=Stream.of((long[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof float[]) teammemberList609=Stream.of((float[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof double[]) teammemberList609=Stream.of((double[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof byte[]) teammemberList609=Stream.of((byte[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList") instanceof String[]) teammemberList609=Stream.of((String[])O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(context.get("team"),"memberList").getClass().isArray()) teammemberList609=Stream.of(O2oUtil.getSmarty4jProperty(context.get("team"),"memberList")).collect(Collectors.toList());
}
teammemberList609.forEach(member->{
try{
sb.append("     		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(member,"playerId"));
sb.append(" ,");
sb.append(O2oUtil.getSmarty4jProperty(member,"job"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(member,"rank"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(member,"nickName"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(member,"online"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(member,"score"));
sb.append(", ");
sb.append("\r\n");
sb.append(", ");
sb.append("\r\n");
sb.append(O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(member,"killNum"))-O2oUtil.parseFloat(O2oUtil.getSmarty4jProperty(member,"deadNum")));
sb.append(",");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(member,"isVip"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jProperty(member,"card"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(member,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
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