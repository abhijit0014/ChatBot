package com.spring.websearch;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class BingSearch extends Thread
{
	private String bingURL = "https://www.bing.com/search?q=";
	private String userQuery;
	private String htmlData;
	SearchData searchData;	
	
	public BingSearch(SearchData searchData) {
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
				  .url(bingURL+this.userQuery)
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
		String bingText=null;
		Document doc = Jsoup.parse(this.htmlData);		
		searchData.bingTitle = doc.getElementsByClass("b_entityTitle").text();
		//String subtitle = doc.getElementsByClass("b_entitySubTitle").text();		
		searchData.bingDescription = doc.getElementsByClass("b_lBottom b_snippet").text();		
		searchData.beingDefination = doc.getElementsByClass("rwrl_padref").text(); 
		searchData.beingDefination = doc.getElementsByClass("dc_mn").text(); 
		
		Elements elements = doc.getElementsByClass("b_snippet");
		for(Element str:elements){
			bingText = bingText+" "+str.text();
		}
		searchData.setText(bingText);
	}
}
