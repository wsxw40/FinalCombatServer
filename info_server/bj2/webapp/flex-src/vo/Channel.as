package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Channel")]	
	public class Channel
	{
		public function Channel()
		{
		}
		public var id:int=0;
		public var name:String;
		public var maxOnline:int=500;
		public var max:int = 500;
		public var min:int = 1;
		public var serverId:int;
		public var channelId:int;
		public var isTcp:int=0;
		public var isChange:int;
		public var maxTeam:int = 10;
		public var minTeam:int = 1;
	}
}