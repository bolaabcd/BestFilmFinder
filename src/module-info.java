module bestFilmFinder {
	requires jdk.httpserver;
	requires json.java;
	requires freemarker;
	requires java.net.http;
	exports com.bestFilmFinder.sourceWebInterfaces;
}