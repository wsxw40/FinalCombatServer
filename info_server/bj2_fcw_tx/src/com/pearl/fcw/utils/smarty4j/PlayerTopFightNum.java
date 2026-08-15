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

public class PlayerTopFightNum implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(context.get("page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(context.get("pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("left_time=");
sb.append(context.get("leftTime"));
sb.append(" ");
sb.append("\r\n");
sb.append("top_num=");
sb.append(context.get("topNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("row_num=");
sb.append(context.get("rowNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("data = { ");
sb.append("\r\n");
sb.append("	{\"排名\",\"玩家\",\"战斗力\",\"杀人/死亡\",\"助攻\",\"胜率\"}, ");
sb.append("\r\n");
List list787 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list787=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list787=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list787=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list787=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list787=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list787=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list787=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list787=Stream.of(context.get("list")).collect(Collectors.toList());
}
list787.forEach(entry->{
try{
sb.append("		");
sb.append(entry);
sb.append(" ");
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