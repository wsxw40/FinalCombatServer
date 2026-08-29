package com.pearl.fcw.lobby.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.utils.StringUtil;

/**
 * 道具附加属性
 */
@GoSchema(type = Schema.NEST)
public class ItemGunProperty implements Serializable {

    private static final long serialVersionUID = -1860436103841807807L;
	private Integer open;//开孔开槽。null表示该附加属性不存在
	private Integer mValue;//镶嵌部件的系统道具mValue值
	private List<WSysItemGunProperty> gunPropertyList = new ArrayList<>();

	public ItemGunProperty() {

	}

	/**
	 * 将x;x;x,x,x,x或者x;x,x,x,x或者x,x,x,x的字符串转为道具附加属性
	 * @param str x;x;x,x,x,x或者x;x,x,x,x或者x,x,x,x
	 */
	public ItemGunProperty(String str) {
		if (StringUtil.isEmpty(str)) {
			return;
		}
		List<String> strList = Arrays.asList(str.split(";"));
		if (strList.size() == 1) {//无孔槽无镶嵌
			open = 0;
		} else if (strList.size() == 2) {//有孔槽无镶嵌
			open = Integer.parseInt(strList.get(0));
		} else if (strList.size() == 3) {//有孔槽有镶嵌
			open = Integer.parseInt(strList.get(0));
			mValue = Integer.parseInt(strList.get(1));
		}
		List<Integer> intList = Stream.of(strList.get(strList.size() - 1).split(",")).map(Integer::parseInt).collect(Collectors.toList());
		for (int i = 0; i < intList.size() / 4; i++) {
			try {
				WSysItemGunProperty m = new WSysItemGunProperty();
				m.setIndex(intList.get(4 * i + 0));
				m.setValue(intList.get(4 * i + 1));
				m.setValue2(intList.get(4 * i + 2));
				m.setTime(intList.get(4 * i + 3));
			} catch (Exception e) {
				continue;
			}
		}
	}

	public Integer getOpen() {
		return open;
	}

	public void setOpen(Integer open) {
		this.open = open;
	}

	public Integer getmValue() {
		return mValue;
	}

	public void setmValue(Integer mValue) {
		this.mValue = mValue;
	}

	public List<WSysItemGunProperty> getGunPropertyList() {
		return gunPropertyList;
	}

	public void setGunPropertyList(List<WSysItemGunProperty> gunPropertyList) {
		this.gunPropertyList = gunPropertyList;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		if (null != open) {
			sb.append(open).append(";");
			if (null != mValue) {//有开孔开槽才能有镶嵌
				sb.append(mValue).append(";");
			}
		}
		gunPropertyList.forEach(p -> {
			if (null != p.getIndex() && null != p.getValue() && null != p.getValue2() && null != p.getTime()) {
				sb.append(p.getIndex()).append(",").append(p.getValue()).append(",").append(p.getValue2()).append(",").append(p.getTime());
			}
		});
		return sb.toString();
	}

}
