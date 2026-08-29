package renderer
{
	import common.Constants;
	
	import mx.controls.CheckBox;

	public class CheckBoxIsUpdateRenderer extends CheckBox
	{
		public function CheckBoxIsUpdateRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange)
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isUpdate==Constants.BOOLEAN_YES){
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
	        _data.isUpdate = Constants.BOOLEAN_YES;
	         }
	    else{
	        _data.isUpdate = Constants.BOOLEAN_NO;
	         }
	  }
	}
}