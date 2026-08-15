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
		public var PRIV_SYSTEM:Boolean 			= false;
		public var DAYPAY_SYSTEM:Boolean 		= false;
		public var DAYINCOME_SYSTEM:Boolean 		= false;
		public var PRIV_PLAYER:Boolean 	= false;
		
		public function clearPrivilege():void{
			PRIV_LOG 			= false;
			PRIV_USER 			= false;
			PRIV_GROUP 			= false;
			PRIV_SYSTEM 			= false;
			DAYPAY_SYSTEM		=false;
			PRIV_PLAYER 	= false;
			DAYINCOME_SYSTEM =false;
		}
	}
}