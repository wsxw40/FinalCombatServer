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

public class SysItemPrice implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysItemId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"sysItemId"));
sb.append(", ");
sb.append("\r\n");
sb.append("payType=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"payType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("unitType=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"unitType.number"));
sb.append(", ");
sb.append("\r\n");
sb.append("cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("unit=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"unit"));
sb.append(", ");
sb.append("\r\n");
sb.append("isTarget=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"isTarget"));
sb.append(", ");
sb.append("\r\n");
sb.append("payGroup=");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"payGroup"));
sb.append(", ");
sb.append("\r\n");
sb.append("itemDisplayName=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"itemDisplayName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("itemName=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("sysItemPrice"),"itemName"));
sb.append("\", ");
sb.append("\r\n");
return sb.toString();
}

}