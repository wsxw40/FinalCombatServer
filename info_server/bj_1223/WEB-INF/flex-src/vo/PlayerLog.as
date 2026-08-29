package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.nosql.object.playerevent.BasePlayerLogEvent")]
	public class PlayerLog
	{
		public var content:String;
		public var timeStr:String;
		public var time:int;
		public var playerName:String;
		public var playerId:int;
		public var type:int;
	}
}