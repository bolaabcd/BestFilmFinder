package com.bestFilmFinder.interfaces;

public class ConsoleUserInterface implements UserInterface {

	@Override
	public UserInputString newUserInputString() {
		return new ConsoleUserInputString();
	}

}
