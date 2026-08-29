
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
import vo.LevelWeapon;
import mx.containers.ControlBar;
import mx.containers.ApplicationControlBar;
import mx.containers.TitleWindow;
import mx.containers.HBox;
import mx.containers.VBox;
import mx.controls.Label;
import mx.containers.Box;
import mx.containers.FormItem;

public class CreateLevelWeaponView extends mx.containers.TitleWindow
{
	public function CreateLevelWeaponView() {}

	[Bindable]
	public var _weapon : vo.LevelWeapon;
	[Bindable]
	public var sysItemId : mx.controls.TextInput;
	[Bindable]
	public var sysLevelId : mx.controls.TextInput;
	[Bindable]
	public var sName : mx.controls.TextInput;
	[Bindable]
	public var displayName : mx.controls.TextInput;
	[Bindable]
	public var wId : mx.controls.TextInput;
	[Bindable]
	public var resourceStable : mx.controls.TextInput;
	[Bindable]
	public var resourceChangeable : mx.controls.TextInput;
	[Bindable]
	public var wChangeInTime : mx.controls.TextInput;
	[Bindable]
	public var wMoveSpeedOffset : mx.controls.TextInput;
	[Bindable]
	public var wPenetration : mx.controls.TextInput;
	[Bindable]
	public var wDamage : mx.controls.TextInput;
	[Bindable]
	public var wRangeModifier : mx.controls.TextInput;
	[Bindable]
	public var wFireTime : mx.controls.TextInput;
	[Bindable]
	public var wReloadTime : mx.controls.TextInput;
	[Bindable]
	public var wZoomTime : mx.controls.TextInput;
	[Bindable]
	public var wZoomValue : mx.controls.TextInput;
	[Bindable]
	public var wAmmoOneClip : mx.controls.TextInput;
	[Bindable]
	public var wAmmoCount : mx.controls.TextInput;
	[Bindable]
	public var wAutoFire : mx.controls.TextInput;
	[Bindable]
	public var wTimeToIdle : mx.controls.TextInput;
	[Bindable]
	public var wMaxLength : mx.controls.TextInput;
	[Bindable]
	public var wUpModifier : mx.controls.TextInput;
	[Bindable]
	public var wStabTime : mx.controls.TextInput;
	[Bindable]
	public var wStabLightTime : mx.controls.TextInput;
	[Bindable]
	public var wStabHurt : mx.controls.TextInput;
	[Bindable]
	public var wStabLightHurt : mx.controls.TextInput;
	[Bindable]
	public var wExplodeTime : mx.controls.TextInput;
	[Bindable]
	public var wReadyTime : mx.controls.TextInput;
	[Bindable]
	public var wThrowOutTime : mx.controls.TextInput;
	[Bindable]
	public var wHurtRange : mx.controls.TextInput;
	[Bindable]
	public var wHurt : mx.controls.TextInput;
	[Bindable]
	public var wShootBulletCount : mx.controls.TextInput;
	[Bindable]
	public var wSpread : mx.controls.TextInput;
	[Bindable]
	public var wFireMaxSpeed : mx.controls.TextInput;
	[Bindable]
	public var wFireStartSpeed : mx.controls.TextInput;
	[Bindable]
	public var wFireAceleration : mx.controls.TextInput;
	[Bindable]
	public var wFireResistance : mx.controls.TextInput;
	[Bindable]
	public var wRangeStart : mx.controls.TextInput;
	[Bindable]
	public var wRangeEnd : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyTime : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyTimeModefier : mx.controls.TextInput;
	[Bindable]
	public var wMaxAccuracy : mx.controls.TextInput;
	[Bindable]
	public var wMinAccuracy : mx.controls.TextInput;
	[Bindable]
	public var wNormalOffset : mx.controls.TextInput;
	[Bindable]
	public var wNormalFactor : mx.controls.TextInput;
	[Bindable]
	public var wOnairOffset : mx.controls.TextInput;
	[Bindable]
	public var wOnairFactor : mx.controls.TextInput;
	[Bindable]
	public var wMoveOffset : mx.controls.TextInput;
	[Bindable]
	public var wMoveFactor : mx.controls.TextInput;
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
	public var wCrossOffset : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyDivisor : mx.controls.TextInput;
	[Bindable]
	public var wAccuracyOffset : mx.controls.TextInput;
	[Bindable]
	public var wMaxInaccuracy : mx.controls.TextInput;
	[Bindable]
	public var wXOffset : mx.controls.TextInput;
	[Bindable]
	public var wSightNormalOffset : mx.controls.TextInput;
	[Bindable]
	public var wSightOnairOffset : mx.controls.TextInput;
	[Bindable]
	public var wSightMoveOffset : mx.controls.TextInput;
	[Bindable]
	public var wStabDistance : mx.controls.TextInput;
	[Bindable]
	public var wStabLightDistance : mx.controls.TextInput;
	[Bindable]
	public var wStabWidth : mx.controls.TextInput;
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
	public var wCrossLengthBase : mx.controls.TextInput;
	[Bindable]
	public var wCrossLengthFactor : mx.controls.TextInput;
	[Bindable]
	public var wCrossDistanceBase : mx.controls.TextInput;
	[Bindable]
	public var wCrossDistanceFactor : mx.controls.TextInput;
	[Bindable]
	public var close : mx.controls.Button;
	[Bindable]
	public var save : mx.controls.Button;
	[Bindable]
	public var clear : mx.controls.Button;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/CreateLevelWeaponView.mxml:6,67";

}}
