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

public class RankList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rank = { ");
sb.append("\r\n");
List rankList843 = new ArrayList<>();
if (null!=context.get("rankList")){
if (context.get("rankList") instanceof List) rankList843=(List<?>)context.get("rankList");
else if (context.get("rankList").getClass().isArray()) rankList843=Stream.of((Object[])context.get("rankList")).collect(Collectors.toList());
}
rankList843.forEach(rank->{
try{
sb.append("		[ ");
sb.append(O2oUtil.getSmarty4jPropertyNil(rank,"id"));
sb.append(" ]={\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(rank,"title"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(rank,"exp"));
sb.append("}, ");
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