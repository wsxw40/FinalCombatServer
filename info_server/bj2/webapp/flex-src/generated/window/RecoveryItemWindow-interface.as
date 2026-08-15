
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
import mx.containers.Panel;
import mx.containers.VBox;
import mx.containers.ViewStack;
import mx.controls.Button;
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
import view.DeletedPlayerItemView;
import mx.containers.ApplicationControlBar;
import mx.containers.HBox;
import mx.containers.Form;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.FormItem;

public class RecoveryItemWindow extends mx.containers.VBox
{
	public function RecoveryItemWindow() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var createRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var playerId : mx.controls.TextInput;
	[Bindable]
	public var playerName : mx.controls.TextInput;
	[Bindable]
	public var searchBtn : mx.controls.Button;
	[Bindable]
	public var refreshBtn : mx.controls.Button;
	[Bindable]
	public var playerListPanel : mx.containers.Panel;
	[Bindable]
	public var playerDataGrid : component.MyDataGrid;
	[Bindable]
	public var pp : mx.containers.Panel;
	[Bindable]
	public var subTab : mx.containers.ViewStack;
	[Bindable]
	public var playerItemList : mx.containers.Panel;
	[Bindable]
	public var deletedPlayerItemView : view.DeletedPlayerItemView;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/RecoveryItemWindow.mxml:7,337";

}}
