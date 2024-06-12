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
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;
import com.br.hotelCalifornia.infraestructure.service.Services;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping({"/api/hotel"})
@RequiredArgsConstructor
public class HotelCaliforniaController  {
	
	private Services hotelServices;

	
    public HotelCaliforniaController(Services hotelServices) {
		this.hotelServices = hotelServices;
    }
	

	private hotelCaliforniaRepository repository;
	
	 @Autowired
	    public HotelCaliforniaController(hotelCaliforniaRepository repository) {
	        this.repository = repository;
	    }
	
	
	@GetMapping
	public List<?> findTodosHoteis(){
		return hotelServices.findTodos();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<HotelCaliforniaModel> findHotelCalifornia(@PathVariable UUID id){
		return hotelServices.find(id);
	}
	
	@PostMapping(value="/salvar")
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