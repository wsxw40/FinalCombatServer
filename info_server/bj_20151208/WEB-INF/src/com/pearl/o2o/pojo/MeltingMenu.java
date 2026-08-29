package com.pearl.o2o.pojo;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Iterables;
import com.pearl.o2o.utils.CommonUtil;
import com.pearl.o2o.utils.Converter;
import com.pearl.o2o.utils.MeltingConstants;
import com.pearl.o2o.utils.ServiceLocator;

public class MeltingMenu implements Serializable,Cloneable {
	
	private static final long serialVersionUID = 4295398319231904959L;
	private String name;
	private int itemId;
	private SysItem item;
	private List<MeltingMenu> children;
	/**
	 * 获得menu显示名称
	 * @return {@link String}
	 */
	public String getName() {
		if(name.startsWith("@")){
			return name.substring(1);
		}
		return CommonUtil.messageFormatI18N(name);
	}
	public void setName(String name) {
		this.name = name;
	}

	public List<MeltingMenu> getChildren() {
		return children;
	}
	public void setChildren(List<MeltingMenu> children) {
		this.children = children;
	}
	public int getItemId() {
		return itemId;
	}
	public void setItemId(int itemId) {
		this.itemId = itemId;
	}
	public SysItem getItem() throws Exception {
		if(item==null){
			item = ServiceLocator.getService.getSysItemByItemId(itemId); 
		}
		return item;
	}
	public void setItem(SysItem item) {
		this.item = item;
	}
	public String getChildrenStr(){
		StringBuilder  childrenStr = new StringBuilder();
		for(MeltingMenu mlm : this.children){
			childrenStr.append(Converter.getMeltingMenuStr(mlm)).append(",").append("\n");
		}
		return childrenStr.toString();
		
	}
	
	public String getMeltingItemStr(){
		Integer[] array = Iterables.toArray(Iterables.transform(MeltingConstants.splitterByUnderScore.split(this.item.getResourceChangeable()), MeltingConstants.functionStrToInt), Integer.class);
		HashMultiset<Integer> moduleMultiset = HashMultiset.create(Iterables.transform(MeltingConstants.splitterByColon.split(this.item.getResourceStable()), MeltingConstants.functionStrToInt));
		StringBuilder sb=new StringBuilder();
		if(array.length!=0&&moduleMultiset.size()!=0){
			try{
				sb.append("items={");
				for(int i:moduleMultiset){
					SysItem si=ServiceLocator.getService.getSysItemByItemId(i);
					sb.append("{");
					sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName()).append("\",");
					sb.append("},");
				}
				sb.append("},");
				sb.append("result={");
				SysItem si=ServiceLocator.getService.getSysItemByItemId(array[0]).clone();
				si.setUnit(array[1]);
				si.setUnitType(array[2]);
				sb.append(Converter.getSysItemToolTips(si));
//				sb.append(si.getId()).append(",\"").append(si.getName()).append("\",\"").append(si.getDisplayName())
//					.append("\",").append(array[1]).append(",").append(array[2]).append(",");
//				sb.append("},");
				sb.append("},");
			}catch (Exception e) {
				sb.append("items={");
				sb.append("},");
				sb.append("result={");
				sb.append("},");
			}
		}
		return sb.toString();
	}
}
