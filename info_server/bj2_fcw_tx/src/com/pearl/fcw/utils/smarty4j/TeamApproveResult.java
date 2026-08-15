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

public class TeamApproveResult implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("success={  ");
sb.append("\r\n");
List successNameList347 = new ArrayList<>();
if (null!=context.get("successNameList")){
if (context.get("successNameList") instanceof List) successNameList347=(List<?>)context.get("successNameList");
else if (context.get("successNameList") instanceof int[]) successNameList347=Stream.of((int[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList") instanceof long[]) successNameList347=Stream.of((long[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList") instanceof float[]) successNameList347=Stream.of((float[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList") instanceof double[]) successNameList347=Stream.of((double[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList") instanceof byte[]) successNameList347=Stream.of((byte[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList") instanceof String[]) successNameList347=Stream.of((String[])context.get("successNameList")).collect(Collectors.toList());
else if (context.get("successNameList").getClass().isArray()) successNameList347=Stream.of(context.get("successNameList")).collect(Collectors.toList());
}
successNameList347.forEach(name->{
try{
sb.append("		\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("fail={ ");
sb.append("\r\n");
sb.append("	size={ ");
sb.append("\r\n");
List szieNameList76 = new ArrayList<>();
if (null!=context.get("szieNameList")){
if (context.get("szieNameList") instanceof List) szieNameList76=(List<?>)context.get("szieNameList");
else if (context.get("szieNameList") instanceof int[]) szieNameList76=Stream.of((int[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList") instanceof long[]) szieNameList76=Stream.of((long[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList") instanceof float[]) szieNameList76=Stream.of((float[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList") instanceof double[]) szieNameList76=Stream.of((double[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList") instanceof byte[]) szieNameList76=Stream.of((byte[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList") instanceof String[]) szieNameList76=Stream.of((String[])context.get("szieNameList")).collect(Collectors.toList());
else if (context.get("szieNameList").getClass().isArray()) szieNameList76=Stream.of(context.get("szieNameList")).collect(Collectors.toList());
}
szieNameList76.forEach(name->{
try{
sb.append("			\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("	expire={ ");
sb.append("\r\n");
List expireNameList217 = new ArrayList<>();
if (null!=context.get("expireNameList")){
if (context.get("expireNameList") instanceof List) expireNameList217=(List<?>)context.get("expireNameList");
else if (context.get("expireNameList") instanceof int[]) expireNameList217=Stream.of((int[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList") instanceof long[]) expireNameList217=Stream.of((long[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList") instanceof float[]) expireNameList217=Stream.of((float[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList") instanceof double[]) expireNameList217=Stream.of((double[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList") instanceof byte[]) expireNameList217=Stream.of((byte[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList") instanceof String[]) expireNameList217=Stream.of((String[])context.get("expireNameList")).collect(Collectors.toList());
else if (context.get("expireNameList").getClass().isArray()) expireNameList217=Stream.of(context.get("expireNameList")).collect(Collectors.toList());
}
expireNameList217.forEach(name->{
try{
sb.append("			\"");
sb.append(name);
sb.append("\", ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}