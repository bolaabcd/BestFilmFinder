package com.bestFilmFinder.userInterfaces;

import java.util.function.Predicate;

public class FormTextField {
	private final String message;
	private final String messageIfInvalid;
	private final Predicate<String> validator;

	public FormTextField(String message, String messageIfInvalid, Predicate<String> validator) {
		this.message = message;
		this.messageIfInvalid = messageIfInvalid;
		this.validator = validator;
	}

	public String getMessage() {
		return message;
	}

	public String getMessageIfInvalid() {
		return messageIfInvalid;
	}

	public Predicate<String> getValidator() {
		return validator;
	}
	
}
