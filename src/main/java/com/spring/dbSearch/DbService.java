package com.spring.dbSearch;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class DbService 
{
	private final QueryRepository queryRepository;
	private final ResponseRepository responseRepository;
	
	public DbService(QueryRepository queryRepository, ResponseRepository responseRepository) {
		super();
		this.queryRepository = queryRepository;
		this.responseRepository = responseRepository;
	}
	
	// query
	public void saveQuery(QueryEntity queryEntity)
	{
		queryRepository.save(queryEntity);
	}
	public List<QueryEntity> findQuery(String query)
	{
		return queryRepository.findByQueryIgnoreCase(query);
	}
	public void deleteQuery(long id)
	{
		queryRepository.delete(id);
	}
	public List<QueryEntity> getTop20Query()
	{
		return queryRepository.findTop20ByOrderByIdDesc();
	}
	public QueryEntity getByQueryId(long queryid)
	{
		return queryRepository.findOne(queryid);
	}
	
	// response
	public void saveResponse(ResponseEntity responseEntity)
	{
		responseRepository.save(responseEntity);
	}
	
	public List<ResponseEntity> getALlResponsesByQueryId(long queryid)
	{
		return responseRepository.findByQueryid(queryid);
	}
	
	public void deleteResponse (long id)
	{
		responseRepository.delete(id);
	}
}
