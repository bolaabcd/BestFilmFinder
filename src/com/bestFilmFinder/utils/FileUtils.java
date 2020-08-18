package com.bestFilmFinder.utils;

import java.io.File;

public class FileUtils {
	public static boolean checkIfDirectoryExists(String path) {
		File file=new File(path);
		return file.exists()&&file.isDirectory();
	}
}
