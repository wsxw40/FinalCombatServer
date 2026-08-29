package event
{
	import flash.events.Event;
	
	import vo.GmGroup;

	public class GmGroupEvent extends Event
	{
		public static const CREATE_GM_GROUP:String = "createGmGroup";
		public static const DELETE_GM_GROUP:String = "deleteGmGroup";
		public static const UPDATE_GM_GROUP:String = "updateGmGroup";
		
		public var gmGroup:GmGroup;
		
		public function GmGroupEvent(gmGroup:GmGroup, type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.gmGroup = gmGroup;
		}
		
	}
}