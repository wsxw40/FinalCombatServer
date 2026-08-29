package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysConfiguration")]
	public class SysConfig
	{
		public var id:int;
		public var key:String;
		public var value:String;
		public var confName:String;
		public function SysConfig()
		{
		}

	}
}