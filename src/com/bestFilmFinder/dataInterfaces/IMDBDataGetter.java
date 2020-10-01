package com.bestFilmFinder.dataInterfaces;

import java.util.Map;

import org.json.JSONObject;

import com.bestFilmFinder.builders.BodyBuilder;
import com.bestFilmFinder.builders.HeaderBuilder;
import com.bestFilmFinder.builders.HttpRequestMethod;
import com.bestFilmFinder.builders.URIBuilder;

public class IMDBDataGetter extends WebAPIDataGetter {
	private final String templateURI;
	private final String[] requiredValues;
	private final Map<String,String> defaultValues;
	
	public IMDBDataGetter(String templateURI,String[] requiredValues, Map<String,String> defaultValues) {
		this.templateURI=templateURI;
		this.requiredValues=requiredValues;
		this.defaultValues=defaultValues;
	}
	
	@Override
	public JSONObject getData(JSONObject filter) {
		return super.getData(filter);
	}
	
	@Override
	protected URIBuilder getURIBuilder() {
		return new URIBuilder(templateURI, requiredValues , defaultValues);
		}
	
	@Override
	protected BodyBuilder getBodyBuilder() {
		return new BodyBuilder(null,null);
	}

	@Override
	protected HeaderBuilder getHeaderBuilder() {
		return new HeaderBuilder(null, null);
	}

	@Override
	protected HttpRequestMethod getHttpRequestMethod() {
		return HttpRequestMethod.GET;
	}

}
