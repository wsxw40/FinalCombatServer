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

public class GetZYZDZCurRank implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
if( O2oUtil.compare(context.get("curZYZDZRankList"),"!=",null)){
sb.append("	curZYZDZRankList = { ");
sb.append("\r\n");
List curZYZDZRankList698 = new ArrayList<>();
if (null!=context.get("curZYZDZRankList")){
if (context.get("curZYZDZRankList") instanceof List) curZYZDZRankList698=(List<?>)context.get("curZYZDZRankList");
else if (context.get("curZYZDZRankList") instanceof int[]) curZYZDZRankList698=Stream.of((int[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList") instanceof long[]) curZYZDZRankList698=Stream.of((long[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList") instanceof float[]) curZYZDZRankList698=Stream.of((float[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList") instanceof double[]) curZYZDZRankList698=Stream.of((double[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList") instanceof byte[]) curZYZDZRankList698=Stream.of((byte[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList") instanceof String[]) curZYZDZRankList698=Stream.of((String[])context.get("curZYZDZRankList")).collect(Collectors.toList());
else if (context.get("curZYZDZRankList").getClass().isArray()) curZYZDZRankList698=Stream.of(context.get("curZYZDZRankList")).collect(Collectors.toList());
}
curZYZDZRankList698.forEach(team->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(team,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jProperty(team,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(team,"teamSpaceLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(team,"predayResAmount"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(team,"curWeekRobCount"));
sb.append("		 ");
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
return sb.toString();
}

}