package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

public class StaticFileHttpHandler extends FileHTTPHandler {
	

public StaticFileHttpHandler(String defaultFilesPath, String httpRootURIContext) {
		super(defaultFilesPath, httpRootURIContext);
	}

	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		OutputStream responseBody = httpExchange.getResponseBody();
		
		File img = new File((String)requestParams.get(JSONParams.FilePath));
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
