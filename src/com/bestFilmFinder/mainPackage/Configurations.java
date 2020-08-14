package com.bestFilmFinder.mainPackage;

import java.net.InetSocketAddress;
import java.util.Scanner;
import java.util.function.Predicate;
import java.util.function.Supplier;


public class Configurations {
	public static InetSocketAddress getAddress() {
		String address=userInput("Insert the server address.");
		int port=Integer.parseInt(
					retryUltilValid(
						(input)->{
							try{
								Integer.parseInt(input);
							}catch(NumberFormatException e){
								return false;
							}
							return true;
						},
						()->{
							return userInput("Insert the port to listen.");
						},
						()->{
							return userInput("Insert the port to listen. (Integer between 1 and 65000)");
						}
					)
				);
		return new InetSocketAddress(address, port);
	}
	private static String userInput(String message) {
		System.out.println(message+"\n");
		try(Scanner scan= new Scanner(System.in);){
			return scan.nextLine();
		}
	}
	private static <T> T retryUltilValid(Predicate<T> testValue,Supplier<T> getValue,Supplier<T> alternativeGetValue) {
		T answer;
		answer=getValue.get();
		while(testValue.test(answer)==false) {
			answer=alternativeGetValue.get();
		}
		return answer;
	}
}
