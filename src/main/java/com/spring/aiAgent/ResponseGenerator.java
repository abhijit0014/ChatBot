package com.spring.aiAgent;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

@Component
public class ResponseGenerator 
{
	@Resource
	private WebAnsware webAnsware;
	private Response response;

	public String getResponse(String userQuery) {
		response = webAnsware.getAnsware(userQuery);
		return response.getResponse();
	}
}
