package com.example.crud.utils;

public final class StringUtils {

	private StringUtils() {
		throw new IllegalStateException("Utility class");
	}
	
	public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
	}

}
