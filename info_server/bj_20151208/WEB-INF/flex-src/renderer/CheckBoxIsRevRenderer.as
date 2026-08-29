package renderer
{
	import common.Constants;
	
	import mx.controls.CheckBox;

	public class CheckBoxIsRevRenderer extends CheckBox
	{
		public function CheckBoxIsRevRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,onChange)
		}
		private var _data:Object;
	 	[Bindable]
		override public function set data(value:Object):void{
			this._data=value;
			if(value.isDeleted==Constants.BOOLEAN_YES){
				this.selected = false;
	        	this.label="No";
	        }else{
	        	this.selected = true;
	            this.label="Yes";
	        } 
		}
	   private function onChange(evt:Event):void{
	    var cb:CheckBox = evt.currentTarget as CheckBox;
	     if(cb.selected){
	        _data.isDeleted = Constants.BOOLEAN_NO;
	         }
	    else{
	        _data.isDeleted = Constants.BOOLEAN_YES;
	         }
	  }
	}
}