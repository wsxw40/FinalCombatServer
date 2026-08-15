package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.LevelInfoPojo")]
	public class LevelInfo
	{
	public var id:int;
	public var type:int;
	public var name:String="";	
	public var startPoints:String="";	
	public var blastPoints:String="";	
	public var flagPoints:String="";	
	public var weaponPoints:String="";	
	public var supplyPoints:String="";	
	public var supplies:String="";
	
	public var isChange:int=0;
	}
}