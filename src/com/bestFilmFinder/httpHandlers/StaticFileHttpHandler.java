package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

public class StaticFileHttpHandler extends FileHTTPHandler {
	private final String defaultFilesPath;
	private final String httpRootURIContext;
	
	public StaticFileHttpHandler(String defaultFilesPath,String httpRootURIContext) {
		this.defaultFilesPath=defaultFilesPath;
		this.httpRootURIContext=httpRootURIContext;
		
	}
	@Override
	protected JSONObject getGETParams(HttpExchange httpExchange) {
		String filePath=httpExchange.getRequestURI().getPath().replaceFirst(httpRootURIContext, defaultFilesPath);
		Map<String,String> ans=new HashMap<String,String>();
		ans.put("filePath", filePath);
		return 	new JSONObject(ans);
	}
	@Override
	protected JSONObject getPOSTParams(HttpExchange httpExchange){
		JSONObject ans=super.getPOSTParams(httpExchange);
		String newPath=defaultFilesPath+"/"+((String )ans.get("filePath"));
		ans.put("filePath", newPath.replaceAll("\\.\\./", ""));
		return ans;
	}
	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		OutputStream responseBody = httpExchange.getResponseBody();
		
		File img = new File((String)requestParams.get("filePath"));
		if (!img.exists() || img.isDirectory()) {
			WebServerUtils.send404NotFound(httpExchange);
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
