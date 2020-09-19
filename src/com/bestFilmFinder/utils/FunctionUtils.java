package com.bestFilmFinder.utils;

import java.util.HashMap;
import java.util.Map;
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
	public static boolean checkIsValidInt(String str,int min,int max) {
		try{
			int val=Integer.parseInt(str);
			if(val>max||val<min)return false;
		}catch(NumberFormatException e){
			return false;
		}
		return true;
	}
	//TODO: make custom exceptions
	public static <K,V> Map<K,V> combinedMap(K[] required,Map<K,V> defaults,Map<K,V> values){
		Map<K,V> ans=new HashMap<K,V>();
		if(defaults!=null)
			ans.putAll(defaults);
		if(values!=null)
			ans.putAll(values);
		if(required!=null)
			for(K key:required)
				if(!ans.containsKey(key))
					throw new IllegalArgumentException("The given values don't have all the required parameters.");
		return ans;
	}
	
}
