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

public class PlayerItem implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("weapon_info = ");
if( O2oUtil.compare(context.get("playerItem"),"!=",null)){
sb.append("	{ ");
sb.append("\r\n");
sb.append("		type=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.wId"));
sb.append(",  ");
sb.append("\r\n");
List wpStable631 = new ArrayList<>();
if (null!=context.get("wpStable")){
if (context.get("wpStable") instanceof List) wpStable631=(List<?>)context.get("wpStable");
else if (context.get("wpStable").getClass().isArray()) wpStable631=Stream.of((Object[])context.get("wpStable")).collect(Collectors.toList());
}
wpStable631.forEach(itemS->{
try{
if((O2oUtil.compare(itemS,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.name"));
sb.append("/");
sb.append(itemS);
sb.append("\",  ");
sb.append("\r\n");
}
}catch(Exception e1){
logger.error(e1.toString());
}
});
List wpChange24 = new ArrayList<>();
if (null!=context.get("wpChange")){
if (context.get("wpChange") instanceof List) wpChange24=(List<?>)context.get("wpChange");
else if (context.get("wpChange").getClass().isArray()) wpChange24=Stream.of((Object[])context.get("wpChange")).collect(Collectors.toList());
}
wpChange24.forEach(itemC->{
try{
if((O2oUtil.compare(itemC,"!=",""))){
sb.append("				\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerItem"),"sysItem.name"));
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
sb.append("		{} ");
sb.append("\r\n");
}
return sb.toString();
}

}