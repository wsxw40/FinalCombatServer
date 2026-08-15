package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.GmReport")]
	
	public class GmReport
	{
		public function GmReport()
		{
		}
		
		public var id:int;
		public var reportPlayerId:int;
		public var reportPlayerName:String;
		public var targetPlayerId:int;
		public var targetPlayerName:String;
		public var msg:String;
		public var reportDate:Date;
		public var type:int;
		public var isHandle:String;
		public var handleTime:Date;
	}
}