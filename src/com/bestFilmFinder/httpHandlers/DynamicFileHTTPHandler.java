package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.IOException;

import org.json.JSONObject;

import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class DynamicFileHTTPHandler extends FileHTTPHandler{
	protected static final Configuration freemarkerConfig=getDefaultFreemarkerConfig();
	
	private static final Configuration getDefaultFreemarkerConfig() {
		Configuration config= new Configuration(Configuration.VERSION_2_3_30);
		config.setDefaultEncoding("UTF-8");
		//TODO: final version should use RETHROW_HANDLER
		config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		config.setLogTemplateExceptions(false);
		config.setWrapUncheckedExceptions(true);
		config.setFallbackOnNullLoopVariable(false);
		return config;
	}
	
	public static void setDirectoryForTemplateLoading(File directory) throws IOException {
		freemarkerConfig.setDirectoryForTemplateLoading(directory);
	}

	public DynamicFileHTTPHandler(String defaultFilesPath, String httpRootURIContext) {
		super(defaultFilesPath, httpRootURIContext);
	}

	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		// TODO Auto-generated method stub
		WebServerUtils.send404NotFound(httpExchange);
		httpExchange.close();
	}
	
}
