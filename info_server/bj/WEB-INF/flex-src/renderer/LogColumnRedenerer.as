package renderer
{
	import common.Constants;
	
	import component.MyDataGridColumn;
	
	import mx.controls.DataGrid;
	import mx.controls.Label;
	import mx.controls.dataGridClasses.DataGridListData;

	public class LogColumnRedenerer extends Label
	{
		public function LogColumnRedenerer()
		{
			super();
			
		}
		 override public function set data(value:Object):void {
            super.data = value;
             var dg:DataGrid = DataGrid(listData.owner);
            var column:MyDataGridColumn = dg.columns[listData.columnIndex];//获取整列的显示对象 
            if(listData.rowIndex==0){
            	 column.baseLine=value;//保存第一行的对象
            }else{
            	var base:Object=column.baseLine[DataGridListData(listData).dataField];//
             	var current:Object=value[DataGridListData(listData).dataField];
             	if(base!=current){
             		column.setStyle("backgroundColor",Constants.different_color);
//             		column.setStyle("color",Constants.black_color);
             		
//             		this.styleName="logDifference";
//             		this.setStyle("backgroundColor",Constants.different_color);
             	}
            }
           	text= value[DataGridListData(listData).dataField];
        }
		
	}
}