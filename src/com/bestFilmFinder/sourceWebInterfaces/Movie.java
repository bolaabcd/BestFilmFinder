package com.bestFilmFinder.sourceWebInterfaces;

import java.net.URL;
import java.util.List;
import java.util.Map;

public class Movie {
	private String name;
	private String shortDescription;
	private String longDescription;
	private Map<String, Integer> scores;
	private List<String> genres;
	private URL imageURL;
	private List<String> whereToWatch;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShortDescription() {
		return shortDescription;
	}
	public void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}
	public String getLongDescription() {
		return longDescription;
	}
	public void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}
	public Map<String, Integer> getScores() {
		return scores;
	}
	public void setScores(Map<String, Integer> scores) {
		this.scores = scores;
	}
	public List<String> getGenres() {
		return genres;
	}
	public void setGenres(List<String> genres) {
		this.genres = genres;
	}
	public URL getImageURL() {
		return imageURL;
	}
	public void setImageURL(URL imageURL) {
		this.imageURL = imageURL;
	}
	public List<String> getWhereToWatch() {
		return whereToWatch;
	}
	public void setWhereToWatch(List<String> whereToWatch) {
		this.whereToWatch = whereToWatch;
	}
	
}
