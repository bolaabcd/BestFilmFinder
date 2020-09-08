package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.json.JSONObject;

import com.bestFilmFinder.dataInterfaces.DataCombiner;
import com.bestFilmFinder.dataInterfaces.ValidDataGetter;
import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

public abstract class DynamicFileHTTPHandler <T> extends FileHTTPHandler{	
	protected final ValidDataGetter<Set<T>, JSONObject> dynamicDataGetter=getDynamicDataGetter();
	protected final DataCombiner<File,Set<T>,InputStream> fileWithDataCombiner=getFileWithDataCombiner();
	protected final File templateFile=getTemplateFile();
	
	public DynamicFileHTTPHandler(File defaultDirectory, String httpRootURIContext) {
		super(defaultDirectory, httpRootURIContext);
	}
	
	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		Set<T> data=dynamicDataGetter.getValidData(requestParams, 10000L);
		InputStream result=fileWithDataCombiner.combineData(templateFile, data);
		if(result==null)
			WebServerUtils.send404NotFound(httpExchange);
		else
			result.transferTo(httpExchange.getResponseBody());
		httpExchange.close();
	}
	
	protected abstract ValidDataGetter<Set<T>, JSONObject> getDynamicDataGetter();
	protected abstract DataCombiner<File,Set<T>,InputStream> getFileWithDataCombiner();
	protected abstract File getTemplateFile();

	
}
