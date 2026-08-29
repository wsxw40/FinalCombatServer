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

public class RequestFriendList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("friendlist = { ");
sb.append("\r\n");
List friends288 = new ArrayList<>();
if (null!=context.get("friends")){
if (context.get("friends") instanceof List) friends288=(List<?>)context.get("friends");
else if (context.get("friends") instanceof int[]) friends288=Stream.of((int[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof long[]) friends288=Stream.of((long[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof float[]) friends288=Stream.of((float[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof double[]) friends288=Stream.of((double[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof byte[]) friends288=Stream.of((byte[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends") instanceof String[]) friends288=Stream.of((String[])context.get("friends")).collect(Collectors.toList());
else if (context.get("friends").getClass().isArray()) friends288=Stream.of(context.get("friends")).collect(Collectors.toList());
}
friends288.forEach(friend->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(friend,"playerId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(friend,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(friend,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("grouplist = { ");
sb.append("\r\n");
List groupList778 = new ArrayList<>();
if (null!=context.get("groupList")){
if (context.get("groupList") instanceof List) groupList778=(List<?>)context.get("groupList");
else if (context.get("groupList") instanceof int[]) groupList778=Stream.of((int[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList") instanceof long[]) groupList778=Stream.of((long[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList") instanceof float[]) groupList778=Stream.of((float[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList") instanceof double[]) groupList778=Stream.of((double[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList") instanceof byte[]) groupList778=Stream.of((byte[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList") instanceof String[]) groupList778=Stream.of((String[])context.get("groupList")).collect(Collectors.toList());
else if (context.get("groupList").getClass().isArray()) groupList778=Stream.of(context.get("groupList")).collect(Collectors.toList());
}
groupList778.forEach(group->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(group,"playerId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(group,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jProperty(group,"type"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
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