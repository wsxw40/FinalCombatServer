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

public class DailyCheckList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("day=\"");
sb.append(context.get("date"));
sb.append("\" ");
sb.append("\r\n");
sb.append("week=");
sb.append(context.get("day"));
sb.append(" ");
sb.append("\r\n");
sb.append("today_num=");
sb.append(context.get("today"));
sb.append(" ");
sb.append("\r\n");
sb.append("my_num=\"");
sb.append(context.get("myNum"));
sb.append("\" ");
sb.append("\r\n");
sb.append("tomorrow_num=\"");
sb.append(context.get("tomorrow"));
sb.append("\" ");
sb.append("\r\n");
sb.append("is_gift=");
sb.append(context.get("isGift"));
sb.append(" ");
sb.append("\r\n");
sb.append("is_show_award=");
sb.append(context.get("isShowAward"));
sb.append(" ");
sb.append("\r\n");
sb.append("check_days={ ");
sb.append("\r\n");
List checkList933 = new ArrayList<>();
if (null!=context.get("checkList")){
if (context.get("checkList") instanceof List) checkList933=(List<?>)context.get("checkList");
else if (context.get("checkList") instanceof int[]) checkList933=Stream.of((int[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof long[]) checkList933=Stream.of((long[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof float[]) checkList933=Stream.of((float[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof double[]) checkList933=Stream.of((double[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof byte[]) checkList933=Stream.of((byte[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList") instanceof String[]) checkList933=Stream.of((String[])context.get("checkList")).collect(Collectors.toList());
else if (context.get("checkList").getClass().isArray()) checkList933=Stream.of(context.get("checkList")).collect(Collectors.toList());
}
checkList933.forEach(checkDay->{
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
sb.append("check_gift={} ");
sb.append("\r\n");
sb.append("freeCheckTimes=");
sb.append(context.get("freeCheckTimes"));
sb.append(" ");
sb.append("\r\n");
sb.append("checkNum=");
sb.append(context.get("checkNum"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}