
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
import mx.containers.Panel;
import mx.containers.VBox;
import mx.controls.Button;
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
import view.TeamLogView;
import mx.containers.ApplicationControlBar;
import mx.containers.HBox;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.controls.Label;

public class TeamListWindow extends mx.containers.VBox
{
	public function TeamListWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var logLabel : mx.controls.Button;
	[Bindable]
	public var teamName : mx.controls.TextInput;
	[Bindable]
	public var searchBtn : mx.controls.Button;
	[Bindable]
	public var deleteBtn : mx.controls.Button;
	[Bindable]
	public var refreshBtn : mx.controls.Button;
	[Bindable]
	public var teamListPanel : mx.containers.Panel;
	[Bindable]
	public var teamDatagrid : mx.controls.DataGrid;
	[Bindable]
	public var teamLogPanel : mx.containers.Panel;
	[Bindable]
	public var teamLogView : view.TeamLogView;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/TeamListWindow.mxml:7,130";

}}
