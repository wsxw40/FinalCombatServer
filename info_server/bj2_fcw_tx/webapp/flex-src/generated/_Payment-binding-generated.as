

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

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
	 * generated bindable wrapper for property cost (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'cost' moved to '_3059661cost'
	 */

    [Bindable(event="propertyChange")]
    public function get cost():int
    {
        return this._3059661cost;
    }

    public function set cost(value:int):void
    {
    	var oldValue:Object = this._3059661cost;
        if (oldValue !== value)
        {
            this._3059661cost = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cost", oldValue, value));
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
	 * generated bindable wrapper for property isChange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isChange' moved to '_687602310isChange'
	 */

    [Bindable(event="propertyChange")]
    public function get isChange():int
    {
        return this._687602310isChange;
    }

    public function set isChange(value:int):void
    {
    	var oldValue:Object = this._687602310isChange;
        if (oldValue !== value)
        {
            this._687602310isChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isShow (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isShow' moved to '_1180125369isShow'
	 */

    [Bindable(event="propertyChange")]
    public function get isShow():int
    {
        return this._1180125369isShow;
    }

    public function set isShow(value:int):void
    {
    	var oldValue:Object = this._1180125369isShow;
        if (oldValue !== value)
        {
            this._1180125369isShow = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isShow", oldValue, value));
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
	 * generated bindable wrapper for property finishPayType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'finishPayType' moved to '_1593274351finishPayType'
	 */

    [Bindable(event="propertyChange")]
    public function get finishPayType():int
    {
        return this._1593274351finishPayType;
    }

    public function set finishPayType(value:int):void
    {
    	var oldValue:Object = this._1593274351finishPayType;
        if (oldValue !== value)
        {
            this._1593274351finishPayType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "finishPayType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property finishCost (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'finishCost' moved to '_1151020800finishCost'
	 */

    [Bindable(event="propertyChange")]
    public function get finishCost():int
    {
        return this._1151020800finishCost;
    }

    public function set finishCost(value:int):void
    {
    	var oldValue:Object = this._1151020800finishCost;
        if (oldValue !== value)
        {
            this._1151020800finishCost = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "finishCost", oldValue, value));
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
