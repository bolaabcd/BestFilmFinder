package com.bestFilmFinder.userInterfaces;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
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
	public static final FormTextField filePath=new FormTextField(
			"Insert the file path for the server %s folder.", 
			"Insert the file path for the server %s folder. (Previous path invalid)", 
			FileUtils::checkIfDirectoryExists);
	
	private final UserInterface defaultUserInterface;
	public UserInterfaceWebServerAdapter(UserInterface ui) {
		defaultUserInterface=ui;
		
	}
	
	public String getPathFromUser(String pathForWhat) {
		FormTextInput fti=defaultUserInterface.newFormTextInput(filePath);
		String path=fti.getUserInput(pathForWhat);
		if(path.charAt(path.length()-1)=="/".charAt(0))path=path.substring(0, path.length()-1);
		return path;
	}
	public ThreadPoolExecutor getThreadPoolFromUser() {
		FormTextInput fti=defaultUserInterface.newFormTextInput(threadPoolSize);
		int poolSize=Integer.parseInt(fti.getUserInput());
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
	}
	public InetSocketAddress getAddressFromUser() {
		FormTextInput ftiAddress=defaultUserInterface.newFormTextInput(serverAddress);
		String address=ftiAddress.getUserInput();
		FormTextInput ftiPort=defaultUserInterface.newFormTextInput(serverPort);
		int port=Integer.parseInt(ftiPort.getUserInput());
		return new InetSocketAddress(address, port);
	}
}
