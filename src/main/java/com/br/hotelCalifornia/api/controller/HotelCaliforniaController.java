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
	public Optional<HotelCaliforniaModel> findHotelCalifornia(@PathVariable UUID id){
		return hotelServices.find(id);
	}
	
	@PostMapping(value="/salvar")
	public HotelCaliforniaModel createHotelCalifornia(@RequestBody HotelCaliforniaModel hotelCalifornia){
		return  hotelServices.create(hotelCalifornia);
	}
	
	@DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteHotelCalifornia(@PathVariable UUID id, HotelCaliforniaModel hotelCalifornia){
		return hotelServices.deleteHotelCalifornia(id, hotelCalifornia);
			
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Object> update(@RequestBody HotelCaliforniaModel hotelCalifornia, @PathVariable UUID id){
		Optional<HotelCaliforniaModel> achar = hotelServices.find(id);
		if(!achar.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar hotel");			
	}
		hotelServices.create(hotelCalifornia);
		return ResponseEntity.status(HttpStatus.OK).body("Atualização realizada com sucesso no objeto: " + hotelCalifornia );
		}
	@GetMapping(value="/getcnpj/{cnpj}")		
	public ResponseEntity<HotelCaliforniaModel> buscarPorCnpj(@PathVariable(value="cnpj") String cnpj){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findCnpj(cnpj));
	}
	
	@GetMapping(value="/getnome/{nome}")		
	public ResponseEntity<HotelCaliforniaModel> buscarNome(@PathVariable(value="nome") String nome){
		return ResponseEntity.status(HttpStatus.OK).body(hotelServices.findNome(nome));
	}
}
