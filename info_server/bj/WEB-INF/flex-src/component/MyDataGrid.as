package component
{
	import mx.controls.DataGrid;
	import mx.core.ScrollPolicy;

	public class MyDataGrid extends DataGrid
	{
		public function MyDataGrid()
		{
			super();
			horizontalScrollPolicy=ScrollPolicy.AUTO;
		}
		 private function setScrollPos():void  
        {  
            if(columns == null || columns.length < lockedColumnCount)  
                return;  
            var xOffset:Number = 0;  
            for(var i:int=0; i<lockedColumnCount; ++i)  
                xOffset += columns[i].width;  
            if (horizontalScrollBar && horizontalScrollBar.x < xOffset){  
                horizontalScrollBar.x = xOffset;      
                horizontalScrollBar.setActualSize(horizontalScrollBar.width - xOffset,   
                    horizontalScrollBar.height);  
            }  
//            if(verticalScrollBar && verticalScrollBar.y < headerHeight){  
//                verticalScrollBar.y = headerHeight;  
//                verticalScrollBar.setActualSize(verticalScrollBar.width,   
//                    verticalScrollBar.height - headerHeight);  
//            }  
        }  
          
        override protected function updateDisplayList(w:Number, h:Number):void  
        {  
            super.updateDisplayList(w, h);  
            setScrollPos();  
        }  
		
	}
}