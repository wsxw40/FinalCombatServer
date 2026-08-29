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
List channelList574 = new ArrayList<>();
if (null!=context.get("channelList")){
if (context.get("channelList") instanceof List) channelList574=(List<?>)context.get("channelList");
else if (context.get("channelList") instanceof int[]) channelList574=Stream.of((int[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof long[]) channelList574=Stream.of((long[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof float[]) channelList574=Stream.of((float[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof double[]) channelList574=Stream.of((double[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof byte[]) channelList574=Stream.of((byte[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof String[]) channelList574=Stream.of((String[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList").getClass().isArray()) channelList574=Stream.of(context.get("channelList")).collect(Collectors.toList());
}
channelList574.forEach(channel->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("	id=");
sb.append(O2oUtil.getSmarty4jProperty(channel,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("	name=\"");
sb.append(O2oUtil.getSmarty4jProperty(channel,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	online=");
sb.append(O2oUtil.getSmarty4jProperty(channel,"online"));
sb.append(",  ");
sb.append("\r\n");
sb.append("	max=");
sb.append(O2oUtil.getSmarty4jProperty(channel,"max"));
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