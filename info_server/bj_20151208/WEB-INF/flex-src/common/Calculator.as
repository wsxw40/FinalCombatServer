package common {
	import vo.SysItem;
	
	public class Calculator{
		
		public function Calculator() {
			
		}
		
		public static function numberFormat(value:Number):Number{
			return Math.round(value * 1000) / 1000;
		}
		
		//后坐力
		public static function getRecoil(sysItem:SysItem):Number{
			var result:Number = 20 - (Math.sqrt(sysItem.WNormalUpMax * sysItem.WNormalUpMax + sysItem.WNormalLateralMax * sysItem.WNormalLateralMax));
			return numberFormat(result);
		}
		
		public static function getHouzuo(sysItem:SysItem):Number{
			var factor:Number = Math.sqrt(200);
			var result:Number = 18 - 18 * Math.sqrt(sysItem.WNormalUpMax * sysItem.WNormalUpMax + sysItem.WNormalLateralMax * sysItem.WNormalLateralMax) / factor;
			return numberFormat(result);
		}
		
		//精度
		public static function getAccuracy(sysItem:SysItem):Number{
			var result:Number = 20 - (2 * sysItem.WNormalDirChange);
			return numberFormat(result);
		}
		
		public static function getJindu(sysItem:SysItem):Number{
			var factor:Number = 0.1;
			var result:Number = 15 - sysItem.WNormalDirChange * factor;
			return numberFormat(result);
		}
		
		//射程
		public static function getFiringRange(sysItem:SysItem):Number{
			var result:Number = 0.2 * sysItem.WRangeEnd;
			return numberFormat(result);
		}
		
		public static function getShecheng(sysItem:SysItem):Number{
			if(sysItem.WRangeEnd >= 50){
				return 10;
			}else{
				var factor:Number = 0.2;
				var result:Number = sysItem.WRangeEnd * factor;
				return numberFormat(result);
			}
		}
		
		//换枪速度
		public static function getSpeedChangeGun(sysItem:SysItem):Number{
			if(sysItem.WChangeInTime == 0){
				return 0;
			}else{
				var result:Number = 20 / sysItem.WChangeInTime;
				return numberFormat(result);
			}				
		}
		
		public static function getHuanqiangsudu(sysItem:SysItem):Number{
			var factor:Number = 8 / 3;
			var result:Number = (3 - sysItem.WChangeInTime) * factor;
			return numberFormat(result);
		}
		
		//换弹速度
		public static function getSpeedChangeMagazine(sysItem:SysItem):Number{
			if(sysItem.WReloadTime == 0){
				return 0;
			}else{
				var result:Number = 20 / sysItem.WReloadTime;
				return numberFormat(result);
			}				
		}
		
		public static function getHuandansudu(sysItem:SysItem):Number{
			var factor:Number = 1.5;
			var result:Number = (6 - sysItem.WReloadTime) * factor;
			return numberFormat(result);
		}
		
		//射速
		public static function getFiringRate(sysItem:SysItem):Number{
			if(sysItem.WFireTime == 0){
				return 0;
			}else{
				var result:Number = 1 / sysItem.WFireTime;
				return numberFormat(result);
			}				
		}
		
		public static function getShesu(sysItem:SysItem):Number{
//			var factor:Number = 5;
//			var result:Number = (3 - sysItem.WFireTime) * factor;
//			return numberFormat(result);
			if(sysItem.WFireTime < 0.2){
				return numberFormat(15 - 50 * sysItem.WFireTime);
			}else{
				return numberFormat(5 - (25 / 9) * sysItem.WFireTime);
			}
		}
		
		//弹量
		public static function getBulletCapacity(sysItem:SysItem):Number{
			var result:Number = 0.1 * sysItem.WAmmoOneClip;
			return numberFormat(result);	
		}
		
		public static function getDanliang(sysItem:SysItem):Number{
			if(sysItem.WAmmoOneClip >= 60){
				return 7;
			}else{
				var factor:Number = 7 / 60;
				var result:Number = sysItem.WAmmoOneClip * factor;
				return numberFormat(result);
			}
		}
		
		//威力
		public static function getPower(sysItem:SysItem):Number{
			var result:Number = 0.1 * sysItem.WDamage;
			return numberFormat(result);	
		}
		
		public static function getWeili(sysItem:SysItem):Number{
			if(sysItem.WDamage >= 60){
				return 20;
			}else{
				var factor:Number = 1 / 3;
				var result:Number = sysItem.WDamage * factor;
				return numberFormat(result);
			}
		}
		
		public static function getTriangleArea(edgeA:Number, edgeB:Number, radian:Number):Number{
			var result:Number = 0.5 * edgeA * edgeB * Math.sin(radian);
			return numberFormat(result);
		}
		
		public static function getArea(array:Array):Number{
			var result:Number = 0;
			var length:int = array.length;
			
			if(length == 1){
				result = array[0];
			}else if(length == 2){
				result = array[0] + array[1];
			}else{
				var radian:Number = 2 * Math.PI / array.length;
				for(var i:int = 0; i < array.length; i++){
					var edgeA:Number = array[i];				
					var j:int = i + 1;
					if(j == array.length){
						j = 0;
					}
					var edgeB:Number = array[j];
					result += getTriangleArea(edgeA, edgeB, radian);
				}
			}			
			return numberFormat(result);
		}
		
		public static function getSum(array:Array):Number{
			var result:Number = 0;
			for(var i:int = 0; i < array.length; i++){
				result += array[i];
			}
			return numberFormat(result);
		}	
	}
}