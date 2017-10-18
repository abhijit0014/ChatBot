package com.spring.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.aiAgent.ResponseGenerator;
import com.spring.aiAgent.Response;

@RestController
@RequestMapping("/chatbot/api")
public class ApiController 
{
	private ResponseGenerator responseGenerator;

	public ApiController(ResponseGenerator responseGeneration, Response response) {
		super();
		this.responseGenerator = responseGeneration;
	}

	@PostMapping("/")
	public String generateResponse(@RequestParam("userResponse") String userResponse)
	{
		return responseGenerator.getResponse(userResponse);
	}
}
