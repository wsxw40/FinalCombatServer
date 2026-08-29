package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.nosql.object.deathrecord.DeathRecord")]
	public class DeadthPointVO
	{
		public var x:int;
		public var y:int;
		public var z:int;
		public var team:int;
		public var type:int;
		public var value:int;
		public var isFilter:Boolean=false;
		public function DeadthPointVO()
		{
		}

	}
}