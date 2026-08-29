package renderer
{
	import mx.controls.CheckBox;
	import common.Constants;
	import event.ItemDeleteEvent;
	public class CheckBoxIsPopularRenderer extends CheckBox
	{
		public function CheckBoxIsPopularRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange);
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isPopular==1){
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
	        _data.isPopular = 1;
	         }
	    else{
	        _data.isPopular = 0;
	         }
	     dispatchEvent(new ItemDeleteEvent(ItemDeleteEvent.ITEM_DELETE,this._data,true));    
	  }
	}
}