package com.bestFilmFinder.mainPackage;

import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleLogger {
	private final SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss -> ");
	private final PrintStream whereToLog;
	
	public SimpleLogger(PrintStream whereToLog) {
		this.whereToLog=whereToLog;
	}
	public void log(String message) {
		whereToLog.println(formatter.format(new Date())+message);
	}
}
