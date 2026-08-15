

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Date;
import Number;
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
	 * generated bindable wrapper for property index (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'index' moved to '_100346066index'
	 */

    [Bindable(event="propertyChange")]
    public function get index():int
    {
        return this._100346066index;
    }

    public function set index(value:int):void
    {
    	var oldValue:Object = this._100346066index;
        if (oldValue !== value)
        {
            this._100346066index = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "index", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isNew (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isNew' moved to '_100473878isNew'
	 */

    [Bindable(event="propertyChange")]
    public function get isNew():int
    {
        return this._100473878isNew;
    }

    public function set isNew(value:int):void
    {
    	var oldValue:Object = this._100473878isNew;
        if (oldValue !== value)
        {
            this._100473878isNew = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isNew", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property startPoints (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'startPoints' moved to '_1956922395startPoints'
	 */

    [Bindable(event="propertyChange")]
    public function get startPoints():String
    {
        return this._1956922395startPoints;
    }

    public function set startPoints(value:String):void
    {
    	var oldValue:Object = this._1956922395startPoints;
        if (oldValue !== value)
        {
            this._1956922395startPoints = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "startPoints", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property blastPoints (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'blastPoints' moved to '_1841398011blastPoints'
	 */

    [Bindable(event="propertyChange")]
    public function get blastPoints():String
    {
        return this._1841398011blastPoints;
    }

    public function set blastPoints(value:String):void
    {
    	var oldValue:Object = this._1841398011blastPoints;
        if (oldValue !== value)
        {
            this._1841398011blastPoints = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "blastPoints", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property flagPoints (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'flagPoints' moved to '_367462255flagPoints'
	 */

    [Bindable(event="propertyChange")]
    public function get flagPoints():String
    {
        return this._367462255flagPoints;
    }

    public function set flagPoints(value:String):void
    {
    	var oldValue:Object = this._367462255flagPoints;
        if (oldValue !== value)
        {
            this._367462255flagPoints = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "flagPoints", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property weaponPoints (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'weaponPoints' moved to '_334759455weaponPoints'
	 */

    [Bindable(event="propertyChange")]
    public function get weaponPoints():String
    {
        return this._334759455weaponPoints;
    }

    public function set weaponPoints(value:String):void
    {
    	var oldValue:Object = this._334759455weaponPoints;
        if (oldValue !== value)
        {
            this._334759455weaponPoints = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "weaponPoints", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property bossItems (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'bossItems' moved to '_1488574547bossItems'
	 */

    [Bindable(event="propertyChange")]
    public function get bossItems():String
    {
        return this._1488574547bossItems;
    }

    public function set bossItems(value:String):void
    {
    	var oldValue:Object = this._1488574547bossItems;
        if (oldValue !== value)
        {
            this._1488574547bossItems = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bossItems", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property supplies (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'supplies' moved to '_1663305267supplies'
	 */

    [Bindable(event="propertyChange")]
    public function get supplies():String
    {
        return this._1663305267supplies;
    }

    public function set supplies(value:String):void
    {
    	var oldValue:Object = this._1663305267supplies;
        if (oldValue !== value)
        {
            this._1663305267supplies = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "supplies", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property zombieInfo (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'zombieInfo' moved to '_393360396zombieInfo'
	 */

    [Bindable(event="propertyChange")]
    public function get zombieInfo():String
    {
        return this._393360396zombieInfo;
    }

    public function set zombieInfo(value:String):void
    {
    	var oldValue:Object = this._393360396zombieInfo;
        if (oldValue !== value)
        {
            this._393360396zombieInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "zombieInfo", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isSelf (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isSelf' moved to '_1180128362isSelf'
	 */

    [Bindable(event="propertyChange")]
    public function get isSelf():int
    {
        return this._1180128362isSelf;
    }

    public function set isSelf(value:int):void
    {
    	var oldValue:Object = this._1180128362isSelf;
        if (oldValue !== value)
        {
            this._1180128362isSelf = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isSelf", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property displayNameCN (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'displayNameCN' moved to '_1970276456displayNameCN'
	 */

    [Bindable(event="propertyChange")]
    public function get displayNameCN():String
    {
        return this._1970276456displayNameCN;
    }

    public function set displayNameCN(value:String):void
    {
    	var oldValue:Object = this._1970276456displayNameCN;
        if (oldValue !== value)
        {
            this._1970276456displayNameCN = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "displayNameCN", oldValue, value));
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
	 * generated bindable wrapper for property levelHorizon (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'levelHorizon' moved to '_1918383753levelHorizon'
	 */

    [Bindable(event="propertyChange")]
    public function get levelHorizon():Number
    {
        return this._1918383753levelHorizon;
    }

    public function set levelHorizon(value:Number):void
    {
    	var oldValue:Object = this._1918383753levelHorizon;
        if (oldValue !== value)
        {
            this._1918383753levelHorizon = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "levelHorizon", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property targetSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'targetSpeed' moved to '_2095779530targetSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get targetSpeed():Number
    {
        return this._2095779530targetSpeed;
    }

    public function set targetSpeed(value:Number):void
    {
    	var oldValue:Object = this._2095779530targetSpeed;
        if (oldValue !== value)
        {
            this._2095779530targetSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "targetSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property lineInfo (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'lineInfo' moved to '_1188327106lineInfo'
	 */

    [Bindable(event="propertyChange")]
    public function get lineInfo():String
    {
        return this._1188327106lineInfo;
    }

    public function set lineInfo(value:String):void
    {
    	var oldValue:Object = this._1188327106lineInfo;
        if (oldValue !== value)
        {
            this._1188327106lineInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lineInfo", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property vehicleInfo (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'vehicleInfo' moved to '_210956794vehicleInfo'
	 */

    [Bindable(event="propertyChange")]
    public function get vehicleInfo():String
    {
        return this._210956794vehicleInfo;
    }

    public function set vehicleInfo(value:String):void
    {
    	var oldValue:Object = this._210956794vehicleInfo;
        if (oldValue !== value)
        {
            this._210956794vehicleInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "vehicleInfo", oldValue, value));
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
	 * generated bindable wrapper for property isVip (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isVip' moved to '_100481683isVip'
	 */

    [Bindable(event="propertyChange")]
    public function get isVip():int
    {
        return this._100481683isVip;
    }

    public function set isVip(value:int):void
    {
    	var oldValue:Object = this._100481683isVip;
        if (oldValue !== value)
        {
            this._100481683isVip = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isVip", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property expAdd (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'expAdd' moved to '_1289198268expAdd'
	 */

    [Bindable(event="propertyChange")]
    public function get expAdd():Number
    {
        return this._1289198268expAdd;
    }

    public function set expAdd(value:Number):void
    {
    	var oldValue:Object = this._1289198268expAdd;
        if (oldValue !== value)
        {
            this._1289198268expAdd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "expAdd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gpAdd (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gpAdd' moved to '_98524920gpAdd'
	 */

    [Bindable(event="propertyChange")]
    public function get gpAdd():Number
    {
        return this._98524920gpAdd;
    }

    public function set gpAdd(value:Number):void
    {
    	var oldValue:Object = this._98524920gpAdd;
        if (oldValue !== value)
        {
            this._98524920gpAdd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gpAdd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property activityStart (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'activityStart' moved to '_1047235949activityStart'
	 */

    [Bindable(event="propertyChange")]
    public function get activityStart():Date
    {
        return this._1047235949activityStart;
    }

    public function set activityStart(value:Date):void
    {
    	var oldValue:Object = this._1047235949activityStart;
        if (oldValue !== value)
        {
            this._1047235949activityStart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "activityStart", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property activityEnd (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'activityEnd' moved to '_917303476activityEnd'
	 */

    [Bindable(event="propertyChange")]
    public function get activityEnd():Date
    {
        return this._917303476activityEnd;
    }

    public function set activityEnd(value:Date):void
    {
    	var oldValue:Object = this._917303476activityEnd;
        if (oldValue !== value)
        {
            this._917303476activityEnd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "activityEnd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property bloodOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'bloodOffset' moved to '_1721617619bloodOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get bloodOffset():int
    {
        return this._1721617619bloodOffset;
    }

    public function set bloodOffset(value:int):void
    {
    	var oldValue:Object = this._1721617619bloodOffset;
        if (oldValue !== value)
        {
            this._1721617619bloodOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "bloodOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isRushGold (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isRushGold' moved to '_172984226isRushGold'
	 */

    [Bindable(event="propertyChange")]
    public function get isRushGold():int
    {
        return this._172984226isRushGold;
    }

    public function set isRushGold(value:int):void
    {
    	var oldValue:Object = this._172984226isRushGold;
        if (oldValue !== value)
        {
            this._172984226isRushGold = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isRushGold", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isMoneyReward (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isMoneyReward' moved to '_1089562757isMoneyReward'
	 */

    [Bindable(event="propertyChange")]
    public function get isMoneyReward():int
    {
        return this._1089562757isMoneyReward;
    }

    public function set isMoneyReward(value:int):void
    {
    	var oldValue:Object = this._1089562757isMoneyReward;
        if (oldValue !== value)
        {
            this._1089562757isMoneyReward = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isMoneyReward", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property rushGlodPoint (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'rushGlodPoint' moved to '_1814636350rushGlodPoint'
	 */

    [Bindable(event="propertyChange")]
    public function get rushGlodPoint():String
    {
        return this._1814636350rushGlodPoint;
    }

    public function set rushGlodPoint(value:String):void
    {
    	var oldValue:Object = this._1814636350rushGlodPoint;
        if (oldValue !== value)
        {
            this._1814636350rushGlodPoint = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "rushGlodPoint", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property num4Team (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'num4Team' moved to '_1929440021num4Team'
	 */

    [Bindable(event="propertyChange")]
    public function get num4Team():int
    {
        return this._1929440021num4Team;
    }

    public function set num4Team(value:int):void
    {
    	var oldValue:Object = this._1929440021num4Team;
        if (oldValue !== value)
        {
            this._1929440021num4Team = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "num4Team", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property TargetSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'TargetSpeed' moved to '_427910890TargetSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set TargetSpeed(value:Number):void
    {
    	var oldValue:Object = this.TargetSpeed;
        if (oldValue !== value)
        {
            this._427910890TargetSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "TargetSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property LevelHorizon (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'LevelHorizon' moved to '_1754063465LevelHorizon'
	 */

    [Bindable(event="propertyChange")]
    public function set LevelHorizon(value:Number):void
    {
    	var oldValue:Object = this.LevelHorizon;
        if (oldValue !== value)
        {
            this._1754063465LevelHorizon = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "LevelHorizon", oldValue, value));
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
