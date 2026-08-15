
package window
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
import mx.containers.ControlBar;
import mx.containers.Panel;
import mx.containers.HBox;
import mx.controls.Button;
import mx.containers.Form;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.FormItem;
import mx.containers.VDividedBox;

public class LuckyPackageWindow extends mx.containers.VBox
{
	public function LuckyPackageWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var fixedDataGrid : mx.controls.DataGrid;
	[Bindable]
	public var fixedLevelSelect : mx.controls.ComboBox;
	[Bindable]
	public var fixedPrice : mx.controls.TextInput;
	[Bindable]
	public var fixedChipPrice : mx.controls.TextInput;
	[Bindable]
	public var fixedNumber : mx.controls.TextInput;
	[Bindable]
	public var fixedUseTypeSelect : mx.controls.ComboBox;
	[Bindable]
	public var fixedIsDeleted : mx.controls.ComboBox;
	[Bindable]
	public var createFixedBtn : mx.controls.Button;
	[Bindable]
	public var deleteFixedBtn : mx.controls.Button;
	[Bindable]
	public var updateFixedBtn : mx.controls.Button;
	[Bindable]
	public var refreshFixedBtn : mx.controls.Button;
	[Bindable]
	public var randomDataGrid : mx.controls.DataGrid;
	[Bindable]
	public var randomLevelSelect : mx.controls.ComboBox;
	[Bindable]
	public var randomWeight : mx.controls.TextInput;
	[Bindable]
	public var randomWeight1 : mx.controls.TextInput;
	[Bindable]
	public var setRandomBtn : mx.controls.Button;
	[Bindable]
	public var randomNumber : mx.controls.TextInput;
	[Bindable]
	public var randomUseTypeSelect : mx.controls.ComboBox;
	[Bindable]
	public var randomIsNoticeSelect : mx.controls.ComboBox;
	[Bindable]
	public var randomIsDeleted : mx.controls.ComboBox;
	[Bindable]
	public var createRandomBtn : mx.controls.Button;
	[Bindable]
	public var deleteRandomBtn : mx.controls.Button;
	[Bindable]
	public var updateRandomBtn : mx.controls.Button;
	[Bindable]
	public var refreshRandomBtn : mx.controls.Button;
	[Bindable]
	public var playerGetPTWt : mx.controls.TextInput;
	[Bindable]
	public var playerPTFlagWtType1 : mx.controls.TextInput;
	[Bindable]
	public var playerPTFlagWtType2 : mx.controls.TextInput;
	[Bindable]
	public var updateBtn : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/LuckyPackageWindow.mxml:7,247";

}}
