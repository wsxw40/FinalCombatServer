package utils
{
	import mx.collections.ArrayCollection;
	
	import vo.BlastPoint;
	import vo.GamePoint;
	import vo.Supplies;
	import vo.WeaponPoint;
	
	public class PointUtils
	{
		public function PointUtils()
		{
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
				value+=weaponPoint.weaponId+","+weaponPoint.x+","+weaponPoint.y+","+weaponPoint.z+","+weaponPoint.rotate+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		public static function blastPointToString(blastPoints:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<blastPoints.length;i++){
				var blastPoint:BlastPoint=blastPoints.getItemAt(i) as BlastPoint;
				value+=blastPoint.x+","+blastPoint.y+","+blastPoint.z+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
		
		public static function suppiesToString(suppiesList:ArrayCollection):String{
			var value:String="";
			for(var i:int=0;i<suppiesList.length;i++){
				var suppies:Supplies=suppiesList.getItemAt(i) as Supplies;
				value+=suppies.type+","+suppies.name+","+suppies.teamId+","+suppies.value+","+suppies.random+","+suppies.skillTime+";";
			}
			value=value.substr(0,value.length-1);
			return value;
		}
	}
}