package com.br.hotelCalifornia.api.controller;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.validation.Valid;

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

import com.br.hotelCalifornia.api.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.domain.service.Services;
import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;





@RestController
@Tag(name = "Hotel California")
@RequestMapping({"/api/hotel"})
public class HotelCaliforniaController  {
    
    private Services hotelServices;
    
    @Autowired
    public HotelCaliforniaController(Services hotelServices) {
        this.hotelServices = hotelServices;
    }
    
    @Operation(summary = "Listar todos os hotéis", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Lista de hotéis recuperada com sucesso"),
        @ApiResponse(responseCode = "404", description = "Não existem hotéis para serem listados"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value = "/listar")
    public List<?> findTodosHoteis(){
        return hotelServices.findTodos();
    }
    
    @Operation(summary = "Achar hotéis pelo id", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Hotel não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno de servidor")
    })
    @GetMapping(value="/{id}")
    public ResponseEntity<HotelCaliforniaDto> findHotelCalifornia(@Valid @PathVariable UUID id) {
    		HotelCaliforniaDto dto = hotelServices.find(id);
            return ResponseEntity.ok(dto);
        
    }
    
    @Operation(summary = "Salvar hotéis", method = "POST")
    @ApiResponses(value = { 
    		@ApiResponse(responseCode = "200", description = "Hotel salvo com sucesso"), 
    		@ApiResponse(responseCode = "409", description = "Hotel salvo com sucesso"),
    		@ApiResponse(responseCode = "400", description = "Erro ao salvar hotel")
    })
    @PostMapping(value="/salvar")
    public HotelCaliforniaDto createHotelCalifornia(@Valid @RequestBody HotelCaliforniaDto hotelCalifornia){
        return  hotelServices.create(hotelCalifornia);
    }
    @Operation(summary = "Deletar hotéis selecionados", method = "DELETE")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel deletado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Erro ao buscar hotel"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @DeleteMapping(path = "/{id}")
    public ResponseEntity<Object> deleteHotelCalifornia(@Valid @PathVariable UUID id, HotelCaliforniaModel hotelCalifornia){
        return hotelServices.deleteHotelCalifornia(id, hotelCalifornia);
            
    }
     
    @Operation(summary = "Atualizar informações dos hotéis selecionados", method = "PUT")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel atualizado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Hotel não encontrado"),
        @ApiResponse(responseCode = "400", description = "Dados inválidos"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @PutMapping(value = "/{id}")
     public ResponseEntity<HotelCaliforniaDto> update(@Valid @RequestBody HotelCaliforniaDto hotelCalifornia, @PathVariable UUID id) {
          try {
                HotelCaliforniaDto dto = hotelServices.updateHotelCalifornia(id, hotelCalifornia);
                return ResponseEntity.ok(dto);
           } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
           }
        }
    @Operation(summary = "Procurar hotéis pelo CNPJ", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "CNPJ não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value="/getcnpj/{cnpj}")        
    public HotelCaliforniaDto buscarPorCnpj(@PathVariable(value="cnpj") String cnpj){
        return hotelServices.findCnpj(cnpj);
    }
    
    @Operation(summary = "Procurar hotéis pelo Nome", method = "GET")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Hotel encontrado com sucesso"),
        @ApiResponse(responseCode = "404", description = "Nome do hotel não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping(value="/getnome/{nome}")        
    public HotelCaliforniaDto buscarNome(@PathVariable(value="nome") String nome){
        return hotelServices.findNome(nome);
    }
}