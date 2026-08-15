package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Message")]
	public class Message
	{
		public var id:int;
		public var receiverId:int;
		public var subject:String;
		public var content:String;
		public var createdTimeStr:String;
		public var open:String;
		public var deleted:String;
		public var isAttached:String;
		public var createdTime:Date;
		
	}
}