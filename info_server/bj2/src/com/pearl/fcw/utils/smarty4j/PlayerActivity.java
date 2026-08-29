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

public class PlayerActivity implements Ctx {
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
sb.append("num=");
sb.append(context.get("num"));
sb.append(" ");
sb.append("\r\n");
sb.append("awoke = ");
sb.append(context.get("awoke"));
sb.append(" ");
sb.append("\r\n");
sb.append("mission = { ");
sb.append("\r\n");
List list461 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list461=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list461=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list461.forEach(pa->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"sysActivity.path"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"sysActivity.title"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"target"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"status"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"award"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"sysActivity.action"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(pa,"sysActivity.needAward"));
sb.append(", ");
sb.append("\r\n");
sb.append("			0, ");
sb.append("\r\n");
sb.append("			0,0,0,0, ");
sb.append("\r\n");
sb.append("			items =  ");
sb.append("			{ ");
sb.append("\r\n");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(pa,"sysActivity.sysItem"),"!=",null)){
Context includeContextVar2139=new Context();
includeContextVar2139.set("sysItem",O2oUtil.getSmarty4jProperty(pa,"sysActivity.sysItem"));
includeContextVar2139.set("unit",O2oUtil.getSmarty4jProperty(pa,"sysActivity.unit"));
includeContextVar2139.set("unitType",O2oUtil.getSmarty4jProperty(pa,"sysActivity.unitType"));
includeContextVar2139.set("isVip",0);
sb.append(new MissionItemAwardEntity().get(includeContextVar2139));
}
sb.append("			}, ");
sb.append("\r\n");
sb.append("		}, ");
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