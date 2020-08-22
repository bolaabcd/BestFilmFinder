package com.bestFilmFinder.mainPackage;

import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import com.bestFilmFinder.sourceWebInterfaces.IMDBWebsite;
import com.bestFilmFinder.sourceWebInterfaces.RottenTomatoesWebSite;
import com.bestFilmFinder.sourceWebInterfaces.Website;
import com.bestFilmFinder.sourceWebInterfaces.WikipediaWebsite;
import com.bestFilmFinder.userInterfaces.ConsoleUserInterface;
import com.bestFilmFinder.userInterfaces.UserInterfaceWebServerAdapter;


public class Configuration {
	private final UserInterfaceWebServerAdapter uiAdapter;
	private InetSocketAddress defaultAddress;//If null, ask the user.
	private ThreadPoolExecutor defaultThreadPoolExecutor;
	private List<? extends Website> defaultWebsitesInOrder;
	private String defaultImagesPath;
	private String defaultCSSPath;
	private String defaultJSPath;
	
	public Configuration() {//TODO: update to read from file, by default.
		uiAdapter=new UserInterfaceWebServerAdapter(new ConsoleUserInterface());
//		defaultAddress=null;
//		defaultThreadPoolExecutor=null;
//		defaultImagesPath=null;
		defaultWebsitesInOrder=getStandardWebsiteList();
		//Read the file and apply the changes there in the list.
		}
	
	public InetSocketAddress getAddress() {
		if(defaultAddress==null) 
			defaultAddress=uiAdapter.getAddressFromUser();
		return defaultAddress;
	}
	public ThreadPoolExecutor getThreadPoolExecutor() {
		if(defaultThreadPoolExecutor==null)
			defaultThreadPoolExecutor=uiAdapter.getThreadPoolFromUser();
		return defaultThreadPoolExecutor;
	}
	public String getImagesPath() {
		if(defaultImagesPath==null)
			defaultImagesPath=uiAdapter.getPathFromUser("images");
		return defaultImagesPath;
	}
	public String getCSSPath() {
		if(defaultCSSPath==null)
			defaultCSSPath=uiAdapter.getPathFromUser("CSS");
		return defaultCSSPath;
	}
	public String getJSPath() {
		if(defaultJSPath==null)
			defaultJSPath=uiAdapter.getPathFromUser("JavaScript");
		return defaultJSPath;
	}
	
	private List<? extends Website> getStandardWebsiteList(){
		return Arrays.asList(
				new RottenTomatoesWebSite(),
				new IMDBWebsite(),
				new WikipediaWebsite()
				);
	}
	
	

}
