package com.bestFilmFinder.interfaces;

public class ConsoleUserInputString implements UserInputString {

	@Override
	public String getUserInput(String message) {
		System.out.println(message);
		return ConsoleUserInterface.consoleUserInput.nextLine();
		
	}

}
