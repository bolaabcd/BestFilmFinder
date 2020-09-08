package com.bestFilmFinder.dataInterfaces;

public interface DataCombiner <T,U,R> {
	public R combineData(T firstElement,U secondElement);
}
