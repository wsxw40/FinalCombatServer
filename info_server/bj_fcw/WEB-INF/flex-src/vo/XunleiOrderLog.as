package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.XunleiOrderLog")]
	public class XunleiOrderLog
	{
		public var id:int;
		public var userId:String;
		public var orderId:String;
		public var playerId:int;
		public var rmb:int;
		public var amount:int;
		public var discount:Number;
		public var createTime:Date;
		public var createTimeStr:String;
	}
}