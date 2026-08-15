package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.TmpGunProperty2;

public class ProxyTmpGunProperty2 extends BeanProxy<TmpGunProperty2> {
	private PropertyProxy<java.lang.Number> index;
	private PropertyProxy<java.lang.Number> value;
	private PropertyProxy<java.lang.Number> value2;
	private PropertyProxy<java.lang.Number> time;

	public PropertyProxy<java.lang.Number> index() {
		return index;
	}

	public PropertyProxy<java.lang.Number> value() {
		return value;
	}

	public PropertyProxy<java.lang.Number> value2() {
		return value2;
	}

	public PropertyProxy<java.lang.Number> time() {
		return time;
	}

	@Override
	public void initFields() {
		TmpGunProperty2 instance = get();
		index = new  PropertyProxy<java.lang.Number> ("index", this, instance::getIndex, instance::setIndex);
		value = new  PropertyProxy<java.lang.Number> ("value", this, instance::getValue, instance::setValue);
		value2 = new  PropertyProxy<java.lang.Number> ("value2", this, instance::getValue2, instance::setValue2);
		time = new  PropertyProxy<java.lang.Number> ("time", this, instance::getTime, instance::setTime);
	}

	public ProxyTmpGunProperty2(Object element, Proxy<?> owner, Supplier<TmpGunProperty2> getter,  Consumer<TmpGunProperty2> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyTmpGunProperty2(Object element, Proxy<?> owner, TmpGunProperty2 instance) {
		super(element, owner, instance);
	}

}