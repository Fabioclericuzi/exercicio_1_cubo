package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Services {
	
	
	@Autowired
	private hotelCaliforniaRepository repository;
	

	public List<HotelCaliforniaModel> findTodos(){
		return repository.findAll();
	}
	
	public ResponseEntity<HotelCaliforniaModel> find( UUID id){
		return repository.findById(id)
		.map(mapping -> ResponseEntity.ok().body(mapping))
		.orElse(ResponseEntity.notFound().build());
	}
	
	
	public  HotelCaliforniaModel create( HotelCaliforniaModel hotelCalifornia){
		return repository.save(hotelCalifornia);
	}
	
	
}
