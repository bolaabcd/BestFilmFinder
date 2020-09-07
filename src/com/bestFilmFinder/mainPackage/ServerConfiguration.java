package com.bestFilmFinder.mainPackage;

import java.io.File;
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


public class ServerConfiguration {
	private final UserInterfaceWebServerAdapter uiAdapter;
	private InetSocketAddress defaultAddress;//If null, ask the user.
	private ThreadPoolExecutor defaultThreadPoolExecutor;
	private List<? extends Website> defaultWebsitesInOrder;
	private File defaultImagesDirectory;
	private File defaultCSSDirectory;
	private File defaultJSDirectory;
	private File defaultHTMLTemplateDirectory;

	
	public ServerConfiguration() {//TODO: update to read from file, by default.
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
	public File getImagesDirectory() {
		if(defaultImagesDirectory==null)
			defaultImagesDirectory=uiAdapter.getDirectoryFromUser("images");
		return defaultImagesDirectory;
	}
	public File getCSSDirectory() {
		if(defaultCSSDirectory==null)
			defaultCSSDirectory=uiAdapter.getDirectoryFromUser("CSS");
		return defaultCSSDirectory;
	}
	public File getJSDirectory() {
		if(defaultJSDirectory==null)
			defaultJSDirectory=uiAdapter.getDirectoryFromUser("JavaScript");
		return defaultJSDirectory;
	}
	
	public File getHTMLDirectory() {
		if(defaultHTMLTemplateDirectory==null)
			defaultHTMLTemplateDirectory=uiAdapter.getDirectoryFromUser("HTML_Template");
		return defaultHTMLTemplateDirectory;
	}
	
	private List<? extends Website> getStandardWebsiteList(){
		return Arrays.asList(
				new RottenTomatoesWebSite(),
				new IMDBWebsite(),
				new WikipediaWebsite()
				);
	}
	
	

}
