package com.bestFilmFinder.dataInterfaces;

public interface DataGetter<F,R> {
	public R getData(F filter);
}
