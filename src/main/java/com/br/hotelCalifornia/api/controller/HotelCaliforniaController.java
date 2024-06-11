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
	
	@DeleteMapping("/{id}")
    public ResponseEntity<?> deletehotelCalifornia(@PathVariable UUID id){
				
			ResponseEntity<?> response = hotelServices.delete(id);
		
			if(response.getStatusCode() == HttpStatus.OK){
				return ResponseEntity.ok("Hotel apagado com sucesso");
			}else{
				return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
			}
		
	}	
	
	@PutMapping(value = "/{id}")
	 public ResponseEntity<?> updatehotelCalifornia(@PathVariable UUID id, HotelCaliforniaModel hotelCalifornia){
		
		ResponseEntity<?> response = hotelServices.update(id, hotelCalifornia);
	
		if(response.getStatusCode() == HttpStatus.OK){
			return ResponseEntity.ok("Hotel atualizado com sucesso");
		}else{
			return ResponseEntity.status(response.getStatusCode()).body(response.getBody());
		}
	
}
	
}
