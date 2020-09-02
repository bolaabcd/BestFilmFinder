package com.bestFilmFinder.httpHandlers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class FileHTTPHandler implements HttpHandler{

	@Override
	public void handle(HttpExchange httpExchange) throws IOException {
		try {
		JSONObject requestParams= null;
		switch(httpExchange.getRequestMethod()) {
		case "GET":
			requestParams = getGETParams(httpExchange);
			handleResponse(httpExchange, requestParams);
			break;
		case "POST":
			requestParams = getPOSTParams(httpExchange);
			handleResponse(httpExchange, requestParams);
			break;
		default:
			WebServerUtils.send404NotFound(httpExchange);
		}
		}catch(ClassCastException|JSONException exception) {
			WebServerUtils.send404NotFound(httpExchange);
		}catch(Exception e) {
			WebServerUtils.send404NotFound(httpExchange);
			e.printStackTrace();
		}
	}

	protected JSONObject getPOSTParams(HttpExchange httpExchange){
		BufferedReader br=new BufferedReader(
				new InputStreamReader(
						httpExchange.getRequestBody()
						)
				);
		String body=br.lines().collect(Collectors.joining("\n"));
		return new JSONObject(body);
	}

	protected abstract JSONObject getGETParams(HttpExchange httpExchange);

	protected abstract void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException;
}
