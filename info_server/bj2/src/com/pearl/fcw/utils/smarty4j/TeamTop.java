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

public class TeamTop implements Ctx {
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
sb.append("myteamid = ");
sb.append(context.get("mytid"));
sb.append(" ");
sb.append("\r\n");
sb.append("index=");
sb.append(context.get("index"));
sb.append(" ");
sb.append("\r\n");
sb.append("data = { ");
sb.append("\r\n");
sb.append("	{\"<C449^0>\",\"<C450^0>\",\"<C451^0>\",\"<C452^0>\",\"<C453^0>\",\"<C454^0>\",\"<C455^0>\",\"<C456^0>\",\"<C457^0>\",\"<C458^0>\",\"<C459^0>\"}, ");
sb.append("\r\n");
List list769 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list769=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list769=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list769.forEach(entry->{
try{
sb.append("		{");
sb.append(entry);
sb.append("}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}