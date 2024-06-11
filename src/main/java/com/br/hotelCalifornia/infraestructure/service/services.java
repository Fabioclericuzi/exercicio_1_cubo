package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;

@Service
public class services {
	
	
	@Autowired
	private hotelCaliforniaRepository repository;
	
	@GetMapping
	public List<HotelCaliforniaModel> findAll(){
		return repository.findAll();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<HotelCaliforniaModel> find(@PathVariable UUID id){
		return repository.findById(id)
		.map(mapping -> ResponseEntity.ok().body(mapping))
		.orElse(ResponseEntity.notFound().build());
	}
	
	@PostMapping
	public HotelCaliforniaModel create(@RequestBody HotelCaliforniaModel hotelCalifornia){
		return repository.save(hotelCalifornia);
	}
	
	
}
