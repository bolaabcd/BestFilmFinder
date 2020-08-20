package com.bestFilmFinder.sourceWebInterfaces;

import java.net.URL;
import java.util.Map;
import java.util.Set;

public class Movie {
	private String name;
	private URL imageURL;
	private String description;
	private Map<String,Integer> scores;
	private URL webpageURL;
	private Website websiteUsed;
	private Set<String> genres;
}
