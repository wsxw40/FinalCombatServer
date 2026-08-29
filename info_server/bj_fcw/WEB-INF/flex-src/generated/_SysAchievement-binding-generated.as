

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import String;
import int;
import Boolean;

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
	 * generated bindable wrapper for property name (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'name' moved to '_3373707name'
	 */

    [Bindable(event="propertyChange")]
    public function get name():String
    {
        return this._3373707name;
    }

    public function set name(value:String):void
    {
    	var oldValue:Object = this._3373707name;
        if (oldValue !== value)
        {
            this._3373707name = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "name", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property descriptionCN (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'descriptionCN' moved to '_568622439descriptionCN'
	 */

    [Bindable(event="propertyChange")]
    public function get descriptionCN():String
    {
        return this._568622439descriptionCN;
    }

    public function set descriptionCN(value:String):void
    {
    	var oldValue:Object = this._568622439descriptionCN;
        if (oldValue !== value)
        {
            this._568622439descriptionCN = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "descriptionCN", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property title (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'title' moved to '_110371416title'
	 */

    [Bindable(event="propertyChange")]
    public function get title():String
    {
        return this._110371416title;
    }

    public function set title(value:String):void
    {
    	var oldValue:Object = this._110371416title;
        if (oldValue !== value)
        {
            this._110371416title = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "title", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property number (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'number' moved to '_1034364087number'
	 */

    [Bindable(event="propertyChange")]
    public function get number():int
    {
        return this._1034364087number;
    }

    public function set number(value:int):void
    {
    	var oldValue:Object = this._1034364087number;
        if (oldValue !== value)
        {
            this._1034364087number = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "number", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property action (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'action' moved to '_1422950858action'
	 */

    [Bindable(event="propertyChange")]
    public function get action():int
    {
        return this._1422950858action;
    }

    public function set action(value:int):void
    {
    	var oldValue:Object = this._1422950858action;
        if (oldValue !== value)
        {
            this._1422950858action = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "action", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property characterId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'characterId' moved to '_46555612characterId'
	 */

    [Bindable(event="propertyChange")]
    public function get characterId():int
    {
        return this._46555612characterId;
    }

    public function set characterId(value:int):void
    {
    	var oldValue:Object = this._46555612characterId;
        if (oldValue !== value)
        {
            this._46555612characterId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "characterId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property selected (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'selected' moved to '_1191572123selected'
	 */

    [Bindable(event="propertyChange")]
    public function get selected():Boolean
    {
        return this._1191572123selected;
    }

    public function set selected(value:Boolean):void
    {
    	var oldValue:Object = this._1191572123selected;
        if (oldValue !== value)
        {
            this._1191572123selected = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "selected", oldValue, value));
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
