package com.sid.textview;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tools {

	  /**
	   * 检查字符串是否为电话号码的方法,并返回true or false的判断值
	   * @param phoneNumber
	   * @return
	   */
	  public static boolean isPhoneNumberValid(String phoneNumber){
	    boolean isValid = false;
	    /* 可接受的电话格式有:
	     * ^\\(? : 可以使用 "(" 作为开头
	     * (\\d{3}): 紧接着三个数字
	     * \\)? : 可以使用")"接续
	     * [- ]? : 在上述格式后可以使用具选择性的 "-".
	     * (\\d{4}) : 再紧接着三个数字
	     * [- ]? : 可以使用具选择性的 "-" 接续.
	     * (\\d{4})$: 以四个数字结束.
	     * 可以比较下列数字格式:
	     * (123)456-78900, 123-4560-7890, 12345678900, (123)-4560-7890  
	    */
	    String expression = "^\\(?(\\d{3})\\)?[- ]?(\\d{3})[- ]?(\\d{5})$";
	    String expression2 ="^\\(?(\\d{3})\\)?[- ]?(\\d{4})[- ]?(\\d{4})$";
	    CharSequence inputStr = phoneNumber;
	    /*创建Pattern*/
	    Pattern pattern = Pattern.compile(expression);
	    /*将Pattern 以参数传入Matcher作Regular expression*/
	    Matcher matcher = pattern.matcher(inputStr);
	    /*创建Pattern2*/
	    Pattern pattern2 =Pattern.compile(expression2);
	    /*将Pattern2 以参数传入Matcher2作Regular expression*/
	    Matcher matcher2= pattern2.matcher(inputStr);
	    if(matcher.matches()||matcher2.matches())
	    {
	      isValid = true;
	    }
	    return isValid; 
	  }

	  /**
	   * 检测是否是邮箱
	   * @param strEmail
	   * @return
	   */
	  public static boolean isEmail(String strEmail) { 
	    String strPattern = "^[a-zA-Z][\\w\\.-]*[a-zA-Z0-9]@[a-zA-Z0-9][\\w\\.-]*[a-zA-Z0-9]\\.[a-zA-Z][a-zA-Z\\.]*[a-zA-Z]$"; 
	    Pattern p = Pattern.compile(strPattern); 
	    Matcher m = p.matcher(strEmail); 
	    return m.matches(); 
	  } 
}
