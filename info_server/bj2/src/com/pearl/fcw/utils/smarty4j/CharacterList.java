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

public class CharacterList implements Ctx {
private Logger logger = LoggerFactory.getLogger(getClass());

@SuppressWarnings({ "unchecked", "rawtypes" })
public String get(Context context) throws Exception {
StringBuilder sb = new StringBuilder();
sb.append("characters = { ");
sb.append("\r\n");
List characterList521 = new ArrayList<>();
if (null!=context.get("characterList")){
if (context.get("characterList") instanceof List) characterList521=(List<?>)context.get("characterList");
else if (context.get("characterList").getClass().isArray()) characterList521=Stream.of((Object[])context.get("characterList")).collect(Collectors.toList());
}
characterList521.forEach(character->{
try{
sb.append("	{ ");
sb.append("\r\n");
sb.append("		id=");
sb.append(O2oUtil.getSmarty4jPropertyNil(character,"id"));
sb.append(", ");
sb.append("\r\n");
sb.append("		gender=\"");
sb.append(O2oUtil.getSmarty4jPropertyNil(character,"gender"));
sb.append("\", ");
sb.append("\r\n");
sb.append("		cost=");
sb.append(O2oUtil.getSmarty4jPropertyNil(character,"cost"));
sb.append(", ");
sb.append("\r\n");
sb.append("		team0={ ");
sb.append("\r\n");
List charactercostumeT267 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(character,"costumeT")){
if (O2oUtil.getSmarty4jProperty(character,"costumeT") instanceof List) charactercostumeT267=(List<?>)O2oUtil.getSmarty4jProperty(character,"costumeT");
else if (O2oUtil.getSmarty4jProperty(character,"costumeT").getClass().isArray()) charactercostumeT267=Stream.of((Object[])O2oUtil.getSmarty4jProperty(character,"costumeT")).collect(Collectors.toList());
}
charactercostumeT267.forEach(resourceT->{
try{
if((O2oUtil.compare(resourceT,"!=",""))){
sb.append("					\"");
sb.append(resourceT);
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("					\"\",  ");
sb.append("\r\n");
}
}catch(Exception e2){
logger.error(e2.toString());
}
});
sb.append("		}, ");
sb.append("\r\n");
sb.append("		team1={ ");
sb.append("\r\n");
List charactercostumeP545 = new ArrayList<>();
if (null!=O2oUtil.getSmarty4jProperty(character,"costumeP")){
if (O2oUtil.getSmarty4jProperty(character,"costumeP") instanceof List) charactercostumeP545=(List<?>)O2oUtil.getSmarty4jProperty(character,"costumeP");
else if (O2oUtil.getSmarty4jProperty(character,"costumeP").getClass().isArray()) charactercostumeP545=Stream.of((Object[])O2oUtil.getSmarty4jProperty(character,"costumeP")).collect(Collectors.toList());
}
charactercostumeP545.forEach(resourceP->{
try{
if((O2oUtil.compare(resourceP,"!=",""))){
sb.append("					");
sb.append(resourceP);
sb.append("\", ");
sb.append("\r\n");
} else {
sb.append("					\"\", ");
sb.append("\r\n");
}
}catch(Exception e3){
logger.error(e3.toString());
}
});
sb.append("		},  ");
sb.append("\r\n");
sb.append("		weapon_info ={type=3,\"mk18/bh1\", \"mk18/rv1\", \"mk18/gs1\", \"mk18/si1\", \"mk18/tg1\", \"mk18/mz1\", \"mk18/rs1\", \"mk18/bl1\", \"mk18/fh1\", }, ");
sb.append("\r\n");
sb.append("	}, ");
sb.append("\r\n");
sb.append("	 ");
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