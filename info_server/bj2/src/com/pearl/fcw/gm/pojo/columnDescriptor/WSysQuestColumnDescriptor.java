package com.pearl.fcw.gm.pojo.columnDescriptor;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.pearl.fcw.core.pojo.columnDescriptor.ColumnDescriptor;
import com.pearl.fcw.gm.pojo.WSysItemPrice;
import com.pearl.fcw.gm.pojo.WSysQuest;
import com.pearl.fcw.lobby.pojo.json.JsonQuestTrigger;
import com.pearl.fcw.lobby.pojo.json.JsonQuestTrigger.Trigger;
import com.pearl.fcw.utils.StringUtil;
import com.pearl.o2o.pojo.GeneralStageClearInfo;
import com.pearl.o2o.pojo.StageClearPlayerInfo;
import com.pearl.o2o.pojo.StageClearPlayerInfo.SingleCharacterInfo;
import com.pearl.o2o.utils.ConfigurationUtil;

import edu.emory.mathcs.backport.java.util.Arrays;

/**
 * SysQuest表的特殊字段描述
 */
public class WSysQuestColumnDescriptor extends ColumnDescriptor<WSysQuest> {
	private Logger logger = LoggerFactory.getLogger(getClass());
	private static final Pattern P1 = Pattern.compile("(([a-zA-Z]+[a-zA-Z0-9_]*)\\.([a-zA-Z]+[a-zA-Z0-9_\\.\\+]*))([>|<|==|<=|>=]*)([\\-\\+]*\\d+\\.*\\d*)");
	private static final Pattern P2 = Pattern.compile("(([a-zA-Z]+[a-zA-Z0-9_]*)\\.([a-zA-Z]+[a-zA-Z0-9_\\.]*))");

	public static void main(String[] args) {
		String str = "a1.b";
		Matcher m = P2.matcher(str);
		while (m.find()) {
			System.out.println(m.groupCount());
			System.out.println(m.group(1));
			System.out.println(m.group(2));
			System.out.println(m.group(3));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public WSysQuest get(WSysQuest m) {
		if (null == m) {
			return m;
		}
		if (!StringUtil.isEmpty(m.getItems())) {//奖励
			List<WSysItemPrice> list = Stream.of(m.getItems().split(";")).map(p -> {
				WSysItemPrice wSysItemPrice = new WSysItemPrice();
				try {
					List<Integer> list1 = Stream.of(p.split(",")).map(Integer::parseInt).collect(Collectors.toList());
					wSysItemPrice.setSysItemId(list1.get(0));
					wSysItemPrice.setPayType(list1.get(1));
					wSysItemPrice.setUnitType(list1.get(2));
					wSysItemPrice.setCost(list1.get(3));
					wSysItemPrice.setUnit(list1.get(4));
				} catch (Exception e) {
					logger.warn("items field has error when sysQuest.id = " + m.getId(), e);
				}
				return wSysItemPrice;
			}).collect(Collectors.toList());
			m.getItemList().addAll(list);
		}
		if (!StringUtil.isEmpty(m.getVipItems())) {//VIP奖励
			List<WSysItemPrice> list = Stream.of(m.getVipItems().split(";")).map(p -> {
				WSysItemPrice wSysItemPrice = new WSysItemPrice();
				try {
					List<Integer> list1 = Stream.of(p.split(",")).map(Integer::parseInt).collect(Collectors.toList());
					wSysItemPrice.setSysItemId(list1.get(0));
					wSysItemPrice.setPayType(list1.get(1));
					wSysItemPrice.setUnitType(list1.get(2));
					wSysItemPrice.setCost(list1.get(3));
					wSysItemPrice.setUnit(list1.get(4));
				} catch (Exception e) {
					logger.warn("vipItems field has error when sysQuest.id = " + m.getId(), e);
				}
				return wSysItemPrice;
			}).collect(Collectors.toList());
			m.getVipItemList().addAll(list);
		}
		if (!StringUtil.isEmpty(m.getTriggerEvent())) {//任务触发器
			JsonQuestTrigger trigger = new JsonQuestTrigger();
			m.setTrigger(trigger);
			String str = m.getTriggerEvent().replaceAll("\\s", "");//去除空白字符
			for (String s : str.split(";")) {//条件，每条分号隔开，但可能用逗号相连的都是同一条限制数据
				List<Trigger> ts = new ArrayList<>();
				Matcher matcher = P1.matcher(s);
				while (matcher.find()) {
					JsonQuestTrigger.Trigger tmp = (generateTrigger(matcher));
					if (null != tmp) {
						ts.add(tmp);
					}
				}
				trigger.getConditionList().add(ts);
			}
			if (!StringUtil.isEmpty(m.getTriggerInterface())) {//接口，逗号间隔
				List<String> uriList = Arrays.asList(m.getTriggerInterface().split(","));
				trigger.getInterfaceList().addAll(uriList.stream().map(p -> {//rpc接口
							return ConfigurationUtil.INFO_INTERFACE_PREFIX + "c_" + p;
						}).collect(Collectors.toList()));
				trigger.getInterfaceList().addAll(uriList.stream().map(p -> {//server接口
							return ConfigurationUtil.INFO_INTERFACE_PREFIX + "s_" + p;
						}).collect(Collectors.toList()));
			}
			if (StringUtil.isEmpty(m.getTriggerNumber())) {//计数
				Matcher matcher = P2.matcher(m.getTriggerNumber());
				if (matcher.find()) {
					JsonQuestTrigger.Trigger tmp = (generateTrigger(matcher));
					trigger.setNumber(tmp);
				}
			}
		}
		return m;
	}

	@Override
	public void set(WSysQuest m) {
	}

	/**
	 * 生成触发器节点
	 * @param matcher
	 * @return
	 */
	private JsonQuestTrigger.Trigger generateTrigger(Matcher matcher) {
		String className = StringUtil.toPascal(matcher.group(2));
		Class<?> cls = null;
		if (className.equalsIgnoreCase("stageClear")) {//过关结算特殊处理
			cls = GeneralStageClearInfo.class;
		} else if (className.equalsIgnoreCase("stageClearPlayer")) {//过关结算特殊处理
			cls = StageClearPlayerInfo.class;
		} else if (className.equalsIgnoreCase("stageClearPlayerCharacter")) {//过关结算特殊处理
			cls = SingleCharacterInfo.class;
		}
		if (null == cls) {//判断是否代理类
			try {
				cls = Class.forName("com.pearl.fcw.lobby.pojo.proxy.ProxyW" + className);
			} catch (ClassNotFoundException e) {
			}
		}
		if (null == cls) {//判断是否数据库实体类
			try {
				cls = Class.forName("com.pearl.fcw.gm.pojo.W" + className);
			} catch (ClassNotFoundException e) {
			}
		}
		if (null == cls) {
			return null;
		}
		JsonQuestTrigger.Trigger trigger = new Trigger();
		trigger.setCls(cls);
		trigger.setProperty(matcher.group(3));
		if (matcher.groupCount() > 3) {//计数没有判断符号
			trigger.setComparator(matcher.group(4));
			trigger.setNumber(Double.parseDouble(matcher.group(5)));
		}
		return trigger;
	}

}
