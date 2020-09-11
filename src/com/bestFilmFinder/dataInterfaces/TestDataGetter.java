package com.bestFilmFinder.dataInterfaces;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.bestFilmFinder.sourceWebInterfaces.Movie;

public class TestDataGetter implements DataGetter<JSONObject, List<Movie>>{

	@Override
	public List<Movie> getData(JSONObject filter) {
		List<Movie> ans=new ArrayList<Movie>();
		Movie first=new Movie();
		first.setDescription("Just a test movie!");
		first.setName("Test Movie");
		ans.add(first);
		return ans;
	}





}
