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

public class LeagueInfo implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("apply_start = \"");
sb.append(context.get("apply_start"));
sb.append("\" ");
sb.append("\r\n");
sb.append("apply_end = \"");
sb.append(context.get("apply_end"));
sb.append("\" ");
sb.append("\r\n");
sb.append("game_start = \"");
sb.append(context.get("game_start"));
sb.append("\" ");
sb.append("\r\n");
sb.append("game_end = \"");
sb.append(context.get("game_end"));
sb.append("\" ");
sb.append("\r\n");
sb.append("remark_1 = \"");
sb.append(context.get("remark_1"));
sb.append("\" ");
sb.append("\r\n");
sb.append("remark_2 = \"");
sb.append(context.get("remark_2"));
sb.append("\" ");
sb.append("\r\n");
sb.append("teamName = \"");
sb.append(context.get("teamName"));
sb.append("\" ");
sb.append("\r\n");
sb.append("haveTeam = ");
sb.append(context.get("haveTeam"));
sb.append(" ");
sb.append("\r\n");
sb.append("job = ");
sb.append(context.get("job"));
sb.append(" ");
sb.append("\r\n");
sb.append("apply = ");
sb.append(context.get("apply"));
sb.append(" ");
sb.append("\r\n");
sb.append("start = ");
sb.append(context.get("start"));
sb.append(" ");
sb.append("\r\n");
sb.append("timeDiff = ");
sb.append(context.get("timeDiff"));
sb.append(" ");
sb.append("\r\n");
sb.append("timeDiffType = ");
sb.append(context.get("timeDiffType"));
sb.append(" ");
sb.append("\r\n");
sb.append("gameType = ");
sb.append(context.get("gameType"));
sb.append(" ");
sb.append("\r\n");
return sb.toString();
}

}