package event
{
	import flash.events.Event;
	
	import vo.GmUser;

	public class GmUserLoginEvent extends Event
	{
		public static const GM_USER_LOGIN:String = "gmUserLogin";
		
		public var gmUser:GmUser;
		
		public function GmUserLoginEvent(type:String, gmUser:GmUser, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			this.gmUser = gmUser;
		}
		
	}
}