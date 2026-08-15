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

public class DailyCheck implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result=");
sb.append(context.get("result"));
sb.append(" ");
sb.append("\r\n");
sb.append("day=\"");
sb.append(context.get("date"));
sb.append("\" ");
sb.append("\r\n");
sb.append("week=");
sb.append(context.get("day"));
sb.append(" ");
sb.append("\r\n");
sb.append("check_days={ ");
sb.append("\r\n");
List checkList668 = new ArrayList<>();
if (null!=context.get("checkList")){
if (context.get("checkList") instanceof List) checkList668=(List<?>)context.get("checkList");
else if (context.get("checkList") instanceof int[]) checkList668=Stream.of((int[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof long[]) checkList668=Stream.of((long[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof float[]) checkList668=Stream.of((float[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof double[]) checkList668=Stream.of((double[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof byte[]) checkList668=Stream.of((byte[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof String[]) checkList668=Stream.of((String[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList").getClass().isArray()) checkList668=Stream.of(context.get("checkList")).collect(Collectors.toList());
}
checkList668.forEach(checkDay->{
try{
sb.append("		\"");
sb.append(checkDay);
sb.append("\", ");
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