package com.spring.dbSearch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="response")
public class ResponseEntity 
{	
	@Id
	@GeneratedValue
	private Long id;
	private Long queryid;
	private String response;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getQueryid() {
		return queryid;
	}
	public void setQueryid(Long queryid) {
		this.queryid = queryid;
	}
	public String getResponse() {
		return response;
	}
	public void setResponse(String response) {
		this.response = response;
	}
	@Override
	public String toString() {
		return "ResponseEntity [id=" + id + ", queryid=" + queryid + ", response=" + response + "]";
	}
	
}
