package com.bestFilmFinder.mainPackage;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

public class Main {
	public static void main(String[] args) throws IOException {
		InetSocketAddress sockAddr=Configurations.getAddress();
		HttpServer server = HttpServer.create(sockAddr, 5);
	}
}
