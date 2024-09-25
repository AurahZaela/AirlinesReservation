package com.synergisticit.service;

import java.util.List;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synergisticit.domain.Airlines;
import com.synergisticit.repository.AirlinesRepository;

@Service
public class AirlinesServiceImpl implements AirlinesService {

	@Autowired
	AirlinesRepository airlinesRepository;
	
	
	@Override
	public Airlines save(Airlines airlines) {
		return airlinesRepository.save(airlines);
	}

	@Override
	public Airlines getById(Long id) {
		Optional<Airlines> optAirline = airlinesRepository.findById(id);
		
		if(optAirline.isPresent())
			return optAirline.get();
		else 
			return null;
	}

	@Override
	public List<Airlines> getAll() {
			return airlinesRepository.findAll();
	}

	@Override
	public void deleteById(Long id) {
		airlinesRepository.deleteById(id);
	}

	@Override
	public boolean existById(Long id) {
		return airlinesRepository.existsById(id);
	}

}
