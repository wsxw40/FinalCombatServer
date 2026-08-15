package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Payment")]
	public class Payment
	{
		public var id:int;
		public var itemId:int=0;
		public var payType:int=0;
		public var unitType:int=0;
		public var cost:int=0;
		public var unit:int=1;
		public var isChange:int=0;
		public var isShow:int=1;
		public var level:int=0;
		public var finishPayType:int=0;
		public var finishCost:int=0;
	}
}