package com.bestFilmFinder.mainPackage;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.bestFilmFinder.sourceWebInterfaces.IMDBWebsite;
import com.bestFilmFinder.sourceWebInterfaces.RottenTomatoesWebSite;
import com.bestFilmFinder.sourceWebInterfaces.Website;
import com.bestFilmFinder.sourceWebInterfaces.WikipediaWebsite;
import com.bestFilmFinder.userInterfaces.ConsoleUserInterface;
import com.bestFilmFinder.userInterfaces.UserInputString;
import com.bestFilmFinder.userInterfaces.UserInterface;
import com.bestFilmFinder.utils.FileUtils;
import com.bestFilmFinder.utils.FunctionUtils;
import com.bestFilmFinder.utils.WebServerUtils;


public class Configuration {
	public UserInterface defaultUserInterface;
	private InetSocketAddress defaultAddress;//If null, ask the user.
	private ThreadPoolExecutor defaultThreadPoolExecutor;
	private List<? extends Website> defaultWebsitesInOrder;
	private String defaultImagesPath;
	
	public Configuration() {//TODO: update to read from file, by default.
		defaultUserInterface=new ConsoleUserInterface();
		//defaultAddress=null;
		//defaultThreadPoolExecutor=null;
		//defaultImagesPath=null;
		defaultWebsitesInOrder=getStandardWebsiteList();
		//Read the file and apply the changes there in the list.
	}
	
	public InetSocketAddress getAddress() {
		if(defaultAddress==null) 
			defaultAddress=getAddressFromUser();
		return defaultAddress;
	}
	public ThreadPoolExecutor getThreadPoolExecutor() {
		if(defaultThreadPoolExecutor==null)
			defaultThreadPoolExecutor=getThreadPoolFromUser();
		return defaultThreadPoolExecutor;
	}
	public String getImagesPath() {
		if(defaultImagesPath==null)
			defaultImagesPath=getImagesPathFromUser();
		return defaultImagesPath;
	}
	
	private List<? extends Website> getStandardWebsiteList(){
		return Arrays.asList(
				new RottenTomatoesWebSite(),
				new IMDBWebsite(),
				new WikipediaWebsite()
				);
	}
	
	private String getImagesPathFromUser() {
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
	private ThreadPoolExecutor getThreadPoolFromUser() {
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
	private InetSocketAddress getAddressFromUser() {
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
