package com.bestFilmFinder.sourceWebInterfaces;

import java.net.URL;
import java.util.Map;
import java.util.Set;

public class Movie {
	private String name;
	private URL imageURL;
	private String description;
	private Map<String, Integer> scores;
	private URL webpageURL;
	private Set<String> genres;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public URL getImageURL() {
		return imageURL;
	}

	public void setImageURL(URL imageURL) {
		this.imageURL = imageURL;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Map<String, Integer> getScores() {
		return scores;
	}

	public void setScores(Map<String, Integer> scores) {
		this.scores = scores;
	}

	public URL getWebpageURL() {
		return webpageURL;
	}

	public void setWebpageURL(URL webpageURL) {
		this.webpageURL = webpageURL;
	}

	public Set<String> getGenres() {
		return genres;
	}

	public void setGenres(Set<String> genres) {
		this.genres = genres;
	}

}
