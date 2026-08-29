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
List channelList986 = new ArrayList<>();
if (null!=context.get("channelList")){
if (context.get("channelList") instanceof List) channelList986=(List<?>)context.get("channelList");
else if (context.get("channelList") instanceof int[]) channelList986=Stream.of((int[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof long[]) channelList986=Stream.of((long[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof float[]) channelList986=Stream.of((float[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof double[]) channelList986=Stream.of((double[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof byte[]) channelList986=Stream.of((byte[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList") instanceof String[]) channelList986=Stream.of((String[])context.get("channelList")).collect(Collectors.toList());
else if (context.get("channelList").getClass().isArray()) channelList986=Stream.of(context.get("channelList")).collect(Collectors.toList());
}
channelList986.forEach(channel->{
try{
sb.append("	&nbsp; &nbsp; &nbsp; &nbsp;");
sb.append(O2oUtil.getSmarty4jProperty(channel,"displayName"));
sb.append("   <a href=\"?channelId=");
sb.append(O2oUtil.getSmarty4jProperty(channel,"channel.id"));
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
List switchs244 = new ArrayList<>();
if (null!=context.get("switchs")){
if (context.get("switchs") instanceof List) switchs244=(List<?>)context.get("switchs");
else if (context.get("switchs") instanceof int[]) switchs244=Stream.of((int[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs") instanceof long[]) switchs244=Stream.of((long[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs") instanceof float[]) switchs244=Stream.of((float[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs") instanceof double[]) switchs244=Stream.of((double[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs") instanceof byte[]) switchs244=Stream.of((byte[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs") instanceof String[]) switchs244=Stream.of((String[])context.get("switchs")).collect(Collectors.toList());
else if (context.get("switchs").getClass().isArray()) switchs244=Stream.of(context.get("switchs")).collect(Collectors.toList());
}
switchs244.forEach(p->{
try{
sb.append("		<tr> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(context.get("switch"),"name"));
sb.append("</td>    ");
sb.append("\r\n");
sb.append("			<td>&nbsp;&nbsp;&nbsp;&nbsp;   ");
sb.append(O2oUtil.getSmarty4jProperty(p,"isOn"));
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
List datas886 = new ArrayList<>();
if (null!=context.get("datas")){
if (context.get("datas") instanceof List) datas886=(List<?>)context.get("datas");
else if (context.get("datas") instanceof int[]) datas886=Stream.of((int[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas") instanceof long[]) datas886=Stream.of((long[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas") instanceof float[]) datas886=Stream.of((float[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas") instanceof double[]) datas886=Stream.of((double[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas") instanceof byte[]) datas886=Stream.of((byte[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas") instanceof String[]) datas886=Stream.of((String[])context.get("datas")).collect(Collectors.toList());
else if (context.get("datas").getClass().isArray()) datas886=Stream.of(context.get("datas")).collect(Collectors.toList());
}
datas886.forEach(data->{
try{
sb.append("		<tr> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"servletName"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			<td> &nbsp; &nbsp;&nbsp;&nbsp;</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"averageCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"maxCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"minCost"));
sb.append("</td>  ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"invokeCounts"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"memcacheHit"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"memcacheMiss"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("			&nbsp; &nbsp;&nbsp;&nbsp; ");
sb.append("\r\n");
sb.append("			<td>");
sb.append(O2oUtil.getSmarty4jProperty(data,"cacheHitRate"));
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