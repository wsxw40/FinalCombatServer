package com.pearl.fcw.core.pojo.proxy;

import java.io.Serializable;

import com.pearl.fcw.core.pojo.BaseEntity;


public class CacheEntry<T extends BaseEntity> implements Serializable {

    private static final long serialVersionUID = 6629110270263820828L;

    private T entity;
    private OperationListener listener = new OperationListener();

    public CacheEntry() {
    }

    public CacheEntry(T entity) {
        this.entity = entity;
    }

    public CacheEntry(T entity, OperationListener listener) {
        this.entity = entity;
        if (null != listener && null != listener.getOperations()) {
            this.listener.getOperations().addAll(listener.getOperations());
        }
    }

    public T getEntity() {
        return entity;
    }

    public Long getVersion() {
        return entity == null ? null : entity.getDbVersion();
    }

    public OperationListener getListener() {
        return listener;
    }

    public void setListener(OperationListener listener) {
        this.listener = listener;
    }

    public void setEntity(T entity) {
        this.entity = entity;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (entity == null ? 0 : entity.hashCode());
        result = prime * result + (listener == null ? 0 : listener.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        @SuppressWarnings("rawtypes")
        CacheEntry other = (CacheEntry) obj;
        if (entity == null) {
            if (other.entity != null) {
                return false;
            }
        } else if (!entity.equals(other.entity)) {
            return false;
        }
        if (listener == null) {
            if (other.listener != null) {
                return false;
            }
        } else if (!listener.equals(other.listener)) {
            return false;
        }
        return true;
    }

}
