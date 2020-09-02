package com.bestFilmFinder.mainPackage;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.bestFilmFinder.httpHandlers.StaticFileHttpHandler;
import com.bestFilmFinder.httpHandlers.RedirectHttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static final SimpleLogger logger = new SimpleLogger(System.out);
	
	public static void main(String[] args) throws IOException {
		ServerConfiguration padrao= new ServerConfiguration();
		
		InetSocketAddress sockAddr=padrao.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);

		server.createContext("/gallery", new  StaticFileHttpHandler(padrao.getImagesPath(),"/gallery"));
		server.createContext("/CSS", new StaticFileHttpHandler(padrao.getCSSPath(), "/CSS"));
		server.createContext("/JS", new StaticFileHttpHandler(padrao.getJSPath(),"/JS"));
		server.createContext("/favicon.ico",new RedirectHttpHandler("/gallery/WebSiteIcon.ico"));
		server.createContext("/",new RedirectHttpHandler("/index.html"));
		
		
		server.setExecutor(padrao.getThreadPoolExecutor());
		server.start();
		logger.log("Started server!");
	}
	
}
