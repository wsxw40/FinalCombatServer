

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import Object;

class BindableProperty
{
	/**
	 * generated bindable wrapper for property data (override public)
	 * - generated setter
	 * - original getter left as-is
	 * - original override public setter 'data' moved to '_3076010data'
	 */

    [Bindable(event="propertyChange")]
    override public function set data(value:Object):void
    {
    	var oldValue:Object = this.data;
        if (oldValue !== value)
        {
            this._3076010data = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "data", oldValue, value));
        }
    }



}
