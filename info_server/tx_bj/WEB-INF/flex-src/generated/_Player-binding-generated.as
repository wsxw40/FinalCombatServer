

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

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
	 * generated bindable wrapper for property sysCharacterId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'sysCharacterId' moved to '_134231319sysCharacterId'
	 */

    [Bindable(event="propertyChange")]
    public function get sysCharacterId():int
    {
        return this._134231319sysCharacterId;
    }

    public function set sysCharacterId(value:int):void
    {
    	var oldValue:Object = this._134231319sysCharacterId;
        if (oldValue !== value)
        {
            this._134231319sysCharacterId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "sysCharacterId", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gender (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gender' moved to '_1249512767gender'
	 */

    [Bindable(event="propertyChange")]
    public function get gender():String
    {
        return this._1249512767gender;
    }

    public function set gender(value:String):void
    {
    	var oldValue:Object = this._1249512767gender;
        if (oldValue !== value)
        {
            this._1249512767gender = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gender", oldValue, value));
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
	 * generated bindable wrapper for property exp (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'exp' moved to '_100893exp'
	 */

    [Bindable(event="propertyChange")]
    public function get exp():int
    {
        return this._100893exp;
    }

    public function set exp(value:int):void
    {
    	var oldValue:Object = this._100893exp;
        if (oldValue !== value)
        {
            this._100893exp = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "exp", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property rank (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'rank' moved to '_3492908rank'
	 */

    [Bindable(event="propertyChange")]
    public function get rank():int
    {
        return this._3492908rank;
    }

    public function set rank(value:int):void
    {
    	var oldValue:Object = this._3492908rank;
        if (oldValue !== value)
        {
            this._3492908rank = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "rank", oldValue, value));
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
	 * generated bindable wrapper for property vipExp (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'vipExp' moved to '_816351040vipExp'
	 */

    [Bindable(event="propertyChange")]
    public function get vipExp():int
    {
        return this._816351040vipExp;
    }

    public function set vipExp(value:int):void
    {
    	var oldValue:Object = this._816351040vipExp;
        if (oldValue !== value)
        {
            this._816351040vipExp = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "vipExp", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property WPackSize (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'WPackSize' moved to '_1814618319WPackSize'
	 */

    [Bindable(event="propertyChange")]
    public function get WPackSize():int
    {
        return this._1814618319WPackSize;
    }

    public function set WPackSize(value:int):void
    {
    	var oldValue:Object = this._1814618319WPackSize;
        if (oldValue !== value)
        {
            this._1814618319WPackSize = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "WPackSize", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property CPackSize (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'CPackSize' moved to '_25267427CPackSize'
	 */

    [Bindable(event="propertyChange")]
    public function get CPackSize():int
    {
        return this._25267427CPackSize;
    }

    public function set CPackSize(value:int):void
    {
    	var oldValue:Object = this._25267427CPackSize;
        if (oldValue !== value)
        {
            this._25267427CPackSize = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "CPackSize", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property GPoint (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'GPoint' moved to '_2109962633GPoint'
	 */

    [Bindable(event="propertyChange")]
    public function get GPoint():int
    {
        return this._2109962633GPoint;
    }

    public function set GPoint(value:int):void
    {
    	var oldValue:Object = this._2109962633GPoint;
        if (oldValue !== value)
        {
            this._2109962633GPoint = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "GPoint", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property GScore (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'GScore' moved to '_2112381579GScore'
	 */

    [Bindable(event="propertyChange")]
    public function get GScore():int
    {
        return this._2112381579GScore;
    }

    public function set GScore(value:int):void
    {
    	var oldValue:Object = this._2112381579GScore;
        if (oldValue !== value)
        {
            this._2112381579GScore = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "GScore", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property credit (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'credit' moved to '_1352291591credit'
	 */

    [Bindable(event="propertyChange")]
    public function get credit():int
    {
        return this._1352291591credit;
    }

    public function set credit(value:int):void
    {
    	var oldValue:Object = this._1352291591credit;
        if (oldValue !== value)
        {
            this._1352291591credit = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "credit", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property voucher (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'voucher' moved to '_640192174voucher'
	 */

    [Bindable(event="propertyChange")]
    public function get voucher():int
    {
        return this._640192174voucher;
    }

    public function set voucher(value:int):void
    {
    	var oldValue:Object = this._640192174voucher;
        if (oldValue !== value)
        {
            this._640192174voucher = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "voucher", oldValue, value));
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
	 * generated bindable wrapper for property blackWhite (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'blackWhite' moved to '_1645710486blackWhite'
	 */

    [Bindable(event="propertyChange")]
    public function get blackWhite():String
    {
        return this._1645710486blackWhite;
    }

    public function set blackWhite(value:String):void
    {
    	var oldValue:Object = this._1645710486blackWhite;
        if (oldValue !== value)
        {
            this._1645710486blackWhite = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "blackWhite", oldValue, value));
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
	 * generated bindable wrapper for property characters (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'characters' moved to '_1245424234characters'
	 */

    [Bindable(event="propertyChange")]
    public function get characters():String
    {
        return this._1245424234characters;
    }

    public function set characters(value:String):void
    {
    	var oldValue:Object = this._1245424234characters;
        if (oldValue !== value)
        {
            this._1245424234characters = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "characters", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character1W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character1W' moved to '_46556369character1W'
	 */

    [Bindable(event="propertyChange")]
    public function get character1W():String
    {
        return this._46556369character1W;
    }

    public function set character1W(value:String):void
    {
    	var oldValue:Object = this._46556369character1W;
        if (oldValue !== value)
        {
            this._46556369character1W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character1W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character2W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character2W' moved to '_46556338character2W'
	 */

    [Bindable(event="propertyChange")]
    public function get character2W():String
    {
        return this._46556338character2W;
    }

    public function set character2W(value:String):void
    {
    	var oldValue:Object = this._46556338character2W;
        if (oldValue !== value)
        {
            this._46556338character2W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character2W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character3W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character3W' moved to '_46556307character3W'
	 */

    [Bindable(event="propertyChange")]
    public function get character3W():String
    {
        return this._46556307character3W;
    }

    public function set character3W(value:String):void
    {
    	var oldValue:Object = this._46556307character3W;
        if (oldValue !== value)
        {
            this._46556307character3W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character3W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character4W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character4W' moved to '_46556276character4W'
	 */

    [Bindable(event="propertyChange")]
    public function get character4W():String
    {
        return this._46556276character4W;
    }

    public function set character4W(value:String):void
    {
    	var oldValue:Object = this._46556276character4W;
        if (oldValue !== value)
        {
            this._46556276character4W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character4W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character5W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character5W' moved to '_46556245character5W'
	 */

    [Bindable(event="propertyChange")]
    public function get character5W():String
    {
        return this._46556245character5W;
    }

    public function set character5W(value:String):void
    {
    	var oldValue:Object = this._46556245character5W;
        if (oldValue !== value)
        {
            this._46556245character5W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character5W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character6W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character6W' moved to '_46556214character6W'
	 */

    [Bindable(event="propertyChange")]
    public function get character6W():String
    {
        return this._46556214character6W;
    }

    public function set character6W(value:String):void
    {
    	var oldValue:Object = this._46556214character6W;
        if (oldValue !== value)
        {
            this._46556214character6W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character6W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property character7W (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'character7W' moved to '_46556183character7W'
	 */

    [Bindable(event="propertyChange")]
    public function get character7W():String
    {
        return this._46556183character7W;
    }

    public function set character7W(value:String):void
    {
    	var oldValue:Object = this._46556183character7W;
        if (oldValue !== value)
        {
            this._46556183character7W = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "character7W", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property tutorial (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'tutorial' moved to '_193276766tutorial'
	 */

    [Bindable(event="propertyChange")]
    public function get tutorial():String
    {
        return this._193276766tutorial;
    }

    public function set tutorial(value:String):void
    {
    	var oldValue:Object = this._193276766tutorial;
        if (oldValue !== value)
        {
            this._193276766tutorial = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "tutorial", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property lastLogin (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'lastLogin' moved to '_1995610739lastLogin'
	 */

    [Bindable(event="propertyChange")]
    public function get lastLogin():String
    {
        return this._1995610739lastLogin;
    }

    public function set lastLogin(value:String):void
    {
    	var oldValue:Object = this._1995610739lastLogin;
        if (oldValue !== value)
        {
            this._1995610739lastLogin = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastLogin", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property lastLogout (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'lastLogout' moved to '_1734396864lastLogout'
	 */

    [Bindable(event="propertyChange")]
    public function get lastLogout():String
    {
        return this._1734396864lastLogout;
    }

    public function set lastLogout(value:String):void
    {
    	var oldValue:Object = this._1734396864lastLogout;
        if (oldValue !== value)
        {
            this._1734396864lastLogout = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "lastLogout", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property isOnline (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'isOnline' moved to '_338188259isOnline'
	 */

    [Bindable(event="propertyChange")]
    public function get isOnline():int
    {
        return this._338188259isOnline;
    }

    public function set isOnline(value:int):void
    {
    	var oldValue:Object = this._338188259isOnline;
        if (oldValue !== value)
        {
            this._338188259isOnline = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "isOnline", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createTime (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createTime' moved to '_1369213417createTime'
	 */

    [Bindable(event="propertyChange")]
    public function get createTime():String
    {
        return this._1369213417createTime;
    }

    public function set createTime(value:String):void
    {
    	var oldValue:Object = this._1369213417createTime;
        if (oldValue !== value)
        {
            this._1369213417createTime = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createTime", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property unusableResource (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'unusableResource' moved to '_2055791615unusableResource'
	 */

    [Bindable(event="propertyChange")]
    public function get unusableResource():int
    {
        return this._2055791615unusableResource;
    }

    public function set unusableResource(value:int):void
    {
    	var oldValue:Object = this._2055791615unusableResource;
        if (oldValue !== value)
        {
            this._2055791615unusableResource = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "unusableResource", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property usableResource (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'usableResource' moved to '_963193562usableResource'
	 */

    [Bindable(event="propertyChange")]
    public function get usableResource():int
    {
        return this._963193562usableResource;
    }

    public function set usableResource(value:int):void
    {
    	var oldValue:Object = this._963193562usableResource;
        if (oldValue !== value)
        {
            this._963193562usableResource = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "usableResource", oldValue, value));
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
