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
List list192 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list192=(List<?>)context.get("list");
else if (context.get("list") instanceof int[]) list192=Stream.of((int[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof long[]) list192=Stream.of((long[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof float[]) list192=Stream.of((float[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof double[]) list192=Stream.of((double[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof byte[]) list192=Stream.of((byte[])context.get("list")).collect(Collectors.toList());
else if (context.get("list") instanceof String[]) list192=Stream.of((String[])context.get("list")).collect(Collectors.toList());
else if (context.get("list").getClass().isArray()) list192=Stream.of(context.get("list")).collect(Collectors.toList());
}
list192.forEach(activity->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(activity,"id"));
sb.append(", \"");
sb.append(O2oUtil.getSmarty4jProperty(activity,"sysActivity.path"));
sb.append("\", \"\", \"");
sb.append(O2oUtil.getSmarty4jProperty(activity,"name"));
sb.append("\", ");
sb.append(O2oUtil.getSmarty4jProperty(activity,"target"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(activity,"number"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(activity,"status"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jProperty(activity,"award"));
sb.append(",2,");
sb.append(O2oUtil.getSmarty4jProperty(activity,"sysActivity.needAward"));
sb.append(", ");
sb.append("\r\n");
sb.append("		items =  ");
sb.append("		{ ");
sb.append("\r\n");
if( (O2oUtil.compare(O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList"),"!=",null))){
List activitysysActivityitemList965 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")){
if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof List) activitysysActivityitemList965=(List<?>)O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList");
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof int[]) activitysysActivityitemList965=Stream.of((int[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof long[]) activitysysActivityitemList965=Stream.of((long[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof float[]) activitysysActivityitemList965=Stream.of((float[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof double[]) activitysysActivityitemList965=Stream.of((double[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof byte[]) activitysysActivityitemList965=Stream.of((byte[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList") instanceof String[]) activitysysActivityitemList965=Stream.of((String[])O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
else if (O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList").getClass().isArray()) activitysysActivityitemList965=Stream.of(O2oUtil.getSmarty4jProperty(activity,"sysActivity.itemList")).collect(Collectors.toList());
}
activitysysActivityitemList965.forEach(sysItem->{
try{
sb.append("					{0 , \"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"name"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"displayName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"description"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jProperty(sysItem,"type"));
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