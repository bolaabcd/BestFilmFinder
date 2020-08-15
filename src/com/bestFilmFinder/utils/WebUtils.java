package com.bestFilmFinder.utils;

import java.net.InetSocketAddress;

public class WebUtils {
	public static boolean checkAddress(String addr) {
		return !new InetSocketAddress(addr, 80).isUnresolved();
	}
	public static boolean checkPort(String port) {
		try{
			int val=Integer.parseInt(port);
			if(val>65535||val<0)return false;
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
}
