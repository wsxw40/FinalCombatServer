package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.BannedWord")]
	
	public class BannedWord
	{
		public function BannedWord()
		{
		}
		
		public var id:int;
		public var bannedWord:String;
		public var isDeleted:String;
		public var includeInWord:String;
		
	}
}