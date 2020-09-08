package com.bestFilmFinder.dataInterfaces;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Set;

import freemarker.core.ParseException;
import freemarker.template.Configuration;
import freemarker.template.MalformedTemplateNameException;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import freemarker.template.TemplateNotFoundException;

public class FreeMarkerDataCombiner<T> implements DataCombiner<File, Set<T>, InputStream> {
	protected static final Configuration freemarkerConfig=getDefaultFreemarkerConfig();
	
	private static final Configuration getDefaultFreemarkerConfig() {
		Configuration config= new Configuration(Configuration.VERSION_2_3_30);
		config.setDefaultEncoding("UTF-8");
		//TODO: final version should use RETHROW_HANDLER
		config.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		config.setLogTemplateExceptions(false);
		config.setWrapUncheckedExceptions(true);
		config.setFallbackOnNullLoopVariable(false);
		return config;
	}
	
	public static void setDirectoryForTemplateLoading(File directory) throws IOException {
		freemarkerConfig.setDirectoryForTemplateLoading(directory);
	}

	@Override
	public InputStream combineData(File firstElement, Set<T> secondElement) {
		String fileName=firstElement.getName();
		try {
			Template template=freemarkerConfig.getTemplate(fileName);
			Writer writer = new StringWriter();
			template.process(secondElement,writer);
			InputStream ans=new ByteArrayInputStream(writer.toString().getBytes(freemarkerConfig.getDefaultEncoding()));
			return ans;
		} catch (TemplateNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MalformedTemplateNameException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TemplateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}
