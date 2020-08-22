package com.bestFilmFinder.userInterfaces;

import java.util.Scanner;

public class ConsoleUserInterface implements UserInterface {
	public static final Scanner consoleUserInput=new Scanner(System.in);
	@Override
	public UserInputString newUserInputString() {
		return new ConsoleUserInputString();
	}
	@Override
	public FormTextInput newFormTextInput(FormTextField formTextField) {
		return new ConsoleFormTextInput(formTextField);
	}
}
