package renderer
{
	import common.Constants;
	
	import mx.controls.Label;
	import mx.controls.ToolTip;

	public class HeaderTipRenderer extends Label
	{
		public function HeaderTipRenderer()
		{
			super();
		}
		override public function set data(value:Object):void {
            super.data = value;
	      
            if (value != null)
            {
               var headText:String=listData.label as String;
               this.toolTip=Constants.headTip[headText];
            }
            super.invalidateDisplayList();
        }
	}
}