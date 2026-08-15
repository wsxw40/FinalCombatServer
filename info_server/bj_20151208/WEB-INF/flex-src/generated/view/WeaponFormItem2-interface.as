
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
import mx.containers.HBox;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import mx.containers.HBox;
import mx.containers.Form;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.FormItem;

public class WeaponFormItem2 extends mx.containers.HBox
{
	public function WeaponFormItem2() {}

	[Bindable]
	public var wNormalUpBase : mx.controls.TextInput;
	[Bindable]
	public var wNormalLateralBase : mx.controls.TextInput;
	[Bindable]
	public var wNormalUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wNormalLateralModifier : mx.controls.TextInput;
	[Bindable]
	public var wNormalUpMax : mx.controls.TextInput;
	[Bindable]
	public var wNormalLateralMax : mx.controls.TextInput;
	[Bindable]
	public var wNormalDirChange : mx.controls.TextInput;
	[Bindable]
	public var wMoveUpBase : mx.controls.TextInput;
	[Bindable]
	public var wMoveLateralBase : mx.controls.TextInput;
	[Bindable]
	public var wMoveUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wMoveLateralModifier : mx.controls.TextInput;
	[Bindable]
	public var wMoveUpMax : mx.controls.TextInput;
	[Bindable]
	public var wMoveLateralMax : mx.controls.TextInput;
	[Bindable]
	public var wMoveDirChange : mx.controls.TextInput;
	[Bindable]
	public var wOnairUpBase : mx.controls.TextInput;
	[Bindable]
	public var wOnairLateralBase : mx.controls.TextInput;
	[Bindable]
	public var wOnairUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wOnairLateralModifier : mx.controls.TextInput;
	[Bindable]
	public var wOnairUpMax : mx.controls.TextInput;
	[Bindable]
	public var wOnairLateralMax : mx.controls.TextInput;
	[Bindable]
	public var wOnairDirChange : mx.controls.TextInput;
	[Bindable]
	public var wCrouchUpBase : mx.controls.TextInput;
	[Bindable]
	public var wCrouchLateralBase : mx.controls.TextInput;
	[Bindable]
	public var wCrouchUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wCrouchLateralModifier : mx.controls.TextInput;
	[Bindable]
	public var wCrouchUpMax : mx.controls.TextInput;
	[Bindable]
	public var wCrouchLateralMax : mx.controls.TextInput;
	[Bindable]
	public var wCrouchDirChange : mx.controls.TextInput;
	[Bindable]
	public var wStabTime : mx.controls.TextInput;
	[Bindable]
	public var wStabLightTime : mx.controls.TextInput;
	[Bindable]
	public var wStabHurt : mx.controls.TextInput;
	[Bindable]
	public var wStabLightHurt : mx.controls.TextInput;
	[Bindable]
	public var wStabDistance : mx.controls.TextInput;
	[Bindable]
	public var wStabLightDistance : mx.controls.TextInput;
	[Bindable]
	public var wStabWidth : mx.controls.TextInput;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/WeaponFormItem2.mxml:4,38";

}}
