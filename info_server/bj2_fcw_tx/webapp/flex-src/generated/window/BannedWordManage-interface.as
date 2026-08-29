
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
import mx.controls.CheckBox;
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
import mx.validators.StringValidator;
import mx.containers.ApplicationControlBar;
import mx.containers.HBox;
import mx.containers.Form;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.controls.Button;
import mx.containers.VBox;
import mx.containers.FormItem;

public class BannedWordManage extends mx.containers.VBox
{
	public function BannedWordManage() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var sv : mx.validators.StringValidator;
	[Bindable]
	public var serachWord : mx.controls.TextInput;
	[Bindable]
	public var bannedWordDG : mx.controls.DataGrid;
	[Bindable]
	public var idLab : mx.controls.Label;
	[Bindable]
	public var wordTI : mx.controls.TextInput;
	[Bindable]
	public var isDeletedCB : mx.controls.CheckBox;
	[Bindable]
	public var includeCB : mx.controls.CheckBox;
	[Bindable]
	public var addBtn : mx.controls.Button;
	[Bindable]
	public var createBtn : mx.controls.Button;
	[Bindable]
	public var saveBtn : mx.controls.Button;
	[Bindable]
	public var loadBtn : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/BannedWordManage.mxml:4,143";

}}
