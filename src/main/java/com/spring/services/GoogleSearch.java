package com.spring.services;

import java.io.IOException;

import org.springframework.stereotype.Component;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

@Component
public class GoogleSearch 
{
	// user query variable to store
	private String queryData;
	private String responseData;
	
	// return reply for user query
	public String response(String query)
	{
		this.queryData = query;
		this.collectData();
		return responseData;
		
	}
	
	private void collectData()
	{
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
			      .url("https://www.google.com/search?q=" + this.queryData)
			      .build();

		Response response;
		try {
			response = client.newCall(request).execute();
			this.responseData = response.body().string();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
