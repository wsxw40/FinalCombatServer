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

public class PushBattleFiledInvitation implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("{ ");
sb.append("\r\n");
sb.append("	cmd = \"battlefield_invitation\", ");
sb.append("\r\n");
sb.append("	inviter_pid=");
sb.append(context.get("inviter_pid"));
sb.append(", ");
sb.append("\r\n");
sb.append("	inviter_name=\"");
sb.append(context.get("inviter_name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	receiver_pid=");
sb.append(context.get("receiver_pid"));
sb.append(", ");
sb.append("\r\n");
sb.append("	serverid=");
sb.append(context.get("serverid"));
sb.append(", ");
sb.append("\r\n");
sb.append("	channelid=");
sb.append(context.get("channelid"));
sb.append(", ");
sb.append("\r\n");
sb.append("	roomid=");
sb.append(context.get("roomid"));
sb.append(", ");
sb.append("\r\n");
sb.append("	roompwd=\"");
sb.append(context.get("roompwd"));
sb.append("\", ");
sb.append("\r\n");
sb.append("	restype=");
sb.append(context.get("restype"));
sb.append(", ");
sb.append("\r\n");
sb.append("	toPlayer_Res_ratio_status=\"");
sb.append(context.get("toPlayer_Res_ratio_status"));
sb.append("\" ");
sb.append("\r\n");
sb.append("}	 ");
sb.append("\r\n");
return sb.toString();
}

}