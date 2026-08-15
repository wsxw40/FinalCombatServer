package com.pearl.fcw.core.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FloatProxy extends PropertyProxy<Float> {

    public FloatProxy(Object element, Proxy<?> owner, Supplier<Float> getter, Consumer<Float> setter) {
        super(element, owner, getter, setter);
    }

    @Override
    public Float get() {
        return null == super.get() ? 0f : super.get();
    }

	public void increase(Float value) throws NullPointerException {
        setWithNolistener(get() + value);
        listener.onIncrease(path, value);
    }

}
