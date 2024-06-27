package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;




@Service
public class Services {
	

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
	 
	public ResponseEntity<Object> deleteHotelCalifornia(UUID id, HotelCaliforniaModel hotelCalifornia){
		Optional<HotelCaliforniaModel> achar = repository.find(id);
		if(!achar.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar hotel");			
	}
		 repository.delete(achar.get());
		 return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
		
	}
	
	  public ResponseEntity<Object> updateHotelCalifornia(UUID id, HotelCaliforniaModel hotelCalifornia) {
	        Optional<HotelCaliforniaModel> achar = repository.find(id);
	        if (achar.isPresent()) {
	            HotelCaliforniaModel hotel = achar.get();
	            hotel.setNome(hotelCalifornia.getNome());
	            hotel.setLocalizacao(hotelCalifornia.getLocalizacao());
	            hotel.setCnpj(hotelCalifornia.getCnpj());
	            repository.save(hotel);
	            return ResponseEntity.status(HttpStatus.OK).body("Atualizado com sucesso");
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Hotel não encontrado");
	        }
	    }
	 
	 public HotelCaliforniaModel findCnpj(String cnpj) {
		 return repository.findCnpj(cnpj);
	 }
	 
	 public HotelCaliforniaModel findNome(String nome) {
		 return repository.findNome(nome);
	 }
}