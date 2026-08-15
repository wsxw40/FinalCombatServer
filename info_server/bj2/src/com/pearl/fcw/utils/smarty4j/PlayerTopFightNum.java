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
List list872 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list872=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list872=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list872.forEach(entry->{
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