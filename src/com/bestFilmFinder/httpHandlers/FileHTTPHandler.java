package com.bestFilmFinder.httpHandlers;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import org.json.JSONException;
import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

public abstract class FileHTTPHandler implements HttpHandler{
	private final File defaultDirectory;
	private final String httpRootURIContext;
	
	public FileHTTPHandler(File defaultDirectory,String httpRootURIContext) {
		this.defaultDirectory=defaultDirectory;
		this.httpRootURIContext=httpRootURIContext;
		
	}
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
		JSONObject result=new JSONObject(body);
		String URIPath=httpExchange.getRequestURI().getPath();	
		result.put(JSONParams.URIPath, URIPath);
		result.put(JSONParams.FilePath, URIPath.replaceFirst(httpRootURIContext, defaultDirectory.getAbsolutePath()));
		return result;
	}

	protected JSONObject getGETParams(HttpExchange httpExchange) {
		String query=httpExchange.getRequestURI().getQuery();
		JSONObject result = new JSONObject();
		if (query != null)
			for (String param : query.split("&")) {
				String[] entry = param.split("=");
				if (entry.length > 1) {
					result.put(entry[0], entry[1]);
				} else {
					result.put(entry[0], "");
				}
			}
		String URIPath=httpExchange.getRequestURI().getPath();	
		result.put(JSONParams.URIPath, URIPath);
		result.put(JSONParams.FilePath, URIPath.replaceFirst(httpRootURIContext, defaultDirectory.getAbsolutePath()));
	    return result;
	}
	
	protected abstract void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException;
}
