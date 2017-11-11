package com.spring.aiAgent;

import org.springframework.stereotype.Component;

import com.spring.service.FormatString;
import com.spring.service.QueryTypeDetection;
import com.spring.service.Utility;
import com.spring.websearch.SearchData;
import com.spring.websearch.WebSearch;

@Component
public class WebAnsware 
{
	private boolean dataFlag = false;
	private String response;
	private QueryTypeDetection queryTypeDetection;
	private WebSearch webSearch;
	private FormatString fS;
	private SearchData searchData;
	
	public WebAnsware(QueryTypeDetection queryTypeDetection, WebSearch webSearch, FormatString fS,
			SearchData searchData) {
		super();
		this.queryTypeDetection = queryTypeDetection;
		this.webSearch = webSearch;
		this.fS = fS;
		this.searchData = searchData;
	}

	public String getAnsware(String query)
	{
		response = null;
		dataFlag = false;
		queryTypeDetection.detect(query);
		searchData = webSearch.result(query);		
		if(queryTypeDetection.getrSize()!=null)
		{
			switch(queryTypeDetection.getrSize()){
				case "short": shortTypeAns(); break;
				case "Description": descriptionTypeAns(); break;
				case "Defination": definationTypeAns(); break;
			}
			if(response==null)definationTypeAns();
			if(response==null)descriptionTypeAns();
		}else{
			shortTypeAns();
			if(dataFlag==false) descriptionTypeAns();
			if(dataFlag==false) definationTypeAns();
		}
		//format string
		response = fS.format(response);
		return response;
	}
	
	private void shortTypeAns()
	{
		if (searchData.bingTitle!=null && !searchData.bingTitle.isEmpty() && dataFlag==false) 
		{
			if(Utility.wordCount(searchData.bingTitle)>1) {
				response=searchData.bingTitle;
				dataFlag=true;
			}
		}
		if (searchData.googleAns!=null && !searchData.googleAns.isEmpty() && dataFlag==false) {
			response=searchData.googleAns;
			dataFlag=true;
		}
	}
	private void descriptionTypeAns()
	{
		if (searchData.googleDescription!=null && !searchData.googleDescription.isEmpty() && dataFlag==false) {
			response=searchData.googleDescription;
			dataFlag=true;
		}
		if (searchData.bingDescription!=null && !searchData.bingDescription.isEmpty() && dataFlag==false) {
			response=searchData.bingDescription;
			dataFlag=true;
		}		
		if (searchData.wikiDescription!=null && !searchData.wikiDescription.isEmpty() && dataFlag==false) {
			response=searchData.wikiDescription;
			dataFlag=true;
		}
		if (searchData.beingDefination!=null && !searchData.beingDefination.isEmpty() && dataFlag==false) {
			response=searchData.beingDefination;
			dataFlag=true;
		}			
	}
	private void definationTypeAns()
	{
		if (searchData.beingDefination!=null && !searchData.beingDefination.isEmpty() && dataFlag==false) {
			response=searchData.beingDefination;
			dataFlag=true;
		}		
		if (searchData.googleDescription!=null && !searchData.googleDescription.isEmpty() && dataFlag==false) {
			response=searchData.googleDescription;
			dataFlag=true;
		}
		if (searchData.googleDefination!=null && !searchData.googleDefination.isEmpty() && dataFlag==false) {
			response=searchData.googleDefination;
			dataFlag=true;
		}	
		if (searchData.bingDescription!=null && !searchData.bingDescription.isEmpty() && dataFlag==false) {
			response=searchData.bingDescription;
			dataFlag=true;
		}			
		if (searchData.wikiDescription!=null && !searchData.wikiDescription.isEmpty() && dataFlag==false) {
			response=searchData.wikiDescription;
			dataFlag=true;
		}		
	}

}
