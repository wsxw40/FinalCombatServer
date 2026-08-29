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

public class ActivityList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = 1, ");
sb.append("\r\n");
sb.append("pages = 1, ");
sb.append("\r\n");
sb.append("num=");
sb.append(context.get("num"));
sb.append(" , ");
sb.append("\r\n");
sb.append("mission =  ");
sb.append("{ ");
sb.append("\r\n");
List list825 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list825=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list825=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list825.forEach(activity->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"sysActivity.path"));
sb.append("\", \"\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"target"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"award"));
sb.append(",2,");
sb.append(O2oUtil.getSmarty4jPropertyNil(activity,"sysActivity.needAward"));
sb.append(", ");
sb.append("\r\n");
sb.append("		items =  ");
sb.append("		{ ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList"),"!=",null))){
List activitysysActivityitemList794 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")){
if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof List) activitysysActivityitemList794=(List<?>)O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList");
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList").getClass().isArray()) activitysysActivityitemList794=Stream.of((Object[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
}
activitysysActivityitemList794.forEach(sysItem->{
try{
sb.append("					{0 , \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(sysItem,"type"));
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
}
sb.append("		}, ");
sb.append("\r\n");
sb.append("  	}, ");
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