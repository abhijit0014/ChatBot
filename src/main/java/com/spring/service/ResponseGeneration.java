package com.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spring.websearch.Search;
import com.spring.websearch.SearchData;
@Component
public class ResponseGeneration 
{
	private boolean dataFlag = false;
	@Autowired
	QTypeDetection QTD;
	@Autowired 
	Search search;
	
	SearchData searchData;
	Response response;
	
	public void reply(Response response,String query)
	{
		dataFlag = false;
		searchData.reset();
		
		this.response = response;
		QTD.detect(query);
		searchData = search.result(query);
		
		System.out.println(QTD.getrSize());
		System.out.println(searchData.toString());
		
		if(QTD.getrSize()!=null)
		{
			switch(QTD.getrSize()){
				case "short": shortTypeAns(); break;
				case "Description": descriptionTypeAns(); break;
				case "Defination": definationTypeAns(); break;
			}
		}else{
			shortTypeAns();
			if(response.getResponse()==null && dataFlag==false){
				descriptionTypeAns();
			}
			if(response.getResponse()==null && dataFlag==false){
				definationTypeAns();
			}			
		}
	}
	private void shortTypeAns()
	{
		if (searchData.bingTitle!=null && !searchData.bingTitle.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.bingTitle);
			dataFlag=true;
		}
		if (searchData.googleAns!=null && !searchData.googleAns.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.googleAns);
			dataFlag=true;
		}
	}
	private void descriptionTypeAns()
	{		
		if (searchData.bingDescription!=null && !searchData.bingDescription.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.bingDescription);
			dataFlag=true;
		}	
		if (searchData.googleDescription!=null && !searchData.googleDescription.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.googleDescription);
			dataFlag=true;
		}
		if (searchData.wikiDescription!=null && !searchData.wikiDescription.isEmpty()&& dataFlag==false) {
			response.setResponse(searchData.wikiDescription);
			dataFlag=true;
		}
		if (searchData.beingDefination!=null && !searchData.beingDefination.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.beingDefination);
			dataFlag=true;
		}			
	}
	private void definationTypeAns()
	{			
		if (searchData.googleDescription!=null && !searchData.googleDescription.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.googleDescription);
			dataFlag=true;
		}
		if (searchData.googleDefination!=null && !searchData.googleDefination.isEmpty()  && dataFlag==false) {
			response.setResponse(searchData.googleDefination);
			dataFlag=true;
		}	
		if (searchData.beingDefination!=null && !searchData.beingDefination.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.beingDefination);
			dataFlag=true;
		}
		if (searchData.bingDescription!=null && !searchData.bingDescription.isEmpty() && dataFlag==false) {
			response.setResponse(searchData.bingDescription);
			dataFlag=true;
		}			
		if (searchData.wikiDescription!=null && dataFlag==false) {
			response.setResponse(searchData.wikiDescription);
			dataFlag=true;
		}		
	}
}
