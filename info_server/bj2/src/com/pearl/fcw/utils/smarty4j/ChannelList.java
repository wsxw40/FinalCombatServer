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

public class ChannelList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("channels = { ");
sb.append("\r\n");
List channelList157 = new ArrayList<>();
if (null!=context.get("channelList")){
if (context.get("channelList") instanceof List) channelList157=(List<?>)context.get("channelList");
else if (context.get("channelList").getClass().isArray()) channelList157=Stream.of((Object[])context.get("channelList")).collect(Collectors.toList());
}
channelList157.forEach(channel->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	online=");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"online"));
sb.append(",  ");
sb.append("\r\n");
sb.append("	max=");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"max"));
sb.append(" ");
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