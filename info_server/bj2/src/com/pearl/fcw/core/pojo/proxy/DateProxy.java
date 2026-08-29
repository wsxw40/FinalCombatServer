package com.pearl.fcw.core.pojo.proxy;

import java.util.Calendar;
import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class DateProxy<T extends Date> extends PropertyProxy<T> {

    public DateProxy(Object element, Proxy<?> owner, Supplier<T> getter, Consumer<T> setter) {
        super(element, owner, getter, setter);
    }

    /**
     * 增加毫秒
     * @param millisecond
     */
    @SuppressWarnings("unchecked")
    public void increase(int millisecond) {
        Calendar c = Calendar.getInstance();
        c.setTime(get());
        c.add(Calendar.MILLISECOND, millisecond);
        setWithNolistener((T) c.getTime());
        listener.onIncrease(path, millisecond);
    }

    public long getTime() {
        return get().getTime();
    }

}
