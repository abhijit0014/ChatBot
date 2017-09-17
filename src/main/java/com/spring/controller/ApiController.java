package com.spring.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.service.Response;
import com.spring.service.ResponseGeneration;
import com.spring.websearch.Search;

@RestController
@RequestMapping("/chatbot/api")
public class ApiController 
{
	@Resource
	private ResponseGeneration rs;
	@Resource
	private Response response;
	
	@PostMapping("/")
	@ResponseBody
	public String generateResponse(@RequestParam("userResponse") String userResponse)
	{
		rs.reply(response, userResponse);
		return response.getResponse();
	}
}
