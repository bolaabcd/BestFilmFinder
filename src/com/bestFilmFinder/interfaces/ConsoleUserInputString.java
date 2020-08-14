package com.bestFilmFinder.interfaces;

import java.util.Scanner;

public class ConsoleUserInputString implements UserInputString {

	@Override
	public String newUserInput(String message) {
		System.out.println(message+"\n");
		try(Scanner scan= new Scanner(System.in)){
			return scan.nextLine();
		}
	}

}
