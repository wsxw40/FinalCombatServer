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

public class GetVipInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("vipLevel=");
sb.append(context.get("vipLevel"));
sb.append(" ");
sb.append("\r\n");
sb.append("vipExp=");
sb.append(context.get("vipExp"));
sb.append(" ");
sb.append("\r\n");
sb.append("nextLevelExp=");
sb.append(context.get("nextLevelExp"));
sb.append(" ");
sb.append("\r\n");
sb.append("currentyEXPItem=");
sb.append(context.get("currentyEXPItem"));
sb.append(" ");
sb.append("\r\n");
sb.append("leftMins=");
sb.append(context.get("leftMins"));
sb.append(" ");
sb.append("\r\n");
sb.append("sid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("vipItem"),"id"));
sb.append(" ");
sb.append("\r\n");
sb.append("display=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("vipItem"),"displayName"));
sb.append("\" ");
sb.append("\r\n");
sb.append("name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("vipItem"),"name"));
sb.append("\" ");
sb.append("\r\n");
sb.append("description =\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("vipItem"),"description"));
sb.append("\" ");
sb.append("\r\n");
sb.append("crprices={ ");
sb.append("\r\n");
List vipItemcrPricesList964 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(context.get("vipItem"),"crPricesList")){
if (O2oUtil.getSmarty4jProperty(context.get("vipItem"),"crPricesList") instanceof List) vipItemcrPricesList964=(List<?>)O2oUtil.getSmarty4jProperty(context.get("vipItem"),"crPricesList");
else if (O2oUtil.getSmarty4jProperty(context.get("vipItem"),"crPricesList").getClass().isArray()) vipItemcrPricesList964=Stream.of((Object[])O2oUtil.getSmarty4jProperty(context.get("vipItem"),"crPricesList")).collect(Collectors.toList());
}
vipItemcrPricesList964.forEach(pay->{
try{
sb.append("	{id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"id"));
sb.append(",unittype=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unitType"));
sb.append(",cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"cost"));
sb.append(",unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(pay,"unit"));
sb.append(",},  ");
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