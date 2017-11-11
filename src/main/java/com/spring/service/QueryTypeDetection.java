package com.spring.service;

import org.springframework.stereotype.Component;

@Component
public class QueryTypeDetection {
	private String queryType=null;
	private String responseType=null;
	private String responseSize=null;
	private String WHwords[][]={
			{"who",		"person",	"Defination"},
			{"where",	"place",	"short"},		
			{"why",		"reason",	"Description"},
			{"when",	"time",		"short"},		
			{"how",		"manner",	"Description"},
			{"what",	"object",	"Defination"}, 	
			{"which",	"choice",	"short"},
			{"whose",	"possession","short"},	
			{"what time",	"time",		"short"},		
			{"how many",	"quantity",	"short"},
			{"how much",	"amount",	"short"},		
			{"how long",	"length",	"short"},
			{"how often",	"frequency","short"},	
			{"how far",		"distance",	"short"},
			{"how old",		"age",		"short"},			
			{"how come",	"reason",	"Description"},
			{"what kind",	"description",	"Description"}
			};
	
	public void detect(String query)
	{
		queryType = null; responseSize = null; responseType=null;
		detectWhQuery(query);
	}
	
	private void detectWhQuery(String query)
	{
		query = query.toLowerCase();
		String words[]=null;
		words=query.split("\\s");
		if (words!=null && ( words[0].charAt(0)=='w' || words[0].charAt(0)=='h' || words[0].charAt(0)=='W' || words[0].charAt(0)=='H'))
		{
			for (int i = 0; i < WHwords.length; i++)
			{
				if(WHwords[i][0].equals(words[0]))
				{
					responseType = WHwords[i][1];
					responseSize = WHwords[i][2];
					queryType = "Interrogative";
					break;
				}
			}
		}		
	}

	public String getqType() {
		return queryType;
	}
	public String getrType() {
		return responseType;
	}
	public String getrSize() {
		return responseSize;
	}
}
