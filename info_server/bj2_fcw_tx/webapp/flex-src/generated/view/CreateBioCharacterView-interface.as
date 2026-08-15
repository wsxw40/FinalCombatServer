
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
import vo.BioCharacter;
import mx.containers.ApplicationControlBar;
import mx.containers.TitleWindow;
import mx.containers.Form;
import mx.containers.VBox;
import mx.containers.FormItem;
import mx.containers.FormHeading;

public class CreateBioCharacterView extends mx.containers.TitleWindow
{
	public function CreateBioCharacterView() {}

	[Bindable]
	public var _character : vo.BioCharacter;
	[Bindable]
	public var cid : mx.controls.TextInput;
	[Bindable]
	public var sid : mx.controls.TextInput;
	[Bindable]
	public var type : mx.controls.TextInput;
	[Bindable]
	public var weapons : mx.controls.TextInput;
	[Bindable]
	public var costumes : mx.controls.TextInput;
	[Bindable]
	public var sname : mx.controls.TextInput;
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
	public var maxHp : mx.controls.TextInput;
	[Bindable]
	public var exHp : mx.controls.TextInput;
	[Bindable]
	public var cost : mx.controls.TextInput;
	[Bindable]
	public var defaultLevel : mx.controls.TextInput;
	[Bindable]
	public var isFired : mx.controls.TextInput;
	[Bindable]
	public var resourceName : mx.controls.TextInput;
	[Bindable]
	public var isEnable : mx.controls.TextInput;
	[Bindable]
	public var controllerHeight : mx.controls.TextInput;
	[Bindable]
	public var controllerRadius : mx.controls.TextInput;
	[Bindable]
	public var controllerCrouchHeight : mx.controls.TextInput;
	[Bindable]
	public var scoreOffset : mx.controls.TextInput;
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

include "D:/info_server/bj/WEB-INF/flex-src/view/CreateBioCharacterView.mxml:6,16";

}}
