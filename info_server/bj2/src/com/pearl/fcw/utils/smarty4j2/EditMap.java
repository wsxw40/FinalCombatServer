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

public class EditMap implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("configs = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("proto"),"configs"));
sb.append("\" ");
sb.append("\r\n");
sb.append("tsl = ");
sb.append(O2oUtil.getSmarty4jProperty(context.get("proto"),"tsl"));
sb.append(" ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(O2oUtil.getSmarty4jProperty(context.get("proto"),"name"));
sb.append("\" ");
sb.append("\r\n");
return sb.toString();
}

}