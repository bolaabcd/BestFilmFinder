package com.bestFilmFinder.httpHandlers;

import java.io.IOException;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public class RedirectHttpHandler implements HttpHandler{
	private final String newLocation;
	
	public RedirectHttpHandler(String newLocation) {
		this.newLocation=newLocation;
	}
	
	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		WebServerUtils.send302Redirect(httpExchange, newLocation);
	}

}
