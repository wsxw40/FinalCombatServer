

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import int;
import String;

class BindableProperty
    implements flash.events.IEventDispatcher
{
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
	 * generated bindable wrapper for property timeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'timeStr' moved to '_1313938396timeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get timeStr():String
    {
        return this._1313938396timeStr;
    }

    public function set timeStr(value:String):void
    {
    	var oldValue:Object = this._1313938396timeStr;
        if (oldValue !== value)
        {
            this._1313938396timeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "timeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property time (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'time' moved to '_3560141time'
	 */

    [Bindable(event="propertyChange")]
    public function get time():int
    {
        return this._3560141time;
    }

    public function set time(value:int):void
    {
    	var oldValue:Object = this._3560141time;
        if (oldValue !== value)
        {
            this._3560141time = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "time", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property playerName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'playerName' moved to '_2095657228playerName'
	 */

    [Bindable(event="propertyChange")]
    public function get playerName():String
    {
        return this._2095657228playerName;
    }

    public function set playerName(value:String):void
    {
    	var oldValue:Object = this._2095657228playerName;
        if (oldValue !== value)
        {
            this._2095657228playerName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "playerName", oldValue, value));
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
