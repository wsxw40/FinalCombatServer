package vo
{
	[Bindable]
	[RemoteClass(alias="com.pearl.o2o.pojo.StrengthenAppend")]
	public class StrengthenAppend
	{
		public function StrengthenAppend()
		{
		}
		public var id:int=0;
		public var level:int=1;
		public var type:int=1;
		private var _property1:Number=0;
		private var _property2:Number=0;
		public var streNum:int;
		public var streGR:int;
		private var _winRate:Number=0;
		private var _falseKeepRate:Number=0;
		private var _falseFallRate:Number=0;
		private var _falseRuinRate:Number=0;
		private var _holeWinRate:Number=0;
		private var _switchFallRate:Number=0;
		private var _isChange:int;
		
		public function  set property1(value:Number):void{
			_property1=value;
		}
		public function get property1():Number{
			return Number(_property1.toFixed(3));
		}
		public function set property2 (value:Number):void{
			_property2=value;
		}
		public function get property2():Number{
			return Number(_property2.toFixed(3));
		}
		public function set winRate (value:Number):void{
			_winRate=value;
		}
		public function get winRate():Number{
			return Number(_winRate.toFixed(3));
		}
		public function set falseKeepRate (value:Number):void{
			_falseKeepRate=value;
		}
		public function get falseKeepRate():Number{
			return Number(_falseKeepRate.toFixed(3));
		}
		public function set falseFallRate (value:Number):void{
			_falseFallRate=value;
		}
		public function get falseFallRate():Number{
			return Number(_falseFallRate.toFixed(3));
		}
		public function set falseRuinRate (value:Number):void{
			_falseRuinRate=value;
		}
		public function get falseRuinRate():Number{
			return Number(_falseRuinRate.toFixed(3));
		}
		public function set holeWinRate (value:Number):void{
			_holeWinRate=value;
		}
		public function get holeWinRate():Number{
			return Number(_holeWinRate.toFixed(3));
		}
		
		public function set switchFallRate (value:Number):void{
			_switchFallRate=value;
		}
		public function get switchFallRate():Number{
			return Number(_switchFallRate.toFixed(3));
		}
	}
}