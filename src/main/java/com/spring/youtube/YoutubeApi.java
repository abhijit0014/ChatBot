package com.spring.youtube;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class YoutubeApi 
{
	private String url;
	private String jsonData;
	private int maxVideos = 0;
	private int senderCounter = 0;
	private String videoIds[];
	
	public String getVideoId(String query)
	{
		senderCounter = 0;
		videoIds = new String[15];
		 
		generateUrl(query);
		fetchData();
		jsonParser();
		return next();
	}
	
	public String next()
	{
		if(senderCounter<maxVideos)
			return videoIds[senderCounter++]+"&&youtube";
		else
			return videoIds[senderCounter--]+"&&youtube";
	}
	public String prev()
	{
		if(maxVideos>0&&senderCounter>0)
			return videoIds[senderCounter--]+"&&youtube";
		else
			return videoIds[senderCounter++]+"&&youtube";
	}	
	private String fetchData()
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
		      .url(url)
		      .build();
		Response response;
		try {
			response = client.newCall(request).execute();
			jsonData =  response.body().string();			
		} catch (IOException e) {
			// TODO: handle exception
		}
		return jsonData;
	}
	
	private String jsonParser()
	{
		String str;
		int counter =0;
		JSONParser parser = new JSONParser();
		try {
	        Object obj = parser.parse(jsonData);
	        JSONObject jsonObject = (JSONObject) obj;
	        JSONArray jsonArray = (JSONArray) jsonObject.get("items");
	        
	        for (int i = 0; i < jsonArray.size(); i++) 
	        {
	        	jsonObject = (JSONObject) jsonArray.get(i);
		        jsonObject = (JSONObject) jsonObject.get("id");
		        str = (String) jsonObject.get("videoId");
		        if(str!=null) 
		        	videoIds[counter++] = str;
			}
	        maxVideos = videoIds.length;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private void generateUrl(String query)
	{
		this.url="https://www.googleapis.com/youtube/v3/search?part=id&q="+query+"&maxResults=15&order=viewCount&user=TEDtalksDirector&key=AIzaSyD1OITejTEgXIseYubqLOvjBfo7WiZEzds";		
	}
}

