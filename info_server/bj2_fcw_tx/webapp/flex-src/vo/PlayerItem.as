package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.PlayerItem")]	
	public class PlayerItem
	{
		public function PlayerItem()
		{
		}

		public var 		id:int;
		public var 		playerId:int;
		public var		name:String;
		public var		itemId:int;
		public var		isDeleted:String;
		public var 		isDefault:String;
		public var      pack:Boolean;	
		public var  	itemDisplayName:String;
		public var 		validDate:Date;
		public var		expireDate:Date;
		public var 		validDateStr:String;
		public var 		expireDateStr:String;
		public var 		level:int;
		public var 		playerItemUnitType:int;
		public var 		quantity:int;
		public var 		type:int;
		
		public var 		gunProperty2:String;
		public var	 	gunProperty3:String;
		public var 		gunProperty4:String;
		public var 		gunProperty5:String;
		public var 		gunProperty6:String;
		public var 		gunProperty7:String;
		[Transient]
		public var 		isUpdate:String;
	}
}