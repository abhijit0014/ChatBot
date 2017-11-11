package com.spring.aiAgent;

import java.util.List;
import java.util.Random;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.spring.dbSearch.DbService;
import com.spring.dbSearch.QueryEntity;
import com.spring.dbSearch.ResponseEntity;

@Component
public class DBAnsware 
{
	@Resource
	private DbService dbService;
	List<QueryEntity> queryList;
	List<ResponseEntity> responseList;
	
	public String getAnsware(String userQuery)
	{
		Long queryid = getQueryId(userQuery);
		if (queryid!=null) 
		{
			responseList = dbService.getALlResponsesByQueryId(queryid);
			if (responseList.size()>0) {
				Random r = new Random();
				int Result = r.nextInt(responseList.size());
				System.out.println(responseList.toString());
				return responseList.get(Result).getResponse();
			}
			else
				return "0 db ans found";			
		}
		return null;
	}
	
	private Long getQueryId(String userQuery)
	{
		queryList = null;
		queryList = dbService.findQuery(userQuery);
		if(queryList.size()>0)
			return queryList.get(0).getId();
		else
			return null;
	}
}
