package com.chinesejr.util;

import org.apache.commons.lang.StringUtils;

public class StringUtil extends StringUtils {
	public static boolean isNotEmpty(Integer i) {
		boolean flag = false;
		
		if(i != null) {
			flag = isNotEmpty(String.valueOf(i));
		}
		
		return flag;
	}
}
