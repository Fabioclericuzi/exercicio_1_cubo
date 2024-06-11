package com.br.hotelCalifornia.api.controller;


import java.util.List;
import java.util.UUID;

import javax.print.PrintException;

import org.apache.catalina.connector.Response;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.br.hotelCalifornia.HotelCaliforniaApplication;
import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;
import com.br.hotelCalifornia.infraestructure.service.services;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({"/api/hotel"})
@RequiredArgsConstructor
public class HotelCaliforniaController  {
	
	private services hotelServices;

	@Autowired
    public HotelCaliforniaController(services hotelServices) {
		this.hotelServices = hotelServices;
    }
	
	@Autowired
	private hotelCaliforniaRepository repository;
	
	 @Autowired
	    public HotelCaliforniaController(hotelCaliforniaRepository repository) {
	        this.repository = repository;
	    }
	
	
	@GetMapping
	public List<?> findAll(){
		return hotelServices.findAll();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<HotelCaliforniaModel> find(@PathVariable UUID id){
		return hotelServices.find(id);
	}
	
	@PostMapping
	public HotelCaliforniaModel createhotelCalifornia(@RequestBody HotelCaliforniaModel hotelCalifornia){
		return  hotelServices.create(hotelCalifornia);
	}
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id){
		return repository.findById(id)
		.map(mapping -> {
    			repository.deleteById(id);
   
                 return ResponseEntity.ok().body("Deletado com sucesso");
		         }).orElse(ResponseEntity.notFound().build());	
		
	}	
	

	
	@PutMapping(value = "/{id}")
	 public ResponseEntity<HotelCaliforniaModel> updatehotelCalifornia(@PathVariable UUID id, @RequestBody HotelCaliforniaModel hotelCalifornia){
		
		return repository.findById(id)
		        .map(hotel -> {
		        	hotel.setNome(hotelCalifornia.getNome());
		        	hotel.setLocalizacao(hotelCalifornia.getLocalizacao());
		        	hotel.setCnpj(hotelCalifornia.getCnpj());
		            
		            HotelCaliforniaModel updatedHotelCalifornia = repository.save(hotel);
	                return ResponseEntity.ok().body(updatedHotelCalifornia);
	
	}).orElse(ResponseEntity.notFound().build());
}
}