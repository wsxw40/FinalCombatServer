

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
	 * generated bindable wrapper for property maxOnline (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'maxOnline' moved to '_697258793maxOnline'
	 */

    [Bindable(event="propertyChange")]
    public function get maxOnline():int
    {
        return this._697258793maxOnline;
    }

    public function set maxOnline(value:int):void
    {
    	var oldValue:Object = this._697258793maxOnline;
        if (oldValue !== value)
        {
            this._697258793maxOnline = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "maxOnline", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property max (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'max' moved to '_107876max'
	 */

    [Bindable(event="propertyChange")]
    public function get max():int
    {
        return this._107876max;
    }

    public function set max(value:int):void
    {
    	var oldValue:Object = this._107876max;
        if (oldValue !== value)
        {
            this._107876max = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "max", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property min (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'min' moved to '_108114min'
	 */

    [Bindable(event="propertyChange")]
    public function get min():int
    {
        return this._108114min;
    }

    public function set min(value:int):void
    {
    	var oldValue:Object = this._108114min;
        if (oldValue !== value)
        {
            this._108114min = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "min", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property serverId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'serverId' moved to '_1379103678serverId'
	 */

    [Bindable(event="propertyChange")]
    public function get serverId():int
    {
        return this._1379103678serverId;
    }

    public function set serverId(value:int):void
    {
    	var oldValue:Object = this._1379103678serverId;
        if (oldValue !== value)
        {
            this._1379103678serverId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "serverId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property channelId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'channelId' moved to '_1461735806channelId'
	 */

    [Bindable(event="propertyChange")]
    public function get channelId():int
    {
        return this._1461735806channelId;
    }

    public function set channelId(value:int):void
    {
    	var oldValue:Object = this._1461735806channelId;
        if (oldValue !== value)
        {
            this._1461735806channelId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "channelId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isTcp (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isTcp' moved to '_100479575isTcp'
	 */

    [Bindable(event="propertyChange")]
    public function get isTcp():int
    {
        return this._100479575isTcp;
    }

    public function set isTcp(value:int):void
    {
    	var oldValue:Object = this._100479575isTcp;
        if (oldValue !== value)
        {
            this._100479575isTcp = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isTcp", oldValue, value));
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
	 * generated bindable wrapper for property maxTeam (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'maxTeam' moved to '_844106209maxTeam'
	 */

    [Bindable(event="propertyChange")]
    public function get maxTeam():int
    {
        return this._844106209maxTeam;
    }

    public function set maxTeam(value:int):void
    {
    	var oldValue:Object = this._844106209maxTeam;
        if (oldValue !== value)
        {
            this._844106209maxTeam = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "maxTeam", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property minTeam (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'minTeam' moved to '_1063904207minTeam'
	 */

    [Bindable(event="propertyChange")]
    public function get minTeam():int
    {
        return this._1063904207minTeam;
    }

    public function set minTeam(value:int):void
    {
    	var oldValue:Object = this._1063904207minTeam;
        if (oldValue !== value)
        {
            this._1063904207minTeam = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "minTeam", oldValue, value));
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
