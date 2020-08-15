package com.bestFilmFinder.utils;

import java.util.function.Function;
import java.util.function.Predicate;

public class FunctionUtils {
	public static <T,A> T retryUltilValid(Predicate<T> testValue,Function<A,T> getValue,Function<A,T> alternativeGetValue, A argument) {
		T answer;
		answer=getValue.apply(argument);
		while(testValue.test(answer)==false) {
			answer=alternativeGetValue.apply(argument);
		}
		return answer;
	}
	
}
