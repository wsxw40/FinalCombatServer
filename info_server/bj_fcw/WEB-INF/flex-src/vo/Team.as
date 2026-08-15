package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Team")]
	public class Team
	{
		public var id:int;
		public var name:String;
		public var declaration:String;
		public var description:String;
		public var board:String;
		public var logo:String;
		public var size:int;
		public var kill:int;
		public var headShot:int;
		public var gameWin:int;
		public var gameTotal:int;
		public var challengeWin:int;
		public var challengeTotal:int;
		public var createTimeStr:String;
		public var score:int;
		public var hitScore:int;
		public var gameRatio:String;
		public var challengeRatio:String;
		public var headerId:int;
		public var deleted:String;
		public var memberCount:int;
		public var leaderName:String;
	}
}