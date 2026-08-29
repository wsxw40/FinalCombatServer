package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.manager.pojo.GmUser")]	
	public class GmUser
	{
		public var 		id:int = 0;
		public var 		userName:String;
		public var		password:String;
		public var		name:String;
		public var		deleted:String;
		public var		creatorId:int;
		public var		creatorName:String;
		public var 		groups:String;
	}
}