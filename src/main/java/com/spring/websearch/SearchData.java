package com.spring.websearch;

public class SearchData {
	private String text="";
	public String googleTitle;
	public String bingTitle;
	public String bingDescription;
	public String beingDefination;
	public String beingSubtitle;
	public String wikiDescription;
	public String googleAns;
	public String googleDefination;
	public String googleDescription;
	
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
