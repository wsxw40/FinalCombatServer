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

public class RankList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rank = { ");
sb.append("\r\n");
List rankList705 = new ArrayList<>();
if (null!=context.get("rankList")){
if (context.get("rankList") instanceof List) rankList705=(List<?>)context.get("rankList");
else if (context.get("rankList") instanceof int[]) rankList705=Stream.of((int[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList") instanceof long[]) rankList705=Stream.of((long[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList") instanceof float[]) rankList705=Stream.of((float[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList") instanceof double[]) rankList705=Stream.of((double[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList") instanceof byte[]) rankList705=Stream.of((byte[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList") instanceof String[]) rankList705=Stream.of((String[])context.get("rankList")).collect(Collectors.toList());
else if (context.get("rankList").getClass().isArray()) rankList705=Stream.of(context.get("rankList")).collect(Collectors.toList());
}
rankList705.forEach(rank->{
try{
sb.append("		[ ");
sb.append(O2oUtil.getSmarty4jProperty(rank,"id"));
sb.append(" ]={\"");
sb.append(O2oUtil.getSmarty4jProperty(rank,"title"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(rank,"exp"));
sb.append("}, ");
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