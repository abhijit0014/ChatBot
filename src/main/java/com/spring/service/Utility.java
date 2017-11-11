package com.spring.service;

import java.io.IOException;

public class Utility {

	public static int wordCount(String str)
	{
		String str1 = str.trim();
		return str1.isEmpty() ? 0 : str1.split("\\s+").length;		
	}
	public static void sleepComputer()
	{
		try {
			Runtime.getRuntime().exec("Rundll32.exe powrprof.dll,SetSuspendState Sleep");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
