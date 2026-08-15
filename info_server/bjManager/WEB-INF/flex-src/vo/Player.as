package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.manager.pojo.PlayerVO")]	
	public class Player
	{
		public function Player()
		{
		}

		public var 		id:int;
		public var 		userName:String;
		public	var		sysCharacterId:int;
		public var		gender:String;
		public var		name:String;
		public	var		exp:int;
		public	var		rank:int;
		public var		WPackSize:int;
		public var		CPackSize:int;
		public	var		GPoint:int;
		public	var		GScore:int;
		public	var		credit:int;
		public	var		voucher:int;
		public var		deleted:String;
		public var 		blackWhite:String;
		public var 		isChange:int=0;	
		public var 		characters:String;	
		public var 		character1W:String;	
		public var 		character2W:String;	
		public var 		character3W:String;	
		public var 		character4W:String;	
		public var 		character5W:String;		
		public var 		character6W:String;	
		public var 		tutorial:String;
		public var 		lastLogin:String;	
		public var 		lastLogout:String;
		public var 		isOnline:int;
		public var 		createTime:String;	
		[Transient]
		public var selected:Boolean = false;
	}
}