package com.br.hotelCalifornia.api.controller;


import java.util.List;
import java.util.NoSuchElementException;
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
	
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	@GetMapping(value="/{id}")
	public Optional<HotelCaliforniaModel> findHotelCalifornia(@PathVariable UUID id){
		return hotelServices.find(id);
	}
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaModel createHotelCalifornia(@RequestBody HotelCaliforniaModel hotelCalifornia){
=======
    @GetMapping(value="/{id}")
    public ResponseEntity<HotelCaliforniaDto> findHotelCalifornia(@PathVariable UUID id) {
        try {
            HotelCaliforniaDto dto = hotelServices.find(id);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaDto createHotelCalifornia(@RequestBody HotelCaliforniaDto hotelCalifornia){
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
    @GetMapping(value="/{id}")
    public ResponseEntity<HotelCaliforniaDto> findHotelCalifornia(@PathVariable UUID id) {
        try {
            HotelCaliforniaDto dto = hotelServices.find(id);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaDto createHotelCalifornia(@RequestBody HotelCaliforniaDto hotelCalifornia){
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
    @GetMapping(value="/{id}")
    public ResponseEntity<HotelCaliforniaDto> findHotelCalifornia(@PathVariable UUID id) {
        try {
            HotelCaliforniaDto dto = hotelServices.find(id);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        
    }
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaDto createHotelCalifornia(@RequestBody HotelCaliforniaDto hotelCalifornia){
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
		return  hotelServices.create(hotelCalifornia);
	}
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteHotelCalifornia(@PathVariable UUID id, HotelCaliforniaModel hotelCalifornia){
		return hotelServices.deleteHotelCalifornia(id, hotelCalifornia);
			
	}
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@RequestBody HotelCaliforniaModel hotelCalifornia, @PathVariable UUID id){
		return hotelServices.updateHotelCalifornia(id, hotelCalifornia);
	}
=======
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
	 
	@PutMapping(value = "/{id}")
	 public ResponseEntity<HotelCaliforniaDto> update(@RequestBody HotelCaliforniaDto hotelCalifornia, @PathVariable UUID id) {
	      try {
	            HotelCaliforniaDto dto = hotelServices.updateHotelCalifornia(id, hotelCalifornia);
	            return ResponseEntity.ok(dto);
	       } catch (NoSuchElementException e) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	       }
	    }
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
	@GetMapping(value="/getcnpj/{cnpj}")		
	public ResponseEntity<HotelCaliforniaModel> buscarPorCnpj(@PathVariable(value="cnpj") String cnpj){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findCnpj(cnpj));
	}
	
	@GetMapping(value="/getnome/{nome}")		
	public ResponseEntity<HotelCaliforniaModel> buscarNome(@PathVariable(value="nome") String nome){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findNome(nome));
	}
}