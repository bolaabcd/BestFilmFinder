package com.bestFilmFinder.httpHandlers;

import java.io.IOException;

import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

public class DynamicFileHTTPHandler extends FileHTTPHandler{

	@Override
	protected JSONObject getGETParams(HttpExchange httpExchange) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		// TODO Auto-generated method stub
		WebServerUtils.send404NotFound(httpExchange);
		httpExchange.close();
	}
	
}
