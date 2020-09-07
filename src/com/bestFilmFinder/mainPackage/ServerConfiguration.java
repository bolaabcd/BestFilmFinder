package com.bestFilmFinder.mainPackage;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadPoolExecutor;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.XML;

import com.bestFilmFinder.httpHandlers.JSONParams;
import com.bestFilmFinder.sourceWebInterfaces.IMDBWebsite;
import com.bestFilmFinder.sourceWebInterfaces.RottenTomatoesWebSite;
import com.bestFilmFinder.sourceWebInterfaces.Website;
import com.bestFilmFinder.sourceWebInterfaces.WikipediaWebsite;
import com.bestFilmFinder.userInterfaces.ConsoleUserInterface;
import com.bestFilmFinder.userInterfaces.UserInterfaceWebServerAdapter;
import com.bestFilmFinder.utils.FileUtils;
import com.bestFilmFinder.utils.WebServerUtils;


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
	public ServerConfiguration(Path configs) {
		this();
		try {
			String fileContent;
			JSONObject jsonConfigFile;
			fileContent = Files.readString(configs);
			if (configs.toString().toLowerCase().endsWith(".json"))
				jsonConfigFile = new JSONObject(fileContent);
			else if (configs.toString().toLowerCase().endsWith(".xml"))
				jsonConfigFile = XML.toJSONObject(fileContent);
			else
				jsonConfigFile=new JSONObject();
			String serverAddress=getStringFromJSON(jsonConfigFile,JSONParams.serverAddress);
			String serverPort=getStringFromJSON(jsonConfigFile,JSONParams.serverPort);
			String CSSDirectory=getStringFromJSON(jsonConfigFile,JSONParams.CSSDirectory);
			String HTMLTemplateDirectory=getStringFromJSON(jsonConfigFile,JSONParams.HTMLTemplateDirectory);
			String imagesDirectory=getStringFromJSON(jsonConfigFile,JSONParams.imagesDirectory);
			String JSDirectory=getStringFromJSON(jsonConfigFile,JSONParams.JSDirectory);
			String threadPoolExecutorSize=getStringFromJSON(jsonConfigFile,JSONParams.threadPoolExecutorSize);
			if(serverAddress!=null&&serverPort!=null)
				if(WebServerUtils.checkAddress(serverAddress)&&WebServerUtils.checkPort(serverPort))
					this.defaultAddress=WebServerUtils.getCompleteAddress(serverAddress, serverPort);
			if(CSSDirectory!=null)
				if(FileUtils.checkIfDirectoryExists(CSSDirectory))
					this.defaultCSSDirectory=new File(CSSDirectory);
			if(JSDirectory!=null)
				if(FileUtils.checkIfDirectoryExists(JSDirectory))
					this.defaultJSDirectory=new File(JSDirectory);
			if(HTMLTemplateDirectory!=null)
				if(FileUtils.checkIfDirectoryExists(HTMLTemplateDirectory))
					this.defaultHTMLTemplateDirectory=new File(HTMLTemplateDirectory);
			if(imagesDirectory!=null)
				if(FileUtils.checkIfDirectoryExists(imagesDirectory))
					this.defaultImagesDirectory=new File(imagesDirectory);
			if(threadPoolExecutorSize!=null)
				if(WebServerUtils.checkThreadPoolSize(threadPoolExecutorSize))
					this.defaultThreadPoolExecutor=WebServerUtils.getThreadPoolExecutor(threadPoolExecutorSize);
			
		} catch (IOException e) {
			System.out.println("An error ocurred while oppening the configuration file:");
			e.printStackTrace();
		} catch(JSONException j) {
			System.out.println("Invalid JSON or XML file.");
			j.printStackTrace();
		}
		
		
		
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
	private String getStringFromJSON(JSONObject jsonObject,String parameter) {
		String ans;
		try {
			ans=jsonObject.getString(parameter);
		}catch(JSONException j) {
			return null;
		}
		return ans;
	}
	
	

}
