package com.bestFilmFinder.dataInterfaces;

public class ValidDataGetter<T,F> {
	private DataValidator<T> dataValidator;
	private DataGetter<F,T> dataGetter;
	
	public ValidDataGetter(DataValidator<T> dataValidator,DataGetter<F,T> dataGetter) {
		this.dataGetter=dataGetter;
		this.dataValidator=dataValidator;
	}
	public T getValidData(F filter,int howManyTimesShouldTry,long forHowLongShouldKeepTryingMilisseconds) {
		long start=System.currentTimeMillis();
		T answer;
		for(int i=0;i<howManyTimesShouldTry;i++) 
			if(System.currentTimeMillis()-start>forHowLongShouldKeepTryingMilisseconds) 
				return null;
			else if(dataValidator.validateData(answer=dataGetter.getData(filter)))
				return answer;
		
		return null;//TODO:Change this to raise exception if could not get valid data.
	}
	public T getValidData(F filter,long forHowLongShouldKeepTryingMilisseconds) {
		return this.getValidData(filter, Integer.MAX_VALUE, forHowLongShouldKeepTryingMilisseconds);
	}
	public T getValidData(F filter,int howManyTimesShouldTry) {
		return this.getValidData(filter, howManyTimesShouldTry, Long.MAX_VALUE);
	}
}
