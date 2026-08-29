package vo
{	
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Server")]	
	public class Server
	{
		public function Server()
		{
		}
		public var id:int=0;
		public var name:String;
		public var maxOnline:int=2000;
		public var max:int = 1000;
		public var min:int = 1;
		public var maxTeam:int = 10;
		public var minTeam:int = 1;
		public var isNew:int;
		public var isChange:int ;
		public var minFightNum:int;
		public var gameType:String;
	}
}