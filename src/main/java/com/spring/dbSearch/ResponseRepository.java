package com.spring.dbSearch;

import org.springframework.data.jpa.repository.JpaRepository;
import java.lang.Long;
import com.spring.dbSearch.ResponseEntity;
import java.util.List;

public interface ResponseRepository extends JpaRepository<ResponseEntity, Long> {
	List<ResponseEntity> findByQueryid(Long queryid);
}
