package event
{
	import flash.events.Event;
	public class DataRefreshEvent extends Event
	{
		public static const REFREASH_DATA:String	= "refreshData";
		public function DataRefreshEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
		}
	}
}