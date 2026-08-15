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

public class SysItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("weapon_info = ");
if( O2oUtil.compare(context.get("sysItem"),"!=",null)){
sb.append("	{ ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"wId"));
sb.append(",  ");
sb.append("\r\n");
List wpStable937 = new ArrayList<>();
if (null!=context.get("wpStable")){
if (context.get("wpStable") instanceof List) wpStable937=(List<?>)context.get("wpStable");
else if (context.get("wpStable") instanceof int[]) wpStable937=Stream.of((int[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof long[]) wpStable937=Stream.of((long[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof float[]) wpStable937=Stream.of((float[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof double[]) wpStable937=Stream.of((double[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof byte[]) wpStable937=Stream.of((byte[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable") instanceof String[]) wpStable937=Stream.of((String[])context.get("wpStable")).collect(Collectors.toList());
else if (context.get("wpStable").getClass().isArray()) wpStable937=Stream.of(context.get("wpStable")).collect(Collectors.toList());
}
wpStable937.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"name"));
sb.append("/");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
List wpChange200 = new ArrayList<>();
if (null!=context.get("wpChange")){
if (context.get("wpChange") instanceof List) wpChange200=(List<?>)context.get("wpChange");
else if (context.get("wpChange") instanceof int[]) wpChange200=Stream.of((int[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof long[]) wpChange200=Stream.of((long[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof float[]) wpChange200=Stream.of((float[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof double[]) wpChange200=Stream.of((double[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof byte[]) wpChange200=Stream.of((byte[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange") instanceof String[]) wpChange200=Stream.of((String[])context.get("wpChange")).collect(Collectors.toList());
else if (context.get("wpChange").getClass().isArray()) wpChange200=Stream.of(context.get("wpChange")).collect(Collectors.toList());
}
wpChange200.forEach(itemC->{
try{
if((O2oUtil.compare(itemC,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("sysItem"),"name"));
sb.append("/");
sb.append(itemC);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	} ");
sb.append("\r\n");
} else {
sb.append("		nil ");
sb.append("\r\n");
}
return sb.toString();
}

}