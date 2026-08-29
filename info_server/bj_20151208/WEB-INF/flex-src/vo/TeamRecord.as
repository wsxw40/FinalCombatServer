package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.TeamRecord")]
	
	public class TeamRecord
	{
		public var team:int;
		public var type:int;
		public var createTime:Date;
		public var playerid:int;
	}
}