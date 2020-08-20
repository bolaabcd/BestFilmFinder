package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class FileHttpHandler implements HttpHandler {
	private final String defaultFilesPath;
	private final String httpRootURIContext;
	
	public FileHttpHandler(String defaultFilesPath,String httpRootURIContext) {
		this.defaultFilesPath=defaultFilesPath;
		this.httpRootURIContext=httpRootURIContext;
	}
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		String requestParams= null;
		if ("GET".equals(httpExchange.getRequestMethod())) {
			requestParams = getGETParams(httpExchange);
			handleResponse(httpExchange, requestParams);
		} else {
			WebServerUtils.send404NotFound(httpExchange);
			httpExchange.close();
		}
	}
	private String getGETParams(HttpExchange httpExchange) {
		return httpExchange.getRequestURI().getPath().replaceFirst(httpRootURIContext, defaultFilesPath);
	}
	private void handleResponse(HttpExchange httpExchange, String requestParams) throws IOException {
		OutputStream responseBody = httpExchange.getResponseBody();
		
		File img = new File(requestParams);
		if (!img.exists() || img.isDirectory()) {
			WebServerUtils.send404NotFound(httpExchange);
			httpExchange.close();
		}
		else {
			InputStream iStream=new FileInputStream(img);
			byte[] fileBytes=iStream.readAllBytes();
			iStream.close();
			httpExchange.sendResponseHeaders(200, fileBytes.length);
			responseBody.write(fileBytes);
			responseBody.flush();
			httpExchange.close();
		}
	}

}
