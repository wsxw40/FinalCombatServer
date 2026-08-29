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

public class PlayerPageList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page_count = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("list = { ");
sb.append("\r\n");
List players712 = new ArrayList<>();
if (null!=context.get("players")){
if (context.get("players") instanceof List) players712=(List<?>)context.get("players");
else if (context.get("players") instanceof int[]) players712=Stream.of((int[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof long[]) players712=Stream.of((long[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof float[]) players712=Stream.of((float[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof double[]) players712=Stream.of((double[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof byte[]) players712=Stream.of((byte[])context.get("players")).collect(Collectors.toList());
else if (context.get("players") instanceof String[]) players712=Stream.of((String[])context.get("players")).collect(Collectors.toList());
else if (context.get("players").getClass().isArray()) players712=Stream.of(context.get("players")).collect(Collectors.toList());
}
players712.forEach(player->{
try{
sb.append("	{");
sb.append(O2oUtil.getSmarty4jProperty(player,"id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(player,"rank"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(player,"name"));
sb.append("\" , \"");
sb.append(O2oUtil.getSmarty4jProperty(player,"playerTeam.name"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(player,"online"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(player,"isVip"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(player,"icon"));
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