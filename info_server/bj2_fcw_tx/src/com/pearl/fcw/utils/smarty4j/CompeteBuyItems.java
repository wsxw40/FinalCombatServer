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

public class CompeteBuyItems implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("type=");
sb.append(context.get("type"));
sb.append(" ");
sb.append("\r\n");
sb.append("time=");
sb.append(context.get("time"));
sb.append(" ");
sb.append("\r\n");
sb.append("itemNums={");
sb.append(context.get("itemNums"));
sb.append("} ");
sb.append("\r\n");
sb.append("leastNums={");
sb.append(context.get("leastNums"));
sb.append("} ");
sb.append("\r\n");
sb.append("validNums={");
sb.append(context.get("validNums"));
sb.append("} ");
sb.append("\r\n");
sb.append("mostNums={");
sb.append(context.get("mostNums"));
sb.append("} ");
sb.append("\r\n");
sb.append("myNums={");
sb.append(context.get("myNums"));
sb.append("} ");
sb.append("\r\n");
sb.append("leftNums={");
sb.append(context.get("leftNums"));
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}