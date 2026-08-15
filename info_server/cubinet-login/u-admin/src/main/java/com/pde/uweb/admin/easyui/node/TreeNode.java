package com.pde.uweb.admin.easyui.node;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午1:12
 * To change this template use File | Settings | File Templates.
 */
public class TreeNode {
    public static final String State_Open = "open";
    public static final String State_Closed = "closed";

    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected int id;
    private String text;
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected String state = "closed";
    @JsonInclude(JsonInclude.Include.NON_DEFAULT)
    protected boolean checked = false;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected final List<TreeNode> children = new ArrayList<TreeNode>();
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    protected final Map<String, Object> attributes = new HashMap<String, Object>();

    public TreeNode(int id, String text) {
        this.id = id;
        this.text = text;
    }

    public TreeNode(int id, String text, String state, boolean checked) {
        this.id = id;
        this.text = text;
        this.state = state;
        this.checked = checked;
    }

    public void addChildren(TreeNode children) {
        this.children.add(children);
    }

    public void addChildren(List<TreeNode> children) {
        this.children.addAll(children);
    }

    public void addAttribute(String key, String value) {
        this.attributes.put(key, value);
    }

    public void addAttribute(Map<String, String> attributes) {
        this.attributes.putAll(attributes);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public List<TreeNode> getChildren() {
        return children;
    }

}
