
package popup
{
import common.DateTimePicker;
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
import mx.containers.FormItem;
import mx.containers.TitleWindow;
import mx.controls.Button;
import mx.controls.ComboBox;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import vo.SysActivity;
import mx.containers.ControlBar;
import mx.containers.TitleWindow;
import mx.containers.HBox;
import mx.containers.Form;
import mx.containers.FormItem;

public class AddSysActivity extends mx.containers.TitleWindow
{
	public function AddSysActivity() {}

	[Bindable]
	public var _sysActivity : vo.SysActivity;
	[Bindable]
	public var action : mx.controls.ComboBox;
	[Bindable]
	public var picker : common.DateTimePicker;
	[Bindable]
	public var picker1 : common.DateTimePicker;
	[Bindable]
	public var rank : mx.containers.FormItem;
	[Bindable]
	public var _rank : mx.controls.TextInput;
	[Bindable]
	public var award : mx.controls.Button;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var close : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/popup/AddSysActivity.mxml:6,43";

}}
