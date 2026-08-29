

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Boolean;

class BindableProperty
    implements flash.events.IEventDispatcher
{
	/**
	 * generated bindable wrapper for property gmUser (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gmUser' moved to '_1242854959gmUser'
	 */

    [Bindable(event="propertyChange")]
    public function get gmUser():GmUser
    {
        return this._1242854959gmUser;
    }

    public function set gmUser(value:GmUser):void
    {
    	var oldValue:Object = this._1242854959gmUser;
        if (oldValue !== value)
        {
            this._1242854959gmUser = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gmUser", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_LOG (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_LOG' moved to '_375979628PRIV_LOG'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_LOG():Boolean
    {
        return this._375979628PRIV_LOG;
    }

    public function set PRIV_LOG(value:Boolean):void
    {
    	var oldValue:Object = this._375979628PRIV_LOG;
        if (oldValue !== value)
        {
            this._375979628PRIV_LOG = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_LOG", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_USER (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_USER' moved to '_1229805403PRIV_USER'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_USER():Boolean
    {
        return this._1229805403PRIV_USER;
    }

    public function set PRIV_USER(value:Boolean):void
    {
    	var oldValue:Object = this._1229805403PRIV_USER;
        if (oldValue !== value)
        {
            this._1229805403PRIV_USER = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_USER", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_GROUP (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_GROUP' moved to '_543687473PRIV_GROUP'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_GROUP():Boolean
    {
        return this._543687473PRIV_GROUP;
    }

    public function set PRIV_GROUP(value:Boolean):void
    {
    	var oldValue:Object = this._543687473PRIV_GROUP;
        if (oldValue !== value)
        {
            this._543687473PRIV_GROUP = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_GROUP", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_SYSITEM (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_SYSITEM' moved to '_528762448PRIV_SYSITEM'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_SYSITEM():Boolean
    {
        return this._528762448PRIV_SYSITEM;
    }

    public function set PRIV_SYSITEM(value:Boolean):void
    {
    	var oldValue:Object = this._528762448PRIV_SYSITEM;
        if (oldValue !== value)
        {
            this._528762448PRIV_SYSITEM = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_SYSITEM", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_SYSITEM_PRICE (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_SYSITEM_PRICE' moved to '_1107308070PRIV_SYSITEM_PRICE'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_SYSITEM_PRICE():Boolean
    {
        return this._1107308070PRIV_SYSITEM_PRICE;
    }

    public function set PRIV_SYSITEM_PRICE(value:Boolean):void
    {
    	var oldValue:Object = this._1107308070PRIV_SYSITEM_PRICE;
        if (oldValue !== value)
        {
            this._1107308070PRIV_SYSITEM_PRICE = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_SYSITEM_PRICE", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_CHARACTER (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_CHARACTER' moved to '_598208135PRIV_CHARACTER'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_CHARACTER():Boolean
    {
        return this._598208135PRIV_CHARACTER;
    }

    public function set PRIV_CHARACTER(value:Boolean):void
    {
    	var oldValue:Object = this._598208135PRIV_CHARACTER;
        if (oldValue !== value)
        {
            this._598208135PRIV_CHARACTER = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_CHARACTER", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_BIO_CHARACTER (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_BIO_CHARACTER' moved to '_466398494PRIV_BIO_CHARACTER'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_BIO_CHARACTER():Boolean
    {
        return this._466398494PRIV_BIO_CHARACTER;
    }

    public function set PRIV_BIO_CHARACTER(value:Boolean):void
    {
    	var oldValue:Object = this._466398494PRIV_BIO_CHARACTER;
        if (oldValue !== value)
        {
            this._466398494PRIV_BIO_CHARACTER = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_BIO_CHARACTER", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_MAPS (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_MAPS' moved to '_1229550119PRIV_MAPS'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_MAPS():Boolean
    {
        return this._1229550119PRIV_MAPS;
    }

    public function set PRIV_MAPS(value:Boolean):void
    {
    	var oldValue:Object = this._1229550119PRIV_MAPS;
        if (oldValue !== value)
        {
            this._1229550119PRIV_MAPS = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_MAPS", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_PLAYER (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_PLAYER' moved to '_577265265PRIV_PLAYER'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_PLAYER():Boolean
    {
        return this._577265265PRIV_PLAYER;
    }

    public function set PRIV_PLAYER(value:Boolean):void
    {
    	var oldValue:Object = this._577265265PRIV_PLAYER;
        if (oldValue !== value)
        {
            this._577265265PRIV_PLAYER = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_PLAYER", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_PLAYER_LOG (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_PLAYER_LOG' moved to '_512879530PRIV_PLAYER_LOG'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_PLAYER_LOG():Boolean
    {
        return this._512879530PRIV_PLAYER_LOG;
    }

    public function set PRIV_PLAYER_LOG(value:Boolean):void
    {
    	var oldValue:Object = this._512879530PRIV_PLAYER_LOG;
        if (oldValue !== value)
        {
            this._512879530PRIV_PLAYER_LOG = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_PLAYER_LOG", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_GM_EMAIL (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_GM_EMAIL' moved to '_371120525PRIV_GM_EMAIL'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_GM_EMAIL():Boolean
    {
        return this._371120525PRIV_GM_EMAIL;
    }

    public function set PRIV_GM_EMAIL(value:Boolean):void
    {
    	var oldValue:Object = this._371120525PRIV_GM_EMAIL;
        if (oldValue !== value)
        {
            this._371120525PRIV_GM_EMAIL = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_GM_EMAIL", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_SERVER (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_SERVER' moved to '_657191635PRIV_SERVER'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_SERVER():Boolean
    {
        return this._657191635PRIV_SERVER;
    }

    public function set PRIV_SERVER(value:Boolean):void
    {
    	var oldValue:Object = this._657191635PRIV_SERVER;
        if (oldValue !== value)
        {
            this._657191635PRIV_SERVER = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_SERVER", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_SYSTEM_CONFIG (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_SYSTEM_CONFIG' moved to '_1418140098PRIV_SYSTEM_CONFIG'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_SYSTEM_CONFIG():Boolean
    {
        return this._1418140098PRIV_SYSTEM_CONFIG;
    }

    public function set PRIV_SYSTEM_CONFIG(value:Boolean):void
    {
    	var oldValue:Object = this._1418140098PRIV_SYSTEM_CONFIG;
        if (oldValue !== value)
        {
            this._1418140098PRIV_SYSTEM_CONFIG = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_SYSTEM_CONFIG", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_REGULAR_NOTICE (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_REGULAR_NOTICE' moved to '_750680203PRIV_REGULAR_NOTICE'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_REGULAR_NOTICE():Boolean
    {
        return this._750680203PRIV_REGULAR_NOTICE;
    }

    public function set PRIV_REGULAR_NOTICE(value:Boolean):void
    {
    	var oldValue:Object = this._750680203PRIV_REGULAR_NOTICE;
        if (oldValue !== value)
        {
            this._750680203PRIV_REGULAR_NOTICE = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_REGULAR_NOTICE", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_BLACK_IP (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_BLACK_IP' moved to '_2051473687PRIV_BLACK_IP'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_BLACK_IP():Boolean
    {
        return this._2051473687PRIV_BLACK_IP;
    }

    public function set PRIV_BLACK_IP(value:Boolean):void
    {
    	var oldValue:Object = this._2051473687PRIV_BLACK_IP;
        if (oldValue !== value)
        {
            this._2051473687PRIV_BLACK_IP = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_BLACK_IP", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_BANNED_WORD (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_BANNED_WORD' moved to '_1583828485PRIV_BANNED_WORD'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_BANNED_WORD():Boolean
    {
        return this._1583828485PRIV_BANNED_WORD;
    }

    public function set PRIV_BANNED_WORD(value:Boolean):void
    {
    	var oldValue:Object = this._1583828485PRIV_BANNED_WORD;
        if (oldValue !== value)
        {
            this._1583828485PRIV_BANNED_WORD = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_BANNED_WORD", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_BLACK_WHITE (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_BLACK_WHITE' moved to '_1919248871PRIV_BLACK_WHITE'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_BLACK_WHITE():Boolean
    {
        return this._1919248871PRIV_BLACK_WHITE;
    }

    public function set PRIV_BLACK_WHITE(value:Boolean):void
    {
    	var oldValue:Object = this._1919248871PRIV_BLACK_WHITE;
        if (oldValue !== value)
        {
            this._1919248871PRIV_BLACK_WHITE = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_BLACK_WHITE", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_PAY_LOG (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_PAY_LOG' moved to '_422783325PRIV_PAY_LOG'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_PAY_LOG():Boolean
    {
        return this._422783325PRIV_PAY_LOG;
    }

    public function set PRIV_PAY_LOG(value:Boolean):void
    {
    	var oldValue:Object = this._422783325PRIV_PAY_LOG;
        if (oldValue !== value)
        {
            this._422783325PRIV_PAY_LOG = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_PAY_LOG", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_TEAM_LOG (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_TEAM_LOG' moved to '_1449543122PRIV_TEAM_LOG'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_TEAM_LOG():Boolean
    {
        return this._1449543122PRIV_TEAM_LOG;
    }

    public function set PRIV_TEAM_LOG(value:Boolean):void
    {
    	var oldValue:Object = this._1449543122PRIV_TEAM_LOG;
        if (oldValue !== value)
        {
            this._1449543122PRIV_TEAM_LOG = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_TEAM_LOG", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_ACTIVITY (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_ACTIVITY' moved to '_1460866719PRIV_ACTIVITY'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_ACTIVITY():Boolean
    {
        return this._1460866719PRIV_ACTIVITY;
    }

    public function set PRIV_ACTIVITY(value:Boolean):void
    {
    	var oldValue:Object = this._1460866719PRIV_ACTIVITY;
        if (oldValue !== value)
        {
            this._1460866719PRIV_ACTIVITY = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_ACTIVITY", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_ACTIVITYS (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_ACTIVITYS' moved to '_1957771884PRIV_ACTIVITYS'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_ACTIVITYS():Boolean
    {
        return this._1957771884PRIV_ACTIVITYS;
    }

    public function set PRIV_ACTIVITYS(value:Boolean):void
    {
    	var oldValue:Object = this._1957771884PRIV_ACTIVITYS;
        if (oldValue !== value)
        {
            this._1957771884PRIV_ACTIVITYS = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_ACTIVITYS", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_ONLINE_AWARD (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_ONLINE_AWARD' moved to '_1848301343PRIV_ONLINE_AWARD'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_ONLINE_AWARD():Boolean
    {
        return this._1848301343PRIV_ONLINE_AWARD;
    }

    public function set PRIV_ONLINE_AWARD(value:Boolean):void
    {
    	var oldValue:Object = this._1848301343PRIV_ONLINE_AWARD;
        if (oldValue !== value)
        {
            this._1848301343PRIV_ONLINE_AWARD = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_ONLINE_AWARD", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property PRIV_STRENGTHEN_APPEND (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'PRIV_STRENGTHEN_APPEND' moved to '_220636863PRIV_STRENGTHEN_APPEND'
	 */

    [Bindable(event="propertyChange")]
    public function get PRIV_STRENGTHEN_APPEND():Boolean
    {
        return this._220636863PRIV_STRENGTHEN_APPEND;
    }

    public function set PRIV_STRENGTHEN_APPEND(value:Boolean):void
    {
    	var oldValue:Object = this._220636863PRIV_STRENGTHEN_APPEND;
        if (oldValue !== value)
        {
            this._220636863PRIV_STRENGTHEN_APPEND = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "PRIV_STRENGTHEN_APPEND", oldValue, value));
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
