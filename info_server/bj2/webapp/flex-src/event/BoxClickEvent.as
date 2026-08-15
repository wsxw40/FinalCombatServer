package event
{
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;

	public class BoxClickEvent extends Event
	{
		
		public static const BOX_CLICK:String	= "boxClick";
    	public var _data:Object;
		public function BoxClickEvent(type:String,data:Object, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this._data = data;
		}
       
		
	}
}