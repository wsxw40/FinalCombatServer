

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Number;
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
	 * generated bindable wrapper for property type (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'type' moved to '_3575610type'
	 */

    [Bindable(event="propertyChange")]
    public function get type():int
    {
        return this._3575610type;
    }

    public function set type(value:int):void
    {
    	var oldValue:Object = this._3575610type;
        if (oldValue !== value)
        {
            this._3575610type = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "type", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property content (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'content' moved to '_951530617content'
	 */

    [Bindable(event="propertyChange")]
    public function get content():String
    {
        return this._951530617content;
    }

    public function set content(value:String):void
    {
    	var oldValue:Object = this._951530617content;
        if (oldValue !== value)
        {
            this._951530617content = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "content", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property startTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'startTime' moved to '_2129294769startTime'
	 */

    [Bindable(event="propertyChange")]
    public function get startTime():Number
    {
        return this._2129294769startTime;
    }

    public function set startTime(value:Number):void
    {
    	var oldValue:Object = this._2129294769startTime;
        if (oldValue !== value)
        {
            this._2129294769startTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property startTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'startTimeStr' moved to '_1448385182startTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get startTimeStr():String
    {
        return this._1448385182startTimeStr;
    }

    public function set startTimeStr(value:String):void
    {
    	var oldValue:Object = this._1448385182startTimeStr;
        if (oldValue !== value)
        {
            this._1448385182startTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property endTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'endTime' moved to '_1607243192endTime'
	 */

    [Bindable(event="propertyChange")]
    public function get endTime():Number
    {
        return this._1607243192endTime;
    }

    public function set endTime(value:Number):void
    {
    	var oldValue:Object = this._1607243192endTime;
        if (oldValue !== value)
        {
            this._1607243192endTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "endTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property endTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'endTimeStr' moved to '_1086433591endTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get endTimeStr():String
    {
        return this._1086433591endTimeStr;
    }

    public function set endTimeStr(value:String):void
    {
    	var oldValue:Object = this._1086433591endTimeStr;
        if (oldValue !== value)
        {
            this._1086433591endTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "endTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property noticeTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'noticeTime' moved to '_1269288507noticeTime'
	 */

    [Bindable(event="propertyChange")]
    public function get noticeTime():int
    {
        return this._1269288507noticeTime;
    }

    public function set noticeTime(value:int):void
    {
    	var oldValue:Object = this._1269288507noticeTime;
        if (oldValue !== value)
        {
            this._1269288507noticeTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "noticeTime", oldValue, value));
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
