package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.MapProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.TmpGunProperty;
import com.pearl.fcw.lobby.pojo.TmpGunProperty1;

public class ProxyTmpGunProperty extends BeanProxy<TmpGunProperty> {
    private MapProxy<String, TmpGunProperty1, ProxyTmpGunProperty1> map;

    public MapProxy<String, TmpGunProperty1, ProxyTmpGunProperty1> map() {
		return map;
	}

	@Override
	public void initFields() {
		TmpGunProperty instance = get();
        map = new MapProxy<String, TmpGunProperty1, ProxyTmpGunProperty1>("map", this, instance::getMap, instance::setMap, ProxyTmpGunProperty1::new);
	}

	public ProxyTmpGunProperty(Object element, Proxy<?> owner, Supplier<TmpGunProperty> getter,  Consumer<TmpGunProperty> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyTmpGunProperty(Object element, Proxy<?> owner, TmpGunProperty instance) {
		super(element, owner, instance);
	}

}