package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class DoubleProxy extends PropertyProxy<Double> {

    public DoubleProxy(Object element, Proxy<?> owner, Supplier<Double> getter, Consumer<Double> setter) {
        super(element, owner, getter, setter);
    }

    @Override
    public Double get() {
        return null == super.get() ? 0.0 : super.get();
    }

	public void increase(Float value) throws NullPointerException {
        setWithNolistener(get() + value);
        listener.onIncrease(path, value);
    }

}
