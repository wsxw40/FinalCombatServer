package renderer
{
	import mx.controls.Label;
	import mx.controls.dataGridClasses.DataGridListData;
	import mx.formatters.DateFormatter;

	public class DateTimeRenderer extends Label
	{
		public function DateTimeRenderer()
		{
			super();
		}
		override public function set data(value:Object):void {
            super.data = value;
	       	var dt:DateFormatter=new DateFormatter();
	       	dt.formatString="YYYY-MM-DD JJ:NN:SS";
            if (value != null)
            {
                text =dt.format(value[DataGridListData(listData).dataField]);
            }
            super.invalidateDisplayList();
        }
	}
}