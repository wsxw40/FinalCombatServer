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
List teamHistoryList320 = new ArrayList<>();
if (null!=context.get("teamHistoryList")){
if (context.get("teamHistoryList") instanceof List) teamHistoryList320=(List<?>)context.get("teamHistoryList");
else if (context.get("teamHistoryList").getClass().isArray()) teamHistoryList320=Stream.of((Object[])context.get("teamHistoryList")).collect(Collectors.toList());
}
teamHistoryList320.forEach(teamHistory->{
try{
sb.append("	{");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamHistory,"result"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamHistory,"attendees"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(teamHistory,"battleTime"));
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