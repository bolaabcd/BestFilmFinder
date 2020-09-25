module bestFilmFinder {
	requires jdk.httpserver;
	requires jsonJava;
	requires freemarker;
	requires java.net.http;
	exports com.bestFilmFinder.sourceWebInterfaces;
}