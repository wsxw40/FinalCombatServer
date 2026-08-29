

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Date;
import String;
import int;

class BindableProperty
    implements flash.events.IEventDispatcher
{
	/**
	 * generated bindable wrapper for property id (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'id' moved to '_3355id'
	 */

    [Bindable(event="propertyChange")]
    public function get id():int
    {
        return this._3355id;
    }

    public function set id(value:int):void
    {
    	var oldValue:Object = this._3355id;
        if (oldValue !== value)
        {
            this._3355id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "id", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property userId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'userId' moved to '_836030906userId'
	 */

    [Bindable(event="propertyChange")]
    public function get userId():String
    {
        return this._836030906userId;
    }

    public function set userId(value:String):void
    {
    	var oldValue:Object = this._836030906userId;
        if (oldValue !== value)
        {
            this._836030906userId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property playerId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'playerId' moved to '_1879273436playerId'
	 */

    [Bindable(event="propertyChange")]
    public function get playerId():int
    {
        return this._1879273436playerId;
    }

    public function set playerId(value:int):void
    {
    	var oldValue:Object = this._1879273436playerId;
        if (oldValue !== value)
        {
            this._1879273436playerId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "playerId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property itemId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'itemId' moved to '_1178662002itemId'
	 */

    [Bindable(event="propertyChange")]
    public function get itemId():int
    {
        return this._1178662002itemId;
    }

    public function set itemId(value:int):void
    {
    	var oldValue:Object = this._1178662002itemId;
        if (oldValue !== value)
        {
            this._1178662002itemId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "itemId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property itemName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'itemName' moved to '_1177331774itemName'
	 */

    [Bindable(event="propertyChange")]
    public function get itemName():String
    {
        return this._1177331774itemName;
    }

    public function set itemName(value:String):void
    {
    	var oldValue:Object = this._1177331774itemName;
        if (oldValue !== value)
        {
            this._1177331774itemName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "itemName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property itemType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'itemType' moved to '_1177533677itemType'
	 */

    [Bindable(event="propertyChange")]
    public function get itemType():int
    {
        return this._1177533677itemType;
    }

    public function set itemType(value:int):void
    {
    	var oldValue:Object = this._1177533677itemType;
        if (oldValue !== value)
        {
            this._1177533677itemType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "itemType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property leftMoney (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'leftMoney' moved to '_1725201657leftMoney'
	 */

    [Bindable(event="propertyChange")]
    public function get leftMoney():int
    {
        return this._1725201657leftMoney;
    }

    public function set leftMoney(value:int):void
    {
    	var oldValue:Object = this._1725201657leftMoney;
        if (oldValue !== value)
        {
            this._1725201657leftMoney = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "leftMoney", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property payType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'payType' moved to '_787406846payType'
	 */

    [Bindable(event="propertyChange")]
    public function get payType():int
    {
        return this._787406846payType;
    }

    public function set payType(value:int):void
    {
    	var oldValue:Object = this._787406846payType;
        if (oldValue !== value)
        {
            this._787406846payType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "payType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property payAmount (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'payAmount' moved to '_1338781920payAmount'
	 */

    [Bindable(event="propertyChange")]
    public function get payAmount():int
    {
        return this._1338781920payAmount;
    }

    public function set payAmount(value:int):void
    {
    	var oldValue:Object = this._1338781920payAmount;
        if (oldValue !== value)
        {
            this._1338781920payAmount = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "payAmount", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createTime' moved to '_1369213417createTime'
	 */

    [Bindable(event="propertyChange")]
    public function get createTime():Date
    {
        return this._1369213417createTime;
    }

    public function set createTime(value:Date):void
    {
    	var oldValue:Object = this._1369213417createTime;
        if (oldValue !== value)
        {
            this._1369213417createTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createTimeStr' moved to '_932579208createTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get createTimeStr():String
    {
        return this._932579208createTimeStr;
    }

    public function set createTimeStr(value:String):void
    {
    	var oldValue:Object = this._932579208createTimeStr;
        if (oldValue !== value)
        {
            this._932579208createTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property payUse (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'payUse' moved to '_995230785payUse'
	 */

    [Bindable(event="propertyChange")]
    public function get payUse():int
    {
        return this._995230785payUse;
    }

    public function set payUse(value:int):void
    {
    	var oldValue:Object = this._995230785payUse;
        if (oldValue !== value)
        {
            this._995230785payUse = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "payUse", oldValue, value));
        }
    }


    //    IEventDispatcher implementation
    //
    private var _bindingEventDispatcher:flash.events.EventDispatcher =
        new flash.events.EventDispatcher(flash.events.IEventDispatcher(this));

    public function addEventListener(type:String, listener:Function,
                                     useCapture:Boolean = false,
                                     priority:int = 0,
                                     weakRef:Boolean = false):void
    {
        _bindingEventDispatcher.addEventListener(type, listener, useCapture,
                                                 priority, weakRef);
    }

    public function dispatchEvent(event:flash.events.Event):Boolean
    {
        return _bindingEventDispatcher.dispatchEvent(event);
    }

    public function hasEventListener(type:String):Boolean
    {
        return _bindingEventDispatcher.hasEventListener(type);
    }

    public function removeEventListener(type:String,
                                        listener:Function,
                                        useCapture:Boolean = false):void
    {
        _bindingEventDispatcher.removeEventListener(type, listener, useCapture);
    }

    public function willTrigger(type:String):Boolean
    {
        return _bindingEventDispatcher.willTrigger(type);
    }

}
