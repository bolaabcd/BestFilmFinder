package com.bestFilmFinder.mainPackage;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;

import com.bestFilmFinder.dataInterfaces.FreeMarkerDataCombiner;
import com.bestFilmFinder.httpHandlers.RedirectHttpHandler;
import com.bestFilmFinder.httpHandlers.StaticFileHttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static final SimpleLogger logger = new SimpleLogger(System.out);	
	
	public static void main(String[] args) throws IOException {
		ServerConfiguration config;
		if(args.length==0)//TODO: change this to automatically create configuration file.
			config= new ServerConfiguration();
		else 
			config= new ServerConfiguration(new File(args[0]).toPath());
		FreeMarkerDataCombiner.setDirectoryForTemplateLoading(config.getHTMLDirectory());
		
		InetSocketAddress sockAddr=config.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);

		server.createContext("/gallery", new  StaticFileHttpHandler(config.getImagesDirectory(),"/gallery"));
		server.createContext("/CSS", new StaticFileHttpHandler(config.getCSSDirectory(), "/CSS"));
		server.createContext("/JS", new StaticFileHttpHandler(config.getJSDirectory(),"/JS"));
		server.createContext("/favicon.ico",new RedirectHttpHandler("/gallery/WebSiteIcon.ico"));
		server.createContext("/",new RedirectHttpHandler("/index.html"));

		server.setExecutor(config.getThreadPoolExecutor());
		server.start();
		logger.log("Started server!");
	}
	
}
