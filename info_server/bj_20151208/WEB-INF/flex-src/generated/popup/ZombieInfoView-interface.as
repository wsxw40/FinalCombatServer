
package popup
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
import mx.containers.TitleWindow;
import mx.controls.Button;
import mx.controls.DataGrid;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import mx.containers.ControlBar;
import mx.containers.TitleWindow;
import mx.containers.VBox;
import mx.controls.Label;

public class ZombieInfoView extends mx.containers.TitleWindow
{
	public function ZombieInfoView() {}

	[Bindable]
	public var zombieInfoDatagrid : mx.controls.DataGrid;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var newBtn : mx.controls.Button;
	[Bindable]
	public var deleteBtn : mx.controls.Button;
	[Bindable]
	public var cancelBtn : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/popup/ZombieInfoView.mxml:6,35";

}}
