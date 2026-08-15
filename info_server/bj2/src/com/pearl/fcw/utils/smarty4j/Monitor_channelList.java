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

public class Monitor_channelList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("<html> ");
sb.append("\r\n");
sb.append("<head></head> ");
sb.append("\r\n");
sb.append("<body> ");
sb.append("\r\n");
sb.append("Proxy List:  ");
sb.append("\r\n");
sb.append("<br/> ");
sb.append("\r\n");
List channelList713 = new ArrayList<>();
if (null!=context.get("channelList")){
if (context.get("channelList") instanceof List) channelList713=(List<?>)context.get("channelList");
else if (context.get("channelList").getClass().isArray()) channelList713=Stream.of((Object[])context.get("channelList")).collect(Collectors.toList());
}
channelList713.forEach(channel->{
try{
sb.append("	&nbsp; &nbsp; &nbsp; &nbsp;");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"displayName"));
sb.append("   <a href=\"?channelId=");
sb.append(O2oUtil.getSmarty4jPropertyNil(channel,"channel.id"));
sb.append("\"> View </a> ");
sb.append("\r\n");
sb.append("	<br/> ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("<br/> ");
sb.append("\r\n");
sb.append("------------------------------Info Server asyn taske queue size-------------------------------------------------------<br/> ");
sb.append("\r\n");
sb.append("");
sb.append(context.get("asynQueueSize"));
sb.append("<br/>		 ");
sb.append("\r\n");
sb.append("-------------------------------Info Server Switch----------------------------------------------------<br/>	 ");
sb.append("\r\n");
sb.append("<table> ");
sb.append("\r\n");
List switchs421 = new ArrayList<>();
if (null!=context.get("switchs")){
if (context.get("switchs") instanceof List) switchs421=(List<?>)context.get("switchs");
else if (context.get("switchs").getClass().isArray()) switchs421=Stream.of((Object[])context.get("switchs")).collect(Collectors.toList());
}
switchs421.forEach(p->{
try{
sb.append("		<tr> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(context.get("switch"),"name"));
sb.append("</td>    ");
sb.append("\r\n");
sb.append("			<td>&nbsp;&nbsp;&nbsp;&nbsp;   ");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"isOn"));
sb.append("</td> 			 ");
sb.append("\r\n");
sb.append("			<br/> ");
sb.append("\r\n");
sb.append("		</tr> ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("</table> ");
sb.append("\r\n");
sb.append("-------------------------------Info Server Interface---------------------------------------------------- ");
sb.append("\r\n");
sb.append("<table> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		<td>name</td>  ");
sb.append("\r\n");
sb.append("		<td> &nbsp; &nbsp;&nbsp;&nbsp;</td>  ");
sb.append("\r\n");
sb.append("		<td>average</td>  ");
sb.append("\r\n");
sb.append("		<td>maxCost</td>  ");
sb.append("\r\n");
sb.append("		<td>minCost</td>  ");
sb.append("\r\n");
sb.append("		<td>invokeCounts</td>  ");
sb.append("\r\n");
sb.append("		<td>memcacheHit</td> ");
sb.append("\r\n");
sb.append("		&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("		<td>memcacheMiss</td> ");
sb.append("\r\n");
sb.append("		&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("		<td>cacheHitRate</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
List datas310 = new ArrayList<>();
if (null!=context.get("datas")){
if (context.get("datas") instanceof List) datas310=(List<?>)context.get("datas");
else if (context.get("datas").getClass().isArray()) datas310=Stream.of((Object[])context.get("datas")).collect(Collectors.toList());
}
datas310.forEach(data->{
try{
sb.append("		<tr> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"servletName"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			<td> &nbsp; &nbsp;&nbsp;&nbsp;</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"averageCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"maxCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"minCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"invokeCounts"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"memcacheHit"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"memcacheMiss"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jPropertyNil(data,"cacheHitRate"));
sb.append("% </td> ");
sb.append("\r\n");
sb.append("		<tr/> ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("</table> ");
sb.append("\r\n");
sb.append("</body> ");
sb.append("\r\n");
sb.append("</html> ");
sb.append("\r\n");
return sb.toString();
}

}