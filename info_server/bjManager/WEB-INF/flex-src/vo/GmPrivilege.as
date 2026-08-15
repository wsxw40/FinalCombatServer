package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.manager.pojo.GmPrivilege")]
	
	public class GmPrivilege
	{
		public function GmPrivilege()
		{
		}
		
		public var id:int;
		public var name:String;
		public var description:String;
		public var parent:String;
		[Transient]
		public var selected:Boolean;

	}
}