package com.br.hotelCalifornia.api.controller;


import java.util.List;
import java.util.UUID;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.service.Services;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({"/api/hotel"})
@RequiredArgsConstructor
public class HotelCaliforniaController  {
	
	@Autowired
	private Services hotelServices;

	
    @GetMapping
	public List<?> findTodosHoteis(){
		return hotelServices.findTodos();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<HotelCaliforniaModel> findHotelCalifornia(@PathVariable UUID id){
		return hotelServices.find(id);
	}
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaModel createHotelCalifornia(@RequestBody HotelCaliforniaModel hotelCalifornia){
		return  hotelServices.create(hotelCalifornia);
	}
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<?> deleteHotelCalifornia(@PathVariable UUID id){
		return hotelServices.delete(id);
		
	}	
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<HotelCaliforniaModel> putHotelCalifornia(@PathVariable UUID id ,@RequestBody HotelCaliforniaModel hotelCalifornia){
		return  hotelServices.update(id ,hotelCalifornia);
	}
}