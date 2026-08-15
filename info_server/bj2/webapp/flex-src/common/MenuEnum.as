package common
{
	import mx.collections.ArrayCollection;
	
	public class MenuEnum
	{
		public static const mainMenuItems:ArrayCollection = new ArrayCollection([
			{label: "Menu", children: new ArrayCollection([
				{label: "Item Manager", value: "sysItems"},
		 		{label: "Character Manager", value: "characters"},
		 		{label: "Map Manager", value: "maps"},	
//		 		{label: "Mode Config Manager", value: "modeCofigs"},	
		 		{label: "Player Manager", value: "players"},
		 		{label: "Server Config", value: "servers"},
//		 		{label: "Dead Point", value: "deadth"},
//		 		{label: "System Config", value: "configs"},
//		 		{label: "Activity Config", value: "activity"},
				{label: "Notice Manage", value: "sysNotice"},	
				{label: "GM Notice", value: "notice"},
				{label: "IP Manager", value: "ip"},
				{label: "Black White List Manager", value: "blackwhiteList"}
			])}										
		]);	
	}
}