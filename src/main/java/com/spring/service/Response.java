package com.spring.service;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private String response;
	private String youtube;
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	public String getYoutube() {
		return youtube;
	}
	public void setYoutube(String youtube) {
		this.youtube = youtube;
	}
}
