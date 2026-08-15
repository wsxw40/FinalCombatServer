package com.pearl.fcw.lobby.pojo.proxy;

import java.util.function.Consumer;
import java.util.function.Supplier;

import com.pearl.fcw.core.pojo.proxy.DateProxy;
import com.pearl.fcw.core.pojo.proxy.EntityProxy;
import com.pearl.fcw.core.pojo.proxy.IntegerProxy;
import com.pearl.fcw.core.pojo.proxy.PropertyProxy;
import com.pearl.fcw.core.pojo.proxy.Proxy;
import com.pearl.fcw.lobby.pojo.WMessage;

public class ProxyWMessage extends EntityProxy<WMessage> {
	private IntegerProxy id;
	private IntegerProxy receiverId;
	private PropertyProxy<java.lang.String> senderName;
	private PropertyProxy<java.lang.String> subject;
	private PropertyProxy<java.lang.String> content;
	private DateProxy<java.util.Date> createdTime;
	private PropertyProxy<java.lang.String> open;
	private PropertyProxy<java.lang.String> isDeleted;
	private PropertyProxy<java.lang.String> sysItems;
	private IntegerProxy senderId;
	private PropertyProxy<java.lang.Boolean> isAttached;
	private PropertyProxy<java.lang.String> itemUnits;
	private PropertyProxy<java.lang.String> itemUnittypes;

	public IntegerProxy id() {
		return id;
	}

	public IntegerProxy receiverId() {
		return receiverId;
	}

	public PropertyProxy<java.lang.String> senderName() {
		return senderName;
	}

	public PropertyProxy<java.lang.String> subject() {
		return subject;
	}

	public PropertyProxy<java.lang.String> content() {
		return content;
	}

	public DateProxy<java.util.Date> createdTime() {
		return createdTime;
	}

	public PropertyProxy<java.lang.String> open() {
		return open;
	}

	public PropertyProxy<java.lang.String> isDeleted() {
		return isDeleted;
	}

	public PropertyProxy<java.lang.String> sysItems() {
		return sysItems;
	}

	public IntegerProxy senderId() {
		return senderId;
	}

	public PropertyProxy<java.lang.Boolean> isAttached() {
		return isAttached;
	}

	public PropertyProxy<java.lang.String> itemUnits() {
		return itemUnits;
	}

	public PropertyProxy<java.lang.String> itemUnittypes() {
		return itemUnittypes;
	}

	@Override
	public void initFields() {
		WMessage instance = get();
		id = new  IntegerProxy ("id", this, instance::getId, instance::setId);
		receiverId = new  IntegerProxy ("receiverId", this, instance::getReceiverId, instance::setReceiverId);
		senderName = new  PropertyProxy<java.lang.String> ("senderName", this, instance::getSenderName, instance::setSenderName);
		subject = new  PropertyProxy<java.lang.String> ("subject", this, instance::getSubject, instance::setSubject);
		content = new  PropertyProxy<java.lang.String> ("content", this, instance::getContent, instance::setContent);
		createdTime = new  DateProxy<java.util.Date> ("createdTime", this, instance::getCreatedTime, instance::setCreatedTime);
		open = new  PropertyProxy<java.lang.String> ("open", this, instance::getOpen, instance::setOpen);
		isDeleted = new  PropertyProxy<java.lang.String> ("isDeleted", this, instance::getIsDeleted, instance::setIsDeleted);
		sysItems = new  PropertyProxy<java.lang.String> ("sysItems", this, instance::getSysItems, instance::setSysItems);
		senderId = new  IntegerProxy ("senderId", this, instance::getSenderId, instance::setSenderId);
		isAttached = new  PropertyProxy<java.lang.Boolean> ("isAttached", this, instance::getIsAttached, instance::setIsAttached);
		itemUnits = new  PropertyProxy<java.lang.String> ("itemUnits", this, instance::getItemUnits, instance::setItemUnits);
		itemUnittypes = new  PropertyProxy<java.lang.String> ("itemUnittypes", this, instance::getItemUnittypes, instance::setItemUnittypes);
	}

	public ProxyWMessage(WMessage  instance) {
		super(instance);
	}

	public ProxyWMessage(Object element, Proxy<?> owner, Supplier<WMessage> getter,  Consumer<WMessage> setter) {
		super(element, owner, getter, setter);
	}

	public ProxyWMessage(Object element, Proxy<?> owner, WMessage instance) {
		super(element, owner, instance);
	}

}