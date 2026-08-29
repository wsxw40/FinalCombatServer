package com.pearl.fcw.utils.smarty4j2;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.pearl.fcw.utils.O2oUtil;
import com.pearl.fcw.utils.Smarty4jUtil2.Ctx;

public class EditTeamMap implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("configs = \"");
sb.append(context.get("configs"));
sb.append("\" ");
sb.append("\r\n");
sb.append("tsl = ");
sb.append(context.get("tsl"));
sb.append(" ");
sb.append("\r\n");
sb.append("name = \"");
sb.append(context.get("name"));
sb.append("\" ");
sb.append("\r\n");
return sb.toString();
}

}