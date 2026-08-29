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
List friendPartners260 = new ArrayList<>();
if (null!=context.get("friendPartners")){
if (context.get("friendPartners") instanceof List) friendPartners260=(List<?>)context.get("friendPartners");
else if (context.get("friendPartners") instanceof int[]) friendPartners260=Stream.of((int[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners") instanceof long[]) friendPartners260=Stream.of((long[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners") instanceof float[]) friendPartners260=Stream.of((float[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners") instanceof double[]) friendPartners260=Stream.of((double[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners") instanceof byte[]) friendPartners260=Stream.of((byte[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners") instanceof String[]) friendPartners260=Stream.of((String[])context.get("friendPartners")).collect(Collectors.toList());
else if (context.get("friendPartners").getClass().isArray()) friendPartners260=Stream.of(context.get("friendPartners")).collect(Collectors.toList());
}
friendPartners260.forEach(p->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(p,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(p,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"team"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(p,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(p,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jProperty(p,"icon"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jProperty(p,"internetCafe"));
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