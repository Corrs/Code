package com.chinesejr.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class Md5Utils {

	/**
	 * md5加密
	 * @param message 输入的数据
	 * @return 加密后的数据
	 */
	public static String md5(String message){
		try{
			MessageDigest md = MessageDigest.getInstance("md5");
			byte md5[] = md.digest(message.getBytes());
			md.update(md5); 
			BigInteger bigInteger = new BigInteger(md.digest()); 
			return bigInteger.toString();
		}catch(NoSuchAlgorithmException e){
			throw new RuntimeException(e);
		}
	}
	
}
