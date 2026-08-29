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

public class TeamRecordList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List list268 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list268=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list268=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list268.forEach(r->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"bTeamName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"levelType"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"levelName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"stringScore"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"createTimeStr"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"result"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"bTeamId"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(r,"bTeamLeader"));
sb.append("\",}, ");
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