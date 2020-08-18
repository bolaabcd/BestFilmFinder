package com.bestFilmFinder.sourceWebInterfaces;

import java.util.ArrayList;
import java.util.Map;

public interface Website {
	public ArrayList<Movie> search(Map<String,String> searchFilters);
	public char getWebsiteChar();
	public String getWebsiteName();
}
