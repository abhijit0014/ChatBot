package com.spring.dbSearch;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="query")
public class QueryEntity 
{	
	@Id
	@GeneratedValue
	private Long id;
	private String query;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getQuery() {
		return query;
	}
	public void setQuery(String query) {
		this.query = query;
	}
	@Override
	public String toString() {
		return "QueryEntity [id=" + id + ", query=" + query + "]";
	}
	
}
