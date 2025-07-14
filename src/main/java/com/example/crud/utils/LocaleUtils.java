package com.example.crud.utils;

import java.util.Locale;

public class LocaleUtils {
	
	private LocaleUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static Locale getLocaleThTH() {
		return Locale.of("th", "TH");
	}
	
	public static Locale getLocale(final String text) {
		try {
			final String[] langs = text.split("_");
			return Locale.of(langs[0], langs[1]);
		} catch (final Exception e) {
			return Locale.of("th", "TH");
		}
	}

}
