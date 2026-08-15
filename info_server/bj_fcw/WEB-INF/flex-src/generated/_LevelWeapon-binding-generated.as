

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Number;
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
	 * generated bindable wrapper for property sysLevelId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'sysLevelId' moved to '_488711118sysLevelId'
	 */

    [Bindable(event="propertyChange")]
    public function get sysLevelId():int
    {
        return this._488711118sysLevelId;
    }

    public function set sysLevelId(value:int):void
    {
    	var oldValue:Object = this._488711118sysLevelId;
        if (oldValue !== value)
        {
            this._488711118sysLevelId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "sysLevelId", oldValue, value));
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
	 * generated bindable wrapper for property displayName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'displayName' moved to '_1714148973displayName'
	 */

    [Bindable(event="propertyChange")]
    public function get displayName():String
    {
        return this._1714148973displayName;
    }

    public function set displayName(value:String):void
    {
    	var oldValue:Object = this._1714148973displayName;
        if (oldValue !== value)
        {
            this._1714148973displayName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "displayName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property resourceStable (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'resourceStable' moved to '_40732439resourceStable'
	 */

    [Bindable(event="propertyChange")]
    public function get resourceStable():String
    {
        return this._40732439resourceStable;
    }

    public function set resourceStable(value:String):void
    {
    	var oldValue:Object = this._40732439resourceStable;
        if (oldValue !== value)
        {
            this._40732439resourceStable = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "resourceStable", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property resourceChangeable (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'resourceChangeable' moved to '_955402312resourceChangeable'
	 */

    [Bindable(event="propertyChange")]
    public function get resourceChangeable():String
    {
        return this._955402312resourceChangeable;
    }

    public function set resourceChangeable(value:String):void
    {
    	var oldValue:Object = this._955402312resourceChangeable;
        if (oldValue !== value)
        {
            this._955402312resourceChangeable = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "resourceChangeable", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WId' moved to '_85970WId'
	 */

    [Bindable(event="propertyChange")]
    public function get WId():int
    {
        return this._85970WId;
    }

    public function set WId(value:int):void
    {
    	var oldValue:Object = this._85970WId;
        if (oldValue !== value)
        {
            this._85970WId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WChangeInTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WChangeInTime' moved to '_23052121WChangeInTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WChangeInTime():Number
    {
        return this._23052121WChangeInTime;
    }

    public function set WChangeInTime(value:Number):void
    {
    	var oldValue:Object = this._23052121WChangeInTime;
        if (oldValue !== value)
        {
            this._23052121WChangeInTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WChangeInTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveSpeedOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveSpeedOffset' moved to '_1876979154WMoveSpeedOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveSpeedOffset():Number
    {
        return this._1876979154WMoveSpeedOffset;
    }

    public function set WMoveSpeedOffset(value:Number):void
    {
    	var oldValue:Object = this._1876979154WMoveSpeedOffset;
        if (oldValue !== value)
        {
            this._1876979154WMoveSpeedOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveSpeedOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrossOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrossOffset' moved to '_1580559332WCrossOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrossOffset():Number
    {
        return this._1580559332WCrossOffset;
    }

    public function set WCrossOffset(value:Number):void
    {
    	var oldValue:Object = this._1580559332WCrossOffset;
        if (oldValue !== value)
        {
            this._1580559332WCrossOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrossOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAccuracyDivisor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAccuracyDivisor' moved to '_1484853234WAccuracyDivisor'
	 */

    [Bindable(event="propertyChange")]
    public function get WAccuracyDivisor():int
    {
        return this._1484853234WAccuracyDivisor;
    }

    public function set WAccuracyDivisor(value:int):void
    {
    	var oldValue:Object = this._1484853234WAccuracyDivisor;
        if (oldValue !== value)
        {
            this._1484853234WAccuracyDivisor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAccuracyDivisor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAccuracyOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAccuracyOffset' moved to '_706047197WAccuracyOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WAccuracyOffset():Number
    {
        return this._706047197WAccuracyOffset;
    }

    public function set WAccuracyOffset(value:Number):void
    {
    	var oldValue:Object = this._706047197WAccuracyOffset;
        if (oldValue !== value)
        {
            this._706047197WAccuracyOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAccuracyOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMaxInaccuracy (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMaxInaccuracy' moved to '_345348747WMaxInaccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function get WMaxInaccuracy():Number
    {
        return this._345348747WMaxInaccuracy;
    }

    public function set WMaxInaccuracy(value:Number):void
    {
    	var oldValue:Object = this._345348747WMaxInaccuracy;
        if (oldValue !== value)
        {
            this._345348747WMaxInaccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMaxInaccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WPenetration (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WPenetration' moved to '_143154572WPenetration'
	 */

    [Bindable(event="propertyChange")]
    public function get WPenetration():int
    {
        return this._143154572WPenetration;
    }

    public function set WPenetration(value:int):void
    {
    	var oldValue:Object = this._143154572WPenetration;
        if (oldValue !== value)
        {
            this._143154572WPenetration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WPenetration", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WDamage (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WDamage' moved to '_1943116454WDamage'
	 */

    [Bindable(event="propertyChange")]
    public function get WDamage():int
    {
        return this._1943116454WDamage;
    }

    public function set WDamage(value:int):void
    {
    	var oldValue:Object = this._1943116454WDamage;
        if (oldValue !== value)
        {
            this._1943116454WDamage = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WDamage", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WRangeModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WRangeModifier' moved to '_1264400355WRangeModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WRangeModifier():Number
    {
        return this._1264400355WRangeModifier;
    }

    public function set WRangeModifier(value:Number):void
    {
    	var oldValue:Object = this._1264400355WRangeModifier;
        if (oldValue !== value)
        {
            this._1264400355WRangeModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WRangeModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFireTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFireTime' moved to '_1166135194WFireTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WFireTime():Number
    {
        return this._1166135194WFireTime;
    }

    public function set WFireTime(value:Number):void
    {
    	var oldValue:Object = this._1166135194WFireTime;
        if (oldValue !== value)
        {
            this._1166135194WFireTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFireTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WReloadTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WReloadTime' moved to '_968855619WReloadTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WReloadTime():Number
    {
        return this._968855619WReloadTime;
    }

    public function set WReloadTime(value:Number):void
    {
    	var oldValue:Object = this._968855619WReloadTime;
        if (oldValue !== value)
        {
            this._968855619WReloadTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WReloadTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WZoomTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WZoomTime' moved to '_1680808265WZoomTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WZoomTime():Number
    {
        return this._1680808265WZoomTime;
    }

    public function set WZoomTime(value:Number):void
    {
    	var oldValue:Object = this._1680808265WZoomTime;
        if (oldValue !== value)
        {
            this._1680808265WZoomTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WZoomTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WZoomValue (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WZoomValue' moved to '_563840313WZoomValue'
	 */

    [Bindable(event="propertyChange")]
    public function get WZoomValue():Number
    {
        return this._563840313WZoomValue;
    }

    public function set WZoomValue(value:Number):void
    {
    	var oldValue:Object = this._563840313WZoomValue;
        if (oldValue !== value)
        {
            this._563840313WZoomValue = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WZoomValue", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAmmoOneClip (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAmmoOneClip' moved to '_1360252079WAmmoOneClip'
	 */

    [Bindable(event="propertyChange")]
    public function get WAmmoOneClip():int
    {
        return this._1360252079WAmmoOneClip;
    }

    public function set WAmmoOneClip(value:int):void
    {
    	var oldValue:Object = this._1360252079WAmmoOneClip;
        if (oldValue !== value)
        {
            this._1360252079WAmmoOneClip = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAmmoOneClip", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAmmoCount (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAmmoCount' moved to '_747324490WAmmoCount'
	 */

    [Bindable(event="propertyChange")]
    public function get WAmmoCount():int
    {
        return this._747324490WAmmoCount;
    }

    public function set WAmmoCount(value:int):void
    {
    	var oldValue:Object = this._747324490WAmmoCount;
        if (oldValue !== value)
        {
            this._747324490WAmmoCount = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAmmoCount", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAutoFire (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAutoFire' moved to '_1126763012WAutoFire'
	 */

    [Bindable(event="propertyChange")]
    public function get WAutoFire():int
    {
        return this._1126763012WAutoFire;
    }

    public function set WAutoFire(value:int):void
    {
    	var oldValue:Object = this._1126763012WAutoFire;
        if (oldValue !== value)
        {
            this._1126763012WAutoFire = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAutoFire", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WTimeToIdle (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WTimeToIdle' moved to '_1093589459WTimeToIdle'
	 */

    [Bindable(event="propertyChange")]
    public function get WTimeToIdle():Number
    {
        return this._1093589459WTimeToIdle;
    }

    public function set WTimeToIdle(value:Number):void
    {
    	var oldValue:Object = this._1093589459WTimeToIdle;
        if (oldValue !== value)
        {
            this._1093589459WTimeToIdle = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WTimeToIdle", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalOffset' moved to '_431492689WNormalOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalOffset():Number
    {
        return this._431492689WNormalOffset;
    }

    public function set WNormalOffset(value:Number):void
    {
    	var oldValue:Object = this._431492689WNormalOffset;
        if (oldValue !== value)
        {
            this._431492689WNormalOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalFactor' moved to '_169124621WNormalFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalFactor():Number
    {
        return this._169124621WNormalFactor;
    }

    public function set WNormalFactor(value:Number):void
    {
    	var oldValue:Object = this._169124621WNormalFactor;
        if (oldValue !== value)
        {
            this._169124621WNormalFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairOffset' moved to '_1810703015WOnairOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairOffset():Number
    {
        return this._1810703015WOnairOffset;
    }

    public function set WOnairOffset(value:Number):void
    {
    	var oldValue:Object = this._1810703015WOnairOffset;
        if (oldValue !== value)
        {
            this._1810703015WOnairOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairFactor' moved to '_1548334947WOnairFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairFactor():Number
    {
        return this._1548334947WOnairFactor;
    }

    public function set WOnairFactor(value:Number):void
    {
    	var oldValue:Object = this._1548334947WOnairFactor;
        if (oldValue !== value)
        {
            this._1548334947WOnairFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveOffset' moved to '_1430763653WMoveOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveOffset():Number
    {
        return this._1430763653WMoveOffset;
    }

    public function set WMoveOffset(value:Number):void
    {
    	var oldValue:Object = this._1430763653WMoveOffset;
        if (oldValue !== value)
        {
            this._1430763653WMoveOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveFactor' moved to '_1693131721WMoveFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveFactor():Number
    {
        return this._1693131721WMoveFactor;
    }

    public function set WMoveFactor(value:Number):void
    {
    	var oldValue:Object = this._1693131721WMoveFactor;
        if (oldValue !== value)
        {
            this._1693131721WMoveFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalUpBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalUpBase' moved to '_611413450WNormalUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalUpBase():Number
    {
        return this._611413450WNormalUpBase;
    }

    public function set WNormalUpBase(value:Number):void
    {
    	var oldValue:Object = this._611413450WNormalUpBase;
        if (oldValue !== value)
        {
            this._611413450WNormalUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalLateralBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalLateralBase' moved to '_637526058WNormalLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalLateralBase():Number
    {
        return this._637526058WNormalLateralBase;
    }

    public function set WNormalLateralBase(value:Number):void
    {
    	var oldValue:Object = this._637526058WNormalLateralBase;
        if (oldValue !== value)
        {
            this._637526058WNormalLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalUpModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalUpModifier' moved to '_786086576WNormalUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalUpModifier():Number
    {
        return this._786086576WNormalUpModifier;
    }

    public function set WNormalUpModifier(value:Number):void
    {
    	var oldValue:Object = this._786086576WNormalUpModifier;
        if (oldValue !== value)
        {
            this._786086576WNormalUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalLateralModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalLateralModifier' moved to '_1485600848WNormalLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalLateralModifier():Number
    {
        return this._1485600848WNormalLateralModifier;
    }

    public function set WNormalLateralModifier(value:Number):void
    {
    	var oldValue:Object = this._1485600848WNormalLateralModifier;
        if (oldValue !== value)
        {
            this._1485600848WNormalLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalUpMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalUpMax' moved to '_257361077WNormalUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalUpMax():Number
    {
        return this._257361077WNormalUpMax;
    }

    public function set WNormalUpMax(value:Number):void
    {
    	var oldValue:Object = this._257361077WNormalUpMax;
        if (oldValue !== value)
        {
            this._257361077WNormalUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalLateralMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalLateralMax' moved to '_1683143915WNormalLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalLateralMax():Number
    {
        return this._1683143915WNormalLateralMax;
    }

    public function set WNormalLateralMax(value:Number):void
    {
    	var oldValue:Object = this._1683143915WNormalLateralMax;
        if (oldValue !== value)
        {
            this._1683143915WNormalLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WNormalDirChange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WNormalDirChange' moved to '_209568737WNormalDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function get WNormalDirChange():Number
    {
        return this._209568737WNormalDirChange;
    }

    public function set WNormalDirChange(value:Number):void
    {
    	var oldValue:Object = this._209568737WNormalDirChange;
        if (oldValue !== value)
        {
            this._209568737WNormalDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WNormalDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveUpBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveUpBase' moved to '_1250842892WMoveUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveUpBase():Number
    {
        return this._1250842892WMoveUpBase;
    }

    public function set WMoveUpBase(value:Number):void
    {
    	var oldValue:Object = this._1250842892WMoveUpBase;
        if (oldValue !== value)
        {
            this._1250842892WMoveUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveLateralBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveLateralBase' moved to '_1058614976WMoveLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveLateralBase():Number
    {
        return this._1058614976WMoveLateralBase;
    }

    public function set WMoveLateralBase(value:Number):void
    {
    	var oldValue:Object = this._1058614976WMoveLateralBase;
        if (oldValue !== value)
        {
            this._1058614976WMoveLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveUpModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveUpModifier' moved to '_129030522WMoveUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveUpModifier():Number
    {
        return this._129030522WMoveUpModifier;
    }

    public function set WMoveUpModifier(value:Number):void
    {
    	var oldValue:Object = this._129030522WMoveUpModifier;
        if (oldValue !== value)
        {
            this._129030522WMoveUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveLateralModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveLateralModifier' moved to '_1531970106WMoveLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveLateralModifier():Number
    {
        return this._1531970106WMoveLateralModifier;
    }

    public function set WMoveLateralModifier(value:Number):void
    {
    	var oldValue:Object = this._1531970106WMoveLateralModifier;
        if (oldValue !== value)
        {
            this._1531970106WMoveLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveUpMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveUpMax' moved to '_1148717855WMoveUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveUpMax():Number
    {
        return this._1148717855WMoveUpMax;
    }

    public function set WMoveUpMax(value:Number):void
    {
    	var oldValue:Object = this._1148717855WMoveUpMax;
        if (oldValue !== value)
        {
            this._1148717855WMoveUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveLateralMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveLateralMax' moved to '_1696706283WMoveLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveLateralMax():Number
    {
        return this._1696706283WMoveLateralMax;
    }

    public function set WMoveLateralMax(value:Number):void
    {
    	var oldValue:Object = this._1696706283WMoveLateralMax;
        if (oldValue !== value)
        {
            this._1696706283WMoveLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMoveDirChange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMoveDirChange' moved to '_595690827WMoveDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function get WMoveDirChange():Number
    {
        return this._595690827WMoveDirChange;
    }

    public function set WMoveDirChange(value:Number):void
    {
    	var oldValue:Object = this._595690827WMoveDirChange;
        if (oldValue !== value)
        {
            this._595690827WMoveDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMoveDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairUpBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairUpBase' moved to '_1990623776WOnairUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairUpBase():Number
    {
        return this._1990623776WOnairUpBase;
    }

    public function set WOnairUpBase(value:Number):void
    {
    	var oldValue:Object = this._1990623776WOnairUpBase;
        if (oldValue !== value)
        {
            this._1990623776WOnairUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairLateralBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairLateralBase' moved to '_1600646764WOnairLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairLateralBase():Number
    {
        return this._1600646764WOnairLateralBase;
    }

    public function set WOnairLateralBase(value:Number):void
    {
    	var oldValue:Object = this._1600646764WOnairLateralBase;
        if (oldValue !== value)
        {
            this._1600646764WOnairLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairUpModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairUpModifier' moved to '_527187622WOnairUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairUpModifier():Number
    {
        return this._527187622WOnairUpModifier;
    }

    public function set WOnairUpModifier(value:Number):void
    {
    	var oldValue:Object = this._527187622WOnairUpModifier;
        if (oldValue !== value)
        {
            this._527187622WOnairUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairLateralModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairLateralModifier' moved to '_832506854WOnairLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairLateralModifier():Number
    {
        return this._832506854WOnairLateralModifier;
    }

    public function set WOnairLateralModifier(value:Number):void
    {
    	var oldValue:Object = this._832506854WOnairLateralModifier;
        if (oldValue !== value)
        {
            this._832506854WOnairLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairUpMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairUpMax' moved to '_2013985739WOnairUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairUpMax():Number
    {
        return this._2013985739WOnairUpMax;
    }

    public function set WOnairUpMax(value:Number):void
    {
    	var oldValue:Object = this._2013985739WOnairUpMax;
        if (oldValue !== value)
        {
            this._2013985739WOnairUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairLateralMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairLateralMax' moved to '_1298549183WOnairLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairLateralMax():Number
    {
        return this._1298549183WOnairLateralMax;
    }

    public function set WOnairLateralMax(value:Number):void
    {
    	var oldValue:Object = this._1298549183WOnairLateralMax;
        if (oldValue !== value)
        {
            this._1298549183WOnairLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WOnairDirChange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WOnairDirChange' moved to '_2106867703WOnairDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function get WOnairDirChange():Number
    {
        return this._2106867703WOnairDirChange;
    }

    public function set WOnairDirChange(value:Number):void
    {
    	var oldValue:Object = this._2106867703WOnairDirChange;
        if (oldValue !== value)
        {
            this._2106867703WOnairDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WOnairDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchUpBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchUpBase' moved to '_1340996701WCrouchUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchUpBase():Number
    {
        return this._1340996701WCrouchUpBase;
    }

    public function set WCrouchUpBase(value:Number):void
    {
    	var oldValue:Object = this._1340996701WCrouchUpBase;
        if (oldValue !== value)
        {
            this._1340996701WCrouchUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchLateralBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchLateralBase' moved to '_319059319WCrouchLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchLateralBase():Number
    {
        return this._319059319WCrouchLateralBase;
    }

    public function set WCrouchLateralBase(value:Number):void
    {
    	var oldValue:Object = this._319059319WCrouchLateralBase;
        if (oldValue !== value)
        {
            this._319059319WCrouchLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchUpModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchUpModifier' moved to '_1212001693WCrouchUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchUpModifier():Number
    {
        return this._1212001693WCrouchUpModifier;
    }

    public function set WCrouchUpModifier(value:Number):void
    {
    	var oldValue:Object = this._1212001693WCrouchUpModifier;
        if (oldValue !== value)
        {
            this._1212001693WCrouchUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchLateralModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchLateralModifier' moved to '_1436373379WCrouchLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchLateralModifier():Number
    {
        return this._1436373379WCrouchLateralModifier;
    }

    public function set WCrouchLateralModifier(value:Number):void
    {
    	var oldValue:Object = this._1436373379WCrouchLateralModifier;
        if (oldValue !== value)
        {
            this._1436373379WCrouchLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchUpMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchUpMax' moved to '_1290194520WCrouchUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchUpMax():Number
    {
        return this._1290194520WCrouchUpMax;
    }

    public function set WCrouchUpMax(value:Number):void
    {
    	var oldValue:Object = this._1290194520WCrouchUpMax;
        if (oldValue !== value)
        {
            this._1290194520WCrouchUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchLateralMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchLateralMax' moved to '_1257228798WCrouchLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchLateralMax():Number
    {
        return this._1257228798WCrouchLateralMax;
    }

    public function set WCrouchLateralMax(value:Number):void
    {
    	var oldValue:Object = this._1257228798WCrouchLateralMax;
        if (oldValue !== value)
        {
            this._1257228798WCrouchLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrouchDirChange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrouchDirChange' moved to '_2024423252WCrouchDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrouchDirChange():Number
    {
        return this._2024423252WCrouchDirChange;
    }

    public function set WCrouchDirChange(value:Number):void
    {
    	var oldValue:Object = this._2024423252WCrouchDirChange;
        if (oldValue !== value)
        {
            this._2024423252WCrouchDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrouchDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WUpModifier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WUpModifier' moved to '_1932936777WUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function get WUpModifier():Number
    {
        return this._1932936777WUpModifier;
    }

    public function set WUpModifier(value:Number):void
    {
    	var oldValue:Object = this._1932936777WUpModifier;
        if (oldValue !== value)
        {
            this._1932936777WUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabTime' moved to '_1263994458WStabTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabTime():Number
    {
        return this._1263994458WStabTime;
    }

    public function set WStabTime(value:Number):void
    {
    	var oldValue:Object = this._1263994458WStabTime;
        if (oldValue !== value)
        {
            this._1263994458WStabTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabLightTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabLightTime' moved to '_577714634WStabLightTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabLightTime():Number
    {
        return this._577714634WStabLightTime;
    }

    public function set WStabLightTime(value:Number):void
    {
    	var oldValue:Object = this._577714634WStabLightTime;
        if (oldValue !== value)
        {
            this._577714634WStabLightTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabLightTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabHurt (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabHurt' moved to '_1264340248WStabHurt'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabHurt():Number
    {
        return this._1264340248WStabHurt;
    }

    public function set WStabHurt(value:Number):void
    {
    	var oldValue:Object = this._1264340248WStabHurt;
        if (oldValue !== value)
        {
            this._1264340248WStabHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabLightHurt (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabLightHurt' moved to '_577368844WStabLightHurt'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabLightHurt():Number
    {
        return this._577368844WStabLightHurt;
    }

    public function set WStabLightHurt(value:Number):void
    {
    	var oldValue:Object = this._577368844WStabLightHurt;
        if (oldValue !== value)
        {
            this._577368844WStabLightHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabLightHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WExplodeTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WExplodeTime' moved to '_1836105097WExplodeTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WExplodeTime():Number
    {
        return this._1836105097WExplodeTime;
    }

    public function set WExplodeTime(value:Number):void
    {
    	var oldValue:Object = this._1836105097WExplodeTime;
        if (oldValue !== value)
        {
            this._1836105097WExplodeTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WExplodeTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WReadyTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WReadyTime' moved to '_996780153WReadyTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WReadyTime():Number
    {
        return this._996780153WReadyTime;
    }

    public function set WReadyTime(value:Number):void
    {
    	var oldValue:Object = this._996780153WReadyTime;
        if (oldValue !== value)
        {
            this._996780153WReadyTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WReadyTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WThrowOutTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WThrowOutTime' moved to '_1895552148WThrowOutTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WThrowOutTime():Number
    {
        return this._1895552148WThrowOutTime;
    }

    public function set WThrowOutTime(value:Number):void
    {
    	var oldValue:Object = this._1895552148WThrowOutTime;
        if (oldValue !== value)
        {
            this._1895552148WThrowOutTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WThrowOutTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WHurtRange (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WHurtRange' moved to '_1958215721WHurtRange'
	 */

    [Bindable(event="propertyChange")]
    public function get WHurtRange():Number
    {
        return this._1958215721WHurtRange;
    }

    public function set WHurtRange(value:Number):void
    {
    	var oldValue:Object = this._1958215721WHurtRange;
        if (oldValue !== value)
        {
            this._1958215721WHurtRange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WHurtRange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WHurt (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WHurt' moved to '_82607366WHurt'
	 */

    [Bindable(event="propertyChange")]
    public function get WHurt():Number
    {
        return this._82607366WHurt;
    }

    public function set WHurt(value:Number):void
    {
    	var oldValue:Object = this._82607366WHurt;
        if (oldValue !== value)
        {
            this._82607366WHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WShootBulletCount (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WShootBulletCount' moved to '_189649403WShootBulletCount'
	 */

    [Bindable(event="propertyChange")]
    public function get WShootBulletCount():int
    {
        return this._189649403WShootBulletCount;
    }

    public function set WShootBulletCount(value:int):void
    {
    	var oldValue:Object = this._189649403WShootBulletCount;
        if (oldValue !== value)
        {
            this._189649403WShootBulletCount = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WShootBulletCount", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WSpread (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WSpread' moved to '_1908408150WSpread'
	 */

    [Bindable(event="propertyChange")]
    public function get WSpread():Number
    {
        return this._1908408150WSpread;
    }

    public function set WSpread(value:Number):void
    {
    	var oldValue:Object = this._1908408150WSpread;
        if (oldValue !== value)
        {
            this._1908408150WSpread = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WSpread", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFireMaxSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFireMaxSpeed' moved to '_1642170480WFireMaxSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get WFireMaxSpeed():Number
    {
        return this._1642170480WFireMaxSpeed;
    }

    public function set WFireMaxSpeed(value:Number):void
    {
    	var oldValue:Object = this._1642170480WFireMaxSpeed;
        if (oldValue !== value)
        {
            this._1642170480WFireMaxSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFireMaxSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFireStartSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFireStartSpeed' moved to '_1672259602WFireStartSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get WFireStartSpeed():Number
    {
        return this._1672259602WFireStartSpeed;
    }

    public function set WFireStartSpeed(value:Number):void
    {
    	var oldValue:Object = this._1672259602WFireStartSpeed;
        if (oldValue !== value)
        {
            this._1672259602WFireStartSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFireStartSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFireAceleration (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFireAceleration' moved to '_1566193810WFireAceleration'
	 */

    [Bindable(event="propertyChange")]
    public function get WFireAceleration():Number
    {
        return this._1566193810WFireAceleration;
    }

    public function set WFireAceleration(value:Number):void
    {
    	var oldValue:Object = this._1566193810WFireAceleration;
        if (oldValue !== value)
        {
            this._1566193810WFireAceleration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFireAceleration", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFireResistance (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFireResistance' moved to '_825444166WFireResistance'
	 */

    [Bindable(event="propertyChange")]
    public function get WFireResistance():Number
    {
        return this._825444166WFireResistance;
    }

    public function set WFireResistance(value:Number):void
    {
    	var oldValue:Object = this._825444166WFireResistance;
        if (oldValue !== value)
        {
            this._825444166WFireResistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFireResistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WRangeStart (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WRangeStart' moved to '_1548651300WRangeStart'
	 */

    [Bindable(event="propertyChange")]
    public function get WRangeStart():Number
    {
        return this._1548651300WRangeStart;
    }

    public function set WRangeStart(value:Number):void
    {
    	var oldValue:Object = this._1548651300WRangeStart;
        if (oldValue !== value)
        {
            this._1548651300WRangeStart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WRangeStart", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WRangeEnd (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WRangeEnd' moved to '_1982730197WRangeEnd'
	 */

    [Bindable(event="propertyChange")]
    public function get WRangeEnd():Number
    {
        return this._1982730197WRangeEnd;
    }

    public function set WRangeEnd(value:Number):void
    {
    	var oldValue:Object = this._1982730197WRangeEnd;
        if (oldValue !== value)
        {
            this._1982730197WRangeEnd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WRangeEnd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAccuracyTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAccuracyTime' moved to '_805051043WAccuracyTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WAccuracyTime():Number
    {
        return this._805051043WAccuracyTime;
    }

    public function set WAccuracyTime(value:Number):void
    {
    	var oldValue:Object = this._805051043WAccuracyTime;
        if (oldValue !== value)
        {
            this._805051043WAccuracyTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAccuracyTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WAccuracyTimeModefier (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WAccuracyTimeModefier' moved to '_270352720WAccuracyTimeModefier'
	 */

    [Bindable(event="propertyChange")]
    public function get WAccuracyTimeModefier():Number
    {
        return this._270352720WAccuracyTimeModefier;
    }

    public function set WAccuracyTimeModefier(value:Number):void
    {
    	var oldValue:Object = this._270352720WAccuracyTimeModefier;
        if (oldValue !== value)
        {
            this._270352720WAccuracyTimeModefier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WAccuracyTimeModefier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMaxAccuracy (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMaxAccuracy' moved to '_86247002WMaxAccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function get WMaxAccuracy():Number
    {
        return this._86247002WMaxAccuracy;
    }

    public function set WMaxAccuracy(value:Number):void
    {
    	var oldValue:Object = this._86247002WMaxAccuracy;
        if (oldValue !== value)
        {
            this._86247002WMaxAccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMaxAccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMinAccuracy (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMinAccuracy' moved to '_763679596WMinAccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function get WMinAccuracy():Number
    {
        return this._763679596WMinAccuracy;
    }

    public function set WMinAccuracy(value:Number):void
    {
    	var oldValue:Object = this._763679596WMinAccuracy;
        if (oldValue !== value)
        {
            this._763679596WMinAccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMinAccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrossLengthBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrossLengthBase' moved to '_1075320288WCrossLengthBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrossLengthBase():Number
    {
        return this._1075320288WCrossLengthBase;
    }

    public function set WCrossLengthBase(value:Number):void
    {
    	var oldValue:Object = this._1075320288WCrossLengthBase;
        if (oldValue !== value)
        {
            this._1075320288WCrossLengthBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrossLengthBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrossLengthFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrossLengthFactor' moved to '_1590263650WCrossLengthFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrossLengthFactor():Number
    {
        return this._1590263650WCrossLengthFactor;
    }

    public function set WCrossLengthFactor(value:Number):void
    {
    	var oldValue:Object = this._1590263650WCrossLengthFactor;
        if (oldValue !== value)
        {
            this._1590263650WCrossLengthFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrossLengthFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrossDistanceBase (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrossDistanceBase' moved to '_1116100305WCrossDistanceBase'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrossDistanceBase():Number
    {
        return this._1116100305WCrossDistanceBase;
    }

    public function set WCrossDistanceBase(value:Number):void
    {
    	var oldValue:Object = this._1116100305WCrossDistanceBase;
        if (oldValue !== value)
        {
            this._1116100305WCrossDistanceBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrossDistanceBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WCrossDistanceFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WCrossDistanceFactor' moved to '_1283488813WCrossDistanceFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WCrossDistanceFactor():Number
    {
        return this._1283488813WCrossDistanceFactor;
    }

    public function set WCrossDistanceFactor(value:Number):void
    {
    	var oldValue:Object = this._1283488813WCrossDistanceFactor;
        if (oldValue !== value)
        {
            this._1283488813WCrossDistanceFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WCrossDistanceFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WXOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WXOffset' moved to '_155643604WXOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WXOffset():Number
    {
        return this._155643604WXOffset;
    }

    public function set WXOffset(value:Number):void
    {
    	var oldValue:Object = this._155643604WXOffset;
        if (oldValue !== value)
        {
            this._155643604WXOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WXOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WSightNormalOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WSightNormalOffset' moved to '_2123610368WSightNormalOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WSightNormalOffset():Number
    {
        return this._2123610368WSightNormalOffset;
    }

    public function set WSightNormalOffset(value:Number):void
    {
    	var oldValue:Object = this._2123610368WSightNormalOffset;
        if (oldValue !== value)
        {
            this._2123610368WSightNormalOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WSightNormalOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WSightOnairOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WSightOnairOffset' moved to '_2005375000WSightOnairOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WSightOnairOffset():Number
    {
        return this._2005375000WSightOnairOffset;
    }

    public function set WSightOnairOffset(value:Number):void
    {
    	var oldValue:Object = this._2005375000WSightOnairOffset;
        if (oldValue !== value)
        {
            this._2005375000WSightOnairOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WSightOnairOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WSightMoveOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WSightMoveOffset' moved to '_238084074WSightMoveOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get WSightMoveOffset():Number
    {
        return this._238084074WSightMoveOffset;
    }

    public function set WSightMoveOffset(value:Number):void
    {
    	var oldValue:Object = this._238084074WSightMoveOffset;
        if (oldValue !== value)
        {
            this._238084074WSightMoveOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WSightMoveOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabDistance (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabDistance' moved to '_1808997966WStabDistance'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabDistance():Number
    {
        return this._1808997966WStabDistance;
    }

    public function set WStabDistance(value:Number):void
    {
    	var oldValue:Object = this._1808997966WStabDistance;
        if (oldValue !== value)
        {
            this._1808997966WStabDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabLightDistance (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabLightDistance' moved to '_242527346WStabLightDistance'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabLightDistance():Number
    {
        return this._242527346WStabLightDistance;
    }

    public function set WStabLightDistance(value:Number):void
    {
    	var oldValue:Object = this._242527346WStabLightDistance;
        if (oldValue !== value)
        {
            this._242527346WStabLightDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabLightDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WStabWidth (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WStabWidth' moved to '_526360051WStabWidth'
	 */

    [Bindable(event="propertyChange")]
    public function get WStabWidth():Number
    {
        return this._526360051WStabWidth;
    }

    public function set WStabWidth(value:Number):void
    {
    	var oldValue:Object = this._526360051WStabWidth;
        if (oldValue !== value)
        {
            this._526360051WStabWidth = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WStabWidth", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WBackFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WBackFactor' moved to '_2015494963WBackFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WBackFactor():Number
    {
        return this._2015494963WBackFactor;
    }

    public function set WBackFactor(value:Number):void
    {
    	var oldValue:Object = this._2015494963WBackFactor;
        if (oldValue !== value)
        {
            this._2015494963WBackFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WBackFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFlashRangeStart (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFlashRangeStart' moved to '_545876002WFlashRangeStart'
	 */

    [Bindable(event="propertyChange")]
    public function get WFlashRangeStart():Number
    {
        return this._545876002WFlashRangeStart;
    }

    public function set WFlashRangeStart(value:Number):void
    {
    	var oldValue:Object = this._545876002WFlashRangeStart;
        if (oldValue !== value)
        {
            this._545876002WFlashRangeStart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFlashRangeStart", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFlashRangeEnd (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFlashRangeEnd' moved to '_1350300841WFlashRangeEnd'
	 */

    [Bindable(event="propertyChange")]
    public function get WFlashRangeEnd():Number
    {
        return this._1350300841WFlashRangeEnd;
    }

    public function set WFlashRangeEnd(value:Number):void
    {
    	var oldValue:Object = this._1350300841WFlashRangeEnd;
        if (oldValue !== value)
        {
            this._1350300841WFlashRangeEnd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFlashRangeEnd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WFlashBackFactor (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WFlashBackFactor' moved to '_1012719665WFlashBackFactor'
	 */

    [Bindable(event="propertyChange")]
    public function get WFlashBackFactor():Number
    {
        return this._1012719665WFlashBackFactor;
    }

    public function set WFlashBackFactor(value:Number):void
    {
    	var oldValue:Object = this._1012719665WFlashBackFactor;
        if (oldValue !== value)
        {
            this._1012719665WFlashBackFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WFlashBackFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WTimeMax (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WTimeMax' moved to '_1651352320WTimeMax'
	 */

    [Bindable(event="propertyChange")]
    public function get WTimeMax():Number
    {
        return this._1651352320WTimeMax;
    }

    public function set WTimeMax(value:Number):void
    {
    	var oldValue:Object = this._1651352320WTimeMax;
        if (oldValue !== value)
        {
            this._1651352320WTimeMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WTimeMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WTimeFade (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WTimeFade' moved to '_347894688WTimeFade'
	 */

    [Bindable(event="propertyChange")]
    public function get WTimeFade():Number
    {
        return this._347894688WTimeFade;
    }

    public function set WTimeFade(value:Number):void
    {
    	var oldValue:Object = this._347894688WTimeFade;
        if (oldValue !== value)
        {
            this._347894688WTimeFade = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WTimeFade", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WTime' moved to '_82953156WTime'
	 */

    [Bindable(event="propertyChange")]
    public function get WTime():Number
    {
        return this._82953156WTime;
    }

    public function set WTime(value:Number):void
    {
    	var oldValue:Object = this._82953156WTime;
        if (oldValue !== value)
        {
            this._82953156WTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WMaxLength (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WMaxLength' moved to '_1295762131WMaxLength'
	 */

    [Bindable(event="propertyChange")]
    public function get WMaxLength():int
    {
        return this._1295762131WMaxLength;
    }

    public function set WMaxLength(value:int):void
    {
    	var oldValue:Object = this._1295762131WMaxLength;
        if (oldValue !== value)
        {
            this._1295762131WMaxLength = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WMaxLength", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WHitSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WHitSpeed' moved to '_398253547WHitSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get WHitSpeed():Number
    {
        return this._398253547WHitSpeed;
    }

    public function set WHitSpeed(value:Number):void
    {
    	var oldValue:Object = this._398253547WHitSpeed;
        if (oldValue !== value)
        {
            this._398253547WHitSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WHitSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WHitAcceleration (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WHitAcceleration' moved to '_1619290532WHitAcceleration'
	 */

    [Bindable(event="propertyChange")]
    public function get WHitAcceleration():Number
    {
        return this._1619290532WHitAcceleration;
    }

    public function set WHitAcceleration(value:Number):void
    {
    	var oldValue:Object = this._1619290532WHitAcceleration;
        if (oldValue !== value)
        {
            this._1619290532WHitAcceleration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WHitAcceleration", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WHitDistance (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WHitDistance' moved to '_203482159WHitDistance'
	 */

    [Bindable(event="propertyChange")]
    public function get WHitDistance():Number
    {
        return this._203482159WHitDistance;
    }

    public function set WHitDistance(value:Number):void
    {
    	var oldValue:Object = this._203482159WHitDistance;
        if (oldValue !== value)
        {
            this._203482159WHitDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WHitDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WSightInfo (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WSightInfo' moved to '_1289736876WSightInfo'
	 */

    [Bindable(event="propertyChange")]
    public function get WSightInfo():String
    {
        return this._1289736876WSightInfo;
    }

    public function set WSightInfo(value:String):void
    {
    	var oldValue:Object = this._1289736876WSightInfo;
        if (oldValue !== value)
        {
            this._1289736876WSightInfo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WSightInfo", oldValue, value));
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

	/**
	 * generated bindable wrapper for property wHitDistance (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wHitDistance' moved to '_367802447wHitDistance'
	 */

    [Bindable(event="propertyChange")]
    public function set wHitDistance(value:Number):void
    {
    	var oldValue:Object = this.wHitDistance;
        if (oldValue !== value)
        {
            this._367802447wHitDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wHitDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wHitAcceleration (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wHitAcceleration' moved to '_776515012wHitAcceleration'
	 */

    [Bindable(event="propertyChange")]
    public function set wHitAcceleration(value:Number):void
    {
    	var oldValue:Object = this.wHitAcceleration;
        if (oldValue !== value)
        {
            this._776515012wHitAcceleration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wHitAcceleration", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wHitSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wHitSpeed' moved to '_1605714421wHitSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set wHitSpeed(value:Number):void
    {
    	var oldValue:Object = this.wHitSpeed;
        if (oldValue !== value)
        {
            this._1605714421wHitSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wHitSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFlashBackFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFlashBackFactor' moved to '_169944145wFlashBackFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wFlashBackFactor(value:Number):void
    {
    	var oldValue:Object = this.wFlashBackFactor;
        if (oldValue !== value)
        {
            this._169944145wFlashBackFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFlashBackFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wTime' moved to '_112505828wTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wTime(value:Number):void
    {
    	var oldValue:Object = this.wTime;
        if (oldValue !== value)
        {
            this._112505828wTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wTimeFade (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wTimeFade' moved to '_1943104640wTimeFade'
	 */

    [Bindable(event="propertyChange")]
    public function set wTimeFade(value:Number):void
    {
    	var oldValue:Object = this.wTimeFade;
        if (oldValue !== value)
        {
            this._1943104640wTimeFade = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wTimeFade", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wTimeMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wTimeMax' moved to '_1586708192wTimeMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wTimeMax(value:Number):void
    {
    	var oldValue:Object = this.wTimeMax;
        if (oldValue !== value)
        {
            this._1586708192wTimeMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wTimeMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFlashRangeEnd (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFlashRangeEnd' moved to '_348307657wFlashRangeEnd'
	 */

    [Bindable(event="propertyChange")]
    public function set wFlashRangeEnd(value:Number):void
    {
    	var oldValue:Object = this.wFlashRangeEnd;
        if (oldValue !== value)
        {
            this._348307657wFlashRangeEnd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFlashRangeEnd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFlashRangeStart (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFlashRangeStart' moved to '_296899518wFlashRangeStart'
	 */

    [Bindable(event="propertyChange")]
    public function set wFlashRangeStart(value:Number):void
    {
    	var oldValue:Object = this.wFlashRangeStart;
        if (oldValue !== value)
        {
            this._296899518wFlashRangeStart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFlashRangeStart", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wBackFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wBackFactor' moved to '_611603693wBackFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wBackFactor(value:Number):void
    {
    	var oldValue:Object = this.wBackFactor;
        if (oldValue !== value)
        {
            this._611603693wBackFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wBackFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabWidth (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabWidth' moved to '_1775142381wStabWidth'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabWidth(value:Number):void
    {
    	var oldValue:Object = this.wStabWidth;
        if (oldValue !== value)
        {
            this._1775142381wStabWidth = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabWidth", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabLightDistance (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabLightDistance' moved to '_1599016878wStabLightDistance'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabLightDistance(value:Number):void
    {
    	var oldValue:Object = this.wStabLightDistance;
        if (oldValue !== value)
        {
            this._1599016878wStabLightDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabLightDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabDistance (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabDistance' moved to '_1010036334wStabDistance'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabDistance(value:Number):void
    {
    	var oldValue:Object = this.wStabDistance;
        if (oldValue !== value)
        {
            this._1010036334wStabDistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabDistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wSightMoveOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wSightMoveOffset' moved to '_1080859594wSightMoveOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wSightMoveOffset(value:Number):void
    {
    	var oldValue:Object = this.wSightMoveOffset;
        if (oldValue !== value)
        {
            this._1080859594wSightMoveOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wSightMoveOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wSightOnairOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wSightOnairOffset' moved to '_1933354952wSightOnairOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wSightOnairOffset(value:Number):void
    {
    	var oldValue:Object = this.wSightOnairOffset;
        if (oldValue !== value)
        {
            this._1933354952wSightOnairOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wSightOnairOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wSightNormalOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wSightNormalOffset' moved to '_329812704wSightNormalOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wSightNormalOffset(value:Number):void
    {
    	var oldValue:Object = this.wSightNormalOffset;
        if (oldValue !== value)
        {
            this._329812704wSightNormalOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wSightNormalOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wXOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wXOffset' moved to '_90999476wXOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wXOffset(value:Number):void
    {
    	var oldValue:Object = this.wXOffset;
        if (oldValue !== value)
        {
            this._90999476wXOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wXOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrossDistanceFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrossDistanceFactor' moved to '_1086015501wCrossDistanceFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrossDistanceFactor(value:Number):void
    {
    	var oldValue:Object = this.wCrossDistanceFactor;
        if (oldValue !== value)
        {
            this._1086015501wCrossDistanceFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrossDistanceFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrossDistanceBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrossDistanceBase' moved to '_1337322767wCrossDistanceBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrossDistanceBase(value:Number):void
    {
    	var oldValue:Object = this.wCrossDistanceBase;
        if (oldValue !== value)
        {
            this._1337322767wCrossDistanceBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrossDistanceBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrossLengthFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrossLengthFactor' moved to '_863159422wCrossLengthFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrossLengthFactor(value:Number):void
    {
    	var oldValue:Object = this.wCrossLengthFactor;
        if (oldValue !== value)
        {
            this._863159422wCrossLengthFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrossLengthFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrossLengthBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrossLengthBase' moved to '_1918095808wCrossLengthBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrossLengthBase(value:Number):void
    {
    	var oldValue:Object = this.wCrossLengthBase;
        if (oldValue !== value)
        {
            this._1918095808wCrossLengthBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrossLengthBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMinAccuracy (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMinAccuracy' moved to '_927999884wMinAccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function set wMinAccuracy(value:Number):void
    {
    	var oldValue:Object = this.wMinAccuracy;
        if (oldValue !== value)
        {
            this._927999884wMinAccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMinAccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMaxAccuracy (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMaxAccuracy' moved to '_250567290wMaxAccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function set wMaxAccuracy(value:Number):void
    {
    	var oldValue:Object = this.wMaxAccuracy;
        if (oldValue !== value)
        {
            this._250567290wMaxAccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMaxAccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wAccuracyTimeModefier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wAccuracyTimeModefier' moved to '_1556352656wAccuracyTimeModefier'
	 */

    [Bindable(event="propertyChange")]
    public function set wAccuracyTimeModefier(value:Number):void
    {
    	var oldValue:Object = this.wAccuracyTimeModefier;
        if (oldValue !== value)
        {
            this._1556352656wAccuracyTimeModefier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wAccuracyTimeModefier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wAccuracyTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wAccuracyTime' moved to '_1604012675wAccuracyTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wAccuracyTime(value:Number):void
    {
    	var oldValue:Object = this.wAccuracyTime;
        if (oldValue !== value)
        {
            this._1604012675wAccuracyTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wAccuracyTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wRangeEnd (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wRangeEnd' moved to '_21237771wRangeEnd'
	 */

    [Bindable(event="propertyChange")]
    public function set wRangeEnd(value:Number):void
    {
    	var oldValue:Object = this.wRangeEnd;
        if (oldValue !== value)
        {
            this._21237771wRangeEnd = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wRangeEnd", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wRangeStart (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wRangeStart' moved to '_1078447356wRangeStart'
	 */

    [Bindable(event="propertyChange")]
    public function set wRangeStart(value:Number):void
    {
    	var oldValue:Object = this.wRangeStart;
        if (oldValue !== value)
        {
            this._1078447356wRangeStart = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wRangeStart", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFireTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFireTime' moved to '_837832774wFireTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wFireTime(value:Number):void
    {
    	var oldValue:Object = this.wFireTime;
        if (oldValue !== value)
        {
            this._837832774wFireTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFireTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wRangeModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wRangeModifier' moved to '_262407171wRangeModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wRangeModifier(value:Number):void
    {
    	var oldValue:Object = this.wRangeModifier;
        if (oldValue !== value)
        {
            this._262407171wRangeModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wRangeModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMaxInaccuracy (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMaxInaccuracy' moved to '_1347341931wMaxInaccuracy'
	 */

    [Bindable(event="propertyChange")]
    public function set wMaxInaccuracy(value:Number):void
    {
    	var oldValue:Object = this.wMaxInaccuracy;
        if (oldValue !== value)
        {
            this._1347341931wMaxInaccuracy = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMaxInaccuracy", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wAccuracyOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wAccuracyOffset' moved to '_290970435wAccuracyOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wAccuracyOffset(value:Number):void
    {
    	var oldValue:Object = this.wAccuracyOffset;
        if (oldValue !== value)
        {
            this._290970435wAccuracyOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wAccuracyOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wChangeInTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wChangeInTime' moved to '_775909511wChangeInTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wChangeInTime(value:Number):void
    {
    	var oldValue:Object = this.wChangeInTime;
        if (oldValue !== value)
        {
            this._775909511wChangeInTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wChangeInTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrossOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrossOffset' moved to '_1744879620wCrossOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrossOffset(value:Number):void
    {
    	var oldValue:Object = this.wCrossOffset;
        if (oldValue !== value)
        {
            this._1744879620wCrossOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrossOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveSpeedOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveSpeedOffset' moved to '_1575212622wMoveSpeedOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveSpeedOffset(value:Number):void
    {
    	var oldValue:Object = this.wMoveSpeedOffset;
        if (oldValue !== value)
        {
            this._1575212622wMoveSpeedOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveSpeedOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wReloadTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wReloadTime' moved to '_1658243037wReloadTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wReloadTime(value:Number):void
    {
    	var oldValue:Object = this.wReloadTime;
        if (oldValue !== value)
        {
            this._1658243037wReloadTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wReloadTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wZoomTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wZoomTime' moved to '_610191063wZoomTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wZoomTime(value:Number):void
    {
    	var oldValue:Object = this.wZoomTime;
        if (oldValue !== value)
        {
            this._610191063wZoomTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wZoomTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wZoomValue (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wZoomValue' moved to '_1737662119wZoomValue'
	 */

    [Bindable(event="propertyChange")]
    public function set wZoomValue(value:Number):void
    {
    	var oldValue:Object = this.wZoomValue;
        if (oldValue !== value)
        {
            this._1737662119wZoomValue = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wZoomValue", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wTimeToIdle (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wTimeToIdle' moved to '_574279181wTimeToIdle'
	 */

    [Bindable(event="propertyChange")]
    public function set wTimeToIdle(value:Number):void
    {
    	var oldValue:Object = this.wTimeToIdle;
        if (oldValue !== value)
        {
            this._574279181wTimeToIdle = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wTimeToIdle", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalOffset' moved to '_367468943wNormalOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalOffset(value:Number):void
    {
    	var oldValue:Object = this.wNormalOffset;
        if (oldValue !== value)
        {
            this._367468943wNormalOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalFactor' moved to '_629837011wNormalFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalFactor(value:Number):void
    {
    	var oldValue:Object = this.wNormalFactor;
        if (oldValue !== value)
        {
            this._629837011wNormalFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairOffset' moved to '_1646382727wOnairOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairOffset(value:Number):void
    {
    	var oldValue:Object = this.wOnairOffset;
        if (oldValue !== value)
        {
            this._1646382727wOnairOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairFactor' moved to '_1384014659wOnairFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairFactor(value:Number):void
    {
    	var oldValue:Object = this.wOnairFactor;
        if (oldValue !== value)
        {
            this._1384014659wOnairFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveOffset (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveOffset' moved to '_1196335003wMoveOffset'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveOffset(value:Number):void
    {
    	var oldValue:Object = this.wMoveOffset;
        if (oldValue !== value)
        {
            this._1196335003wMoveOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveFactor (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveFactor' moved to '_933966935wMoveFactor'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveFactor(value:Number):void
    {
    	var oldValue:Object = this.wMoveFactor;
        if (oldValue !== value)
        {
            this._933966935wMoveFactor = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveFactor", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalUpBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalUpBase' moved to '_187548182wNormalUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalUpBase(value:Number):void
    {
    	var oldValue:Object = this.wNormalUpBase;
        if (oldValue !== value)
        {
            this._187548182wNormalUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalLateralBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalLateralBase' moved to '_1204018166wNormalLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalLateralBase(value:Number):void
    {
    	var oldValue:Object = this.wNormalLateralBase;
        if (oldValue !== value)
        {
            this._1204018166wNormalLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalUpModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalUpModifier' moved to '_429849232wNormalUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalUpModifier(value:Number):void
    {
    	var oldValue:Object = this.wNormalUpModifier;
        if (oldValue !== value)
        {
            this._429849232wNormalUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalLateralModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalLateralModifier' moved to '_2016074640wNormalLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalLateralModifier(value:Number):void
    {
    	var oldValue:Object = this.wNormalLateralModifier;
        if (oldValue !== value)
        {
            this._2016074640wNormalLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalUpMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalUpMax' moved to '_421681365wNormalUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalUpMax(value:Number):void
    {
    	var oldValue:Object = this.wNormalUpMax;
        if (oldValue !== value)
        {
            this._421681365wNormalUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalLateralMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalLateralMax' moved to '_2039381259wNormalLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalLateralMax(value:Number):void
    {
    	var oldValue:Object = this.wNormalLateralMax;
        if (oldValue !== value)
        {
            this._2039381259wNormalLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wNormalDirChange (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wNormalDirChange' moved to '_633206783wNormalDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function set wNormalDirChange(value:Number):void
    {
    	var oldValue:Object = this.wNormalDirChange;
        if (oldValue !== value)
        {
            this._633206783wNormalDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wNormalDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveUpBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveUpBase' moved to '_1376255764wMoveUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveUpBase(value:Number):void
    {
    	var oldValue:Object = this.wMoveUpBase;
        if (oldValue !== value)
        {
            this._1376255764wMoveUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveLateralBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveLateralBase' moved to '_215839456wMoveLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveLateralBase(value:Number):void
    {
    	var oldValue:Object = this.wMoveLateralBase;
        if (oldValue !== value)
        {
            this._215839456wMoveLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveUpModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveUpModifier' moved to '_1126048154wMoveUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveUpModifier(value:Number):void
    {
    	var oldValue:Object = this.wMoveUpModifier;
        if (oldValue !== value)
        {
            this._1126048154wMoveUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveLateralModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveLateralModifier' moved to '_1729443418wMoveLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveLateralModifier(value:Number):void
    {
    	var oldValue:Object = this.wMoveLateralModifier;
        if (oldValue !== value)
        {
            this._1729443418wMoveLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveUpMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveUpMax' moved to '_1152784577wMoveUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveUpMax(value:Number):void
    {
    	var oldValue:Object = this.wMoveUpMax;
        if (oldValue !== value)
        {
            this._1152784577wMoveUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveLateralMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveLateralMax' moved to '_699688651wMoveLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveLateralMax(value:Number):void
    {
    	var oldValue:Object = this.wMoveLateralMax;
        if (oldValue !== value)
        {
            this._699688651wMoveLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wMoveDirChange (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wMoveDirChange' moved to '_406302357wMoveDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function set wMoveDirChange(value:Number):void
    {
    	var oldValue:Object = this.wMoveDirChange;
        if (oldValue !== value)
        {
            this._406302357wMoveDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wMoveDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairUpBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairUpBase' moved to '_1826303488wOnairUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairUpBase(value:Number):void
    {
    	var oldValue:Object = this.wOnairUpBase;
        if (oldValue !== value)
        {
            this._1826303488wOnairUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairLateralBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairLateralBase' moved to '_1244409420wOnairLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairLateralBase(value:Number):void
    {
    	var oldValue:Object = this.wOnairLateralBase;
        if (oldValue !== value)
        {
            this._1244409420wOnairLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairUpModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairUpModifier' moved to '_1369963142wOnairUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairUpModifier(value:Number):void
    {
    	var oldValue:Object = this.wOnairUpModifier;
        if (oldValue !== value)
        {
            this._1369963142wOnairUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairLateralModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairLateralModifier' moved to '_1635755066wOnairLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairLateralModifier(value:Number):void
    {
    	var oldValue:Object = this.wOnairLateralModifier;
        if (oldValue !== value)
        {
            this._1635755066wOnairLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairUpMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairUpMax' moved to '_613112917wOnairUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairUpMax(value:Number):void
    {
    	var oldValue:Object = this.wOnairUpMax;
        if (oldValue !== value)
        {
            this._613112917wOnairUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairLateralMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairLateralMax' moved to '_455773663wOnairLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairLateralMax(value:Number):void
    {
    	var oldValue:Object = this.wOnairLateralMax;
        if (oldValue !== value)
        {
            this._455773663wOnairLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wOnairDirChange (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wOnairDirChange' moved to '_1109850071wOnairDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function set wOnairDirChange(value:Number):void
    {
    	var oldValue:Object = this.wOnairDirChange;
        if (oldValue !== value)
        {
            this._1109850071wOnairDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wOnairDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchUpBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchUpBase' moved to '_542035069wCrouchUpBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchUpBase(value:Number):void
    {
    	var oldValue:Object = this.wCrouchUpBase;
        if (oldValue !== value)
        {
            this._542035069wCrouchUpBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchUpBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchLateralBase (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchLateralBase' moved to '_1522484905wCrouchLateralBase'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchLateralBase(value:Number):void
    {
    	var oldValue:Object = this.wCrouchLateralBase;
        if (oldValue !== value)
        {
            this._1522484905wCrouchLateralBase = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchLateralBase", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchUpModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchUpModifier' moved to '_855764349wCrouchUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchUpModifier(value:Number):void
    {
    	var oldValue:Object = this.wCrouchUpModifier;
        if (oldValue !== value)
        {
            this._855764349wCrouchUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchLateralModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchLateralModifier' moved to '_2065302109wCrouchLateralModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchLateralModifier(value:Number):void
    {
    	var oldValue:Object = this.wCrouchLateralModifier;
        if (oldValue !== value)
        {
            this._2065302109wCrouchLateralModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchLateralModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchUpMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchUpMax' moved to '_1125874232wCrouchUpMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchUpMax(value:Number):void
    {
    	var oldValue:Object = this.wCrouchUpMax;
        if (oldValue !== value)
        {
            this._1125874232wCrouchUpMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchUpMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchLateralMax (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchLateralMax' moved to '_1613466142wCrouchLateralMax'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchLateralMax(value:Number):void
    {
    	var oldValue:Object = this.wCrouchLateralMax;
        if (oldValue !== value)
        {
            this._1613466142wCrouchLateralMax = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchLateralMax", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wCrouchDirChange (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wCrouchDirChange' moved to '_1181647732wCrouchDirChange'
	 */

    [Bindable(event="propertyChange")]
    public function set wCrouchDirChange(value:Number):void
    {
    	var oldValue:Object = this.wCrouchDirChange;
        if (oldValue !== value)
        {
            this._1181647732wCrouchDirChange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wCrouchDirChange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wUpModifier (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wUpModifier' moved to '_265068137wUpModifier'
	 */

    [Bindable(event="propertyChange")]
    public function set wUpModifier(value:Number):void
    {
    	var oldValue:Object = this.wUpModifier;
        if (oldValue !== value)
        {
            this._265068137wUpModifier = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wUpModifier", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabTime' moved to '_1027004870wStabTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabTime(value:Number):void
    {
    	var oldValue:Object = this.wStabTime;
        if (oldValue !== value)
        {
            this._1027004870wStabTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabLightTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabLightTime' moved to '_1579707818wStabLightTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabLightTime(value:Number):void
    {
    	var oldValue:Object = this.wStabLightTime;
        if (oldValue !== value)
        {
            this._1579707818wStabLightTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabLightTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabHurt (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabHurt' moved to '_1026659080wStabHurt'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabHurt(value:Number):void
    {
    	var oldValue:Object = this.wStabHurt;
        if (oldValue !== value)
        {
            this._1026659080wStabHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wStabLightHurt (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wStabLightHurt' moved to '_1579362028wStabLightHurt'
	 */

    [Bindable(event="propertyChange")]
    public function set wStabLightHurt(value:Number):void
    {
    	var oldValue:Object = this.wStabLightHurt;
        if (oldValue !== value)
        {
            this._1579362028wStabLightHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wStabLightHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wExplodeTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wExplodeTime' moved to '_2000425385wExplodeTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wExplodeTime(value:Number):void
    {
    	var oldValue:Object = this.wExplodeTime;
        if (oldValue !== value)
        {
            this._2000425385wExplodeTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wExplodeTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wReadyTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wReadyTime' moved to '_996684711wReadyTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wReadyTime(value:Number):void
    {
    	var oldValue:Object = this.wReadyTime;
        if (oldValue !== value)
        {
            this._996684711wReadyTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wReadyTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wThrowOutTime (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wThrowOutTime' moved to '_1600453516wThrowOutTime'
	 */

    [Bindable(event="propertyChange")]
    public function set wThrowOutTime(value:Number):void
    {
    	var oldValue:Object = this.wThrowOutTime;
        if (oldValue !== value)
        {
            this._1600453516wThrowOutTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wThrowOutTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wHurtRange (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wHurtRange' moved to '_343286711wHurtRange'
	 */

    [Bindable(event="propertyChange")]
    public function set wHurtRange(value:Number):void
    {
    	var oldValue:Object = this.wHurtRange;
        if (oldValue !== value)
        {
            this._343286711wHurtRange = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wHurtRange", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wHurt (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wHurt' moved to '_112160038wHurt'
	 */

    [Bindable(event="propertyChange")]
    public function set wHurt(value:Number):void
    {
    	var oldValue:Object = this.wHurt;
        if (oldValue !== value)
        {
            this._112160038wHurt = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wHurt", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wSpread (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wSpread' moved to '_721905866wSpread'
	 */

    [Bindable(event="propertyChange")]
    public function set wSpread(value:Number):void
    {
    	var oldValue:Object = this.wSpread;
        if (oldValue !== value)
        {
            this._721905866wSpread = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wSpread", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFireStartSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFireStartSpeed' moved to '_1625690062wFireStartSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set wFireStartSpeed(value:Number):void
    {
    	var oldValue:Object = this.wFireStartSpeed;
        if (oldValue !== value)
        {
            this._1625690062wFireStartSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFireStartSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFireMaxSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFireMaxSpeed' moved to '_1853835184wFireMaxSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set wFireMaxSpeed(value:Number):void
    {
    	var oldValue:Object = this.wFireMaxSpeed;
        if (oldValue !== value)
        {
            this._1853835184wFireMaxSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFireMaxSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFireResistance (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFireResistance' moved to '_1822461798wFireResistance'
	 */

    [Bindable(event="propertyChange")]
    public function set wFireResistance(value:Number):void
    {
    	var oldValue:Object = this.wFireResistance;
        if (oldValue !== value)
        {
            this._1822461798wFireResistance = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFireResistance", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property wFireAceleration (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'wFireAceleration' moved to '_1885997966wFireAceleration'
	 */

    [Bindable(event="propertyChange")]
    public function set wFireAceleration(value:Number):void
    {
    	var oldValue:Object = this.wFireAceleration;
        if (oldValue !== value)
        {
            this._1885997966wFireAceleration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "wFireAceleration", oldValue, value));
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
