package renderer
{
	
	import event.BoxClickEvent;
	
	import flash.events.MouseEvent;
	
	import mx.containers.Box;
	
	import vo.DeadthPointVO;
	public class DeadthPointItem extends Box
	{
		private var alpa:Number=0.8;
		public var deadthPointVO:DeadthPointVO;
		public var z:int=50;
		public var deadNum:int=100;
		public function DeadthPointItem(deadthPointVO:DeadthPointVO):void
		{
			
			this.x=deadthPointVO.x;
			this.y=deadthPointVO.z;
			this.deadNum=deadthPointVO.value;
//			trace("render===="+this.x+":"+this.y+":"+this.deadNum);
			this.deadthPointVO=deadthPointVO;
			setColor();
			this.addEventListener(MouseEvent.CLICK,clickHandler);
			setTooltip();
		}
		private function setColor():void{
			if(this.deadNum>=5){
				this.setStyle("backgroundColor",0xff0000);
			}else if(this.deadNum>=2&&this.deadNum<5){
				this.setStyle("backgroundColor",0x0066ff);
			}else{
				this.setStyle("backgroundColor",0x00ff33);
			}
		}
		
		private function clickHandler(e:MouseEvent):void{
			var evt:BoxClickEvent=new BoxClickEvent(BoxClickEvent.BOX_CLICK,deadthPointVO,true);
			this.dispatchEvent(evt);
		}
		private function setTooltip():void{
			this.toolTip=this.x+":"+this.y+":"+this.deadNum;
		}
	}
}