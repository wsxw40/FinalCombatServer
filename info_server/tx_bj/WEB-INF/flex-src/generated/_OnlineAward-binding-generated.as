

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
	 * generated bindable wrapper for property level (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'level' moved to '_102865796level'
	 */

    [Bindable(event="propertyChange")]
    public function get level():int
    {
        return this._102865796level;
    }

    public function set level(value:int):void
    {
    	var oldValue:Object = this._102865796level;
        if (oldValue !== value)
        {
            this._102865796level = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "level", oldValue, value));
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
	 * generated bindable wrapper for property unit (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'unit' moved to '_3594628unit'
	 */

    [Bindable(event="propertyChange")]
    public function get unit():int
    {
        return this._3594628unit;
    }

    public function set unit(value:int):void
    {
    	var oldValue:Object = this._3594628unit;
        if (oldValue !== value)
        {
            this._3594628unit = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "unit", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property unitType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'unitType' moved to '_292652322unitType'
	 */

    [Bindable(event="propertyChange")]
    public function get unitType():int
    {
        return this._292652322unitType;
    }

    public function set unitType(value:int):void
    {
    	var oldValue:Object = this._292652322unitType;
        if (oldValue !== value)
        {
            this._292652322unitType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "unitType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property weight (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'weight' moved to '_791592328weight'
	 */

    [Bindable(event="propertyChange")]
    public function get weight():int
    {
        return this._791592328weight;
    }

    public function set weight(value:int):void
    {
    	var oldValue:Object = this._791592328weight;
        if (oldValue !== value)
        {
            this._791592328weight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "weight", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property music (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'music' moved to '_104263205music'
	 */

    [Bindable(event="propertyChange")]
    public function get music():int
    {
        return this._104263205music;
    }

    public function set music(value:int):void
    {
    	var oldValue:Object = this._104263205music;
        if (oldValue !== value)
        {
            this._104263205music = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "music", oldValue, value));
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
