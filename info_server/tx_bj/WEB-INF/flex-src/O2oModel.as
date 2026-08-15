package
{
	import vo.GmUser;
	
	//import vo.GmUser;
	
	[Bindable]
	public class O2oModel
	{
		private static var o2oModel:O2oModel;
		
		public static function getInstance():O2oModel{
			if(o2oModel == null){
				o2oModel = new O2oModel();
			}
			return o2oModel;
		}
		
		public var gmUser:GmUser;	
		
//============================================================================================
//			GM Privileges
//============================================================================================
		public var PRIV_LOG:Boolean 			= false;
		public var PRIV_USER:Boolean 			= false;
		public var PRIV_GROUP:Boolean 			= false;
		public var PRIV_SYSITEM:Boolean 		= false;
		public var PRIV_SYSITEM_PRICE:Boolean 	= false;
		public var PRIV_CHARACTER:Boolean 		= false;
		public var PRIV_BIO_CHARACTER:Boolean 		= false;
		public var PRIV_MAPS:Boolean 			= false;
		public var PRIV_PLAYER:Boolean 	= false;
		public var PRIV_PLAYER_LOG:Boolean 			= false;
		public var PRIV_GM_EMAIL:Boolean 			= false;
		public var PRIV_SERVER:Boolean 		= false;
		public var PRIV_SYSTEM_CONFIG:Boolean 	= false;
		public var PRIV_REGULAR_NOTICE:Boolean = false;
		public var PRIV_BLACK_IP:Boolean 	= false;
		public var PRIV_BANNED_WORD:Boolean 		= false;
		public var PRIV_BLACK_WHITE:Boolean 			= false;
		public var PRIV_PAY_LOG:Boolean 	= false;
		public var PRIV_TEAM_LOG:Boolean 	= false;
		public var PRIV_ACTIVITY:Boolean 	= false;
		public var PRIV_ACTIVITYS:Boolean 	= false;
		public var PRIV_ONLINE_AWARD:Boolean= false;
		public var PRIV_STRENGTHEN_APPEND:Boolean=false;
		
		public function clearPrivilege():void{
			PRIV_LOG 			= false;
			PRIV_USER 			= false;
			PRIV_GROUP 			= false;
			PRIV_SYSITEM 		= false;
			PRIV_SYSITEM_PRICE 	= false;
			PRIV_CHARACTER 		= false;
			PRIV_BIO_CHARACTER 		= false;
			PRIV_MAPS 			= false;
			PRIV_PLAYER 	= false;
			PRIV_PLAYER_LOG 			= false;
			PRIV_GM_EMAIL 			= false;
			PRIV_SERVER 		= false;
			PRIV_SYSTEM_CONFIG 	= false;
			PRIV_REGULAR_NOTICE = false;
			PRIV_BLACK_IP 	= false;
			PRIV_BANNED_WORD 		= false;
			PRIV_BLACK_WHITE 			= false;
			PRIV_PAY_LOG 	= false;
			PRIV_TEAM_LOG 	= false;
			PRIV_ACTIVITY 	= false;
			PRIV_ACTIVITYS 	= false;
			PRIV_ONLINE_AWARD= false;
			PRIV_STRENGTHEN_APPEND=false;
		}
	}
}