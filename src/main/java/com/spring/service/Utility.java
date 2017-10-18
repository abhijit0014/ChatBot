package com.spring.service;

public class Utility {

	public static int wordCount(String str)
	{
		String str1 = str.trim();
		return str1.isEmpty() ? 0 : str1.split("\\s+").length;		
	}
}
