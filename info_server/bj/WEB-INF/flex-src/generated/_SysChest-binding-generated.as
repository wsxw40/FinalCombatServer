

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
	 * generated bindable wrapper for property sysItemId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'sysItemId' moved to '_88299333sysItemId'
	 */

    [Bindable(event="propertyChange")]
    public function get sysItemId():int
    {
        return this._88299333sysItemId;
    }

    public function set sysItemId(value:int):void
    {
    	var oldValue:Object = this._88299333sysItemId;
        if (oldValue !== value)
        {
            this._88299333sysItemId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "sysItemId", oldValue, value));
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
	 * generated bindable wrapper for property weight1 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'weight1' moved to '_1230441657weight1'
	 */

    [Bindable(event="propertyChange")]
    public function get weight1():int
    {
        return this._1230441657weight1;
    }

    public function set weight1(value:int):void
    {
    	var oldValue:Object = this._1230441657weight1;
        if (oldValue !== value)
        {
            this._1230441657weight1 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "weight1", oldValue, value));
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
	 * generated bindable wrapper for property useType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'useType' moved to '_148001439useType'
	 */

    [Bindable(event="propertyChange")]
    public function get useType():int
    {
        return this._148001439useType;
    }

    public function set useType(value:int):void
    {
    	var oldValue:Object = this._148001439useType;
        if (oldValue !== value)
        {
            this._148001439useType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "useType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isNotice (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isNotice' moved to '_365655902isNotice'
	 */

    [Bindable(event="propertyChange")]
    public function get isNotice():int
    {
        return this._365655902isNotice;
    }

    public function set isNotice(value:int):void
    {
    	var oldValue:Object = this._365655902isNotice;
        if (oldValue !== value)
        {
            this._365655902isNotice = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isNotice", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property price (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'price' moved to '_106934601price'
	 */

    [Bindable(event="propertyChange")]
    public function get price():int
    {
        return this._106934601price;
    }

    public function set price(value:int):void
    {
    	var oldValue:Object = this._106934601price;
        if (oldValue !== value)
        {
            this._106934601price = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "price", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property chipPrice (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'chipPrice' moved to '_1768541chipPrice'
	 */

    [Bindable(event="propertyChange")]
    public function get chipPrice():int
    {
        return this._1768541chipPrice;
    }

    public function set chipPrice(value:int):void
    {
    	var oldValue:Object = this._1768541chipPrice;
        if (oldValue !== value)
        {
            this._1768541chipPrice = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "chipPrice", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isDeleted (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isDeleted' moved to '_970684303isDeleted'
	 */

    [Bindable(event="propertyChange")]
    public function get isDeleted():int
    {
        return this._970684303isDeleted;
    }

    public function set isDeleted(value:int):void
    {
    	var oldValue:Object = this._970684303isDeleted;
        if (oldValue !== value)
        {
            this._970684303isDeleted = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isDeleted", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property sysItemName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'sysItemName' moved to '_1043836459sysItemName'
	 */

    [Bindable(event="propertyChange")]
    public function get sysItemName():String
    {
        return this._1043836459sysItemName;
    }

    public function set sysItemName(value:String):void
    {
    	var oldValue:Object = this._1043836459sysItemName;
        if (oldValue !== value)
        {
            this._1043836459sysItemName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "sysItemName", oldValue, value));
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
