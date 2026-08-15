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

public class Payment implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("unittype = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"unittype.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("unit = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("cost = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("payType = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"payType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysItemId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("payment"),"sysItemId"));
sb.append(", ");
sb.append("\r\n");
return sb.toString();
}

}