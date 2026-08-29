
package window
{
import component.MyDataGrid;
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
import mx.controls.TabBar;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.rpc.remoting.mxml.RemoteObject;
import mx.styles.*;
import view.PaymentView;
import mx.containers.ApplicationControlBar;
import mx.containers.ControlBar;
import mx.containers.Panel;
import mx.containers.HBox;
import mx.containers.VBox;
import mx.containers.DividedBox;

public class SysItemPriceManageWindow extends mx.containers.VBox
{
	public function SysItemPriceManageWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var typeTab : mx.controls.TabBar;
	[Bindable]
	public var subTypeTab : mx.controls.TabBar;
	[Bindable]
	public var refreshBtn : mx.controls.Button;
	[Bindable]
	public var showPaymentView : mx.controls.Button;
	[Bindable]
	public var sysItemDataGrid : component.MyDataGrid;
	[Bindable]
	public var paymentView : view.PaymentView;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/SysItemPriceManageWindow.mxml:8,361";

}}
