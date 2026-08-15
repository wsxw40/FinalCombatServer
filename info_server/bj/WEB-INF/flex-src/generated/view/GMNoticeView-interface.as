
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
import mx.containers.HBox;
import mx.containers.VBox;
import mx.controls.Button;
import mx.controls.DataGrid;
import mx.controls.Label;
import mx.controls.RadioButton;
import mx.controls.RadioButtonGroup;
import mx.controls.TextArea;
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
import mx.containers.HBox;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.controls.Label;

public class GMNoticeView extends mx.containers.VBox
{
	public function GMNoticeView() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var noticeType : mx.controls.RadioButtonGroup;
	[Bindable]
	public var serverNotice : mx.controls.RadioButton;
	[Bindable]
	public var proxyNotice : mx.controls.RadioButton;
	[Bindable]
	public var serverview : mx.containers.HBox;
	[Bindable]
	public var selectServer : mx.controls.Label;
	[Bindable]
	public var serverDataGrid : mx.controls.DataGrid;
	[Bindable]
	public var msg : mx.controls.TextArea;
	[Bindable]
	public var sendBtn : mx.controls.Button;
	[Bindable]
	public var resetBtn : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/GMNoticeView.mxml:4,68";

}}
