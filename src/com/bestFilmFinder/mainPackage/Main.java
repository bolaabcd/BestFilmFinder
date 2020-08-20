package com.bestFilmFinder.mainPackage;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.bestFilmFinder.httpHandlers.FileHttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static final SimpleLogger logger = new SimpleLogger(System.out);
	
	public static void main(String[] args) throws IOException {
		Configuration padrao= new Configuration();
		
		InetSocketAddress sockAddr=padrao.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);
		server.createContext("/gallery", new  FileHttpHandler(padrao.getImagesPath(),"/gallery"));
		server.setExecutor(padrao.getThreadPoolExecutor());
		server.start();
		logger.log("Started server!");
	}
	
}
