
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

public class WeaponFormItem3 extends mx.containers.HBox
{
	public function WeaponFormItem3() {}

	[Bindable]
	public var wExplodeTime : mx.controls.TextInput;
	[Bindable]
	public var wReadyTime : mx.controls.TextInput;
	[Bindable]
	public var wThrowOutTime : mx.controls.TextInput;
	[Bindable]
	public var wBackFactor : mx.controls.TextInput;
	[Bindable]
	public var wFlashRangeStart : mx.controls.TextInput;
	[Bindable]
	public var wFlashRangeEnd : mx.controls.TextInput;
	[Bindable]
	public var wFlashBackFactor : mx.controls.TextInput;
	[Bindable]
	public var wTimeMax : mx.controls.TextInput;
	[Bindable]
	public var wTimeFade : mx.controls.TextInput;
	[Bindable]
	public var wTime : mx.controls.TextInput;
	[Bindable]
	public var wUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyTime : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyTimeModefier : mx.controls.TextInput;
	[Bindable]
	public var wMaxAccuracy : mx.controls.TextInput;
	[Bindable]
	public var wMinAccuracy : mx.controls.TextInput;
	[Bindable]
	public var wHitAcceleration : mx.controls.TextInput;
	[Bindable]
	public var wHitDistance : mx.controls.TextInput;
	[Bindable]
	public var wMaxDistance : mx.controls.TextInput;
	[Bindable]
	public var wLastTime : mx.controls.TextInput;
	[Bindable]
	public var wSpecialRange : mx.controls.TextInput;
	[Bindable]
	public var wSpecialDistance : mx.controls.TextInput;
	[Bindable]
	public var wSpecialLasttime : mx.controls.TextInput;
	[Bindable]
	public var wSpecialHurt : mx.controls.TextInput;
	[Bindable]
	public var wSpecialLasthurt : mx.controls.TextInput;
	[Bindable]
	public var wHurt : mx.controls.TextInput;
	[Bindable]
	public var wHurtRange : mx.controls.TextInput;
	[Bindable]
	public var WAddBlood : mx.controls.TextInput;
	[Bindable]
	public var wFlySpeed : mx.controls.TextInput;
	[Bindable]
	public var wMaxaliveTime : mx.controls.TextInput;
	[Bindable]
	public var WGravity : mx.controls.TextInput;
	[Bindable]
	public var wParticlenum : mx.controls.TextInput;
	[Bindable]
	public var wShowSpeed : mx.controls.TextInput;
	[Bindable]
	public var WAmmoType : mx.controls.TextInput;
	[Bindable]
	public var WAmmopartKey : mx.controls.TextInput;
	[Bindable]
	public var wCapsuleHeight : mx.controls.TextInput;
	[Bindable]
	public var wCapsuleRadius : mx.controls.TextInput;
	[Bindable]
	public var MType : mx.controls.TextInput;
	[Bindable]
	public var MValue : mx.controls.TextInput;
	[Bindable]
	public var teamOccupyLength : mx.controls.TextInput;
	[Bindable]
	public var teamOccupyWidth : mx.controls.TextInput;
	[Bindable]
	public var needTeamPlaceLevel : mx.controls.TextInput;
	[Bindable]
	public var teamItemUpgradePrice : mx.controls.TextInput;
	[Bindable]
	public var timeForCreate : mx.controls.TextInput;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/WeaponFormItem3.mxml:4,38";

}}
