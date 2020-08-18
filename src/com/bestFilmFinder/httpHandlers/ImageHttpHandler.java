package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class ImageHttpHandler implements HttpHandler {
	private final String defaultImagesPath;
	
	public ImageHttpHandler(String defaultImagesPath) {
		this.defaultImagesPath=defaultImagesPath;
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
		return httpExchange.getRequestURI().getPath().replaceFirst("/gallery", defaultImagesPath);
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
			byte[] imgBytes=iStream.readAllBytes();
			iStream.close();
			httpExchange.sendResponseHeaders(200, imgBytes.length);
			responseBody.write(imgBytes);
			responseBody.flush();
			httpExchange.close();
		}
	}

}
