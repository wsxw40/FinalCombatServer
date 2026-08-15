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
List friends553 = new ArrayList<>();
if (null!=context.get("friends")){
if (context.get("friends") instanceof List) friends553=(List<?>)context.get("friends");
else if (context.get("friends").getClass().isArray()) friends553=Stream.of((Object[])context.get("friends")).collect(Collectors.toList());
}
friends553.forEach(friend->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"playerId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"type"));
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
List groupList130 = new ArrayList<>();
if (null!=context.get("groupList")){
if (context.get("groupList") instanceof List) groupList130=(List<?>)context.get("groupList");
else if (context.get("groupList").getClass().isArray()) groupList130=Stream.of((Object[])context.get("groupList")).collect(Collectors.toList());
}
groupList130.forEach(group->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(group,"playerId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(group,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(group,"type"));
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