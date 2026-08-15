package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.manager.pojo.ChargeLog")]
	public class ChargeLog
	{
		public var id:int;
		public var userId:String;
		public var orderId:String;
		public var playerId:int;
		public var rmb:int;
		public var amount:int;
		public var discount:int;
		public var createTime:Date;
		public var createTimeStr:String;
		public var type:int;
	}
}