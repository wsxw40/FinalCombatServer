package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.ParamObject;

public class ProxyParamObject<T> extends BeanProxy<ParamObject<T>> {
    private PropertyProxy<T> value;

    public PropertyProxy<T> value() {
		return value;
	}

	@Override
	public void initFields() {
        ParamObject<T> instance = get();
        value = new PropertyProxy<T>("value", this, instance::getValue, instance::setValue);
	}

    public ProxyParamObject(Object element, Proxy<?> owner, Supplier<ParamObject<T>> getter, Consumer<ParamObject<T>> setter) {
		super(element, owner, getter, setter);
	}

    public ProxyParamObject(Object element, Proxy<?> owner, ParamObject<T> instance) {
		super(element, owner, instance);
	}

}