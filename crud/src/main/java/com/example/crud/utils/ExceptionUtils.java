package com.example.crud.utils;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;

public class ExceptionUtils {
	
	private ExceptionUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static String exceptionToString(final Exception ex) {
		final Writer writer = new StringWriter();
		final PrintWriter printWriter = new PrintWriter(writer);
		ex.printStackTrace(printWriter);
		return writer.toString();
	}

}
