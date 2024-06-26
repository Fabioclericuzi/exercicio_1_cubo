package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

import org.hibernate.hql.internal.HolderInstantiator;
import org.omg.PortableServer.RequestProcessingPolicyOperations;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.model.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;



@Service
public class Services {
	

	private hotelCaliforniaRepository repository;
	
	@Autowired
	public Services(hotelCaliforniaRepository repository) {
		this.repository = repository;
	}
	
	
	public List<HotelCaliforniaDto> findTodos(){
		List<HotelCaliforniaModel> hotelCalifornia = repository.findAll();
		return listDto(hotelCalifornia);
	}
	
	
	public HotelCaliforniaDto achar(UUID id){
		HotelCaliforniaModel hotelCalifornia = repository.find(id);
		return ModelToDto(hotelCalifornia);
		
	}
	
	
	public HotelCaliforniaDto create( HotelCaliforniaDto hotelCalifornia){
		HotelCaliforniaModel hotelModel = DtotoModel(hotelCalifornia);
		HotelCaliforniaModel salvarHotel = repository.save(hotelModel);
		return ModelToDto(salvarHotel);
	}
	 
	public HotelCaliforniaDto deleteHotelCalifornia(UUID id, HotelCaliforniaDto hotelCalifornia){
		HotelCaliforniaModel achar = repository.find(id);
		if(achar == null) {
			throw new EntityNotFoundException("Erro ao buscar hotel");			
	}
		 repository.delete(achar);
		 HotelCaliforniaDto response = new HotelCaliforniaDto();
		 response.mensagens("Deletado com sucesso");
		 return response;
		 
		
	}
	  @Transactional	
	  public HotelCaliforniaDto updateHotelCalifornia(String cnpj, HotelCaliforniaDto hotelCaliforniaDto) {
	       HotelCaliforniaModel hotelModel = DtotoModel(hotelCaliforniaDto);
	       HotelCaliforniaModel hotelsave = repository.findCnpj(cnpj);
	       if(!hotelsave.getCnpj().equals(cnpj)) {
	    	   throw new RuntimeException("Erro ao atualizar informações do hotel(cnpj não encontrado)");
	       }
	       
	       return ModelToDto(repository.save(hotelModel));
	 
	  }
	 public HotelCaliforniaDto findCnpj(String cnpj) {
		 return ModelToDto(repository.findCnpj(cnpj));
	 }
	 
	 public HotelCaliforniaModel findNome(String nome) {
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
	 
	 private List<HotelCaliforniaDto> listDto(List<HotelCaliforniaModel> listaDto){
		 return listaDto.stream().map(this::ModelToDto).collect(Collectors.toList());
	 }
	 
	 
}
