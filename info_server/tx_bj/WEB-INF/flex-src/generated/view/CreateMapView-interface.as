
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
import mx.containers.TitleWindow;
import mx.controls.Button;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import vo.LevelInfo;
import mx.containers.ApplicationControlBar;
import mx.containers.TitleWindow;
import mx.containers.Form;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.FormItem;
import mx.containers.FormHeading;

public class CreateMapView extends mx.containers.TitleWindow
{
	public function CreateMapView() {}

	[Bindable]
	public var _levelInfo : vo.LevelInfo;
	[Bindable]
	public var type : mx.controls.TextInput;
	[Bindable]
	public var mapName : mx.controls.TextInput;
	[Bindable]
	public var index : mx.controls.TextInput;
	[Bindable]
	public var isNew : mx.controls.TextInput;
	[Bindable]
	public var displayName : mx.controls.TextInput;
	[Bindable]
	public var startPoints : mx.controls.TextInput;
	[Bindable]
	public var blastPoints : mx.controls.TextInput;
	[Bindable]
	public var flagPoints : mx.controls.TextInput;
	[Bindable]
	public var weaponPoints : mx.controls.TextInput;
	[Bindable]
	public var bossItems : mx.controls.TextInput;
	[Bindable]
	public var supplies : mx.controls.TextInput;
	[Bindable]
	public var isSelf : mx.controls.TextInput;
	[Bindable]
	public var levelHorizon : mx.controls.TextInput;
	[Bindable]
	public var targetSpeed : mx.controls.TextInput;
	[Bindable]
	public var lineInfo : mx.controls.TextInput;
	[Bindable]
	public var vehicleInfo : mx.controls.TextInput;
	[Bindable]
	public var description : mx.controls.TextInput;
	[Bindable]
	public var num4Team : mx.controls.TextInput;
	[Bindable]
	public var cancel : mx.controls.Button;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var clear : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/CreateMapView.mxml:6,16";

}}
