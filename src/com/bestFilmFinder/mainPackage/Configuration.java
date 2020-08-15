package com.bestFilmFinder.mainPackage;

import java.net.InetSocketAddress;

import com.bestFilmFinder.interfaces.ConsoleUserInterface;
import com.bestFilmFinder.interfaces.UserInputString;
import com.bestFilmFinder.interfaces.UserInterface;
import com.bestFilmFinder.utils.FunctionUtils;
import com.bestFilmFinder.utils.WebUtils;


public class Configuration {
	public UserInterface defaultUserInterface;
	private InetSocketAddress defaultAddress;//If null, ask the user.
	
	public Configuration() {//TODO: update to read from file, by default.
		defaultUserInterface=new ConsoleUserInterface();
		defaultAddress=null;
	}
	
	public InetSocketAddress getAddress() {
		if(defaultAddress==null) 
			defaultAddress=getAddressFromUser();
		return defaultAddress;
	}
	
	private InetSocketAddress getAddressFromUser() {
		UserInputString uis =defaultUserInterface.newUserInputString();

		String address=FunctionUtils.retryUltilValid(
				WebUtils::checkAddress,
				(userInputString)->userInputString.getUserInput("Insert the server address.")
				,
				(userInputString)->userInputString.getUserInput("Insert the server address (previous address invalid).")
				,
				uis
				);
		int port=Integer.parseInt(
				FunctionUtils.retryUltilValid(
						WebUtils::checkPort,
						(userInputString)->userInputString.getUserInput("WARNING:Any port below 1024 require admin/sudo permission.\nInsert the port to listen.")
						,
						(userInputString)->userInputString.getUserInput("WARNING:Any port below 1024 require admin/sudo permission.\nInsert the port to listen. (Integer between 0 and 65535)")
						,
						uis
					)
				);
		return new InetSocketAddress(address, port);
	}

}
