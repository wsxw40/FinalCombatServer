
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
import mx.controls.DataGrid;
import mx.controls.Label;
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
import mx.containers.HBox;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.Form;
import mx.controls.Button;
import mx.containers.VBox;
import mx.controls.Label;
import mx.validators.NumberValidator;
import mx.containers.FormItem;

public class SysStrengthenAppendManageWindow extends mx.containers.VBox
{
	public function SysStrengthenAppendManageWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var validateArray : Array;
	[Bindable]
	public var aid : mx.controls.Label;
	[Bindable]
	public var level : mx.controls.TextInput;
	[Bindable]
	public var type : mx.controls.TextInput;
	[Bindable]
	public var property1 : mx.controls.TextInput;
	[Bindable]
	public var property2 : mx.controls.TextInput;
	[Bindable]
	public var streNum : mx.controls.TextInput;
	[Bindable]
	public var streGR : mx.controls.TextInput;
	[Bindable]
	public var winRate : mx.controls.TextInput;
	[Bindable]
	public var falseKeepRate : mx.controls.TextInput;
	[Bindable]
	public var falseFallRate : mx.controls.TextInput;
	[Bindable]
	public var falseRuinRate : mx.controls.TextInput;
	[Bindable]
	public var holeWinRate : mx.controls.TextInput;
	[Bindable]
	public var switchFallRate : mx.controls.TextInput;
	[Bindable]
	public var dg : mx.controls.DataGrid;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/SysStrengthenAppendManageWindow.mxml:4,134";

}}
