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

public class SysCharacterList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("items ={ ");
sb.append("\r\n");
List list302 = new ArrayList<>();
if (null!=context.get("list")){
if (context.get("list") instanceof List) list302=(List<?>)context.get("list");
else if (context.get("list").getClass().isArray()) list302=Stream.of((Object[])context.get("list")).collect(Collectors.toList());
}
list302.forEach(ch->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		cid=");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		name=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(ch,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		is_open=  ");
if( O2oUtil.compare(O2oUtil.getSmarty4jProperty(ch,"isDefault"),"==","Y")){
sb.append("				1 , ");
sb.append("\r\n");
} else {
sb.append("				0 , ");
sb.append("\r\n");
}
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