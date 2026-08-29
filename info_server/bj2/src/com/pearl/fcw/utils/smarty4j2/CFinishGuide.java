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

public class CFinishGuide implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("playerAchievement={ ");
sb.append("\r\n");
Map<String, Object> includeContextVar2252=new HashMap<>();
includeContextVar2252.put("playerAchievement",O2oUtil.getSmarty4jProperty(context.get("proto"),"playerAchievement"));
sb.append(new PlayerAchievement().get(includeContextVar2252));
sb.append("} ");
sb.append("\r\n");
sb.append("nextSysAchievement={ ");
sb.append("\r\n");
Map<String, Object> includeContextVar4478=new HashMap<>();
includeContextVar4478.put("sysAchievement",O2oUtil.getSmarty4jProperty(context.get("proto"),"nextSysAchievement"));
sb.append(new SysAchievement().get(includeContextVar4478));
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}