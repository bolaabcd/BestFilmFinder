package com.bestFilmFinder.userInterfaces;

class ConsoleUserInputString implements UserInputString {

	@Override
	public String getUserInput(String message) {
		System.out.println(message);
		return ConsoleUserInterface.consoleUserInput.nextLine();
		
	}

}
