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

public class GrowthMissionList implements Ctx {
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
sb.append("num = ");
sb.append(context.get("num"));
sb.append(" ");
sb.append("\r\n");
sb.append("awoke = ");
sb.append(context.get("awoke"));
sb.append(" ");
sb.append("\r\n");
sb.append("mission = { ");
sb.append("\r\n");
List missions262 = new ArrayList<>();
if (null!=context.get("missions")){
if (context.get("missions") instanceof List) missions262=(List<?>)context.get("missions");
else if (context.get("missions").getClass().isArray()) missions262=Stream.of((Object[])context.get("missions")).collect(Collectors.toList());
}
missions262.forEach(mi->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"icon"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"title"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"desc"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"target"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"number"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"status"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"award"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"type"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"needaward"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"isnew"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"money"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"exp"));
sb.append(",0,0,");
sb.append(O2oUtil.getSmarty4jPropertyNil(mi,"isMain"));
sb.append(", ");
sb.append("\r\n");
Context includeContextVar6361=new Context();
includeContextVar6361.set("awardItemVos",O2oUtil.getSmarty4jProperty(mi,"awardItemVos"));
sb.append(new MissionItemAward().get(includeContextVar6361));
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