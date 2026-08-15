
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
import mx.controls.CheckBox;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import vo.ResourceChangeable;
import mx.containers.ControlBar;
import mx.containers.TitleWindow;
import mx.containers.HBox;
import mx.containers.Form;
import mx.containers.FormItem;

public class AddResourceChangeable extends mx.containers.TitleWindow
{
	public function AddResourceChangeable() {}

	[Bindable]
	public var _resourceChangeable : vo.ResourceChangeable;
	[Bindable]
	public var gs : mx.controls.TextInput;
	[Bindable]
	public var gsCk : mx.controls.CheckBox;
	[Bindable]
	public var si : mx.controls.TextInput;
	[Bindable]
	public var siCk : mx.controls.CheckBox;
	[Bindable]
	public var tg : mx.controls.TextInput;
	[Bindable]
	public var tgCk : mx.controls.CheckBox;
	[Bindable]
	public var mz : mx.controls.TextInput;
	[Bindable]
	public var mzCk : mx.controls.CheckBox;
	[Bindable]
	public var rs : mx.controls.TextInput;
	[Bindable]
	public var rsCk : mx.controls.CheckBox;
	[Bindable]
	public var fh : mx.controls.TextInput;
	[Bindable]
	public var fhCk : mx.controls.CheckBox;
	[Bindable]
	public var fl : mx.controls.TextInput;
	[Bindable]
	public var flCk : mx.controls.CheckBox;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var cancel : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/popup/AddResourceChangeable.mxml:6,16";

}}
