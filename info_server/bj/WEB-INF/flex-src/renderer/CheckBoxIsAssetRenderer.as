package renderer
{
	import common.Constants;
	
	import event.ItemDeleteEvent;
	
	import mx.controls.CheckBox;

	public class CheckBoxIsAssetRenderer extends CheckBox
	{
		public function CheckBoxIsAssetRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange)
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isAsset==1){
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
	        _data.isAsset = 1;
	         }
	    else{
	        _data.isAsset = 0;
	         }
	     dispatchEvent(new ItemDeleteEvent(ItemDeleteEvent.ITEM_DELETE,this._data,true));    
	  }
	}
}