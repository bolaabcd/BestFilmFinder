package com.bestFilmFinder.mainPackage;

import java.io.File;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;

import org.json.JSONObject;

import com.bestFilmFinder.dataInterfaces.FreeMarkerDataCombiner;
import com.bestFilmFinder.dataInterfaces.TestDataGetter;
import com.bestFilmFinder.dataInterfaces.ValidDataGetter;
import com.bestFilmFinder.httpHandlers.DynamicHTMLHandler;
import com.bestFilmFinder.httpHandlers.RedirectHttpHandler;
import com.bestFilmFinder.httpHandlers.StaticFileHttpHandler;
import com.bestFilmFinder.sourceWebInterfaces.Movie;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static final SimpleLogger logger = new SimpleLogger(System.out);	
	
	public static void main(String[] args) throws IOException {
		ServerConfiguration padrao;
		if(args.length==0)//TODO: change this to automatically create configuration file.
			padrao= new ServerConfiguration();
		else 
			padrao= new ServerConfiguration(new File(args[0]).toPath());
		FreeMarkerDataCombiner.setDirectoryForTemplateLoading(padrao.getHTMLDirectory());
		
		InetSocketAddress sockAddr=padrao.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);

		server.createContext("/gallery", new  StaticFileHttpHandler(padrao.getImagesDirectory(),"/gallery"));
		server.createContext("/CSS", new StaticFileHttpHandler(padrao.getCSSDirectory(), "/CSS"));
		server.createContext("/JS", new StaticFileHttpHandler(padrao.getJSDirectory(),"/JS"));
		server.createContext("/favicon.ico",new RedirectHttpHandler("/gallery/WebSiteIcon.ico"));
		server.createContext("/",new RedirectHttpHandler("/index.html"));
		server.createContext("/index.html", new DynamicHTMLHandler<Movie>(padrao.getHTMLDirectory(), "/index.html",new ValidDataGetter<List<Movie>, JSONObject>((List<Movie> data)->true, new TestDataGetter()) ));
		
		server.setExecutor(padrao.getThreadPoolExecutor());
		server.start();
		logger.log("Started server!");
	}
	
}
