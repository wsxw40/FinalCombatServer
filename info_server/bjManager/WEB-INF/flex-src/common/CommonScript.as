import mx.charts.CategoryAxis;
import mx.charts.chartClasses.IAxis;
import mx.controls.Alert;
import mx.rpc.events.FaultEvent;


private function defaultFaultHandler(event:FaultEvent):void{
	Alert.show(event.message.toString());		
}

private function pieChartLabFunc(data:Object, field:String, index:Number, percentValue:Number):String{
	return data.label + " : " + data.num.toString() + "(" + percentValue.toFixed(1).toString() + "%)";
}

private function columnChartLabFunc(categoryValue:Object, previousCategoryValue:Object, axis:CategoryAxis, categoryItem:Object):String{
	return categoryValue.toString().concat("级");
}

private function columnChartVerticalLabFunc(labelValue:Object, previousValue:Object, axis:IAxis):String{
	return labelValue.toString().concat("人");
}