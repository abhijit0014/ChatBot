package com.spring.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.services.BingSearch;

@RestController
@RequestMapping("/chatbot/api")
public class ApiController 
{
	@Resource
	private BingSearch bingSearch;
	
	@PostMapping("/")
	@ResponseBody
	public String generateResponse(@RequestParam("userResponse") String userResponse)
	{
		return bingSearch.response(userResponse);
	}
}
