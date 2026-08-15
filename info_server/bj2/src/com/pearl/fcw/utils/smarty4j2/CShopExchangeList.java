package com.pearl.fcw.utils.smarty4j2;

import java.util.List;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;

public class CShopExchangeList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("page = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"page"));
sb.append(" ");
sb.append("\r\n");
sb.append("pages = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"pages"));
sb.append(" ");
sb.append("\r\n");
sb.append("chipNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"chipNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("reviceCoinNum=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"reviceCoinNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("sysItem ={ ");
sb.append("\r\n");
List protosysItemList757 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemList") instanceof List) protosysItemList757=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemList").getClass().isArray()) protosysItemList757=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemList")).collect(Collectors.toList());
}
protosysItemList757.forEach(sysItem->{
try{
sb.append("		{ ");
sb.append("\r\n");
Map<String, Object> includeContextVar4623=new HashMap<>();
includeContextVar4623.put("sysItem",sysItem);
sb.append(new SysItem().get(includeContextVar4623));
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("sysItemAndQuantity={ ");
sb.append("\r\n");
List protosysItemAndQuantityList113 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemAndQuantityList")){
if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemAndQuantityList") instanceof List) protosysItemAndQuantityList113=(List<?>)O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemAndQuantityList");
else if (O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemAndQuantityList").getClass().isArray()) protosysItemAndQuantityList113=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("proto"),"sysItemAndQuantityList")).collect(Collectors.toList());
}
protosysItemAndQuantityList113.forEach(item->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("		sysItemId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"k"));
sb.append(" ,  ");
sb.append("\r\n");
sb.append("		quantity = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"v"));
sb.append(" , ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}