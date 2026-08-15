
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
import mx.containers.VBox;
import mx.containers.ViewStack;
import mx.controls.TabBar;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.styles.*;
import view.WeaponFormItem1;
import view.WeaponFormItem2;
import view.WeaponFormItem3;
import view.WeaponFormItem4;
import mx.containers.VBox;

public class WeaponForm extends mx.containers.VBox
{
	public function WeaponForm() {}

	[Bindable]
	public var tb : mx.controls.TabBar;
	[Bindable]
	public var vs : mx.containers.ViewStack;
	[Bindable]
	public var w1 : view.WeaponFormItem1;
	[Bindable]
	public var w4 : view.WeaponFormItem4;
	[Bindable]
	public var w2 : view.WeaponFormItem2;
	[Bindable]
	public var w3 : view.WeaponFormItem3;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/WeaponForm.mxml:4,14";

}}
