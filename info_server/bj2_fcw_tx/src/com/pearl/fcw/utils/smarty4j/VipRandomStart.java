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

public class VipRandomStart implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("num=");
sb.append(context.get("openChanceNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("need=");
sb.append(context.get("needVipNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("vip_num=");
sb.append(context.get("vipNum"));
sb.append(" ");
sb.append("\r\n");
sb.append("stop_num=");
sb.append(context.get("randomNum"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}