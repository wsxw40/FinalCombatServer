package renderer
{
	import component.MyDataGridColumn;
	
	import event.LogSelectEvent;
	
	import flash.events.Event;
	
	import mx.collections.ArrayCollection;
	import mx.controls.Alert;
	import mx.controls.CheckBox;
	import mx.controls.DataGrid;

	public class LogSelectRenderer extends CheckBox
	{
		 private var currentData:Object; //保存当前一行值的对象
		
		public function LogSelectRenderer()
		{
			super();
			this.addEventListener(Event.CHANGE,changeHandle)
		}
		[Bindable]
		override public function set data(value:Object):void{
			this.selected = value.selected;
			this.currentData = value; //保存整行的引用
		}
		
		//点击check box时，根据状况向selectedItems array中添加当前行的引用，或者从array中移除
		private function changeHandle(e:Event):void{
			var dg:DataGrid = DataGrid(listData.owner);//获取DataGrid对象 
            var column:MyDataGridColumn = dg.columns[listData.columnIndex];//获取整列的显示对象 
            var itemArray:ArrayCollection = column.selectItems; 
			var sysItem:Object=this.currentData;
			
			
			if(this.selected){
				if(itemArray.length==2){
					this.selected=false;
					Alert.show("请选择两条日志进行比较。");
				}else{
					sysItem.selected=true;
					itemArray.addItem(this.currentData);
				}
			}else{
				sysItem.selected=false;
                for(var i:int = 0; i<itemArray.length; i++){
                    if(itemArray[i] == this.currentData){
                        itemArray.removeItemAt(i);
                    }
                }
			}
			dispatchEvent(new LogSelectEvent(LogSelectEvent.LOG_SELECT,itemArray,true,false));
		}
	}
}