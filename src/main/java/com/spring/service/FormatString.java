package com.spring.service;

import org.springframework.stereotype.Component;

@Component
public class FormatString 
{
	private String str;
	private String words[]= {". Wikipedia",
							"· Text under CC-BY-SA license"};
	public String format(String str)
	{
		this.str = str;
		removeWords();
		return this.str;
	}
	private void removeWords()
	{
		for (int i = 0; i < words.length; i++) {
			str =str.replace(words[i], "");
		}
	}
}
