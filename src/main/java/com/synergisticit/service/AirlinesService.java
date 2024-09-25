package com.synergisticit.service;

import java.util.List;

import com.synergisticit.domain.Airlines;

public interface AirlinesService {
	 	Airlines save(Airlines airlines);
	    Airlines getById(Long id);
	    List<Airlines> getAll();
	    void deleteById(Long id);
	    boolean existById(Long id);
}
