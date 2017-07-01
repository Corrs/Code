package com.chinesejr.util;

public class CodeUtils {
	public static final String SUCCESS = "01";
	
	public static final String ERROR = "00"; 
	
	/**
     * 不够位数的在前面补0，保留num的长度位数字
     * @param code
     * @return
     */
	public static String autoGenericCode(int code, int num) {
        String result = "";
        // 保留num的位数
        //0 代表前面补充0     
        // num 代表长度为4     
        // d 代表参数为正数型 
        result = String.format("%0" + num + "d", code);

        return result;
    }
}
