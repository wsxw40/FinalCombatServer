
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
import mx.controls.TabBar;
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
import view.PlayerDefaultItemView;
import view.PlayerItemView;
import mx.containers.ApplicationControlBar;
import mx.containers.Panel;
import mx.containers.HBox;
import mx.containers.Form;
import mx.containers.VBox;
import mx.controls.Text;
import mx.controls.Label;
import mx.containers.FormItem;

public class PlayerManageWindow extends mx.containers.VBox
{
	public function PlayerManageWindow() {}

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
	public var kickPlayer : mx.controls.Button;
	[Bindable]
	public var resetPlayerWeapon : mx.controls.Button;
	[Bindable]
	public var checkPlayerData : mx.controls.Button;
	[Bindable]
	public var playerListPanel : mx.containers.Panel;
	[Bindable]
	public var playerDataGrid : component.MyDataGrid;
	[Bindable]
	public var pp : mx.containers.Panel;
	[Bindable]
	public var exp : mx.controls.TextInput;
	[Bindable]
	public var rank : mx.controls.TextInput;
	[Bindable]
	public var GPoint : mx.controls.TextInput;
	[Bindable]
	public var GScore : mx.controls.TextInput;
	[Bindable]
	public var credit : mx.controls.TextInput;
	[Bindable]
	public var voucher : mx.controls.TextInput;
	[Bindable]
	public var isVip : mx.controls.TextInput;
	[Bindable]
	public var vipExp : mx.controls.TextInput;
	[Bindable]
	public var tutorial : mx.controls.TextInput;
	[Bindable]
	public var unusableResource : mx.controls.TextInput;
	[Bindable]
	public var usableResource : mx.controls.TextInput;
	[Bindable]
	public var saveChangeBtn : mx.controls.Button;
	[Bindable]
	public var tabType : mx.controls.TabBar;
	[Bindable]
	public var subTab : mx.containers.ViewStack;
	[Bindable]
	public var playerItemList : mx.containers.Panel;
	[Bindable]
	public var playerItemView : view.PlayerItemView;
	[Bindable]
	public var defaultPlayerItemList : mx.containers.Panel;
	[Bindable]
	public var playerDefaultItemView : view.PlayerDefaultItemView;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/window/PlayerManageWindow.mxml:7,407";

}}
