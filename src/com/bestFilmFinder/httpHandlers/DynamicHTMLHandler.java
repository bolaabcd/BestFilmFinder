package com.bestFilmFinder.httpHandlers;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import org.json.JSONObject;

import com.bestFilmFinder.dataInterfaces.DataCombiner;
import com.bestFilmFinder.dataInterfaces.FreeMarkerDataCombiner;
import com.bestFilmFinder.dataInterfaces.ValidDataGetter;


public class DynamicHTMLHandler<T> extends DynamicFileHTTPHandler<T>{	
	private final ValidDataGetter<List<T>, JSONObject> dynamicDataGetter;
	private final DataCombiner<File,List<T>,InputStream> fileWithDataCombiner=new FreeMarkerDataCombiner<T>();
	private final File templateFile;

	public DynamicHTMLHandler(File defaultTemplateDirectory, String httpRootURIContext,ValidDataGetter<List<T>, JSONObject> dynamicDataGetter) {
		//TODO: add exception if defaultTemplateFile is a directory.
		super(defaultTemplateDirectory, httpRootURIContext);
		String filePath=defaultTemplateDirectory.toString()+httpRootURIContext;
		templateFile=new File(filePath);
		this.dynamicDataGetter=dynamicDataGetter;
	}

	@Override
	protected ValidDataGetter<List<T>, JSONObject> getDynamicDataGetter() {
		return dynamicDataGetter;
	}

	@Override
	protected DataCombiner<File,List<T>,InputStream> getFileWithDataCombiner() {
		return fileWithDataCombiner;
	}

	@Override
	protected File getTemplateFile() {
		return templateFile;
	}

}
