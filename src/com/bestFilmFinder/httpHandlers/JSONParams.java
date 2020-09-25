package com.bestFilmFinder.httpHandlers;

public final class JSONParams {
	//Server-Client communication JSON
	public static final String URIPath="URIPath";
	public static final String filePath="FilePath";
	
	//Configuration File JSON
	public static final String serverAddress="serverAddress";
	public static final String serverPort="serverPort";
	public static final String threadPoolExecutorSize="threadPoolExecutorSize";
	public static final String imagesDirectory="imagesDirectory";
	public static final String CSSDirectory="CSSDirectory";
	public static final String JSDirectory="JSDirectory";
	public static final String HTMLTemplateDirectory="HTMLTemplateDirectory";
	public static final String APIKeys="APIKeys";
		//Name of the APIs in the APIKeys map
		public static final String IMDB_API="IMDB";
	
	//Server-WebAPI JSON
	public static final String URIFilter="URIFilter";
	public static final String bodyFilter="bodyFilter";
	public static final String headerFilter="headerFilter";
	
}
