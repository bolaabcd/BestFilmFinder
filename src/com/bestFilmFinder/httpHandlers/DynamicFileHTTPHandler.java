package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;

import com.bestFilmFinder.dataInterfaces.DataCombiner;
import com.bestFilmFinder.dataInterfaces.ValidDataGetter;
import com.bestFilmFinder.utils.WebServerUtils;
import com.sun.net.httpserver.HttpExchange;

public abstract class DynamicFileHTTPHandler <T> extends FileHTTPHandler{	
//	protected final ValidDataGetter<List<T>, JSONObject> dynamicDataGetter=getDynamicDataGetter();
//	protected final DataCombiner<File,List<T>,InputStream> fileWithDataCombiner=getFileWithDataCombiner();
//	protected final File templateFile=getTemplateFile();
	
	public DynamicFileHTTPHandler(File defaultDirectory, String httpRootURIContext) {
		super(defaultDirectory, httpRootURIContext);
	}
	
	@Override
	protected void handleResponse(HttpExchange httpExchange, JSONObject requestParams) throws IOException {
		List<T> data=getDynamicDataGetter().getValidData(requestParams, 10000L);
		InputStream result=getFileWithDataCombiner().combineData(getTemplateFile(), data);
		if(result==null)
			WebServerUtils.send404NotFound(httpExchange);
		else {
				byte[] resultBytes=result.readAllBytes();
				httpExchange.sendResponseHeaders(200, resultBytes.length);
				httpExchange.getResponseBody().write(resultBytes);
			}
		httpExchange.close();
	}
	
	protected abstract ValidDataGetter<List<T>, JSONObject> getDynamicDataGetter();
	protected abstract DataCombiner<File,List<T>,InputStream> getFileWithDataCombiner();
	protected abstract File getTemplateFile();

	
}
