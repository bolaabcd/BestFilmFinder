package com.bestFilmFinder.userInterfaces;

import java.io.File;
import java.net.InetSocketAddress;
import java.util.concurrent.ThreadPoolExecutor;

import com.bestFilmFinder.utils.FileUtils;
import com.bestFilmFinder.utils.WebServerUtils;

public class UserInterfaceWebServerAdapter {
	private static final FormTextField threadPoolSize=new FormTextField(
			"Insert the ammount of Threads in the thread pool (fixed pool).", //message
			"Insert the ammount of Threads in the thread pool (fixed pool, between 1 and 1000 Threads).", //messageIfInvalid
			WebServerUtils::checkThreadPoolSize //validator
			);
	public static final FormTextField serverAddress=new FormTextField(
			"Insert the server address.", 
			"Insert the server address (previous address invalid).", 
			WebServerUtils::checkAddress
			);
	public static final FormTextField serverPort=new FormTextField(
			"WARNING:Any port below 1024 require admin/sudo permission.\nInsert the port to listen.", 
			"WARNING:Any port below 1024 require admin/sudo permission.\nInsert the port to listen. (Integer between 0 and 65535)", 
			WebServerUtils::checkAddress
			);
	public static final FormTextField directoryPath=new FormTextField(
			"Insert the file path for the server %s folder.", 
			"Insert the file path for the server %s folder. (Previous path invalid)", 
			FileUtils::checkIfDirectoryExists);
	private static final FormTextField APIKey=new FormTextField(
			"Insert the key for the %s API.", 
			"Insert the key for the %s API. (Previous key invalid)", 
			(parameter)->true);//TODO:make it possible to validate API keys
	
	private final UserInterface defaultUserInterface;
	public UserInterfaceWebServerAdapter(UserInterface ui) {
		defaultUserInterface=ui;
		
	}
	
	public File getDirectoryFromUser(String DirectoryForWhat) {
		FormTextInput fti=defaultUserInterface.newFormTextInput(directoryPath);
		String path=fti.getUserInput(DirectoryForWhat);
		//if(path.charAt(path.length()-1)=="/".charAt(0))path=path.substring(0, path.length()-1);
		return new File(path);
	}
	public ThreadPoolExecutor getThreadPoolFromUser() {
		FormTextInput fti=defaultUserInterface.newFormTextInput(threadPoolSize);
		return WebServerUtils.getThreadPoolExecutor(fti.getUserInput());
	}
	public InetSocketAddress getAddressFromUser() {
		FormTextInput ftiAddress=defaultUserInterface.newFormTextInput(serverAddress);
		String address=ftiAddress.getUserInput();
		FormTextInput ftiPort=defaultUserInterface.newFormTextInput(serverPort);
		String port=ftiPort.getUserInput();
		return WebServerUtils.getCompleteAddress(address, port);
	}
	public String getAPIKeyFromUser(String APIName) {
		FormTextInput fti=defaultUserInterface.newFormTextInput(APIKey);
		return fti.getUserInput(APIName);
	}
}
