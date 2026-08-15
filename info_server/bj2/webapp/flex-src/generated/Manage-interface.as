
package 
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
import mx.containers.ViewStack;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.rpc.remoting.mxml.RemoteObject;
import mx.styles.*;
import window.ActivityMainWindow;
import window.BannedWordManage;
import window.BlackIPManageWindow;
import window.BlackWhiteUnspeakerWindow;
import window.CharacterWindow;
import window.MapManagementWindow;
import window.PlayerManagementWindow;
import window.ServerConfigWindow;
import window.SysConfigManageWindow;
import window.SysItemManageWindow;
import window.SysItemPriceManageWindow;
import window.SysNoticeManageWindow;
import window.SysOnlineAwardMngmentWindow;
import window.TeamListWindow;
import window.SysStrengthenAppendManageWindow;
import mx.containers.VBox;
import common.Header;
import window.GmUserWindow;

public class Manage extends mx.containers.VBox
{
	public function Manage() {}

	[Bindable]
	public var deleteRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var mainScreenVS : mx.containers.ViewStack;
	[Bindable]
	public var sysItems : window.SysItemManageWindow;
	[Bindable]
	public var characters : window.CharacterWindow;
	[Bindable]
	public var maps : window.MapManagementWindow;
	[Bindable]
	public var sysItemsPrice : window.SysItemPriceManageWindow;
	[Bindable]
	public var playerMg : window.PlayerManagementWindow;
	[Bindable]
	public var servers : window.ServerConfigWindow;
	[Bindable]
	public var configs : window.SysConfigManageWindow;
	[Bindable]
	public var sysNotice : window.SysNoticeManageWindow;
	[Bindable]
	public var ip : window.BlackIPManageWindow;
	[Bindable]
	public var bannedWord : window.BannedWordManage;
	[Bindable]
	public var blackwhiteList : window.BlackWhiteUnspeakerWindow;
	[Bindable]
	public var team : window.TeamListWindow;
	[Bindable]
	public var activitys : window.ActivityMainWindow;
	[Bindable]
	public var onlineAward : window.SysOnlineAwardMngmentWindow;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/Manage.mxml:4,32";

}}
