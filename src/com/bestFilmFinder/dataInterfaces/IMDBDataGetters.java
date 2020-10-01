package com.bestFilmFinder.dataInterfaces;

import static com.bestFilmFinder.utils.IMDBApiRequestParameters.APIKeyAsString;
import static com.bestFilmFinder.utils.IMDBApiRequestParameters.LanguageAsString;
import static com.bestFilmFinder.utils.IMDBApiRequestParameters.SearchExpressionAsString;
import static com.bestFilmFinder.utils.IMDBApiRequestParameters.SearchTypeAsString;

import java.util.HashMap;
import java.util.Map;

import com.bestFilmFinder.httpHandlers.JSONParams;
import com.bestFilmFinder.mainPackage.Main;

public class IMDBDataGetters {
	private static IMDBDataGetter getSearchAPIDataGetter(Map<String, String> defaultValues) {
		String[] requiredValues = new String[] { APIKeyAsString, SearchExpressionAsString, LanguageAsString,
				SearchTypeAsString };
		if(!defaultValues.containsKey(LanguageAsString))
			defaultValues.put(LanguageAsString, "en");
		if(!defaultValues.containsKey(APIKeyAsString))
			defaultValues.put(APIKeyAsString, Main.globalConfiguration.getAPIKey(JSONParams.IMDB_API));
		
		StringBuilder templateURIBuilder = new StringBuilder();
		templateURIBuilder.append("https://imdb-api.com/");
		templateURIBuilder.append(LanguageAsString);
		templateURIBuilder.append("/API/");
		templateURIBuilder.append(SearchTypeAsString);
		templateURIBuilder.append("/");
		templateURIBuilder.append(APIKeyAsString);
		templateURIBuilder.append("/");
		templateURIBuilder.append(SearchExpressionAsString);
		String templateURI = templateURIBuilder.toString();
		return new IMDBDataGetter(templateURI, requiredValues, defaultValues);
	}

	//TODO: make it possible for the client or the server to change the language and (maybe) the API key. (now it is only possible for the client...)
	public static IMDBDataGetter getMovieListDataGetter() {
		Map<String, String> defaultValues = new HashMap<String, String>();
		defaultValues.put(SearchTypeAsString, "SearchMovie");
		return getSearchAPIDataGetter(defaultValues);
	}

}
