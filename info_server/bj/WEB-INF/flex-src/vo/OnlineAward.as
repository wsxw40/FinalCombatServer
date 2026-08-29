package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.OnlineAward")]
	public class OnlineAward
	{
		public function OnlineAward()
		{
		}
		public var id:int;
		public var itemId:int;
		public var itemName:String;
		public var level:int;
		public var type:int;
		public var unit:int;
		public var unitType:int;
		public var weight:int;
		public var music:int;
	}
}