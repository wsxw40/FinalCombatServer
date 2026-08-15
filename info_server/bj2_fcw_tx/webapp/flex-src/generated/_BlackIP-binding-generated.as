

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

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
	 * generated bindable wrapper for property ip (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'ip' moved to '_3367ip'
	 */

    [Bindable(event="propertyChange")]
    public function get ip():String
    {
        return this._3367ip;
    }

    public function set ip(value:String):void
    {
    	var oldValue:Object = this._3367ip;
        if (oldValue !== value)
        {
            this._3367ip = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "ip", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isBanned (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isBanned' moved to '_722308888isBanned'
	 */

    [Bindable(event="propertyChange")]
    public function get isBanned():String
    {
        return this._722308888isBanned;
    }

    public function set isBanned(value:String):void
    {
    	var oldValue:Object = this._722308888isBanned;
        if (oldValue !== value)
        {
            this._722308888isBanned = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isBanned", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property bannedTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'bannedTime' moved to '_1044609269bannedTime'
	 */

    [Bindable(event="propertyChange")]
    public function get bannedTime():int
    {
        return this._1044609269bannedTime;
    }

    public function set bannedTime(value:int):void
    {
    	var oldValue:Object = this._1044609269bannedTime;
        if (oldValue !== value)
        {
            this._1044609269bannedTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bannedTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property description (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'description' moved to '_1724546052description'
	 */

    [Bindable(event="propertyChange")]
    public function get description():String
    {
        return this._1724546052description;
    }

    public function set description(value:String):void
    {
    	var oldValue:Object = this._1724546052description;
        if (oldValue !== value)
        {
            this._1724546052description = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "description", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createTime' moved to '_1369213417createTime'
	 */

    [Bindable(event="propertyChange")]
    public function get createTime():int
    {
        return this._1369213417createTime;
    }

    public function set createTime(value:int):void
    {
    	var oldValue:Object = this._1369213417createTime;
        if (oldValue !== value)
        {
            this._1369213417createTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isChanged (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isChanged' moved to '_159164970isChanged'
	 */

    [Bindable(event="propertyChange")]
    public function get isChanged():int
    {
        return this._159164970isChanged;
    }

    public function set isChanged(value:int):void
    {
    	var oldValue:Object = this._159164970isChanged;
        if (oldValue !== value)
        {
            this._159164970isChanged = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isChanged", oldValue, value));
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
