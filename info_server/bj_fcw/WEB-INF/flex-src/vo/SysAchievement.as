package vo
{
	import mx.formatters.DateFormatter;
		
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysAchievement")]	
	public class SysAchievement
	{
		public var id:int;
		public var name:String;
		public var descriptionCN:String;
		public var title:String;
		public var number:int;
		public var action:int;
		public var characterId:int;
		[Transient]
		public var selected:Boolean = false;
	}
}