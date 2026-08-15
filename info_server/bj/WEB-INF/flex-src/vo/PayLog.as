package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.PayLog")]
	public class PayLog
	{
		public var id:int;
		public var userId:String;
		public var playerId:int;
		public var itemId:int;
		public var itemName:String;
		public var itemType:int;
		public var leftMoney:int;
		public var payType:int;
		public var payAmount:int;
		public var createTime:Date;
		public var createTimeStr:String;
		public var payUse:int;
	}
}