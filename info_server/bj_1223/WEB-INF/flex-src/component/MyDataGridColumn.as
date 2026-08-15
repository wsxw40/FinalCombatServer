package component
{
	import mx.collections.ArrayCollection;
	import mx.controls.dataGridClasses.DataGridColumn;
	import mx.core.ClassFactory;
	
	import renderer.HeaderTipRenderer;
	import renderer.LogColumnRedenerer;

	public class MyDataGridColumn extends DataGridColumn
	{
		[Bindable]
		public var selectItems:ArrayCollection = new ArrayCollection();//用户保存用户选中的数据 
		
		public var baseLine:Object;
		
		public function MyDataGridColumn(columnName:String=null,headerName:String=null,isHiLight:Boolean=false,widthNum:Number=100,styleProp:String=null,value:Object=null)
		{
			
			super();
			
			if (columnName)
	        {
	            dataField = columnName;
	        }
	        if (headerName)
	        {
	            headerText = headerName;
	        }
	         if (widthNum)
	        {
	            width = widthNum;
	        }
	        this.draggable=false;
	        setStyle(styleProp,value);
	        headerRenderer=new ClassFactory(HeaderTipRenderer);
	        if(isHiLight){
	        	itemRenderer=new ClassFactory(LogColumnRedenerer);
	        	sortable=false;
	        }
		}
	}
}