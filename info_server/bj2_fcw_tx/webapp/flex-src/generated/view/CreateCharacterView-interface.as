
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
import vo.Character;
import mx.containers.ApplicationControlBar;
import mx.containers.TitleWindow;
import mx.containers.Form;
import mx.containers.VBox;
import mx.containers.FormItem;
import mx.containers.FormHeading;

public class CreateCharacterView extends mx.containers.TitleWindow
{
	public function CreateCharacterView() {}

	[Bindable]
	public var _character : vo.Character;
	[Bindable]
	public var sname : mx.controls.TextInput;
	[Bindable]
	public var maxHp : mx.controls.TextInput;
	[Bindable]
	public var exHp : mx.controls.TextInput;
	[Bindable]
	public var level : mx.controls.TextInput;
	[Bindable]
	public var runSpeed : mx.controls.TextInput;
	[Bindable]
	public var walkSpeed : mx.controls.TextInput;
	[Bindable]
	public var crouchSpeed : mx.controls.TextInput;
	[Bindable]
	public var accelSpeed : mx.controls.TextInput;
	[Bindable]
	public var jumpSpeed : mx.controls.TextInput;
	[Bindable]
	public var throwSpeed : mx.controls.TextInput;
	[Bindable]
	public var costumeResource : mx.controls.TextInput;
	[Bindable]
	public var isDefault : mx.controls.TextInput;
	[Bindable]
	public var cost : mx.controls.TextInput;
	[Bindable]
	public var isFired : mx.controls.TextInput;
	[Bindable]
	public var resourceName : mx.controls.TextInput;
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

include "D:/info_server/bj/WEB-INF/flex-src/view/CreateCharacterView.mxml:6,16";

}}
