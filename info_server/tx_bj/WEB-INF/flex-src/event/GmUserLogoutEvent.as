package event
{
	import flash.events.Event;
	
	import vo.GmUser;

	public class GmUserLogoutEvent extends Event
	{
		public static const GM_USER_LOGOUT:String = "gmUserLogout";
		
		
		
		public function GmUserLogoutEvent(type:String, bubbles:Boolean=false, cancelable:Boolean=false)
		{
			super(type, bubbles, cancelable);
			
		}
		
	}
}