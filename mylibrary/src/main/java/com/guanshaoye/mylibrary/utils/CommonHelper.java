package com.guanshaoye.mylibrary.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CommonHelper {


	 /**
    * 是否是email
    * @param str
    * @return
    */
   public static boolean isEmail(String str) {
   	//\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*
		Pattern pattern = Pattern.compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*",Pattern.CASE_INSENSITIVE); 
		Matcher matcher = pattern.matcher(str); 
		if (matcher.matches()) {
			return true;
		}else {
			return false;
		}	  
	}    
	 
   /**
    * 是否是手机号
    * @param str
    * @return
    */
   public static boolean isCellphone(String str) {
		Pattern pattern = Pattern.compile("1[0-9]{10}");
		Matcher matcher = pattern.matcher(str); 
		if (matcher.matches()) {
			return true;
		}else {
			return false;
		}	  
	}

   /**
    * 是不是QQ
    * @param str
    * @return
    */
   public static boolean isQQ(String str) {
		Pattern pattern = Pattern.compile("[1-9]{5,10}");
		Matcher matcher = pattern.matcher(str); 
		if (matcher.matches()) {
			return true;
		}else {
			return false;
		}	  
	}
}
