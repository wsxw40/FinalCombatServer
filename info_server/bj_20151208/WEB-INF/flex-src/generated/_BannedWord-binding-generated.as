

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
	 * generated bindable wrapper for property bannedWord (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'bannedWord' moved to '_1044513976bannedWord'
	 */

    [Bindable(event="propertyChange")]
    public function get bannedWord():String
    {
        return this._1044513976bannedWord;
    }

    public function set bannedWord(value:String):void
    {
    	var oldValue:Object = this._1044513976bannedWord;
        if (oldValue !== value)
        {
            this._1044513976bannedWord = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bannedWord", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isDeleted (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isDeleted' moved to '_970684303isDeleted'
	 */

    [Bindable(event="propertyChange")]
    public function get isDeleted():String
    {
        return this._970684303isDeleted;
    }

    public function set isDeleted(value:String):void
    {
    	var oldValue:Object = this._970684303isDeleted;
        if (oldValue !== value)
        {
            this._970684303isDeleted = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isDeleted", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property includeInWord (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'includeInWord' moved to '_2119848087includeInWord'
	 */

    [Bindable(event="propertyChange")]
    public function get includeInWord():String
    {
        return this._2119848087includeInWord;
    }

    public function set includeInWord(value:String):void
    {
    	var oldValue:Object = this._2119848087includeInWord;
        if (oldValue !== value)
        {
            this._2119848087includeInWord = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "includeInWord", oldValue, value));
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
