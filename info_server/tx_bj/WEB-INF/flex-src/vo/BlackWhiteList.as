package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.BlackWhiteList")]
	public class BlackWhiteList
	{
		public var id:int;
		public var ip:String;
		public var isBanned:String="Y";
		public var bannedTime:int=0;
		public var description:String;
		public var createTime:int;
		public var isChanged:int=0;
		public var createTimeStr:String;
	}
}