package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class Services {
	
	
	
	private hotelCaliforniaRepository repository;
	
	@Autowired
	public Services(hotelCaliforniaRepository repository) {
		this.repository = repository;
	}
	
	@Autowired
	public List<HotelCaliforniaModel> findTodos(){
		return repository.findAll();
	}
	
	@GetMapping(value="/{id}")
	public ResponseEntity<HotelCaliforniaModel> find( UUID id){
		return repository.findById(id)
		.map(mapping -> ResponseEntity.ok().body(mapping))
		.orElse(ResponseEntity.notFound().build());
	}
	
	
	public  HotelCaliforniaModel create( HotelCaliforniaModel hotelCalifornia){
		return repository.save(hotelCalifornia);
	}
	
	@PutMapping(value="/{id}")
	 public ResponseEntity<HotelCaliforniaModel> update(UUID id, HotelCaliforniaModel hotelCalifornia){
			
			return repository.findById(id)
			        .map(hotel -> {
			        	hotel.setNome(hotelCalifornia.getNome());
			        	hotel.setLocalizacao(hotelCalifornia.getLocalizacao());
			        	hotel.setCnpj(hotelCalifornia.getCnpj());
			            
			            HotelCaliforniaModel updatedHotelCalifornia = repository.save(hotel);
		                return ResponseEntity.ok().body(updatedHotelCalifornia);
		
		}).orElse(ResponseEntity.notFound().build());
	}
	 
	@DeleteMapping(value="/{id}")
	 public ResponseEntity<?> delete(@PathVariable UUID id){
			return repository.findById(id)
			.map(mapping -> {
	    			repository.deleteById(id);
	   
	                 return ResponseEntity.ok().body("Deletado com sucesso");
			         }).orElse(ResponseEntity.notFound().build());	
	
	 }
}
