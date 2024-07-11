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
import com.br.hotelCalifornia.infraestructure.exceptions.ConflictException;
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
        }catch(UnprocessableEntityException e) {
    		throw new UnprocessableEntityException(e.getMessage());
    }
   }
    
    @Transactional(readOnly = true)
    public HotelCaliforniaDto find(UUID id) {
    	try {
    	Optional<HotelCaliforniaModel> dto = repository.findById(id);
    	if(!dto.isPresent()) {
    		throw new UnprocessableEntityException("Hotel não encontrado");
    	}
        return converter.toDto(dto.get());
    }catch(UnprocessableEntityException e){
    	throw new UnprocessableEntityException(e.getMessage());
    }
   }
    @Modifying
    @Transactional
    public HotelCaliforniaDto create(HotelCaliforniaDto dto) {
        try {
        	if(repository.validaCnpj(dto.getCnpj()) != null){
        		throw new ConflictException("Já existe esse CNPJ cadastrado");
        	}
        	 if (dto.getCnpj() == null || dto.getNome() == null || dto.getLocalizacao() == null) {
				throw new UnprocessableEntityException("Os campos nome, CNPJ e localização são obrigatórios");
			}
        
    	HotelCaliforniaModel model = converter.toModel(dto);
        return converter.toDto(repository.save(model));
       }catch(ConflictException e){
    	   throw new ConflictException(e.getMessage());
       }catch(BusinessException e){
    	   throw new UnprocessableEntityException(e.getMessage());
       }
        
    }
     
    @Modifying
    @Transactional
    public ResponseEntity<Object> deleteHotelCalifornia(UUID id, HotelCaliforniaModel hotelCalifornia){
        try {
    	Optional<HotelCaliforniaModel> achar = repository.find(id);
        if(!achar.isPresent()) {
         throw new UnprocessableEntityException("Hotel não encontrado");    
    }
         repository.delete(achar.get());
         return ResponseEntity.status(HttpStatus.OK).body("Hotel deletado com sucesso");
        
    }catch(UnprocessableEntityException e) {
    	throw new UnprocessableEntityException(e.getMessage());
     }catch(BusinessException e) {
    	 throw new BusinessException("Hotel não podê ser deletado" + e);
     }
    }
    @Modifying
    @Transactional
    public HotelCaliforniaDto updateHotelCalifornia(UUID id, HotelCaliforniaDto dto) {
        HotelCaliforniaModel hotel = repository.findById(id)
                .orElseThrow(() -> new UnprocessableEntityException("Hotel não encontrado"));

        hotel.setNome(dto.getNome());
        hotel.setLocalizacao(dto.getLocalizacao());
        hotel.setCnpj(dto.getCnpj());

        return converter.toDto(repository.save(hotel));
    }
    
    @Transactional(readOnly = true)
     public HotelCaliforniaDto validaCnpj(String cnpj) {
         return converter.toDto(repository.validaCnpj(cnpj));
     }
     
    @Transactional(readOnly = true)
     public HotelCaliforniaDto findNome(String nome) {
        try {
        	Optional<HotelCaliforniaModel> dto = repository.findNome(nome);
        	if(!dto.isPresent()) {
        		throw new UnprocessableEntityException("Nome do hotel não encontrado");
        	}
        
    	return converter.toDto(dto.get());
     }catch(UnprocessableEntityException e) {
    	 throw new UnprocessableEntityException(e.getMessage());
     }
    }
    
    @Transactional(readOnly = true)
    public HotelCaliforniaDto findCnpj(String cnpj) {
        try {
        	Optional<HotelCaliforniaModel> dto = repository.findCnpj(cnpj);
        	if(!dto.isPresent()) {
        		throw new UnprocessableEntityException("CNPJ não encontrado");
        	}
        	return converter.toDto(dto.get());
    }catch(UnprocessableEntityException e){
    	throw new UnprocessableEntityException(e.getMessage());
     }
    } 
}