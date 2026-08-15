package com.pde.uweb.admin.easyui.data;

import com.pde.uweb.admin.easyui.node.PropertyGridNode;

import java.util.Collection;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午1:37
 * To change this template use File | Settings | File Templates.
 */
public class PropertyGrid extends Grid{
    public PropertyGrid() {
    }

    public PropertyGrid(Map<? extends Object, ? extends Object> map) {
        for (Map.Entry<? extends Object, ? extends Object> row : map.entrySet()) {
              addRow(new PropertyGridNode(row.getKey(), row.getValue()));
        }
    }

    public PropertyGrid(Collection<PropertyGridNode> props) {
        super(props);
    }
}
