
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
import mx.controls.ComboBox;
import mx.controls.TextArea;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import vo.BlackIP;
import mx.containers.ApplicationControlBar;
import mx.containers.TitleWindow;
import mx.containers.Form;
import mx.containers.VBox;
import mx.containers.FormItem;
import mx.containers.FormHeading;

public class CreateBlackIPView extends mx.containers.TitleWindow
{
	public function CreateBlackIPView() {}

	[Bindable]
	public var _blackIP : vo.BlackIP;
	[Bindable]
	public var ip : mx.controls.TextInput;
	[Bindable]
	public var isBanned : mx.controls.ComboBox;
	[Bindable]
	public var bannedTime : mx.controls.TextInput;
	[Bindable]
	public var description : mx.controls.TextArea;
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

include "D:/info_server/bj/WEB-INF/flex-src/view/CreateBlackIPView.mxml:6,30";

}}
