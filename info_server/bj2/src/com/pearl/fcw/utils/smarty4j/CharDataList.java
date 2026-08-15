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
List list243 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list243=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list243=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list243.forEach(data->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		characterId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"characterId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		kill=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"kill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		dead=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"dead"));
sb.append(", ");
sb.append("\r\n");
sb.append("		knifeKill=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"knifeKill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		controlNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"controlNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		revengeNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"revengeNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		assistNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"assistNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		usedCount=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"usedCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxHeadshot=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxHeadshot"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxKill=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxKill"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxHealth=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxHealth"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxDamage=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxDamage"));
sb.append(", ");
sb.append("\r\n");
sb.append("		maxAliveTime=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxAliveTime"));
sb.append(", ");
sb.append("\r\n");
sb.append("		mvpNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"mvpNum"));
sb.append(", ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		playerId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"playerId"));
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