package com.spring.websearch;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GoogleSearch extends Thread
{
	private String googleURL = "https://www.google.com/search?q=";
	private String userQuery;
	private String htmlData;
	SearchData searchData;
	
	public GoogleSearch(SearchData searchData) {
		this.searchData = searchData;
	}
	public void run()
	{
		this.downloadHtml();
		this.parseHtml();
	}	
	public void setUserQuery(String res)
	{
		this.userQuery = res;
	}
	private void downloadHtml()
	{
		OkHttpClient client = new OkHttpClient();
		  Request request = new Request.Builder()
			      .url(googleURL+userQuery )
			      .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			this.htmlData = response.body().string();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	public void parseHtml()
	{
		String googleText=null;
		Document doc = Jsoup.parse(this.htmlData);
		
		Elements elements = doc.getElementsByClass("st");
		for(Element str:elements){
			googleText = googleText+" "+str.text();
		}
		searchData.setText(googleText);
		
		searchData.googleDefination= doc.getElementsByClass("_sPg").text();	
		searchData.googleDescription = doc.getElementsByClass("_tXc").text();
		searchData.googleAns= doc.getElementsByClass("_XWk").text();		
		
	}
}
