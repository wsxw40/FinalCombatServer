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

public class CharDataList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("myData =  ");
sb.append("{ ");
sb.append("\r\n");
List list527 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list527=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list527=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list527=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list527=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list527=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list527=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list527=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list527=Stream.of(context.get("list")).collect(Collectors.toList());
}
list527.forEach(data->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		characterId=");
sb.append(O2oUtil.getSmarty4jProperty(data,"characterId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		kill=");
sb.append(O2oUtil.getSmarty4jProperty(data,"kill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		dead=");
sb.append(O2oUtil.getSmarty4jProperty(data,"dead"));
sb.append(", ");
sb.append("\r\n");
sb.append("		knifeKill=");
sb.append(O2oUtil.getSmarty4jProperty(data,"knifeKill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		controlNum=");
sb.append(O2oUtil.getSmarty4jProperty(data,"controlNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		revengeNum=");
sb.append(O2oUtil.getSmarty4jProperty(data,"revengeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		assistNum=");
sb.append(O2oUtil.getSmarty4jProperty(data,"assistNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		usedCount=");
sb.append(O2oUtil.getSmarty4jProperty(data,"usedCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxHeadshot=");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxHeadshot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxKill=");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxKill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxHealth=");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxHealth"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxDamage=");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxDamage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxAliveTime=");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxAliveTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mvpNum=");
sb.append(O2oUtil.getSmarty4jProperty(data,"mvpNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jProperty(data,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		playerId=");
sb.append(O2oUtil.getSmarty4jProperty(data,"playerId"));
sb.append(" ");
sb.append("\r\n");
sb.append("	}, ");
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