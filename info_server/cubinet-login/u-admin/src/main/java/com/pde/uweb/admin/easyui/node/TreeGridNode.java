package com.pde.uweb.admin.easyui.node;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午1:08
 * To change this template use File | Settings | File Templates.
 */
@JsonAutoDetect
public abstract class TreeGridNode implements GridNode {
    public static final String State_Open = "open";
    public static final String State_Closed = "closed";

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected int id;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected String state = "closed";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected boolean checked = false;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected int _parentId = 0;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected final List<TreeGridNode> children = new ArrayList<TreeGridNode>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected final Map<String, Object> attributes = new HashMap<String, Object>();

    protected TreeGridNode(int id) {
        this.id = id;
    }

    protected TreeGridNode() {

    }

    protected TreeGridNode(int id, String state, boolean checked) {
        this.id = id;
        this.state = state;
        this.checked = checked;
    }

    public void addChildren(TreeGridNode children) {
        children._parentId = this.id;
        this.children.add(children);
    }

    public void addChildren(List<TreeGridNode> children) {
        for (int i = 0; i < children.size(); i++) {
            addChildren(children.get(i));
        }
    }

    public void addAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public void addAttribute(Map<String, String> attributes) {
        if (null == attributes)
            attributes = new HashMap<String, String>();
        this.attributes.putAll(attributes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public Map<String, Object> getAttributes() {
        return attributes;
    }

    public List<TreeGridNode> getChildren() {
        return children;
    }

    public int get_parentId() {
        return _parentId;
    }
}
