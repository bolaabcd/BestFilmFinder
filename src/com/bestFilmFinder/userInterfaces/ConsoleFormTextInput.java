package com.bestFilmFinder.userInterfaces;

import com.bestFilmFinder.utils.FunctionUtils;

public class ConsoleFormTextInput extends FormTextInput {
	private final ConsoleUserInputString userInputString=new ConsoleUserInputString();
	public ConsoleFormTextInput(FormTextField formTextField) {
		super(formTextField);
		
	}

	@Override
	public String getUserInput() {
		return FunctionUtils.retryUltilValid(
				super.formTextField.getValidator(), 
				(userInputString)->userInputString.getUserInput(
						super.formTextField.getMessage()
						), 
				(userInputString)->userInputString.getUserInput(
						super.formTextField.getMessageIfInvalid()
						), 
				userInputString);
	}

	@Override
	public String getUserInput(String strFormatterArgument) {
		return FunctionUtils.retryUltilValid(
				super.formTextField.getValidator(), 
				(userInputString)->userInputString.getUserInput(
						super.formTextField.getMessage(strFormatterArgument)
						), 
				(userInputString)->userInputString.getUserInput(
						super.formTextField.getMessageIfInvalid(strFormatterArgument)
						), 
				userInputString);
	}

}
