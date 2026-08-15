

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
	 * generated bindable wrapper for property cid (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'cid' moved to '_98494cid'
	 */

    [Bindable(event="propertyChange")]
    public function get cid():int
    {
        return this._98494cid;
    }

    public function set cid(value:int):void
    {
    	var oldValue:Object = this._98494cid;
        if (oldValue !== value)
        {
            this._98494cid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "cid", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property sid (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'sid' moved to '_113870sid'
	 */

    [Bindable(event="propertyChange")]
    public function get sid():int
    {
        return this._113870sid;
    }

    public function set sid(value:int):void
    {
    	var oldValue:Object = this._113870sid;
        if (oldValue !== value)
        {
            this._113870sid = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "sid", oldValue, value));
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
	 * generated bindable wrapper for property weapons (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'weapons' moved to '_1223328215weapons'
	 */

    [Bindable(event="propertyChange")]
    public function get weapons():String
    {
        return this._1223328215weapons;
    }

    public function set weapons(value:String):void
    {
    	var oldValue:Object = this._1223328215weapons;
        if (oldValue !== value)
        {
            this._1223328215weapons = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "weapons", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property costumes (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'costumes' moved to '_423700845costumes'
	 */

    [Bindable(event="propertyChange")]
    public function get costumes():String
    {
        return this._423700845costumes;
    }

    public function set costumes(value:String):void
    {
    	var oldValue:Object = this._423700845costumes;
        if (oldValue !== value)
        {
            this._423700845costumes = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "costumes", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property nameCN (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'nameCN' moved to '_1052832714nameCN'
	 */

    [Bindable(event="propertyChange")]
    public function get nameCN():String
    {
        return this._1052832714nameCN;
    }

    public function set nameCN(value:String):void
    {
    	var oldValue:Object = this._1052832714nameCN;
        if (oldValue !== value)
        {
            this._1052832714nameCN = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "nameCN", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _runSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_runSpeed' moved to '_895444443_runSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _runSpeed():Number
    {
        return this._895444443_runSpeed;
    }

    public function set _runSpeed(value:Number):void
    {
    	var oldValue:Object = this._895444443_runSpeed;
        if (oldValue !== value)
        {
            this._895444443_runSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_runSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _walkSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_walkSpeed' moved to '_69194113_walkSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _walkSpeed():Number
    {
        return this._69194113_walkSpeed;
    }

    public function set _walkSpeed(value:Number):void
    {
    	var oldValue:Object = this._69194113_walkSpeed;
        if (oldValue !== value)
        {
            this._69194113_walkSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_walkSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _crouchSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_crouchSpeed' moved to '_653163758_crouchSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _crouchSpeed():Number
    {
        return this._653163758_crouchSpeed;
    }

    public function set _crouchSpeed(value:Number):void
    {
    	var oldValue:Object = this._653163758_crouchSpeed;
        if (oldValue !== value)
        {
            this._653163758_crouchSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_crouchSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _accelSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_accelSpeed' moved to '_282833442_accelSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _accelSpeed():Number
    {
        return this._282833442_accelSpeed;
    }

    public function set _accelSpeed(value:Number):void
    {
    	var oldValue:Object = this._282833442_accelSpeed;
        if (oldValue !== value)
        {
            this._282833442_accelSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_accelSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _jumpSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_jumpSpeed' moved to '_814972102_jumpSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _jumpSpeed():Number
    {
        return this._814972102_jumpSpeed;
    }

    public function set _jumpSpeed(value:Number):void
    {
    	var oldValue:Object = this._814972102_jumpSpeed;
        if (oldValue !== value)
        {
            this._814972102_jumpSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_jumpSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _throwSpeed (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_throwSpeed' moved to '_803413664_throwSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function get _throwSpeed():Number
    {
        return this._803413664_throwSpeed;
    }

    public function set _throwSpeed(value:Number):void
    {
    	var oldValue:Object = this._803413664_throwSpeed;
        if (oldValue !== value)
        {
            this._803413664_throwSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_throwSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property costumeResource (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'costumeResource' moved to '_827314674costumeResource'
	 */

    [Bindable(event="propertyChange")]
    public function get costumeResource():String
    {
        return this._827314674costumeResource;
    }

    public function set costumeResource(value:String):void
    {
    	var oldValue:Object = this._827314674costumeResource;
        if (oldValue !== value)
        {
            this._827314674costumeResource = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "costumeResource", oldValue, value));
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
	 * generated bindable wrapper for property isFired (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isFired' moved to '_2058845668isFired'
	 */

    [Bindable(event="propertyChange")]
    public function get isFired():int
    {
        return this._2058845668isFired;
    }

    public function set isFired(value:int):void
    {
    	var oldValue:Object = this._2058845668isFired;
        if (oldValue !== value)
        {
            this._2058845668isFired = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isFired", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property resourceName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'resourceName' moved to '_384566343resourceName'
	 */

    [Bindable(event="propertyChange")]
    public function get resourceName():String
    {
        return this._384566343resourceName;
    }

    public function set resourceName(value:String):void
    {
    	var oldValue:Object = this._384566343resourceName;
        if (oldValue !== value)
        {
            this._384566343resourceName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "resourceName", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _controllerHeight (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_controllerHeight' moved to '_463790526_controllerHeight'
	 */

    [Bindable(event="propertyChange")]
    public function get _controllerHeight():Number
    {
        return this._463790526_controllerHeight;
    }

    public function set _controllerHeight(value:Number):void
    {
    	var oldValue:Object = this._463790526_controllerHeight;
        if (oldValue !== value)
        {
            this._463790526_controllerHeight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_controllerHeight", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _controllerRadius (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_controllerRadius' moved to '_181339731_controllerRadius'
	 */

    [Bindable(event="propertyChange")]
    public function get _controllerRadius():Number
    {
        return this._181339731_controllerRadius;
    }

    public function set _controllerRadius(value:Number):void
    {
    	var oldValue:Object = this._181339731_controllerRadius;
        if (oldValue !== value)
        {
            this._181339731_controllerRadius = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_controllerRadius", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property _controllerCrouchHeight (public)
	 * - generated setter
	 * - generated getter
	 * - original public var '_controllerCrouchHeight' moved to '_814724004_controllerCrouchHeight'
	 */

    [Bindable(event="propertyChange")]
    public function get _controllerCrouchHeight():Number
    {
        return this._814724004_controllerCrouchHeight;
    }

    public function set _controllerCrouchHeight(value:Number):void
    {
    	var oldValue:Object = this._814724004_controllerCrouchHeight;
        if (oldValue !== value)
        {
            this._814724004_controllerCrouchHeight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "_controllerCrouchHeight", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property scoreOffset (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'scoreOffset' moved to '_1384569285scoreOffset'
	 */

    [Bindable(event="propertyChange")]
    public function get scoreOffset():int
    {
        return this._1384569285scoreOffset;
    }

    public function set scoreOffset(value:int):void
    {
    	var oldValue:Object = this._1384569285scoreOffset;
        if (oldValue !== value)
        {
            this._1384569285scoreOffset = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "scoreOffset", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isEnable (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isEnable' moved to '_624814259isEnable'
	 */

    [Bindable(event="propertyChange")]
    public function get isEnable():int
    {
        return this._624814259isEnable;
    }

    public function set isEnable(value:int):void
    {
    	var oldValue:Object = this._624814259isEnable;
        if (oldValue !== value)
        {
            this._624814259isEnable = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isEnable", oldValue, value));
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
	 * generated bindable wrapper for property maxHp (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'maxHp' moved to '_103671180maxHp'
	 */

    [Bindable(event="propertyChange")]
    public function get maxHp():int
    {
        return this._103671180maxHp;
    }

    public function set maxHp(value:int):void
    {
    	var oldValue:Object = this._103671180maxHp;
        if (oldValue !== value)
        {
            this._103671180maxHp = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "maxHp", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property exHp (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'exHp' moved to '_3126555exHp'
	 */

    [Bindable(event="propertyChange")]
    public function get exHp():int
    {
        return this._3126555exHp;
    }

    public function set exHp(value:int):void
    {
    	var oldValue:Object = this._3126555exHp;
        if (oldValue !== value)
        {
            this._3126555exHp = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "exHp", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property defaultLevel (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'defaultLevel' moved to '_668232253defaultLevel'
	 */

    [Bindable(event="propertyChange")]
    public function get defaultLevel():int
    {
        return this._668232253defaultLevel;
    }

    public function set defaultLevel(value:int):void
    {
    	var oldValue:Object = this._668232253defaultLevel;
        if (oldValue !== value)
        {
            this._668232253defaultLevel = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "defaultLevel", oldValue, value));
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
	 * generated bindable wrapper for property controllerHeight (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'controllerHeight' moved to '_179192861controllerHeight'
	 */

    [Bindable(event="propertyChange")]
    public function set controllerHeight(value:Number):void
    {
    	var oldValue:Object = this.controllerHeight;
        if (oldValue !== value)
        {
            this._179192861controllerHeight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "controllerHeight", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property controllerRadius (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'controllerRadius' moved to '_103257934controllerRadius'
	 */

    [Bindable(event="propertyChange")]
    public function set controllerRadius(value:Number):void
    {
    	var oldValue:Object = this.controllerRadius;
        if (oldValue !== value)
        {
            this._103257934controllerRadius = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "controllerRadius", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property controllerCrouchHeight (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'controllerCrouchHeight' moved to '_1191734589controllerCrouchHeight'
	 */

    [Bindable(event="propertyChange")]
    public function set controllerCrouchHeight(value:Number):void
    {
    	var oldValue:Object = this.controllerCrouchHeight;
        if (oldValue !== value)
        {
            this._1191734589controllerCrouchHeight = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "controllerCrouchHeight", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property accelSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'accelSpeed' moved to '_1371146177accelSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set accelSpeed(value:Number):void
    {
    	var oldValue:Object = this.accelSpeed;
        if (oldValue !== value)
        {
            this._1371146177accelSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "accelSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property runSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'runSpeed' moved to '_804926588runSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set runSpeed(value:Number):void
    {
    	var oldValue:Object = this.runSpeed;
        if (oldValue !== value)
        {
            this._804926588runSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "runSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property walkSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'walkSpeed' moved to '_1419719678walkSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set walkSpeed(value:Number):void
    {
    	var oldValue:Object = this.walkSpeed;
        if (oldValue !== value)
        {
            this._1419719678walkSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "walkSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property crouchSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'crouchSpeed' moved to '_1275207341crouchSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set crouchSpeed(value:Number):void
    {
    	var oldValue:Object = this.crouchSpeed;
        if (oldValue !== value)
        {
            this._1275207341crouchSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "crouchSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property jumpSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'jumpSpeed' moved to '_673941689jumpSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set jumpSpeed(value:Number):void
    {
    	var oldValue:Object = this.jumpSpeed;
        if (oldValue !== value)
        {
            this._673941689jumpSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "jumpSpeed", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property throwSpeed (public)
	 * - generated setter
	 * - original getter left as-is
	 * - original public setter 'throwSpeed' moved to '_284899071throwSpeed'
	 */

    [Bindable(event="propertyChange")]
    public function set throwSpeed(value:Number):void
    {
    	var oldValue:Object = this.throwSpeed;
        if (oldValue !== value)
        {
            this._284899071throwSpeed = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "throwSpeed", oldValue, value));
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
