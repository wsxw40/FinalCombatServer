
package common
{
import flash.accessibility.*;
import flash.debugger.*;
import flash.display.*;
import flash.errors.*;
import flash.events.*;
import flash.external.*;
import flash.filters.*;
import flash.geom.*;
import flash.media.*;
import flash.net.*;
import flash.printing.*;
import flash.profiler.*;
import flash.system.*;
import flash.text.*;
import flash.ui.*;
import flash.utils.*;
import flash.xml.*;
import mx.binding.*;
import mx.containers.HBox;
import mx.controls.DateField;
import mx.controls.NumericStepper;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import mx.containers.HBox;
import mx.controls.Label;
import mx.controls.Spacer;


        [Event(name="change", type="flash.events.Event")]
    
public class DateTimePicker extends mx.containers.HBox
{
	public function DateTimePicker() {}

	[Bindable]
	public var date : mx.controls.DateField;
	[Bindable]
	public var hours : mx.controls.NumericStepper;
	[Bindable]
	public var minutes : mx.controls.NumericStepper;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/common/DateTimePicker.mxml:9,82";

}}
