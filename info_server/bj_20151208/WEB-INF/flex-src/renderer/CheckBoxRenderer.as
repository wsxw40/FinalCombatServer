package renderer
{
	import common.Constants;
	
	import event.ItemDeleteEvent;
	
	import mx.controls.CheckBox;

	public class CheckBoxRenderer extends CheckBox
	{
		public function CheckBoxRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange)
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isDeleted==Constants.BOOLEAN_YES){
	              this.selected = true;
	              this.label="Yes";
	        }else{
	        	this.selected = false;
	        	this.label="No";
	        } 
		}
	   private function onChange(evt:Event):void{
	    var cb:CheckBox = evt.currentTarget as CheckBox;
	     if(cb.selected){
	        _data.isDeleted = Constants.BOOLEAN_YES;
	         }
	    else{
	        _data.isDeleted = Constants.BOOLEAN_NO;
	         }
	     dispatchEvent(new ItemDeleteEvent(ItemDeleteEvent.ITEM_DELETE,this._data,true));    
	  }
	}
}