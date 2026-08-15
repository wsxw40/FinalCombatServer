package com.pearl.fcw.core.pojo.proxy;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class OperationListener implements Serializable {

    private static final long serialVersionUID = 2534022697150472921L;
    private final List<Operation> operations = new ArrayList<>();

    public void onIncrease(String path, Object value) {
        operations.add(new Operation(Operation.Type.INCREASE, path, value));
    }

    public void onUpdate(String path, Object value) {
        operations.add(new Operation(Operation.Type.UPDATE, path, value));
    }

    public void onAdd(String path, Object value) {
        operations.add(new Operation(Operation.Type.ADD, path, value));
    }

    public void onAdd(String path, Object[] value) {
        operations.add(new Operation(Operation.Type.ADD, path, value));
    }

    public void onRemove(String path) {
        operations.add(new Operation(Operation.Type.REMOVE, path));
    }

    public void onSet(String path, Object value) {
        operations.add(new Operation(Operation.Type.SET, path, value));
    }

    public void onPut(String path, Object value) {
        operations.add(new Operation(Operation.Type.PUT, path, value));
    }

	public void onNewInstance(String path) {
		operations.add(new Operation(Operation.Type.NEWINSTANCE, path, null));
	}

    public List<Operation> getOperations() {
        return operations;
    }

    public void clear() {
        operations.clear();
    }

}
