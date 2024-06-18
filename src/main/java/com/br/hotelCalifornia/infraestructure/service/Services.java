package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;

import lombok.RequiredArgsConstructor;


@Service
public class Services {
	
	private Services hotelServices;
	
	private hotelCaliforniaRepository repository;
	
	@Autowired
	public Services(hotelCaliforniaRepository repository) {
		this.repository = repository;
	}
	
	
	public List<HotelCaliforniaModel> findTodos(){
		return repository.findAll();
	}
	
	
	public Optional<HotelCaliforniaModel> find(UUID id){
		return repository.findById(id);
		
	}
	
	
	public  HotelCaliforniaModel create( HotelCaliforniaModel hotelCalifornia){
		return repository.save(hotelCalifornia);
	}
	 
	 public void delete(HotelCaliforniaModel hotelCalifornia){
			repository.delete(hotelCalifornia);
	 
	 }
}
