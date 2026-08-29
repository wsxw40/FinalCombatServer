package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Player")]	
	public class Player
	{
		public function Player()
		{
		}

		public var 		id:int;
		public var 		userId:int;
		public	var		sysCharacterId:int;
		public var		gender:String;
		public var		name:String;
		public var		teamId:int;
		public var		laborUnionId:int;
		public	var		exp:int;
		public	var		rank:int;
		public var		wPackSize:int;
		public var		cPackSize:int;
		public	var		GPoint:int;
		public var		deleted:String;
		
		public var		victoryRate:Number;
		public var		killRate:Number;
		public var		victoryNum:int;
		public var		failureNum:int;
		public var		killNum:int;
		public var		deathNum:int;
		public var		headshotNum:int;
		public var		resourceP:String;
		public var		resourceT:String;
		
		//Join from table SYS_CHARACTER
		public var		runSpeed:Number;
		public var		walkSpeed:Number;
		public	var		crouchSpeed:Number;
		public var 		accelSpeed:Number;
		public var		jumpSpeed:Number;
		public var 		throwSpeed:Number;
		
		//Join from table GROUP
		public var		team:String;
		
		//Join from table UNION
		public var		laborUnion:String;	
		
		public var isChange:int=0;	
	}
}