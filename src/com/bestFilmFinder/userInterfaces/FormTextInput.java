package com.bestFilmFinder.userInterfaces;

public abstract class FormTextInput {
	protected final FormTextField formTextField;
	public FormTextInput(FormTextField formTextField) {
		this.formTextField=formTextField;
	}
	public abstract String getUserInput();
	public abstract String getUserInput(String strFormatterArgument);
}
