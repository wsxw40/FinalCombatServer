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
List players287 = new ArrayList<>();
if (null!=context.get("players")){
if (context.get("players") instanceof List) players287=(List<?>)context.get("players");
else if (context.get("players").getClass().isArray()) players287=Stream.of((Object[])context.get("players")).collect(Collectors.toList());
}
players287.forEach(player->{
try{
sb.append("	{");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"id"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"rank"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"name"));
sb.append("\" , \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"playerTeam.name"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"online"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"isVip"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(player,"icon"));
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