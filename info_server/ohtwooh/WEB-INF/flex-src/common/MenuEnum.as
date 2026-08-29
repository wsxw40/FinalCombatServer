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
		 		{label: "Mode Config Manager", value: "modeCofigs"},	
		 		{label: "Player Manager", value: "players"},
			])}										
		]);	
	}
}