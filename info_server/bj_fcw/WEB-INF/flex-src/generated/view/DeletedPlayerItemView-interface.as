
package view
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
import mx.containers.ApplicationControlBar;
import mx.core.IFactory;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.controls.Label;
import component.MyDataGridColumn;

public class DeletedPlayerItemView extends mx.containers.VBox
{
	public function DeletedPlayerItemView() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var playerItemDatagrid : mx.controls.DataGrid;
	[Bindable]
	public var playerItemId : mx.controls.TextInput;
	[Bindable]
	public var sysitemId : mx.controls.TextInput;
	[Bindable]
	public var itemLevel : mx.controls.TextInput;
	[Bindable]
	public var itemUnitType : mx.controls.ComboBox;
	[Bindable]
	public var siType : mx.controls.ComboBox;
	[Bindable]
	public var searchBtn : mx.controls.Button;
	[Bindable]
	public var recoveryBtn : mx.controls.Button;
	[Bindable]
	public var cleanBtn : mx.controls.Button;
	[Bindable]
	public var refreshBtn : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/DeletedPlayerItemView.mxml:4,165";

}}
