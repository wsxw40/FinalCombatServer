package com.pearl.fcw.lobby.pojo.json;

import java.util.ArrayList;
import java.util.List;

/**
 * 任务触发器
 */
public class JsonQuestTrigger {
	private List<String> interfaceList = new ArrayList<>();//触发接口名称
	private List<List<Trigger>> conditionList = new ArrayList<>();//限制条件
	private Trigger number;//计数增长

	public List<String> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(List<String> interfaceList) {
		this.interfaceList = interfaceList;
	}

	public List<List<Trigger>> getConditionList() {
		return conditionList;
	}

	public void setConditionList(List<List<Trigger>> conditionList) {
		this.conditionList = conditionList;
	}

	public Trigger getNumber() {
		return number;
	}

	public void setNumber(Trigger number) {
		this.number = number;
	}

	public static class Trigger {
		private Class<?> cls;//对象类
		private String property;//属性
		private String comparator;//比较器
		private double number = 0.0;//阈值

		public Class<?> getCls() {
			return cls;
		}

		public void setCls(Class<?> cls) {
			this.cls = cls;
		}

		public String getProperty() {
			return property;
		}

		public void setProperty(String property) {
			this.property = property;
		}

		public String getComparator() {
			return comparator;
		}

		public void setComparator(String comparator) {
			this.comparator = comparator;
		}

		public double getNumber() {
			return number;
		}

		public void setNumber(double number) {
			this.number = number;
		}
	}
}
