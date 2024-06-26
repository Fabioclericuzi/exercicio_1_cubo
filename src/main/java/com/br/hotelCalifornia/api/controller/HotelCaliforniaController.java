package com.br.hotelCalifornia.api.controller;


import java.util.List;
import java.util.Optional;
import java.util.UUID;



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

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.model.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.infraestructure.service.Services;


@RestController
@RequestMapping({"/api/hotel"})
public class HotelCaliforniaController<T>  {
	
	private Services hotelServices;
	
	@Autowired
    public HotelCaliforniaController(Services hotelServices) {
		this.hotelServices = hotelServices;
	}
	
    @GetMapping
	public List<?> findTodosHoteis(){
		return hotelServices.findTodos();
	}
	
	@GetMapping(value="/{id}")
	public HotelCaliforniaDto findHotelCalifornia(@PathVariable UUID id){
		return hotelServices.achar(id);
	}
	
	@PostMapping(value="/salvar")
	public ResponseEntity<HotelCaliforniaDto> createHotelCalifornia(@RequestBody HotelCaliforniaDto hotelCalifornia){
		HotelCaliforniaDto hotelDto = hotelServices.create(hotelCalifornia);
		return  ResponseEntity.status(HttpStatus.CREATED).body(hotelDto);	
		
	}
	
	@DeleteMapping(path = "/{id}")
    public HotelCaliforniaDto deleteHotelCalifornia(@PathVariable UUID id, HotelCaliforniaDto hotelCalifornia){
		return hotelServices.deleteHotelCalifornia(id, hotelCalifornia);
			
	}
	@PutMapping(value = "/{cnpj}")
	public HotelCaliforniaDto update(@RequestBody HotelCaliforniaDto hotelCalifornia, @PathVariable(value= "cnpj") String cnpj){
		return hotelServices.updateHotelCalifornia(cnpj, hotelCalifornia);
	}
	@GetMapping(value="/getcnpj/{cnpj}")		
	public ResponseEntity<HotelCaliforniaDto> buscarPorCnpj(@PathVariable(value="cnpj") String cnpj){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findCnpj(cnpj));
	}
	
	@GetMapping(value="/getnome/{nome}")		
	public ResponseEntity<HotelCaliforniaModel> buscarNome(@PathVariable(value="nome") String nome){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findNome(nome));
	}
}
