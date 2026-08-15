package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysNotice")]	
	public class SysNotice
	{
		public function SysNotice()
		{
		}

		public  var 	id:int;
		public	var		type:int = 1;
		public	var		content:String;
		public  var		startTime:Number;
		public  var		startTimeStr:String;
		public  var		endTime:Number;
		public  var		endTimeStr:String;
		public	var		noticeTime:int;
	}
}