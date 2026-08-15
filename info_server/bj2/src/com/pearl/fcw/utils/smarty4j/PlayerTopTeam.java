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

public class PlayerTopTeam implements Ctx {
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
sb.append("data = { ");
sb.append("\r\n");
sb.append("	{\"排名\",\"昵称\",\"积分\",\"杀人数\",\"死亡数\",\"爆头数\",\"手雷杀人数\",\"刀杀人数\",\"击中分数\",\"胜率\",}, ");
sb.append("\r\n");
List list537 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list537=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list537=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list537.forEach(entry->{
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