package com.spring.service;

import static org.hamcrest.CoreMatchers.nullValue;

import org.mockito.internal.util.collections.ArrayUtils;
import org.springframework.stereotype.Component;

@Component
public class QTypeDetection {
	private String qType=null;
	private String rType=null;
	private String rSize=null;
	private boolean detectionFlag = false;
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
		qType = null; rSize = null; qType=null;
		detectWhQuery(query);
	}
	
	private void detectWhQuery(String query)
	{
		String words[]=null;
		words=query.split("\\s");
		if (words!=null && (words[0].charAt(0)=='w' || words[0].charAt(0)=='h'))
		{
			for (int i = 0; i < WHwords.length; i++)
			{
				if(WHwords[i][0].equals(words[0]))
				{
					rType = WHwords[i][1];
					rSize = WHwords[i][2];
					qType = "Interrogative";
					detectionFlag = true;
					break;
				}
			}
		}		
	}

	public String getqType() {
		return qType;
	}
	public String getrType() {
		return rType;
	}
	public String getrSize() {
		return rSize;
	}
}
