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

public class GroupList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("myGroup = { ");
sb.append("\r\n");
List myGroup375 = new ArrayList<>();
if (null!=context.get("myGroup")){
if (context.get("myGroup") instanceof List) myGroup375=(List<?>)context.get("myGroup");
else if (context.get("myGroup").getClass().isArray()) myGroup375=Stream.of((Object[])context.get("myGroup")).collect(Collectors.toList());
}
myGroup375.forEach(friend->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		\"\", ");
sb.append("\r\n");
sb.append("		\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"groupName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"card"));
sb.append(", ");
sb.append("\r\n");
sb.append("		");
sb.append(O2oUtil.getSmarty4jPropertyNil(friend,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e1){
logger.error(e1.toString());
}
});
sb.append("} ");
sb.append("\r\n");
sb.append("addGroups = { ");
sb.append("\r\n");
List addGroups415 = new ArrayList<>();
if (null!=context.get("addGroups")){
if (context.get("addGroups") instanceof List) addGroups415=(List<?>)context.get("addGroups");
else if (context.get("addGroups").getClass().isArray()) addGroups415=Stream.of((Object[])context.get("addGroups")).collect(Collectors.toList());
}
addGroups415.forEach(group->{
try{
sb.append("	{ ");
sb.append("\r\n");
List group956 = new ArrayList<>();
if (null!=group){
if (group instanceof List) group956=(List<?>)group;
else if (group.getClass().isArray()) group956=Stream.of((Object[])group).collect(Collectors.toList());
}
group956.forEach(p->{
try{
sb.append("		{ ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"friendId"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"rank"));
sb.append(", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"name"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			\"\", ");
sb.append("\r\n");
sb.append("			\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"groupName"));
sb.append("\", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"online"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"isVip"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"card"));
sb.append(", ");
sb.append("\r\n");
sb.append("			");
sb.append(O2oUtil.getSmarty4jPropertyNil(p,"internetCafe"));
sb.append(", ");
sb.append("\r\n");
sb.append("		}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("	}, ");
sb.append("\r\n");
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("} ");
sb.append("\r\n");
return sb.toString();
}

}