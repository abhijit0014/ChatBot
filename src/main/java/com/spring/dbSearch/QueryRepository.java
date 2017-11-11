package com.spring.dbSearch;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface QueryRepository extends JpaRepository<QueryEntity, Long> {
	List<QueryEntity> findByQueryIgnoreCase(String query);
	List<QueryEntity> findTop20ByOrderByIdDesc();
}
