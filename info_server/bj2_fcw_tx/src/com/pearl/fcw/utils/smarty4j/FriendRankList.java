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

public class FriendRankList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("list={ ");
sb.append("\r\n");
List list144 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list144=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list144=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list144=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list144=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list144=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list144=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list144=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list144=Stream.of(context.get("list")).collect(Collectors.toList());
}
list144.forEach(friend->{
try{
sb.append("	{");
sb.append(O2oUtil.getSmarty4jProperty(friend,"friendId"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jProperty(friend,"name"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(friend,"rank"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(friend,"gameRank"));
sb.append(",}, ");
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