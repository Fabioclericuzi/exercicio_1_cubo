package com.br.hotelCalifornia.domain.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.hotelCalifornia.api.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.domain.conversores.Conversores;
import com.br.hotelCalifornia.infraestructure.exceptions.BusinessException;
import com.br.hotelCalifornia.infraestructure.exceptions.UnprocessableEntityException;
import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;

import com.br.hotelCalifornia.infraestructure.repository.hotelCaliforniaRepository;




@Service
public class Services {
    
	private final Conversores converter;
	
    private final hotelCaliforniaRepository repository;
    
    @Autowired
    public Services(hotelCaliforniaRepository repository, Conversores converter) {
        this.repository = repository;
        this.converter = converter;
    }
    
    @Transactional(readOnly = true)
    public List<HotelCaliforniaDto> findTodos(){
        try {
        	List<HotelCaliforniaModel> hotelCalifornia = repository.findAll();
        	if(hotelCalifornia.isEmpty()) {
        		throw new UnprocessableEntityException("Não existem hotéis para serem listados");
        	}
    	return hotelCalifornia.stream()
                .map(converter::toDto)
                .collect(Collectors.toList());
    }catch (BusinessException e) {
		throw new UnprocessableEntityException(e.getMessage());
	}catch(UnprocessableEntityException e) {
		throw new BusinessException("Não existem hotéis para serem listados");
	}
    }
    
    @Transactional(readOnly = true)
    public HotelCaliforniaDto find(UUID id) {
        return repository.findById(id)
                .map(converter::toDto)
                .orElseThrow(() -> new NoSuchElementException("Erro ao buscar o hotel"));
    }
        
    
    @Modifying
    @Transactional
    public HotelCaliforniaDto create(HotelCaliforniaDto dto) {
        HotelCaliforniaModel model = converter.toModel(dto);
        return converter.toDto(repository.save(model));
    }
     
    @Modifying
    @Transactional
    public ResponseEntity<Object> deleteHotelCalifornia(UUID id, HotelCaliforniaModel hotelCalifornia){
        Optional<HotelCaliforniaModel> achar = repository.find(id);
        if(!achar.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Erro ao buscar hotel");            
    }
         repository.delete(achar.get());
         return ResponseEntity.status(HttpStatus.OK).body("Deletado com sucesso");
        
    }
    
    @Modifying
    @Transactional
    public HotelCaliforniaDto updateHotelCalifornia(UUID id, HotelCaliforniaDto dto) {
        HotelCaliforniaModel hotel = repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Hotel não encontrado"));

        hotel.setNome(dto.getNome());
        hotel.setLocalizacao(dto.getLocalizacao());
        hotel.setCnpj(dto.getCnpj());

        return converter.toDto(repository.save(hotel));
    }
    
    @Transactional(readOnly = true)
     public HotelCaliforniaModel findCnpj(String cnpj) {
         return repository.findCnpj(cnpj);
     }
     
    @Transactional(readOnly = true)
     public HotelCaliforniaModel findNome(String nome) {
         return repository.findNome(nome);
     }

}