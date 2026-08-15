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

public class PartnerList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("partnerslist =  ");
sb.append("{ ");
sb.append("\r\n");
List friendPartners957 = new ArrayList<>();
if (null!=context.get("friendPartners")){
if (context.get("friendPartners") instanceof List) friendPartners957=(List<?>)context.get("friendPartners");
else if (context.get("friendPartners").getClass().isArray()) friendPartners957=Stream.of((Object[])context.get("friendPartners")).collect(Collectors.toList());
}
friendPartners957.forEach(p->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"team"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
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