package utils
{
	import mx.controls.Alert;
	import mx.utils.StringUtil;
	import mx.validators.NumberValidator;
	public class ValidateUtils
	{
		public static function validateInt(str:String):int{
            	var numValidte:NumberValidator=new NumberValidator();
				numValidte.property="text";
				numValidte.domain = "int";
				var frontStr:String = StringUtil.trim(str);
				if(frontStr == ""){
					return 1;
				}
				else if(numValidte.validate(frontStr).type=="invalid"){
					return 2;
				} else if(numValidte.validate(frontStr).type=="valid"){
					return 0;
				}
				return 0;
            }
           public static function validateFloat(str:String):int{
            	var numValidte:NumberValidator=new NumberValidator();
				numValidte.property="text";
				numValidte.domain = "float";
				var frontStr:String = StringUtil.trim(str);
				if(frontStr == ""){
					return 1;
				}
				else if(numValidte.validate(frontStr).type=="invalid"){
					return 2;
				} else if(numValidte.validate(frontStr).type=="valid"){
					return 0;
				}
				return 0;
            }
	}
}