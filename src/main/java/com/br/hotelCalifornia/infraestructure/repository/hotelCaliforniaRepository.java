package com.br.hotelCalifornia.infraestructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.br.hotelCalifornia.api.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;


@Repository
public interface hotelCaliforniaRepository extends JpaRepository<HotelCaliforniaModel, UUID>{
    
	@Transactional(readOnly = true)
    @Query(value= "SELECT * FROM hotel_california hc WHERE hc.cnpj = :cnpj", nativeQuery=true)
    Optional<HotelCaliforniaModel> findCnpj(@Param("cnpj")String cnpj);
    
	@Transactional(readOnly = true)
    @Query(value= "SELECT * FROM hotel_california hc WHERE hc.nome = :nome", nativeQuery=true)
    Optional<HotelCaliforniaModel> findNome(@Param("nome")String nome);
    
	@Transactional(readOnly = true)
    @Query(value="SELECT * FROM hotel_california hc WHERE hc.localizacao = :localizacao", nativeQuery=true)
    HotelCaliforniaModel findLocalizacao(@Param("localizacao")String localizacao);
    
	@Transactional(readOnly = true)
    @Query(value="SELECT * FROM hotel_california hc WHERE hc.id = :id", nativeQuery = true)
    Optional<HotelCaliforniaModel> find(@Param("id")UUID id);

	@Transactional(readOnly = true)
    @Query(value= "SELECT * FROM hotel_california hc WHERE hc.cnpj = :cnpj", nativeQuery=true)
    HotelCaliforniaModel validaCnpj(@Param("cnpj")String cnpj);
   
}