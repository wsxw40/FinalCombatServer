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

public class PlayerQuest implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public <T> String get(Map<String,T> context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("id = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerQuest"),"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("sysQuestId = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerQuest"),"sysQuestId"));
sb.append(", ");
sb.append("\r\n");
sb.append("number = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerQuest"),"number"));
sb.append(", ");
sb.append("\r\n");
sb.append("completeCount = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerQuest"),"completeCount"));
sb.append(", ");
sb.append("\r\n");
sb.append("status = ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("playerQuest"),"status.number"));
sb.append(", ");
sb.append("\r\n");
return sb.toString();
}

}