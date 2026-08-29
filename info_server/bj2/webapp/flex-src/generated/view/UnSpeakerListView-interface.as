
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
import mx.containers.Panel;
import mx.containers.VBox;
import mx.controls.Button;
import mx.controls.DataGrid;
import mx.controls.TextInput;
import mx.core.ClassFactory;
import mx.core.DeferredInstanceFromClass;
import mx.core.DeferredInstanceFromFunction;
import mx.core.IDeferredInstance;
import mx.core.IFactory;
import mx.core.IPropertyChangeNotifier;
import mx.core.mx_internal;
import mx.rpc.remoting.mxml.RemoteObject;
import mx.styles.*;
import mx.containers.ApplicationControlBar;
import mx.containers.HBox;
import mx.containers.HDividedBox;
import mx.controls.dataGridClasses.DataGridColumn;
import mx.containers.VBox;
import mx.containers.FormItem;
import mx.containers.VDividedBox;

public class UnSpeakerListView extends mx.containers.VBox
{
	public function UnSpeakerListView() {}

	[Bindable]
	public var getRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var updateRo : mx.rpc.remoting.mxml.RemoteObject;
	[Bindable]
	public var userIdTI : mx.controls.TextInput;
	[Bindable]
	public var nameTI : mx.controls.TextInput;
	[Bindable]
	public var queryBtn : mx.controls.Button;
	[Bindable]
	public var clearBtn : mx.controls.Button;
	[Bindable]
	public var addWhiteBtn : mx.controls.Button;
	[Bindable]
	public var addNormalBtn : mx.controls.Button;
	[Bindable]
	public var refreshBtn : mx.controls.Button;
	[Bindable]
	public var playerListPanel : mx.containers.Panel;
	[Bindable]
	public var userDG : mx.controls.DataGrid;
	[Bindable]
	public var unSpeakerListPanel : mx.containers.Panel;
	[Bindable]
	public var whiteDG : mx.controls.DataGrid;

	mx_internal var _bindings : Array;
	mx_internal var _watchers : Array;
	mx_internal var _bindingsByDestination : Object;
	mx_internal var _bindingsBeginWithWord : Object;

include "D:/info_server/bj/WEB-INF/flex-src/view/UnSpeakerListView.mxml:7,125";

}}
