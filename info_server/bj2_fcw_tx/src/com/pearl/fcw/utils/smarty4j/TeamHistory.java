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

public class TeamHistory implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("history = { ");
sb.append("\r\n");
List teamHistoryList100 = new ArrayList<>();
if (null!=context.get("teamHistoryList")){
if (context.get("teamHistoryList") instanceof List) teamHistoryList100=(List<?>)context.get("teamHistoryList");
else if (context.get("teamHistoryList") instanceof int[]) teamHistoryList100=Stream.of((int[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList") instanceof long[]) teamHistoryList100=Stream.of((long[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList") instanceof float[]) teamHistoryList100=Stream.of((float[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList") instanceof double[]) teamHistoryList100=Stream.of((double[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList") instanceof byte[]) teamHistoryList100=Stream.of((byte[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList") instanceof String[]) teamHistoryList100=Stream.of((String[])context.get("teamHistoryList")).collect(Collectors.toList());
else if (context.get("teamHistoryList").getClass().isArray()) teamHistoryList100=Stream.of(context.get("teamHistoryList")).collect(Collectors.toList());
}
teamHistoryList100.forEach(teamHistory->{
try{
sb.append("	{");
sb.append(O2oUtil.getSmarty4jProperty(teamHistory,"result"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(teamHistory,"attendees"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jProperty(teamHistory,"battleTime"));
sb.append("\"},	 ");
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