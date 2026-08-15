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

public class CSloting implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"result"));
sb.append(" ");
sb.append("\r\n");
sb.append("item= { ");
sb.append("\r\n");
Map<String, Object> includeContextVar6092=new HashMap<>();
includeContextVar6092.put("playerItem",O2oUtil.getSmarty4jProperty(context.get("proto"),"item"));
sb.append(new PlayerItem().get(includeContextVar6092));
sb.append("} ");
sb.append("\r\n");
sb.append("sloterItem={ ");
sb.append("\r\n");
Map<String, Object> includeContextVar8443=new HashMap<>();
includeContextVar8443.put("playerItem",O2oUtil.getSmarty4jProperty(context.get("proto"),"sloterItem"));
sb.append(new PlayerItem().get(includeContextVar8443));
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}