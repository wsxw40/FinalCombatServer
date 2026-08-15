package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.GmGroup")]
	
	public class GmGroup
	{
		public function GmGroup()
		{
		}
		
		public var id:int;
		public var name:String;
		public var description:String;
		public var creatorId:int;
		public var createName:String;
		public var code:String;
		[Transient]
		public var selected:Boolean;
	}
}