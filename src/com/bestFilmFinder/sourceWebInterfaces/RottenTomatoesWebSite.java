package com.bestFilmFinder.sourceWebInterfaces;

import java.util.ArrayList;
import java.util.Map;

public class RottenTomatoesWebSite implements Website {
	private static final char websiteChar="R".charAt(0);
	private static final String websiteName="Rotten Tomatoes";
	
	@Override
	public ArrayList<Movie> search(Map<String, String> searchFilters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public char getWebsiteChar() {
		return websiteChar;
	}

	@Override
	public String getWebsiteName() {
		return websiteName;
	}

}
