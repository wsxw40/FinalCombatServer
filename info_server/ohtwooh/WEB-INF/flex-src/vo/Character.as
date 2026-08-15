package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.Character")]
	public class Character
	{
		public var id:int;
		public var _runSpeed:Number=0;
		public var _walkSpeed:Number=0;
		public var _crouchSpeed:Number=0;
		public var _accelSpeed:Number=0;
		public var _jumpSpeed:Number=0;
		public var _throwSpeed:Number=0;
		public var resourceP:String;
		public var resourceT:String;
		public var isChange:int=0;
		public var isDefault:String="";
		public var gender:String;
		public var logVersion:int;
		public var logTime:Date;
		public var logId:int;
		public var cost:int;
		[Transient]
		public var selected:Boolean = false;
		
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