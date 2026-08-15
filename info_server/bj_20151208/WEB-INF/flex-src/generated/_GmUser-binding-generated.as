

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
	 * generated bindable wrapper for property userName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'userName' moved to '_266666762userName'
	 */

    [Bindable(event="propertyChange")]
    public function get userName():String
    {
        return this._266666762userName;
    }

    public function set userName(value:String):void
    {
    	var oldValue:Object = this._266666762userName;
        if (oldValue !== value)
        {
            this._266666762userName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "userName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property password (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'password' moved to '_1216985755password'
	 */

    [Bindable(event="propertyChange")]
    public function get password():String
    {
        return this._1216985755password;
    }

    public function set password(value:String):void
    {
    	var oldValue:Object = this._1216985755password;
        if (oldValue !== value)
        {
            this._1216985755password = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "password", oldValue, value));
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
	 * generated bindable wrapper for property creatorId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'creatorId' moved to '_598683239creatorId'
	 */

    [Bindable(event="propertyChange")]
    public function get creatorId():int
    {
        return this._598683239creatorId;
    }

    public function set creatorId(value:int):void
    {
    	var oldValue:Object = this._598683239creatorId;
        if (oldValue !== value)
        {
            this._598683239creatorId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "creatorId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property creatorName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'creatorName' moved to '_190875433creatorName'
	 */

    [Bindable(event="propertyChange")]
    public function get creatorName():String
    {
        return this._190875433creatorName;
    }

    public function set creatorName(value:String):void
    {
    	var oldValue:Object = this._190875433creatorName;
        if (oldValue !== value)
        {
            this._190875433creatorName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "creatorName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property groups (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'groups' moved to '_1237460524groups'
	 */

    [Bindable(event="propertyChange")]
    public function get groups():String
    {
        return this._1237460524groups;
    }

    public function set groups(value:String):void
    {
    	var oldValue:Object = this._1237460524groups;
        if (oldValue !== value)
        {
            this._1237460524groups = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "groups", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property groupIds (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'groupIds' moved to '_506340281groupIds'
	 */

    [Bindable(event="propertyChange")]
    public function get groupIds():String
    {
        return this._506340281groupIds;
    }

    public function set groupIds(value:String):void
    {
    	var oldValue:Object = this._506340281groupIds;
        if (oldValue !== value)
        {
            this._506340281groupIds = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "groupIds", oldValue, value));
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
