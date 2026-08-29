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

public class ExpiredItemNotify_mail implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<C444^1^ ");
sb.append("\r\n");
List list63 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list63=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list63=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list63.forEach(item->{
try{
sb.append("	<C443^2^");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"sysItem.displayName"));
sb.append("^");
sb.append(O2oUtil.getSmarty4jPropertyNil(item,"expireDateStr"));
sb.append("> ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("> ");
sb.append("\r\n");
return sb.toString();
}

}