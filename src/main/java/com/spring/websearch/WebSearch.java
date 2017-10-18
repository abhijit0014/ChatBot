package com.spring.websearch;

import org.springframework.stereotype.Component;

@Component
public class WebSearch {

	private String userQuery;
	private SearchData searchData = new SearchData();

	public SearchData result(String userQuery) {
		this.userQuery = userQuery;
		this.searchData.reset();
		this.webSpiders();
		System.out.println(searchData.toString());
		return searchData;
	}
	
	private void webSpiders()
	{	
    	// create web objects
    	GoogleSearch googleSearch = new GoogleSearch(searchData);
    	WikipediaSearch wikipediaSearch = new WikipediaSearch(searchData);
    	BingSearch bingSearch = new BingSearch(searchData);

    	//set query
    	googleSearch.setUserQuery(userQuery);    	
    	bingSearch.setUserQuery(userQuery);
    	wikipediaSearch.setUserQuery(userQuery);
    	
    	//start threads
    	bingSearch.start();
    	wikipediaSearch.start();    
    	googleSearch.start(); 
    	
    	// join threads
    	try {
			googleSearch.join();
			bingSearch.join();
			wikipediaSearch.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}	
	}
}
