package com.spring.websearch;

import java.io.IOException;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WikipediaSearch extends Thread
{
	public SearchData searchData;
	private String wikiURL = "https://en.wikipedia.org/w/api.php?format=json&action=query&prop=extracts&exintro=&explaintext=&titles=";
	private String userQuery;
	private String jsonData;
	private String description;

	public WikipediaSearch(SearchData searchData) {
		this.searchData = searchData;
	}
	public void run()
	{
		this.downloadHtml();
		this.parseJson();
	}	
	public void setUserQuery(String res)
	{
		this.userQuery = res;
	}
	
	private void downloadHtml()
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url(wikiURL+userQuery)
			      .build();
		try {
			Response response = client.newCall(request).execute();
			this.jsonData = response.body().string();	
		} catch (IOException e) {
			e.printStackTrace();
		}	
	}
	
	public void parseJson()
	{
		JsonParser parser = new JsonParser();
		JsonObject jsonObject = parser.parse(this.jsonData).getAsJsonObject();
        jsonObject  = jsonObject.getAsJsonObject("query").getAsJsonObject("pages");
        if (jsonObject.getAsJsonObject("25675557") != null){	
        	this.description = jsonObject.getAsJsonObject("25675557").get("extract").getAsString();
        	this.description =  this.description.replaceAll("^\"|\"$", "");
        	this.description =  this.description.replaceAll("\n","");
        }
        searchData.wikiDescription = this.description;
	}
}

