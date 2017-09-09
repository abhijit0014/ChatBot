package com.spring.services;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class BingSearch 
{
	private String userResponse;
	private String htmlData;
	private String systemResponse;
	
	public String response(String res)
	{
		this.userResponse = res;
		this.search();
		this.parseHtml();
		return this.systemResponse;
	}
	
	private void search()
	{
		OkHttpClient client = new OkHttpClient();
		  Request request = new Request.Builder()
				  .url("https://www.bing.com/search?q="+this.userResponse)
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
		Document doc = Jsoup.parse(this.htmlData);
		
		String title = doc.getElementsByClass("b_entityTitle").text();
		System.out.println("title =>"+title);
		String subtitle = doc.getElementsByClass("b_entitySubTitle").text();
		System.out.println("subtitle =>"+subtitle);
		String description = doc.getElementsByClass("b_lBottom b_snippet").text();
		System.out.println("description =>"+description);
		String defination = doc.getElementsByClass("rwrl rwrl_sec rwrl_padref").text(); 
		System.out.println("\ndefination =>"+defination);
		
		Elements elements = doc.getElementsByClass("b_snippet");
		for(Element str:elements){
			System.out.println(str.text());
		}
		//System.out.println(this.htmlData);
		
		
		
		//return data temp//-------------------
		if (title!=""&&title!=null){
			this.systemResponse = title;
		}
		else if (description!=""&&description!=null) {
			this.systemResponse = description;
		}
	}
}

