package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.LevelInfo")]
	public class LevelInfo
	{
	public var id:int;
	public var type:int;
	public var name:String="";	
	public var index:int;	
	public var isNew:int;	
	public var startPoints:String="";	
	public var blastPoints:String="";	
	public var flagPoints:String="";	
	public var weaponPoints:String="";	
	public var bossItems:String="";	
	public var supplies:String="";
	public var zombieInfo:String="";
	public var isSelf:int=0;
	public var displayNameCN:String="";
	public var description:String="";
	public var levelHorizon:Number=0;
	public var targetSpeed:Number=0;
	public var lineInfo:String="";
	public var vehicleInfo:String="";
	public var isChange:int=0;
	public var isVip:int=0;
	public var expAdd:Number=0.0;
	public var gpAdd:Number=0.0;
	public var activityStart:Date;
	public var activityEnd:Date;
	public var bloodOffset:int;
	public var isRushGold:int;
	public var isMoneyReward:int;
	public var rushGlodPoint:String="";
	public var num4Team:int=0;
	public function get TargetSpeed():Number{
			return Number(this.targetSpeed.toFixed(3));
		}
	public function set TargetSpeed(num:Number):void{
			this.targetSpeed=num;
		}
	
	public function get LevelHorizon():Number{
			return Number(this.levelHorizon.toFixed(3));
	}
	public function set LevelHorizon(num:Number):void{
			this.levelHorizon=num;
		}
	}

}