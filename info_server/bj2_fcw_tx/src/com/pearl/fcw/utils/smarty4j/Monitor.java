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

public class Monitor implements Ctx {
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
sb.append("-------------------------------Current Proxy :");
sb.append(context.get("proxyName"));
sb.append("----------------------------------------------------	 ");
sb.append("\r\n");
sb.append("<br/> ");
sb.append("\r\n");
sb.append("<br/>	 ");
sb.append("\r\n");
sb.append("RequestQueue : ");
sb.append(context.get("reqQueueSize"));
sb.append(" <br/> ");
sb.append("\r\n");
sb.append("ResponseQueue : ");
sb.append(context.get("resQueueSize"));
sb.append(" <br/> ");
sb.append("\r\n");
sb.append("<table> ");
sb.append("\r\n");
sb.append("	<th> ");
sb.append("\r\n");
sb.append("		<td>name</td>  ");
sb.append("\r\n");
sb.append("		<td> &nbsp; &nbsp;&nbsp;&nbsp;</td>  ");
sb.append("\r\n");
sb.append("		<td>active</td>  ");
sb.append("\r\n");
sb.append("		<td>size</td>  ");
sb.append("\r\n");
sb.append("		<td>queue</td>  ");
sb.append("\r\n");
sb.append("	</th> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		 <td>clientPool<td/> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("clientActive"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("clientMax"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("clientTaskQueue"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		 <td>playerPool<td/> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("playerActive"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("playerMax"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("playerTaskQueue"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		 <td>loginPool<td/> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("loginActive"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("loginMax"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("loginTaskQueue"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		 <td>serverPool<td/> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("serverActive"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("serverMax"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("serverTaskQueue"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
sb.append("	<tr> ");
sb.append("\r\n");
sb.append("		 <td>gamePool<td/> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("gameActive"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("gameMax"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("		 <td>");
sb.append(context.get("gameTaskQueue"));
sb.append("</td> ");
sb.append("\r\n");
sb.append("	</tr> ");
sb.append("\r\n");
sb.append("</table> ");
sb.append("\r\n");
sb.append("<br/> ");
sb.append("\r\n");
sb.append("<br/> ======================================Proxy  info ============================================ ");
sb.append("<br/> 		 ");
sb.append("\r\n");
sb.append("<table> ");
sb.append("\r\n");
List proxyInfo444 = new ArrayList<>();
if (null!=context.get("proxyInfo")){
if (context.get("proxyInfo") instanceof List) proxyInfo444=(List<?>)context.get("proxyInfo");
else if (context.get("proxyInfo") instanceof int[]) proxyInfo444=Stream.of((int[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo") instanceof long[]) proxyInfo444=Stream.of((long[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo") instanceof float[]) proxyInfo444=Stream.of((float[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo") instanceof double[]) proxyInfo444=Stream.of((double[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo") instanceof byte[]) proxyInfo444=Stream.of((byte[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo") instanceof String[]) proxyInfo444=Stream.of((String[])context.get("proxyInfo")).collect(Collectors.toList());
else if (context.get("proxyInfo").getClass().isArray()) proxyInfo444=Stream.of(context.get("proxyInfo")).collect(Collectors.toList());
}
proxyInfo444.forEach(item->{
try{
sb.append("	<tr> ");
sb.append("\r\n");
List item688 = new ArrayList<>();
if (null!=item){
if (item instanceof List) item688=(List<?>)item;
else if (item instanceof int[]) item688=Stream.of((int[])item).collect(Collectors.toList());
else if (item instanceof long[]) item688=Stream.of((long[])item).collect(Collectors.toList());
else if (item instanceof float[]) item688=Stream.of((float[])item).collect(Collectors.toList());
else if (item instanceof double[]) item688=Stream.of((double[])item).collect(Collectors.toList());
else if (item instanceof byte[]) item688=Stream.of((byte[])item).collect(Collectors.toList());
else if (item instanceof String[]) item688=Stream.of((String[])item).collect(Collectors.toList());
else if (item.getClass().isArray()) item688=Stream.of(item).collect(Collectors.toList());
}
item688.forEach(im->{
try{
sb.append("		<td width=100>");
sb.append(im);
sb.append("</td> ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("	<tr/> ");
sb.append("\r\n");
}catch(Exception e2){
logger.error(e2.toString());
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