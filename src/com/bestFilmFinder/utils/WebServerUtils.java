package com.bestFilmFinder.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

import com.sun.net.httpserver.HttpExchange;

public class WebServerUtils {
	private static final byte[] fileNotFoundHTML="<html><body><h1>404 Not Found</h1>Requested File not found</body></html>".getBytes();
	
	public static boolean checkAddress(String addr) {
		return !new InetSocketAddress(addr, 80).isUnresolved();
	}
	public static boolean checkPort(String port) {
		return FunctionUtils.checkIsValidInt(port,0,65535);
	}
	public static boolean checkThreadPoolSize(String size) {
		return FunctionUtils.checkIsValidInt(size,1,1000);//Arbitrarily set to 1000.
	}
	public static InetSocketAddress getCompleteAddress(String address,String port) {
		return new InetSocketAddress(address, Integer.parseInt(port));
	}
	public static ThreadPoolExecutor getThreadPoolExecutor(String size) {
		int poolSize=Integer.parseInt(size);
		return (ThreadPoolExecutor) Executors.newFixedThreadPool(poolSize);
	}
	public static void send404NotFound(HttpExchange httpExchange) throws IOException {
		OutputStream responseBody = httpExchange.getResponseBody();
		httpExchange.sendResponseHeaders(404, fileNotFoundHTML.length);
		responseBody.write(fileNotFoundHTML);
		httpExchange.close();
	}
	public static void send302Redirect(HttpExchange httpExchange,String newLocation) throws IOException {
		httpExchange.getResponseHeaders().add("Location", newLocation);
		httpExchange.sendResponseHeaders(302, -1);
		httpExchange.close();
	}
}
