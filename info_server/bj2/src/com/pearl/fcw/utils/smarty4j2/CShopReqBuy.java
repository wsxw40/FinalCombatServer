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

public class CShopReqBuy implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("result = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"result"));
sb.append(" ");
sb.append("\r\n");
sb.append("buildTime = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"buildTime"));
sb.append(" ");
sb.append("\r\n");
sb.append("error = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"error"));
sb.append("\" ");
sb.append("\r\n");
sb.append("successMsg = \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("proto"),"successMsg"));
sb.append("\" ");
sb.append("\r\n");
return sb.toString();
}

}