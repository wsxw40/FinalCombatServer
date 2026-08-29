package renderer
{
	import event.RadarChartItemChangeEvent;
	
	import flash.events.Event;
	
	import mx.controls.CheckBox;
	
	import vo.SysItem;
	
	public class RadarChartCheckBoxRenderer extends CheckBox {
		public function RadarChartCheckBoxRenderer() {
			super();
			this.addEventListener(Event.CHANGE, onCheckBoxChange);
		}		
		
		private var _data:Object;
		[Bindable]
		public override function set data(value:Object):void{
			this._data = value;
			this.selected = value.radarSelected
			this.label = data.displayName;
		}
		
		public override function get data():Object{
			return this._data;
		}
		
		private function onCheckBoxChange(e:Event):void{
			var cb:CheckBox = e.currentTarget as CheckBox;
			data.radarSelected = cb.selected;
			dispatchEvent(new RadarChartItemChangeEvent(RadarChartItemChangeEvent.EVENT_ITEM_CHANGE, data, true));
		}
	}
}