package com.pearl.fcw.core.pojo.proxy;



/**
 * 持久层实体代理。
 * @param <T> 被代理类类型
 */
public abstract class Proxy<T> {

	protected final Object element; // 代理类元素名字，可能为属性名（如：“name”），或列表索引（如：“0”）
	protected final Proxy<?> owner; // 当前代理的所有者
	protected final String path;    // 当前代理相对于根元素的路径

    protected OperationListener listener;

    public Proxy(Object element, Proxy<?> owner) {
        this.element = element;
        this.owner = owner;
        if (owner != null) {
            this.path = owner.makeSubPath(element);
            this.listener = owner.getListener();
        } else {
			// 只有root会执行到该分支
            this.path = element != null ? element.toString() : null;
        }
    }

    public Proxy(Object element, Proxy<?> owner, OperationListener listener) {
        this(element, owner);
        this.listener = listener;
    }

    public Proxy(OperationListener listener) {
        this(null, null, listener);
    }

    public Object getElement() {
        return element;
    }

    public Proxy<?> getOwner() {
        return owner;
    }

    public String getPath() {
        return path;
    }

    public OperationListener getListener() {
        return listener;
    }

    public boolean isRoot() {
        return owner == null;
    }

    public void setListener(OperationListener listener) {
        this.listener = listener;
    }

    protected String makeSubPath(Object subElement) {
		// 子元素不可为null
        return this.path != null ? this.path + "." + subElement : subElement.toString();
    }
}