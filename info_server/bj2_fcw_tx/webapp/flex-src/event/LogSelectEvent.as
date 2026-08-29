package event
{
	import flash.events.Event;

	public class LogSelectEvent extends Event
	{
		
		public static const LOG_SELECT:String	= "logSelect";
    	public var selectedItems:Object;
		public function LogSelectEvent(type:String,data:Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.selectedItems = data;
		}
        override public function clone():Event {
            return new LogSelectEvent(type, selectedItems);
        }
		
	}
}