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

public class FriendList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("friendlist = { ");
sb.append("\r\n");
List friends347 = new ArrayList<>();
if (null!=context.get("friends")){
if (context.get("friends") instanceof List) friends347=(List<?>)context.get("friends");
else if (context.get("friends") instanceof int[]) friends347=Stream.of((int[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof long[]) friends347=Stream.of((long[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof float[]) friends347=Stream.of((float[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof double[]) friends347=Stream.of((double[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof byte[]) friends347=Stream.of((byte[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof String[]) friends347=Stream.of((String[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends").getClass().isArray()) friends347=Stream.of(context.get("friends")).collect(Collectors.toList());
}
friends347.forEach(friend->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(friend,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(friend,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(friend,"card"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("blacklist = { ");
sb.append("\r\n");
List blackList238 = new ArrayList<>();
if (null!=context.get("blackList")){
if (context.get("blackList") instanceof List) blackList238=(List<?>)context.get("blackList");
else if (context.get("blackList") instanceof int[]) blackList238=Stream.of((int[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList") instanceof long[]) blackList238=Stream.of((long[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList") instanceof float[]) blackList238=Stream.of((float[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList") instanceof double[]) blackList238=Stream.of((double[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList") instanceof byte[]) blackList238=Stream.of((byte[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList") instanceof String[]) blackList238=Stream.of((String[])context.get("blackList")).collect(Collectors.toList());
else if (context.get("blackList").getClass().isArray()) blackList238=Stream.of(context.get("blackList")).collect(Collectors.toList());
}
blackList238.forEach(black->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(black,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(black,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(black,"card"));
sb.append(",		 ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}