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
List list656 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list656=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list656=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list656=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list656=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list656=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list656=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list656=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list656=Stream.of(context.get("list")).collect(Collectors.toList());
}
list656.forEach(r->{
try{
sb.append("		{\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"bTeamName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"levelType"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"levelName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"stringScore"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"createTimeStr"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"result"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"bTeamId"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(r,"bTeamLeader"));
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