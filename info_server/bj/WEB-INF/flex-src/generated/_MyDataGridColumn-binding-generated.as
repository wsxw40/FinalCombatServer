

import flash.events.Event;
import flash.events.EventDispatcher;
import flash.events.IEventDispatcher;
import mx.core.IPropertyChangeNotifier;
import mx.events.PropertyChangeEvent;
import mx.utils.ObjectProxy;
import mx.utils.UIDUtil;

import mx.collections.ArrayCollection;

class BindableProperty
{
	/**
	 * generated bindable wrapper for property selectItems (public)
	 * - generated setter
	 * - generated getter
	 * - original public var 'selectItems' moved to '_195133476selectItems'
	 */

    [Bindable(event="propertyChange")]
    public function get selectItems():ArrayCollection
    {
        return this._195133476selectItems;
    }

    public function set selectItems(value:ArrayCollection):void
    {
    	var oldValue:Object = this._195133476selectItems;
        if (oldValue !== value)
        {
            this._195133476selectItems = value;
            this.dispatchEvent(mx.events.PropertyChangeEvent.createUpdateEvent(this, "selectItems", oldValue, value));
        }
    }



}
