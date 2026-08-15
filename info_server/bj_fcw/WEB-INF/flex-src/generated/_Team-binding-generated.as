

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
	 * generated bindable wrapper for property declaration (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'declaration' moved to '_2028505734declaration'
	 */

    [Bindable(event="propertyChange")]
    public function get declaration():String
    {
        return this._2028505734declaration;
    }

    public function set declaration(value:String):void
    {
    	var oldValue:Object = this._2028505734declaration;
        if (oldValue !== value)
        {
            this._2028505734declaration = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "declaration", oldValue, value));
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
	 * generated bindable wrapper for property board (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'board' moved to '_93908710board'
	 */

    [Bindable(event="propertyChange")]
    public function get board():String
    {
        return this._93908710board;
    }

    public function set board(value:String):void
    {
    	var oldValue:Object = this._93908710board;
        if (oldValue !== value)
        {
            this._93908710board = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "board", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property logo (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'logo' moved to '_3327403logo'
	 */

    [Bindable(event="propertyChange")]
    public function get logo():String
    {
        return this._3327403logo;
    }

    public function set logo(value:String):void
    {
    	var oldValue:Object = this._3327403logo;
        if (oldValue !== value)
        {
            this._3327403logo = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "logo", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property size (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'size' moved to '_3530753size'
	 */

    [Bindable(event="propertyChange")]
    public function get size():int
    {
        return this._3530753size;
    }

    public function set size(value:int):void
    {
    	var oldValue:Object = this._3530753size;
        if (oldValue !== value)
        {
            this._3530753size = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "size", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property kill (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'kill' moved to '_3291998kill'
	 */

    [Bindable(event="propertyChange")]
    public function get kill():int
    {
        return this._3291998kill;
    }

    public function set kill(value:int):void
    {
    	var oldValue:Object = this._3291998kill;
        if (oldValue !== value)
        {
            this._3291998kill = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "kill", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property headShot (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'headShot' moved to '_1115804422headShot'
	 */

    [Bindable(event="propertyChange")]
    public function get headShot():int
    {
        return this._1115804422headShot;
    }

    public function set headShot(value:int):void
    {
    	var oldValue:Object = this._1115804422headShot;
        if (oldValue !== value)
        {
            this._1115804422headShot = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "headShot", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gameWin (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gameWin' moved to '_195614070gameWin'
	 */

    [Bindable(event="propertyChange")]
    public function get gameWin():int
    {
        return this._195614070gameWin;
    }

    public function set gameWin(value:int):void
    {
    	var oldValue:Object = this._195614070gameWin;
        if (oldValue !== value)
        {
            this._195614070gameWin = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gameWin", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gameTotal (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gameTotal' moved to '_990856818gameTotal'
	 */

    [Bindable(event="propertyChange")]
    public function get gameTotal():int
    {
        return this._990856818gameTotal;
    }

    public function set gameTotal(value:int):void
    {
    	var oldValue:Object = this._990856818gameTotal;
        if (oldValue !== value)
        {
            this._990856818gameTotal = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gameTotal", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property challengeWin (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'challengeWin' moved to '_112351353challengeWin'
	 */

    [Bindable(event="propertyChange")]
    public function get challengeWin():int
    {
        return this._112351353challengeWin;
    }

    public function set challengeWin(value:int):void
    {
    	var oldValue:Object = this._112351353challengeWin;
        if (oldValue !== value)
        {
            this._112351353challengeWin = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "challengeWin", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property challengeTotal (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'challengeTotal' moved to '_592884897challengeTotal'
	 */

    [Bindable(event="propertyChange")]
    public function get challengeTotal():int
    {
        return this._592884897challengeTotal;
    }

    public function set challengeTotal(value:int):void
    {
    	var oldValue:Object = this._592884897challengeTotal;
        if (oldValue !== value)
        {
            this._592884897challengeTotal = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "challengeTotal", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property createTimeStr (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'createTimeStr' moved to '_932579208createTimeStr'
	 */

    [Bindable(event="propertyChange")]
    public function get createTimeStr():String
    {
        return this._932579208createTimeStr;
    }

    public function set createTimeStr(value:String):void
    {
    	var oldValue:Object = this._932579208createTimeStr;
        if (oldValue !== value)
        {
            this._932579208createTimeStr = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "createTimeStr", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property score (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'score' moved to '_109264530score'
	 */

    [Bindable(event="propertyChange")]
    public function get score():int
    {
        return this._109264530score;
    }

    public function set score(value:int):void
    {
    	var oldValue:Object = this._109264530score;
        if (oldValue !== value)
        {
            this._109264530score = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "score", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property hitScore (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'hitScore' moved to '_1332019521hitScore'
	 */

    [Bindable(event="propertyChange")]
    public function get hitScore():int
    {
        return this._1332019521hitScore;
    }

    public function set hitScore(value:int):void
    {
    	var oldValue:Object = this._1332019521hitScore;
        if (oldValue !== value)
        {
            this._1332019521hitScore = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "hitScore", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property gameRatio (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'gameRatio' moved to '_988592953gameRatio'
	 */

    [Bindable(event="propertyChange")]
    public function get gameRatio():String
    {
        return this._988592953gameRatio;
    }

    public function set gameRatio(value:String):void
    {
    	var oldValue:Object = this._988592953gameRatio;
        if (oldValue !== value)
        {
            this._988592953gameRatio = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "gameRatio", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property challengeRatio (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'challengeRatio' moved to '_590621032challengeRatio'
	 */

    [Bindable(event="propertyChange")]
    public function get challengeRatio():String
    {
        return this._590621032challengeRatio;
    }

    public function set challengeRatio(value:String):void
    {
    	var oldValue:Object = this._590621032challengeRatio;
        if (oldValue !== value)
        {
            this._590621032challengeRatio = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "challengeRatio", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property headerId (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'headerId' moved to '_1115259768headerId'
	 */

    [Bindable(event="propertyChange")]
    public function get headerId():int
    {
        return this._1115259768headerId;
    }

    public function set headerId(value:int):void
    {
    	var oldValue:Object = this._1115259768headerId;
        if (oldValue !== value)
        {
            this._1115259768headerId = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "headerId", oldValue, value));
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
	 * generated bindable wrapper for property memberCount (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'memberCount' moved to '_1358063253memberCount'
	 */

    [Bindable(event="propertyChange")]
    public function get memberCount():int
    {
        return this._1358063253memberCount;
    }

    public function set memberCount(value:int):void
    {
    	var oldValue:Object = this._1358063253memberCount;
        if (oldValue !== value)
        {
            this._1358063253memberCount = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "memberCount", oldValue, value));
        }
    }

	/**
	 * generated bindable wrapper for property leaderName (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'leaderName' moved to '_1191282484leaderName'
	 */

    [Bindable(event="propertyChange")]
    public function get leaderName():String
    {
        return this._1191282484leaderName;
    }

    public function set leaderName(value:String):void
    {
    	var oldValue:Object = this._1191282484leaderName;
        if (oldValue !== value)
        {
            this._1191282484leaderName = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "leaderName", oldValue, value));
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
