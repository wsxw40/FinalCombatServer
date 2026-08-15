package vo
{
	import mx.formatters.DateFormatter;
		
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysActivity")]	
	public class SysActivity
	{
		public function SysActivity()
		{
		}
		public var id:int;
		public var title:String;
		public var name:String;
		public var startTime:Date;
		public var startTimeStr:String;
		public var endTime:Date;
		public var endTimeStr:String;
		public var value:int=0;
		public var targetNum:int=0;
		public var action:int;
		public var theSwitch:String;
		public var items:String="";
		public var path:String;
		public var isDeleted:String="N";
		public var chracter_id:int=0;
		public var achievement_action:int=0;
		public var activityName:String;
		public var itemsStr:String = "";
		public var description:String="";
		public var withAward:int=1;
		public var unit:int;
		public var unitType:int;
		public var backup:String;
	}
}