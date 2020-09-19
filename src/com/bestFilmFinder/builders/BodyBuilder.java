package com.bestFilmFinder.builders;

import java.util.Map;

import org.json.JSONObject;

import com.bestFilmFinder.utils.FunctionUtils;

public class BodyBuilder implements Builder<byte[],JSONObject>{
	private final JSONObject defaults;
	private final String[] required;

	public BodyBuilder(JSONObject defaults, String[] required) {
		this.defaults = defaults;
		this.required = required;
	}

	@Override
	public byte[] build(JSONObject dymanicArgument) {
		Map<String,Object> jsonAsMap=FunctionUtils.combinedMap(required, defaults.toMap(), dymanicArgument.toMap());
		JSONObject ans=new JSONObject(jsonAsMap);
		return ans.toString().getBytes();
	}
}
