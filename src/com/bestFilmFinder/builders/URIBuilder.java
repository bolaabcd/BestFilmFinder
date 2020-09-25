package com.bestFilmFinder.builders;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Map;

import com.bestFilmFinder.utils.FunctionUtils;

public class URIBuilder implements Builder<URI,Map<String,String>> {
	private final String template;
	private final String[] required;
	private final Map<String, String> defaults;

	public URIBuilder(String template, String[] required, Map<String, String> defaults) {
		this.template = template;
		this.required = required;
		this.defaults = defaults;
	}

	//TODO: make custom runtime exceptions
	@Override
	public URI build(Map<String, String> dymanicArgument) {
		Map<String,String> result=FunctionUtils.combinedMap(required, defaults, dymanicArgument);		
		String uriAsString=template;
		for(String key:result.keySet())
			uriAsString=uriAsString.replaceAll(key, FunctionUtils.encodeURIComponent(result.get(key)));
		URI ans;
		try {
			ans = new URI(uriAsString);
		} catch (URISyntaxException e) {
			throw new RuntimeException(e);
		}
		return ans;
	}


}
