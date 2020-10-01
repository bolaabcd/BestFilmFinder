package com.bestFilmFinder.builders;

import static com.bestFilmFinder.utils.IMDBApiRequestParameters.APIKeyAsString;
import static com.bestFilmFinder.utils.IMDBApiRequestParameters.LanguageAsString;
import static com.bestFilmFinder.utils.IMDBApiRequestParameters.SearchExpressionAsString;

import java.util.HashMap;
import java.util.Map;

public class IMDBSearchAPIMapBuilder {
	private final String apiKey;
	private final String searchExpression;
	private String language;
	
	public IMDBSearchAPIMapBuilder(String apiKey, String searchExpression) {
		this.apiKey = apiKey;
		this.searchExpression = searchExpression;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
	
	public Map<String,String> getMap(){
		Map<String,String> ans=new HashMap<String, String>();
		ans.put(APIKeyAsString, apiKey);
		ans.put(SearchExpressionAsString, searchExpression);
		if(language!=null)
			ans.put(LanguageAsString, language);
		return ans;
	}
}
