

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
	 * generated bindable wrapper for property isDefault (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isDefault' moved to '_965025207isDefault'
	 */

    [Bindable(event="propertyChange")]
    public function get isDefault():String
    {
        return this._965025207isDefault;
    }

    public function set isDefault(value:String):void
    {
    	var oldValue:Object = this._965025207isDefault;
        if (oldValue !== value)
        {
            this._965025207isDefault = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isDefault", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property pack (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'pack' moved to '_3432985pack'
	 */

    [Bindable(event="propertyChange")]
    public function get pack():Boolean
    {
        return this._3432985pack;
    }

    public function set pack(value:Boolean):void
    {
    	var oldValue:Object = this._3432985pack;
        if (oldValue !== value)
        {
            this._3432985pack = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "pack", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property itemDisplayName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'itemDisplayName' moved to '_1567123802itemDisplayName'
	 */

    [Bindable(event="propertyChange")]
    public function get itemDisplayName():String
    {
        return this._1567123802itemDisplayName;
    }

    public function set itemDisplayName(value:String):void
    {
    	var oldValue:Object = this._1567123802itemDisplayName;
        if (oldValue !== value)
        {
            this._1567123802itemDisplayName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "itemDisplayName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property validDate (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'validDate' moved to '_1110665782validDate'
	 */

    [Bindable(event="propertyChange")]
    public function get validDate():Date
    {
        return this._1110665782validDate;
    }

    public function set validDate(value:Date):void
    {
    	var oldValue:Object = this._1110665782validDate;
        if (oldValue !== value)
        {
            this._1110665782validDate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "validDate", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property expireDate (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'expireDate' moved to '_835208851expireDate'
	 */

    [Bindable(event="propertyChange")]
    public function get expireDate():Date
    {
        return this._835208851expireDate;
    }

    public function set expireDate(value:Date):void
    {
    	var oldValue:Object = this._835208851expireDate;
        if (oldValue !== value)
        {
            this._835208851expireDate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expireDate", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property validDateStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'validDateStr' moved to '_583820295validDateStr'
	 */

    [Bindable(event="propertyChange")]
    public function get validDateStr():String
    {
        return this._583820295validDateStr;
    }

    public function set validDateStr(value:String):void
    {
    	var oldValue:Object = this._583820295validDateStr;
        if (oldValue !== value)
        {
            this._583820295validDateStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "validDateStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property expireDateStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'expireDateStr' moved to '_961250940expireDateStr'
	 */

    [Bindable(event="propertyChange")]
    public function get expireDateStr():String
    {
        return this._961250940expireDateStr;
    }

    public function set expireDateStr(value:String):void
    {
    	var oldValue:Object = this._961250940expireDateStr;
        if (oldValue !== value)
        {
            this._961250940expireDateStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expireDateStr", oldValue, value));
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
	 * generated bindable wrapper for property playerItemUnitType (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'playerItemUnitType' moved to '_1763654802playerItemUnitType'
	 */

    [Bindable(event="propertyChange")]
    public function get playerItemUnitType():int
    {
        return this._1763654802playerItemUnitType;
    }

    public function set playerItemUnitType(value:int):void
    {
    	var oldValue:Object = this._1763654802playerItemUnitType;
        if (oldValue !== value)
        {
            this._1763654802playerItemUnitType = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "playerItemUnitType", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property quantity (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'quantity' moved to '_1285004149quantity'
	 */

    [Bindable(event="propertyChange")]
    public function get quantity():int
    {
        return this._1285004149quantity;
    }

    public function set quantity(value:int):void
    {
    	var oldValue:Object = this._1285004149quantity;
        if (oldValue !== value)
        {
            this._1285004149quantity = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "quantity", oldValue, value));
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
	 * generated bindable wrapper for property gunProperty2 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty2' moved to '_1760416669gunProperty2'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty2():String
    {
        return this._1760416669gunProperty2;
    }

    public function set gunProperty2(value:String):void
    {
    	var oldValue:Object = this._1760416669gunProperty2;
        if (oldValue !== value)
        {
            this._1760416669gunProperty2 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty2", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gunProperty3 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty3' moved to '_1760416670gunProperty3'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty3():String
    {
        return this._1760416670gunProperty3;
    }

    public function set gunProperty3(value:String):void
    {
    	var oldValue:Object = this._1760416670gunProperty3;
        if (oldValue !== value)
        {
            this._1760416670gunProperty3 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty3", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gunProperty4 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty4' moved to '_1760416671gunProperty4'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty4():String
    {
        return this._1760416671gunProperty4;
    }

    public function set gunProperty4(value:String):void
    {
    	var oldValue:Object = this._1760416671gunProperty4;
        if (oldValue !== value)
        {
            this._1760416671gunProperty4 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty4", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gunProperty5 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty5' moved to '_1760416672gunProperty5'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty5():String
    {
        return this._1760416672gunProperty5;
    }

    public function set gunProperty5(value:String):void
    {
    	var oldValue:Object = this._1760416672gunProperty5;
        if (oldValue !== value)
        {
            this._1760416672gunProperty5 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty5", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gunProperty6 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty6' moved to '_1760416673gunProperty6'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty6():String
    {
        return this._1760416673gunProperty6;
    }

    public function set gunProperty6(value:String):void
    {
    	var oldValue:Object = this._1760416673gunProperty6;
        if (oldValue !== value)
        {
            this._1760416673gunProperty6 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty6", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gunProperty7 (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gunProperty7' moved to '_1760416674gunProperty7'
	 */

    [Bindable(event="propertyChange")]
    public function get gunProperty7():String
    {
        return this._1760416674gunProperty7;
    }

    public function set gunProperty7(value:String):void
    {
    	var oldValue:Object = this._1760416674gunProperty7;
        if (oldValue !== value)
        {
            this._1760416674gunProperty7 = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gunProperty7", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isUpdate (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isUpdate' moved to '_164812141isUpdate'
	 */

    [Bindable(event="propertyChange")]
    public function get isUpdate():String
    {
        return this._164812141isUpdate;
    }

    public function set isUpdate(value:String):void
    {
    	var oldValue:Object = this._164812141isUpdate;
        if (oldValue !== value)
        {
            this._164812141isUpdate = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isUpdate", oldValue, value));
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
