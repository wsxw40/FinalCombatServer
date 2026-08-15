package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.SysBioCharacter")]
	public class BioCharacter
	{
		public var id:int;
		
		public var cid:int;
		public var sid:int;
		public var type:int;
		public var weapons:String;
		public var costumes:String;
		
		public var nameCN:String;

		public var _runSpeed:Number=0;
		public var _walkSpeed:Number=0;
		public var _crouchSpeed:Number=0;
		public var _accelSpeed:Number=0;
		public var _jumpSpeed:Number=0;
		public var _throwSpeed:Number=0;
		public var costumeResource:String;
		public var isChange:int=0;
		public var isDefault:String="";
		
		public var isFired:int=1;
		public var resourceName:String;
		public var _controllerHeight:Number;
		public var _controllerRadius:Number;
		public var _controllerCrouchHeight:Number;
		public var scoreOffset:int;
		public var isEnable:int;
		
		public var cost:int;
		public var maxHp:int;
		public var exHp:int;
		public var defaultLevel:int;
		[Transient]
		public var selected:Boolean = false;
		
		public function set controllerHeight(rs:Number):void{
			this._controllerHeight=rs;
		}
		public function get controllerHeight():Number{
			return Number(this._controllerHeight.toFixed(3));
		}
		
		public function set controllerRadius(rs:Number):void{
			this._controllerRadius=rs;
		}
		public function get controllerRadius():Number{
			return Number(this._controllerRadius.toFixed(3));
		}
		
		public function set controllerCrouchHeight(rs:Number):void{
			this._controllerCrouchHeight=rs;
		}
		public function get controllerCrouchHeight():Number{
			return Number(this._controllerCrouchHeight.toFixed(3));
		}
		
		public function set  accelSpeed(rs:Number):void{
			this._accelSpeed=rs;
		}
		public function get  accelSpeed():Number{
			return Number(this._accelSpeed.toFixed(3));
		}
		public function set  runSpeed(rs:Number):void{
			this._runSpeed=rs;
		}
		public function get  runSpeed():Number{
			return Number(this._runSpeed.toFixed(3));
		}
		
		public function set  walkSpeed(rs:Number):void{
			this._walkSpeed=rs;
		}
		public function get  walkSpeed():Number{
			return Number(this._walkSpeed.toFixed(3));
		}
		
		public function set  crouchSpeed(rs:Number):void{
			this._crouchSpeed=rs;
		}
		public function get  crouchSpeed():Number{
			return Number(this._crouchSpeed.toFixed(3));
		}
		
		public function set  jumpSpeed(rs:Number):void{
			this._jumpSpeed=rs;
		}
		public function get  jumpSpeed():Number{
			return Number(this._jumpSpeed.toFixed(3));
		}
		
		public function set  throwSpeed(rs:Number):void{
			this._throwSpeed=rs;
		}
		public function get  throwSpeed():Number{
			return Number(this._throwSpeed.toFixed(3));
		}

	}
}