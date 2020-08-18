package com.bestFilmFinder.sourceWebInterfaces;

import java.io.InputStream;
import java.util.Map;

public class Movie {
	private String name;
	private InputStream image;
	private String description;
	private Map<String,Integer> scores;
	private String webpageURL;
	private Website websiteUsed;
}
