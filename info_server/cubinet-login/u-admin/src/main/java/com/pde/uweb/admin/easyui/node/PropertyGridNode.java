package com.pde.uweb.admin.easyui.node;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: lifengyang
 * Date: 12-8-26
 * Time: 上午1:22
 * To change this template use File | Settings | File Templates.
 */
@JsonAutoDetect
public class PropertyGridNode implements GridNode{
    private Object name;
    private Object value;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String group;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private final Map<Object, Object> editor = new HashMap<Object, Object>();

    public PropertyGridNode() {
    }

    public PropertyGridNode(Object name, Object value) {
        this.name = name;
        this.value = value;
    }

    public PropertyGridNode(Object name, Object value, String group) {
        this.name = name;
        this.value = value;
        this.group = group;
    }


    public void addEditor(Object key, Object value) {
        this.editor.put(key, value);
    }

    public void addEditor(Map<Object, Object> editor) {
        if (null != editor) {
            this.editor.putAll(editor);
        }
    }

    public Object getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Map<Object, Object> getEditor() {
        return editor;
    }
}
