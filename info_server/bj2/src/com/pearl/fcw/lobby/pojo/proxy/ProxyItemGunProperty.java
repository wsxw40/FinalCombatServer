package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.ListProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.gm.pojo.WSysItemGunProperty;
import com.pearl.fcw.lobby.pojo.ItemGunProperty;

public class ProxyItemGunProperty extends BeanProxy<ItemGunProperty> {
	private IntegerProxy open;
	private IntegerProxy mValue;
	private ListProxy<WSysItemGunProperty, ProxyWSysItemGunProperty> gunPropertyList;

	public IntegerProxy open() {
		return open;
	}

	public IntegerProxy mValue() {
		return mValue;
	}

	public ListProxy<WSysItemGunProperty, ProxyWSysItemGunProperty> gunPropertyList() {
		return gunPropertyList;
	}

	@Override
	public void initFields() {
		ItemGunProperty instance = get();
		open = new  IntegerProxy ("open", this, instance::getOpen, instance::setOpen);
		mValue = new  IntegerProxy ("mValue", this, instance::getmValue, instance::setmValue);
		gunPropertyList = new ListProxy<WSysItemGunProperty, ProxyWSysItemGunProperty>("gunPropertyList", this, instance::getGunPropertyList, instance::setGunPropertyList,
				ProxyWSysItemGunProperty::new);
	}

	public ProxyItemGunProperty(Object element, Proxy<?> owner, Supplier<ItemGunProperty> getter,  Consumer<ItemGunProperty> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyItemGunProperty(Object element, Proxy<?> owner, ItemGunProperty instance) {
		super(element, owner, instance);
	}

}