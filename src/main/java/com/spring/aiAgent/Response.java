package com.spring.aiAgent;

import org.springframework.stereotype.Component;

@Component
public class Response {
	private String response = null;
	private String youtube = null;
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
	public void reset() {
		this.response = null;
		this.youtube = null;
	}
}
