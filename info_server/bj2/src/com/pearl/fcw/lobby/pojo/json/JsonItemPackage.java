package com.pearl.fcw.lobby.pojo.json;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.pearl.fcw.gm.pojo.WPayment;

/**
 * 道具包
 */
public class JsonItemPackage {
    private Map<Integer, List<WPayment>> itemForSysItemId = new HashMap<>();
    private Map<Integer, List<WPayment>> itemForIId = new HashMap<>();
    public Map<Integer, List<WPayment>> getItemForSysItemId() {
        return itemForSysItemId;
    }
    public void setItemForSysItemId(Map<Integer, List<WPayment>> itemForSysItemId) {
        this.itemForSysItemId = itemForSysItemId;
    }
    public Map<Integer, List<WPayment>> getItemForIId() {
        return itemForIId;
    }
    public void setItemForIId(Map<Integer, List<WPayment>> itemForIId) {
        this.itemForIId = itemForIId;
    }

}
