package com.bestFilmFinder.dataInterfaces;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.Map;

import org.json.JSONObject;

import com.bestFilmFinder.builders.BodyBuilder;
import com.bestFilmFinder.builders.HeaderBuilder;
import com.bestFilmFinder.builders.HttpRequestMethod;
import com.bestFilmFinder.builders.URIBuilder;
import com.bestFilmFinder.httpHandlers.JSONParams;

public abstract class WebAPIDataGetter implements DataGetter<JSONObject, JSONObject>{
	private final HttpClient httpClient;
	
	public WebAPIDataGetter() {
		this.httpClient=HttpClient.newHttpClient();
	}
	public WebAPIDataGetter(HttpClient httpClient) {
		this.httpClient=httpClient;
	}
	@Override
	public JSONObject getData(JSONObject filter) {
		JSONObject uriFilter=filter.getJSONObject(JSONParams.URIFilter);
		JSONObject bodyFilter=filter.getJSONObject(JSONParams.bodyFilter);
		JSONObject headerFilter=filter.getJSONObject(JSONParams.headerFilter);
		HttpRequest.Builder requestBuilder;
		if (uriFilter != null) {
			Map<String, Object> uriMap = uriFilter.toMap();
			requestBuilder=HttpRequest.newBuilder(getURIBuilder().build((Map<String, String>) (Map<?, ?>) uriMap));
		}else {
			requestBuilder=HttpRequest.newBuilder();
		}
		if(bodyFilter!=null) {
			BodyPublisher bodyPublisher=BodyPublishers.ofByteArray(getBodyBuilder().build(bodyFilter));
			requestBuilder.method(getHttpRequestMethod().toString(), bodyPublisher);
		}
		if(headerFilter!=null) {
			Map<String,String> headerMap=(Map<String, String>) (Map<?, ?>)headerFilter.toMap();
			for(String key:headerMap.keySet())
				requestBuilder.header(key, headerMap.get(key));
		}
		HttpResponse<String> result;
		try {
			result = httpClient.send(requestBuilder.build(), BodyHandlers.ofString());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(e);
		}

		return new JSONObject(result.body());
	}
	protected abstract BodyBuilder getBodyBuilder();
	protected abstract URIBuilder getURIBuilder();
	protected abstract HeaderBuilder getHeaderBuilder();
	protected abstract HttpRequestMethod getHttpRequestMethod();
	
}
