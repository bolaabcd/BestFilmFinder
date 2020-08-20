package com.bestFilmFinder.userInterfaces;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.bestFilmFinder.utils.FileUtils;
import com.bestFilmFinder.utils.FunctionUtils;
import com.bestFilmFinder.utils.WebServerUtils;

public class UserInterfaceWebServerAdapter {
	private final UserInterface defaultUserInterface;
	public UserInterfaceWebServerAdapter(UserInterface ui) {
		defaultUserInterface=ui;
	}
	
	public String getImagesPathFromUser() {
		UserInputString uis =defaultUserInterface.newUserInputString();
		String path = FunctionUtils.retryUltilValid(
					FileUtils::checkIfDirectoryExists,
					(userInputString) -> userInputString.getUserInput("Insert the file path for the server images folder."),
					(userInputString) -> userInputString.getUserInput("Insert the file path for the server images folder. (Previous path invalid)"),
					uis
				);
		if(path.charAt(path.length()-1)=="/".charAt(0))path=path.substring(0, path.length()-1);
		return path;
	}
	public ThreadPoolExecutor getThreadPoolFromUser() {
		UserInputString uis =defaultUserInterface.newUserInputString();
		int poolSize=Integer.parseInt(
				FunctionUtils.retryUltilValid(
						WebServerUtils::checkThreadPoolSize,
						(userInputString)->userInputString.getUserInput("Insert the ammount of Threads in the thread pool (fixed pool).")
						,
						(userInputString)->userInputString.getUserInput("Insert the ammount of Threads in the thread pool (fixed pool, between 1 and 1000 Threads).")
						,
						uis
						)
				);
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
	}
	public InetSocketAddress getAddressFromUser() {
		UserInputString uis =defaultUserInterface.newUserInputString();

		String address=FunctionUtils.retryUltilValid(
				WebServerUtils::checkAddress,
				(userInputString)->userInputString.getUserInput("Insert the server address.")
				,
				(userInputString)->userInputString.getUserInput("Insert the server address (previous address invalid).")
				,
				uis
				);
		int port=Integer.parseInt(
				FunctionUtils.retryUltilValid(
						WebServerUtils::checkPort,
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
