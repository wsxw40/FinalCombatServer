
package window
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
import mx.containers.VBox;
import mx.controls.Button;
import mx.controls.ComboBox;
import mx.controls.DataGrid;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.rpc.remoting.mxml.RemoteObject;
import mx.styles.*;
import mx.containers.ApplicationControlBar;
import mx.containers.ControlBar;
import mx.containers.Panel;
import mx.containers.HBox;
import mx.core.IFactory;
import mx.containers.Form;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.FormItem;
import mx.containers.VDividedBox;

public class ActivityWindow extends mx.containers.VBox
{
	public function ActivityWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var activityDataGrid : mx.controls.DataGrid;
	[Bindable]
	public var rank : mx.containers.FormItem;
	[Bindable]
	public var _rank : mx.controls.TextInput;
	[Bindable]
	public var random_num : mx.containers.FormItem;
	[Bindable]
	public var _random_num : mx.controls.TextInput;
	[Bindable]
	public var random_space : mx.containers.FormItem;
	[Bindable]
	public var _random_space : mx.controls.TextInput;
	[Bindable]
	public var kill_num : mx.containers.FormItem;
	[Bindable]
	public var _kill_num : mx.controls.TextInput;
	[Bindable]
	public var pay_num : mx.containers.FormItem;
	[Bindable]
	public var _pay_num : mx.controls.TextInput;
	[Bindable]
	public var boss_kill_num : mx.containers.FormItem;
	[Bindable]
	public var _boss_kill_num : mx.controls.TextInput;
	[Bindable]
	public var kill_boss_num : mx.containers.FormItem;
	[Bindable]
	public var _kill_boss_num : mx.controls.TextInput;
	[Bindable]
	public var mission_num : mx.containers.FormItem;
	[Bindable]
	public var _mission_num : mx.controls.TextInput;
	[Bindable]
	public var chooseOverDay : mx.containers.FormItem;
	[Bindable]
	public var InitOverDay : mx.controls.ComboBox;
	[Bindable]
	public var percentNum : mx.containers.FormItem;
	[Bindable]
	public var _percentNum : mx.controls.TextInput;
	[Bindable]
	public var achieve_num : mx.containers.FormItem;
	[Bindable]
	public var _achieve_num : mx.controls.TextInput;
	[Bindable]
	public var exp_double : mx.containers.FormItem;
	[Bindable]
	public var _exp_double : mx.controls.TextInput;
	[Bindable]
	public var hour2hour : mx.containers.FormItem;
	[Bindable]
	public var hour1 : mx.controls.ComboBox;
	[Bindable]
	public var hour2 : mx.controls.ComboBox;
	[Bindable]
	public var top_start : mx.containers.FormItem;
	[Bindable]
	public var _top_start : mx.controls.TextInput;
	[Bindable]
	public var top_end : mx.containers.FormItem;
	[Bindable]
	public var _top_end : mx.controls.TextInput;
	[Bindable]
	public var loginLevel : mx.containers.FormItem;
	[Bindable]
	public var _loginLevel : mx.controls.TextInput;
	[Bindable]
	public var title : mx.containers.FormItem;
	[Bindable]
	public var _title : mx.controls.TextInput;
	[Bindable]
	public var actionType : mx.containers.FormItem;
	[Bindable]
	public var action : mx.controls.ComboBox;
	[Bindable]
	public var button : mx.controls.ComboBox;
	[Bindable]
	public var path : mx.containers.FormItem;
	[Bindable]
	public var _path : mx.controls.TextInput;
	[Bindable]
	public var startTime : common.DateTimePicker;
	[Bindable]
	public var endTime : common.DateTimePicker;
	[Bindable]
	public var award : mx.controls.Button;
	[Bindable]
	public var achievement : mx.controls.Button;
	[Bindable]
	public var fresh : mx.controls.Button;
	[Bindable]
	public var deleteBtn : mx.controls.Button;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var cancle : mx.controls.Button;
	[Bindable]
	public var newBtn : mx.controls.Button;
	[Bindable]
	public var okNew : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/ActivityWindow.mxml:7,708";

}}
