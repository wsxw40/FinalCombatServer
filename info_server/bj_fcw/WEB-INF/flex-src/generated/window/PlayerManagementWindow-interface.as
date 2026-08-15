
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
import mx.containers.ViewStack;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import window.GMMailManageWindow;
import window.ModifyItemWindow;
import window.PlayerLogManagerWindow;
import window.PlayerManageWindow;
import window.RecoveryItemWindow;
import window.TransferPlayer;
import view.PayLogManageView;
import mx.controls.TabBar;
import mx.containers.VBox;
import view.TopUpView;

public class PlayerManagementWindow extends mx.containers.VBox
{
	public function PlayerManagementWindow() {}

	[Bindable]
	public var subTab : mx.containers.ViewStack;
	[Bindable]
	public var players : window.PlayerManageWindow;
	[Bindable]
	public var playerLog : window.PlayerLogManagerWindow;
	[Bindable]
	public var gmMail : window.GMMailManageWindow;
	[Bindable]
	public var tranfer : window.TransferPlayer;
	[Bindable]
	public var recovery_item : window.RecoveryItemWindow;
	[Bindable]
	public var modify_item : window.ModifyItemWindow;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;


}}
