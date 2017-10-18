package com.spring.websearch;

import org.springframework.stereotype.Component;

@Component
public class SearchData {
	private String text="";
	public String googleTitle=null;
	public String bingTitle=null;
	public String bingDescription=null;
	public String beingDefination=null;
	public String beingSubtitle=null;
	public String wikiDescription=null;
	public String googleAns=null;
	public String googleDefination=null;
	public String googleDescription=null;
	
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = this.text+" "+text;
	}	
	@Override
	public String toString() {
		return "SearchData-------------\ngoogleAns="+googleAns+"\ngoogleTitle=" + googleTitle + "\nbingTitle=" + bingTitle
				+ "\nbingDescription=" + bingDescription + "\nbeingDefination=" + beingDefination + "\nbeingSubtitle="
				+ beingSubtitle + "\nwikiDescription=" + wikiDescription + "\ngoogleDefination=" + googleDefination
				+ "\ngoogleDescription=" + googleDescription + "\ntext=" + text + "]";
	}
	public void reset()
	{
		text="";
		googleTitle=null;
		bingTitle=null;
		bingDescription=null;
		beingDefination=null;
		beingSubtitle=null;
		wikiDescription=null;
		googleAns=null;
		googleDefination=null;
		googleDescription=null;		
	}
}
