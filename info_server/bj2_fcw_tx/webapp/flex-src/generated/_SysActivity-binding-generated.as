

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
	 * generated bindable wrapper for property startTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'startTime' moved to '_2129294769startTime'
	 */

    [Bindable(event="propertyChange")]
    public function get startTime():Date
    {
        return this._2129294769startTime;
    }

    public function set startTime(value:Date):void
    {
    	var oldValue:Object = this._2129294769startTime;
        if (oldValue !== value)
        {
            this._2129294769startTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property startTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'startTimeStr' moved to '_1448385182startTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get startTimeStr():String
    {
        return this._1448385182startTimeStr;
    }

    public function set startTimeStr(value:String):void
    {
    	var oldValue:Object = this._1448385182startTimeStr;
        if (oldValue !== value)
        {
            this._1448385182startTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property endTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'endTime' moved to '_1607243192endTime'
	 */

    [Bindable(event="propertyChange")]
    public function get endTime():Date
    {
        return this._1607243192endTime;
    }

    public function set endTime(value:Date):void
    {
    	var oldValue:Object = this._1607243192endTime;
        if (oldValue !== value)
        {
            this._1607243192endTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "endTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property endTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'endTimeStr' moved to '_1086433591endTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get endTimeStr():String
    {
        return this._1086433591endTimeStr;
    }

    public function set endTimeStr(value:String):void
    {
    	var oldValue:Object = this._1086433591endTimeStr;
        if (oldValue !== value)
        {
            this._1086433591endTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "endTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property value (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'value' moved to '_111972721value'
	 */

    [Bindable(event="propertyChange")]
    public function get value():int
    {
        return this._111972721value;
    }

    public function set value(value:int):void
    {
    	var oldValue:Object = this._111972721value;
        if (oldValue !== value)
        {
            this._111972721value = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "value", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property targetNum (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'targetNum' moved to '_815592395targetNum'
	 */

    [Bindable(event="propertyChange")]
    public function get targetNum():int
    {
        return this._815592395targetNum;
    }

    public function set targetNum(value:int):void
    {
    	var oldValue:Object = this._815592395targetNum;
        if (oldValue !== value)
        {
            this._815592395targetNum = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "targetNum", oldValue, value));
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
	 * generated bindable wrapper for property theSwitch (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'theSwitch' moved to '_709719291theSwitch'
	 */

    [Bindable(event="propertyChange")]
    public function get theSwitch():String
    {
        return this._709719291theSwitch;
    }

    public function set theSwitch(value:String):void
    {
    	var oldValue:Object = this._709719291theSwitch;
        if (oldValue !== value)
        {
            this._709719291theSwitch = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "theSwitch", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property items (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'items' moved to '_100526016items'
	 */

    [Bindable(event="propertyChange")]
    public function get items():String
    {
        return this._100526016items;
    }

    public function set items(value:String):void
    {
    	var oldValue:Object = this._100526016items;
        if (oldValue !== value)
        {
            this._100526016items = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "items", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property path (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'path' moved to '_3433509path'
	 */

    [Bindable(event="propertyChange")]
    public function get path():String
    {
        return this._3433509path;
    }

    public function set path(value:String):void
    {
    	var oldValue:Object = this._3433509path;
        if (oldValue !== value)
        {
            this._3433509path = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "path", oldValue, value));
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
	 * generated bindable wrapper for property chracter_id (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'chracter_id' moved to '_1976705096chracter_id'
	 */

    [Bindable(event="propertyChange")]
    public function get chracter_id():int
    {
        return this._1976705096chracter_id;
    }

    public function set chracter_id(value:int):void
    {
    	var oldValue:Object = this._1976705096chracter_id;
        if (oldValue !== value)
        {
            this._1976705096chracter_id = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "chracter_id", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property achievement_action (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'achievement_action' moved to '_463292742achievement_action'
	 */

    [Bindable(event="propertyChange")]
    public function get achievement_action():int
    {
        return this._463292742achievement_action;
    }

    public function set achievement_action(value:int):void
    {
    	var oldValue:Object = this._463292742achievement_action;
        if (oldValue !== value)
        {
            this._463292742achievement_action = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "achievement_action", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property activityName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'activityName' moved to '_1628619322activityName'
	 */

    [Bindable(event="propertyChange")]
    public function get activityName():String
    {
        return this._1628619322activityName;
    }

    public function set activityName(value:String):void
    {
    	var oldValue:Object = this._1628619322activityName;
        if (oldValue !== value)
        {
            this._1628619322activityName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "activityName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property itemsStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'itemsStr' moved to '_1178420817itemsStr'
	 */

    [Bindable(event="propertyChange")]
    public function get itemsStr():String
    {
        return this._1178420817itemsStr;
    }

    public function set itemsStr(value:String):void
    {
    	var oldValue:Object = this._1178420817itemsStr;
        if (oldValue !== value)
        {
            this._1178420817itemsStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "itemsStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property description (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'description' moved to '_1724546052description'
	 */

    [Bindable(event="propertyChange")]
    public function get description():String
    {
        return this._1724546052description;
    }

    public function set description(value:String):void
    {
    	var oldValue:Object = this._1724546052description;
        if (oldValue !== value)
        {
            this._1724546052description = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "description", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property withAward (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'withAward' moved to '_885089591withAward'
	 */

    [Bindable(event="propertyChange")]
    public function get withAward():int
    {
        return this._885089591withAward;
    }

    public function set withAward(value:int):void
    {
    	var oldValue:Object = this._885089591withAward;
        if (oldValue !== value)
        {
            this._885089591withAward = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "withAward", oldValue, value));
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
	 * generated bindable wrapper for property backup (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'backup' moved to '_1396673086backup'
	 */

    [Bindable(event="propertyChange")]
    public function get backup():String
    {
        return this._1396673086backup;
    }

    public function set backup(value:String):void
    {
    	var oldValue:Object = this._1396673086backup;
        if (oldValue !== value)
        {
            this._1396673086backup = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "backup", oldValue, value));
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
