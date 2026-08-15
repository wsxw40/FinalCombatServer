package common
{
	import mx.collections.ArrayCollection;
	
	public class ImageEnum
	{
			
		[Embed(source="/resource/level1.png")]
		public static const level1:Class;
		
//		[Embed(source="/resource/level2.png")]
//		public static const Level2:Class;
		
		[Embed(source="/resource/level3.png")]
		public static const level3:Class;
		
		[Embed(source="/resource/level4.png")]
		public static const level4:Class;
		
		[Embed(source="/resource/level5.png")]
		public static const level5:Class;
		
		[Embed(source="/resource/level6.png")]
		public static const level6:Class;
		
		[Embed(source="/resource/level7.png")]
		public static const level7:Class;
		
		[Embed(source="/resource/level8.png")]
		public static const level8:Class;
		
		public static var array:Array = [{xCenter:-6.4,yCenter:-13.7,xSize:61.5,ySize:62.1},
		{},
		{xCenter:2.6,yCenter:0.9,xSize:46.3,ySize:48.8},
		{xCenter:0,yCenter:0,xSize:50,ySize:50},
		{xCenter:0,yCenter:0,xSize:50,ySize:50},
		{xCenter:0,yCenter:0,xSize:50,ySize:50},
		{xCenter:9.2,yCenter:18.9,xSize:55.2998,ySize:55.2998},
		{xCenter:0,yCenter:0,xSize:50,ySize:50}
		];
		public static function getLevelImage(level:String):Class{
			if("level1"==level){return level1;}
			else if("level3"==level){return level3;}
			else if("level4"==level){return level4;}
			else if("level5"==level){return level5;}
			else if("level6"==level){return level6;}
			else if("level7"==level){return level7;}
			else if("level8"==level){return level8;}
			else{return null;}
		}
		public static function getMapParams(level:String):Object{
			if("level1"==level){return array[0];}
			else if("level3"==level){return array[2];}
			else if("level4"==level){return array[3];}
			else if("level5"==level){return array[4];}
			else if("level6"==level){return array[5];}
			else if("level7"==level){return array[6];}
			else if("level8"==level){return array[7];}
			else if("flagwx"==level){return array[7];}
			else if("flaggs"==level){return array[7];}
			else{return null;}
		}
	}
}