package utils
{
	import mx.collections.ArrayCollection;
	
	import vo.BlastPoint;
	import vo.FlagPoint;
	import vo.GamePoint;
	import vo.PointVO;
	import vo.Supplies;
	import vo.VehicleLinePoint;
	import vo.WeaponPoint;
	import vo.ZombieInfo;
	
	public class PointUtils
	{
		public function PointUtils()
		{
		}
		public static function pointToString(points:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<points.length;i++){
				var point:PointVO=points.getItemAt(i) as PointVO;
				value+=point.x+","+point.y+","+point.z+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function gamePointToString(gamePoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<gamePoints.length;i++){
				var gamePoint:GamePoint=gamePoints.getItemAt(i) as GamePoint;
				value+=gamePoint.teamId+","+gamePoint.x+","+gamePoint.y+","+gamePoint.z+","+gamePoint.rotate+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function weaponPointToString(weaponPoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<weaponPoints.length;i++){
				var weaponPoint:WeaponPoint=weaponPoints.getItemAt(i) as WeaponPoint;
				value+=weaponPoint.weaponId+","+weaponPoint.x+","+weaponPoint.y+","+weaponPoint.z+","+weaponPoint.rotate+","+weaponPoint.type+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function blastPointToString(blastPoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<blastPoints.length;i++){
				var blastPoint:BlastPoint=blastPoints.getItemAt(i) as BlastPoint;
				value+=blastPoint.x+","+blastPoint.y+","+blastPoint.z+","+blastPoint.rotate+","
				+blastPoint.x1+","+blastPoint.y1+","+blastPoint.z1+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function vehicleLinePointToString(vehicleLinePoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<vehicleLinePoints.length;i++){
				var vehicleLinePoint:VehicleLinePoint=vehicleLinePoints.getItemAt(i) as VehicleLinePoint;
				value+=vehicleLinePoint.lineId+"," +vehicleLinePoint.index+"," +vehicleLinePoint.x+","+vehicleLinePoint.y+","+vehicleLinePoint.z+","
				+vehicleLinePoint.x1+","+vehicleLinePoint.y1+","+vehicleLinePoint.z1+","+vehicleLinePoint.isSlope+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function flagPointToString(flagPoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<flagPoints.length;i++){
				var flagPoint:FlagPoint=flagPoints.getItemAt(i) as FlagPoint;
				value+=flagPoint.teamId+","+flagPoint.x+","+flagPoint.y+","+flagPoint.z+","+flagPoint.rotate+","
				+flagPoint.x1+","+flagPoint.y1+","+flagPoint.z1+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function suppiesToString(suppiesList:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<suppiesList.length;i++){
				var suppies:Supplies=suppiesList.getItemAt(i) as Supplies;
				value+=suppies.x+","+suppies.y+","+suppies.z+","+suppies.type+","+suppies.name+","+suppies.value+","+suppies.random+","+suppies.skillTime+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function zombieInfosToString(zombies:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<zombies.length;i++){
				var zombie:ZombieInfo=zombies.getItemAt(i) as ZombieInfo;
				value+=zombie.type+";"+zombie.level1+";"+zombie.level2+";"+zombie.level3+";"+zombie.level4+";"+zombie.skillType+";"
				+zombie.effect+";"+zombie.effectTime+";"+zombie.cooldown+";"+zombie.hurtAddition+":";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
	}
}