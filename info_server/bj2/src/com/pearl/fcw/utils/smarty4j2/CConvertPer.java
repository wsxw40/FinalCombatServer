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

public class CConvertPer implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("rate=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"rate"));
sb.append(" ");
sb.append("\r\n");
sb.append("from_rate=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"rateFrom"));
sb.append(" ");
sb.append("\r\n");
sb.append("to_rate=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"rateTo"));
sb.append(" ");
sb.append("\r\n");
sb.append("from=  ");
Map<String, Object> includeContextVar7688=new HashMap<>();
includeContextVar7688.put("playerItem",O2oUtil.getSmarty4jProperty(context.get("proto"),"from"));
sb.append(new PlayerItem().get(includeContextVar7688));
sb.append("} ");
sb.append("\r\n");
sb.append("to= { ");
sb.append("\r\n");
Map<String, Object> includeContextVar986=new HashMap<>();
includeContextVar986.put("playerItem",O2oUtil.getSmarty4jProperty(context.get("proto"),"to"));
sb.append(new PlayerItem().get(includeContextVar986));
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}