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

public class TeamList implements Ctx {
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
List teamList867 = new ArrayList<>();
if (null!=context.get("teamList")){
if (context.get("teamList") instanceof List) teamList867=(List<?>)context.get("teamList");
else if (context.get("teamList").getClass().isArray()) teamList867=Stream.of((Object[])context.get("teamList")).collect(Collectors.toList());
}
teamList867.forEach(team->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"logo"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"kill"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"headShot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"gameWin"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"gameTotal"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"memberCount"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"size"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"challengeRatio"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"leaderName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"city"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"level"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"fight"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(team,"createTimeStr"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	}, ");
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