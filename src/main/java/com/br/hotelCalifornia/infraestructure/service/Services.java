package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
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
import com.br.hotelCalifornia.infraestructure.model.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;

import lombok.RequiredArgsConstructor;


@Service
public class Services {
	

	private hotelCaliforniaRepository repository;
	
	@Autowired
	public Services(hotelCaliforniaRepository repository) {
		this.repository = repository;
	}
	
	
	public List<HotelCaliforniaDto> findTodos(){
		return repository.findAll();
	}
	
	
	public Optional<HotelCaliforniaDto> find(UUID id){
		return repository.findById(id);
		
	}
	
	@Transactional
	public HotelCaliforniaDto create( HotelCaliforniaDto hotelCalifornia){
		HotelCaliforniaModel hotelModel = DtotoModel(hotelCalifornia);
		HotelCaliforniaModel salvarHotel = repository.save(hotelModel);
		return ModelToDto(salvarHotel);
	}
	 
	public ResponseEntity<Object> deleteHotelCalifornia(UUID id, HotelCaliforniaDto hotelCalifornia){
		Optional<HotelCaliforniaDto> achar = repository.find(id);
		if(!achar.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar hotel");			
	}
		 repository.delete(achar.get());
		 return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
		
	}
	
	  public HotelCaliforniaDto updateHotelCalifornia(String cnpj, HotelCaliforniaDto hotelCaliforniaDto) {
	       HotelCaliforniaModel hotelModel = DtotoModel(hotelCaliforniaDto);
	       HotelCaliforniaDto hotelsave = repository.findCnpj(cnpj);
	       if(!hotelsave.getCnpj().equals(cnpj)) {
	    	   throw new RuntimeException("Erro ao atualizar informações do hotel(cnpj não encontrado)");
	       }
	       
	       return ModelToDto(repository.save(hotelModel));
	 
	  }
	 public HotelCaliforniaDto findCnpj(String cnpj) {
		 return repository.findCnpj(cnpj);
	 }
	 
	 public HotelCaliforniaDto findNome(String nome) {
		 return repository.findNome(nome);
	 }
	 
	 private HotelCaliforniaDto ModelToDto(HotelCaliforniaModel hotelCalifornia) {
		 HotelCaliforniaDto dto = new HotelCaliforniaDto();	
		 BeanUtils.copyProperties(hotelCalifornia, dto);
		 return dto;
	 }
	 private HotelCaliforniaModel DtotoModel(HotelCaliforniaDto dto) {
		 HotelCaliforniaModel hotelCalifornia = new HotelCaliforniaModel();	
		 BeanUtils.copyProperties(dto, hotelCalifornia);
		 return hotelCalifornia;
	 }
	 
	 private List<HotelCaliforniaDto> ListDto(List<HotelCaliforniaModel> Lista){
		 return Lista.stream().map(this::ModelToDto).collect(Collectors.toList());
	 }
	 
	 
}
