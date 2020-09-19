package com.bestFilmFinder.builders;

import java.util.Map;

import com.bestFilmFinder.utils.FunctionUtils;

public class HeaderBuilder implements Builder<Map<String,String>, Map<String,String>>{
	private final String[] required;
	private final Map<String, String> defaults;

	public HeaderBuilder(String[] required, Map<String, String> defaults) {
		this.required = required;
		this.defaults = defaults;
	}

	@Override
	public Map<String, String> build(Map<String, String> dymanicArgument) {
		return FunctionUtils.combinedMap(required, defaults, dymanicArgument);
	}
	
}
