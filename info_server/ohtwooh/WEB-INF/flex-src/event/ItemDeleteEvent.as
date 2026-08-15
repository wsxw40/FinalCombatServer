package event
{
	import flash.events.Event;

	public class ItemDeleteEvent extends Event
	{
		
		public static const ITEM_DELETE:String	= "itemDelete";
    	public var _data:Object;
		public function ItemDeleteEvent(type:String,data:Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this._data = data;
		}
        override public function clone():Event {
            return new LogSelectEvent(type, _data);
        }
		
	}
}