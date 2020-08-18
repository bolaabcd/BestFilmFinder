package com.bestFilmFinder.mainPackage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bestFilmFinder.httpHandlers.ImageHttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Main {
	public static final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss -> ");

	public static void main(String[] args) throws IOException {
		Configuration padrao= new Configuration();
		
		InetSocketAddress sockAddr=padrao.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);
		server.createContext("/gallery", new  ImageHttpHandler(padrao.getImagesPath()));
		server.setExecutor(padrao.getThreadPoolExecutor());
		server.start();
		System.out.println(formatter.format(new Date())+"Started server!");
	}
}
