package com.br.hotelCalifornia.infraestructure.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

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
		return repository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }
	
	
	
	public HotelCaliforniaDto find(UUID id) {
        return repository.findById(id)
                .map(this::toDto)
                .orElseThrow(() -> new NoSuchElementException("Erro ao buscar o hotel"));
    }
		
	
	public HotelCaliforniaDto create(HotelCaliforniaDto dto) {
        HotelCaliforniaModel model = toModel(dto);
        return toDto(repository.save(model));
    }
	 
	public ResponseEntity<Object> deleteHotelCalifornia(UUID id, HotelCaliforniaModel hotelCalifornia){
		Optional<HotelCaliforniaModel> achar = repository.find(id);
		if(!achar.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar hotel");			
	}
		 repository.delete(achar.get());
		 return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
		
	}
	
	public HotelCaliforniaDto updateHotelCalifornia(UUID id, HotelCaliforniaDto dto) {
        HotelCaliforniaModel hotel = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel não encontrado"));

        hotel.setNome(dto.getNome());
        hotel.setLocalizacao(dto.getLocalizacao());
        hotel.setCnpj(dto.getCnpj());

        return toDto(repository.save(hotel));
    }
	
	 
	 public HotelCaliforniaModel findCnpj(String cnpj) {
		 return repository.findCnpj(cnpj);
	 }
	 
	 public HotelCaliforniaModel findNome(String nome) {
		 return repository.findNome(nome);
	 }

	 private HotelCaliforniaDto toDto(HotelCaliforniaModel hotelCalifornia) {
		 HotelCaliforniaDto dto = new HotelCaliforniaDto();
		 BeanUtils.copyProperties(hotelCalifornia, dto);
		 return dto;
	 }
	 
	 private HotelCaliforniaModel toModel(HotelCaliforniaDto dto) {
		 HotelCaliforniaModel hotelCalifornia = new HotelCaliforniaModel();
		 BeanUtils.copyProperties(dto, hotelCalifornia);
		 return hotelCalifornia;
	 }
	 
	 private List<HotelCaliforniaDto> DtoTOList(List<HotelCaliforniaModel> Lista){
		 return Lista.stream().map(this::toDto).collect(Collectors.toList());
	 }
}