package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.BeanProxy;
import com.pearl.fcw.core.pojo.proxy.ListProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.TmpGunProperty1;
import com.pearl.fcw.lobby.pojo.TmpGunProperty2;

public class ProxyTmpGunProperty1 extends BeanProxy<TmpGunProperty1> {
    private ListProxy<TmpGunProperty2, ProxyTmpGunProperty2> list;

    public ListProxy<TmpGunProperty2, ProxyTmpGunProperty2> list() {
		return list;
	}

	@Override
	public void initFields() {
		TmpGunProperty1 instance = get();
        list = new ListProxy<TmpGunProperty2, ProxyTmpGunProperty2>("list", this, instance::getList, instance::setList, ProxyTmpGunProperty2::new);
	}

	public ProxyTmpGunProperty1(Object element, Proxy<?> owner, Supplier<TmpGunProperty1> getter,  Consumer<TmpGunProperty1> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyTmpGunProperty1(Object element, Proxy<?> owner, TmpGunProperty1 instance) {
		super(element, owner, instance);
	}

}