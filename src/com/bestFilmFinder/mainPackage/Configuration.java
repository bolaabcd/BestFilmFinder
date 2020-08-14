package com.bestFilmFinder.mainPackage;

import java.net.InetSocketAddress;

import com.bestFilmFinder.interfaces.ConsoleUserInterface;
import com.bestFilmFinder.interfaces.UserInputString;
import com.bestFilmFinder.interfaces.UserInterface;
import com.bestFilmFinder.utils.FunctionUtils;


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
		String address=uis.newUserInput("Insert the server address.");
		int port=Integer.parseInt(
				FunctionUtils.retryUltilValid(
						(input)->{
							try{
								Integer.parseInt(input);
							}catch(NumberFormatException e){
								return false;
							}
							return true;
						},
						(userInputString)->{
							return userInputString.newUserInput("Insert the port to listen.");
						},
						(userInputString)->{
							return userInputString.newUserInput("Insert the port to listen. (Integer between 1 and 65000)");
						},
						uis
					)
				);
		return new InetSocketAddress(address, port);
	}

}
