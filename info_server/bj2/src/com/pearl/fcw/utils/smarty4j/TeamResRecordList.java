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

public class TeamResRecordList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List list590 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list590=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list590=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list590.forEach(record->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		 ");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"battleFieldRobDaily.type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		 ");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"isAttacker"));
sb.append(", ");
sb.append("\r\n");
sb.append("		 \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"attackTeam.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		 \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"defenceTeam.name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		 ");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"battleFieldRobDaily.robAmount"));
sb.append(", ");
sb.append("\r\n");
sb.append("		 ");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"battleFieldRobDaily.beRobAmount"));
sb.append(", ");
sb.append("\r\n");
sb.append("		 \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(record,"battleTime"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	 },	 ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}