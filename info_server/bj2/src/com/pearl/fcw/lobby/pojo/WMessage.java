package com.pearl.fcw.lobby.pojo;

import java.util.Date;

import com.pearl.fcw.core.pojo.DmModel;
import com.pearl.fcw.core.pojo.GoSchema;
import com.pearl.fcw.core.pojo.Schema;

@GoSchema(type = Schema.MAIN)
public class WMessage extends DmModel {

    private static final long serialVersionUID = -4844338452208760297L;

    private Integer id;

    private Integer receiverId;

    private String senderName;

    private String subject;

    private String content;

    private Date createdTime;

    private String open;

    private String isDeleted;

    private String sysItems;

    private Integer senderId;

    private Boolean isAttached;

    private String itemUnits;

    private String itemUnittypes;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderName() {
        return senderName;
    }

    public void setSenderName(String senderName) {
        this.senderName = senderName == null ? null : senderName.trim();
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject == null ? null : subject.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open == null ? null : open.trim();
    }

    public String getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(String isDeleted) {
        this.isDeleted = isDeleted == null ? null : isDeleted.trim();
    }

    public String getSysItems() {
        return sysItems;
    }

    public void setSysItems(String sysItems) {
        this.sysItems = sysItems == null ? null : sysItems.trim();
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Boolean getIsAttached() {
        return isAttached;
    }

    public void setIsAttached(Boolean isAttached) {
        this.isAttached = isAttached;
    }

    public String getItemUnits() {
        return itemUnits;
    }

    public void setItemUnits(String itemUnits) {
        this.itemUnits = itemUnits == null ? null : itemUnits.trim();
    }

    public String getItemUnittypes() {
        return itemUnittypes;
    }

    public void setItemUnittypes(String itemUnittypes) {
        this.itemUnittypes = itemUnittypes == null ? null : itemUnittypes.trim();
    }

    @Override
    public boolean getIsRemoved() {
        return "Y".equals(isDeleted);
    }

    @Override
    public void setIsRemoved(boolean isRemoved) {
        isDeleted = isRemoved ? "Y" : "N";
    }
}