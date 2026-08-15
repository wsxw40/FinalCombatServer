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

public class Team implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("team = { ");
sb.append("\r\n");
if( O2oUtil.compare(context.get("team"),"!=",null)){
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"id"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"name"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"logo"));
sb.append("\", \"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"description"));
sb.append("\",  ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"kill"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"headShot"));
sb.append(",  ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"gameWin"));
sb.append(", ");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"gameTotal"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"gameRatio"));
sb.append("\",");
sb.append(context.get("job"));
sb.append(",\"");
sb.append(context.get("createTime"));
sb.append("\",");
sb.append(context.get("currentMembers"));
sb.append(",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"size"));
sb.append(",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"leaderName"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"province"));
sb.append("\",\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"city"));
sb.append("\",");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"level"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"exp"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"totalExp"));
sb.append(",			 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"fight"));
sb.append(",				 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"recoreRankingCurr"));
sb.append(", 	 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"recoreOffset"));
sb.append(", 		 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"fightRankingCurr"));
sb.append(",	 ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("team"),"fightOffset"));
sb.append(",		 ");
sb.append("\r\n");
}
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}