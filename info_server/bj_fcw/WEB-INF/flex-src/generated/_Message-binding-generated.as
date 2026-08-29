

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
	 * generated bindable wrapper for property receiverId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'receiverId' moved to '_209269610receiverId'
	 */

    [Bindable(event="propertyChange")]
    public function get receiverId():int
    {
        return this._209269610receiverId;
    }

    public function set receiverId(value:int):void
    {
    	var oldValue:Object = this._209269610receiverId;
        if (oldValue !== value)
        {
            this._209269610receiverId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "receiverId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property subject (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'subject' moved to '_1867885268subject'
	 */

    [Bindable(event="propertyChange")]
    public function get subject():String
    {
        return this._1867885268subject;
    }

    public function set subject(value:String):void
    {
    	var oldValue:Object = this._1867885268subject;
        if (oldValue !== value)
        {
            this._1867885268subject = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "subject", oldValue, value));
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
	 * generated bindable wrapper for property createdTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createdTimeStr' moved to '_603985892createdTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get createdTimeStr():String
    {
        return this._603985892createdTimeStr;
    }

    public function set createdTimeStr(value:String):void
    {
    	var oldValue:Object = this._603985892createdTimeStr;
        if (oldValue !== value)
        {
            this._603985892createdTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createdTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property open (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'open' moved to '_3417674open'
	 */

    [Bindable(event="propertyChange")]
    public function get open():String
    {
        return this._3417674open;
    }

    public function set open(value:String):void
    {
    	var oldValue:Object = this._3417674open;
        if (oldValue !== value)
        {
            this._3417674open = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "open", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property deleted (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'deleted' moved to '_1550463001deleted'
	 */

    [Bindable(event="propertyChange")]
    public function get deleted():String
    {
        return this._1550463001deleted;
    }

    public function set deleted(value:String):void
    {
    	var oldValue:Object = this._1550463001deleted;
        if (oldValue !== value)
        {
            this._1550463001deleted = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "deleted", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isAttached (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isAttached' moved to '_254532370isAttached'
	 */

    [Bindable(event="propertyChange")]
    public function get isAttached():String
    {
        return this._254532370isAttached;
    }

    public function set isAttached(value:String):void
    {
    	var oldValue:Object = this._254532370isAttached;
        if (oldValue !== value)
        {
            this._254532370isAttached = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isAttached", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createdTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createdTime' moved to '_489909803createdTime'
	 */

    [Bindable(event="propertyChange")]
    public function get createdTime():Date
    {
        return this._489909803createdTime;
    }

    public function set createdTime(value:Date):void
    {
    	var oldValue:Object = this._489909803createdTime;
        if (oldValue !== value)
        {
            this._489909803createdTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createdTime", oldValue, value));
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
