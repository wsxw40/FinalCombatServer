

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
	 * generated bindable wrapper for property key (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'key' moved to '_106079key'
	 */

    [Bindable(event="propertyChange")]
    public function get key():String
    {
        return this._106079key;
    }

    public function set key(value:String):void
    {
    	var oldValue:Object = this._106079key;
        if (oldValue !== value)
        {
            this._106079key = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "key", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property value (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'value' moved to '_111972721value'
	 */

    [Bindable(event="propertyChange")]
    public function get value():String
    {
        return this._111972721value;
    }

    public function set value(value:String):void
    {
    	var oldValue:Object = this._111972721value;
        if (oldValue !== value)
        {
            this._111972721value = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "value", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property confName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'confName' moved to '_580949041confName'
	 */

    [Bindable(event="propertyChange")]
    public function get confName():String
    {
        return this._580949041confName;
    }

    public function set confName(value:String):void
    {
    	var oldValue:Object = this._580949041confName;
        if (oldValue !== value)
        {
            this._580949041confName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "confName", oldValue, value));
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
