package com.bestFilmFinder.mainPackage;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Main {
	public static void main(String[] args) throws IOException {
		Configuration padrao= new Configuration();
		
		
		InetSocketAddress sockAddr=padrao.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);
		
		server.setExecutor(padrao.getThreadPoolExecutor());
		
	}
}
