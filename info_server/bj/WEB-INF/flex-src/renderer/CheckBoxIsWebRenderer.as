package renderer
{
	import event.ItemDeleteEvent;
	
	import mx.controls.CheckBox;
	
	public class CheckBoxIsWebRenderer  extends CheckBox
	{
		public function CheckBoxIsWebRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange)
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isWeb==1){
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
	        _data.isWeb = 1;
	         }
	    else{
	        _data.isWeb = 0;
	         }
	     dispatchEvent(new ItemDeleteEvent(ItemDeleteEvent.ITEM_DELETE,this._data,true));    
	  }
	}
}