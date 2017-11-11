package com.spring.aiAgent;
import org.springframework.stereotype.Component;

import com.spring.service.Utility;
import com.spring.youtube.YoutubeApi;

@Component
public class ResponseGenerator 
{
	private int queryCount = 0;
	private int slangCounter = 0;
	private String lastResponseSource;
	private WebAnsware webAnsware;
	private DBAnsware dbAnsware;
	private YoutubeApi youtubeApi;
	private String response;

	public ResponseGenerator(WebAnsware webAnsware, DBAnsware dbAnsware,YoutubeApi youtubeApi) 
	{
		this.webAnsware = webAnsware;
		this.dbAnsware = dbAnsware;
		this.youtubeApi = youtubeApi;
	}
	
	public String getResponse(String userQuery) 
	{
		userQuery = userQuery.trim();
		response=null;
		
		// query counter
		queryCounter(true);
		// "next" option
		if(isNext(userQuery)) {
			if(lastResponseSource=="video")
			return youtubeApi.next();
		}
		//previous option
		if(isPrevious(userQuery)) {
			if(lastResponseSource=="video")
			return youtubeApi.prev();
		}
		// slang counter
		if(isThereSlang(userQuery)) {
			slangCounter(true);
			if (slangCounter==1) return "I don't like slang";
			if (slangCounter==2) return "I have said you that I don't like slang";
			if (slangCounter>3) {
				Utility.sleepComputer(); 
				return "OK";
			}
		}
		// joke detect
		if (isJoke(userQuery)) userQuery = "joke";
		//generate response
		if (isVideoQuery(userQuery)) {
			lastResponseSource = "video";
			return youtubeApi.getVideoId(userQuery);
		}else {
			lastResponseSource = "text";
			response = dbAnsware.getAnsware(userQuery);
			if (response==null) {
				response = webAnsware.getAnsware(userQuery);
			}
			if (response==null) {
				response = "Sorry, I don’t understand";
			}
			return response;
		}
	}
	
	private boolean isVideoQuery(String srt)
	{
		boolean video = false;
		if (srt.indexOf("video")>=0)video=true;
		else if (srt.indexOf("tutorial")>=0)video=true;
		else if (srt.indexOf("watch")>=0)video=true;
		else if (srt.indexOf("play")>=0 && srt.indexOf("song")>=0)video=true;
		else if (srt.indexOf("play")>=0 && srt.indexOf("music")>=0)video=true;
		else if (srt.indexOf("full")>=0 && srt.indexOf("movie")>=0)video=true;
		return video;
	}
	
	private boolean isThereSlang(String str)
	{
		boolean slang = false;
		if (str.indexOf("fuck")>=0)slang=true;
		return slang;
	}
	private void queryCounter(boolean counter) {
		if(counter==true)
		this.queryCount++;
	}
	private void slangCounter(boolean counter) {
		if(counter==true)
		this.slangCounter++;
	}
	private boolean isJoke(String str)
	{
		boolean joke = false;
		if (str.indexOf("joke")>=0)joke=true;
		return joke;		
	}
	private boolean isNext(String str)
	{
		if (str.indexOf("next")==0 && str.indexOf(" ")<0)
			return true;
		else
			return false;
	}
	private boolean isPrevious(String str)
	{
		if (str.indexOf("previous")==0 && str.indexOf(" ")<0)
			return true;
		else
			return false;
	}	
}
