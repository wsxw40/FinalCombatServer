package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysChest")]
	
	public class SysChest
	{
		public function SysChest()
		{
		}
		public var id:int;
		public var type:int;
		public var level:int=1;
		public var sysItemId:int=0;
		public var weight:int;
		public var weight1:int;
		public var number:int;
		public var useType:int=1;
		public var isNotice:int;
		public var price:int;
		public var chipPrice:int;
		public var isDeleted:int;
		public var sysItemName:String;
	}
}