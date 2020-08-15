package com.bestFilmFinder.utils;

import java.net.InetSocketAddress;

public class WebServerUtils {
	public static boolean checkAddress(String addr) {
		return !new InetSocketAddress(addr, 80).isUnresolved();
	}
	public static boolean checkPort(String port) {
		return checkIsValidInt(port,0,65535);
	}
	public static boolean checkThreadPoolSize(String size) {
		return checkIsValidInt(size,1,1000);//Arbitrarily set to 1000.
	}
	private static boolean checkIsValidInt(String str,int min,int max) {
		try{
			int val=Integer.parseInt(str);
			if(val>max||val<min)return false;
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}
