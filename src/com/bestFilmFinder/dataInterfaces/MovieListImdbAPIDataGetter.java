package com.bestFilmFinder.dataInterfaces;

import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.bestFilmFinder.builders.BodyBuilder;
import com.bestFilmFinder.builders.HeaderBuilder;
import com.bestFilmFinder.builders.HttpRequestMethod;
import com.bestFilmFinder.builders.URIBuilder;

public class MovieListImdbAPIDataGetter extends WebAPIDataGetter {

	@Override
	public JSONObject getData(JSONObject filter) {
		return super.getData(filter);
	}
	
	@Override
	protected BodyBuilder getBodyBuilder() {
		return new BodyBuilder(null,null);
	}

	@Override
	protected URIBuilder getURIBuilder() {
		Map<String,String> defaultMap=new HashMap<String,String>();
		defaultMap.put("language", "en");
		String[] required=new String[] {"APIKey","MovieName"};
		String template="https://imdb-api.com/language/API/SearchMovie/APIKey/MovieName";
		return new URIBuilder(template, required , defaultMap);
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
