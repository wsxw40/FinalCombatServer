package event{
	import flash.events.Event;

	public class RadarChartItemChangeEvent extends Event{
		
		public static const EVENT_ITEM_CHANGE:String = "radarChartItemChange";
		
		public var _data:Object;
		
		public function RadarChartItemChangeEvent(type:String, data:Object, bubbles:Boolean=false, cancelable:Boolean=false){
			super(type, bubbles, cancelable);
			this._data = data;
		}		
	}
}