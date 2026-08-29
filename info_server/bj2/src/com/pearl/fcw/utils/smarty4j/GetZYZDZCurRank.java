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
List curZYZDZRankList152 = new ArrayList<>();
if (null!=context.get("curZYZDZRankList")){
if (context.get("curZYZDZRankList") instanceof List) curZYZDZRankList152=(List<?>)context.get("curZYZDZRankList");
else if (context.get("curZYZDZRankList").getClass().isArray()) curZYZDZRankList152=Stream.of((Object[])context.get("curZYZDZRankList")).collect(Collectors.toList());
}
curZYZDZRankList152.forEach(team->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"teamSpaceLevel"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"predayResAmount"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"curWeekRobCount"));
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