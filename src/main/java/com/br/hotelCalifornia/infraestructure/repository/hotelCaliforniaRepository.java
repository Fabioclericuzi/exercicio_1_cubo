package com.br.hotelCalifornia.infraestructure.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;
import com.br.hotelCalifornia.infraestructure.model.dto.HotelCaliforniaDto;


@Repository
public interface hotelCaliforniaRepository extends JpaRepository<HotelCaliforniaDto, UUID>{
	
	@Query(value= "SELECT * FROM hotel_california hc WHERE hc.cnpj = :cnpj", nativeQuery=true)
	HotelCaliforniaDto findCnpj(@Param("cnpj")String cnpj);
	
	@Query(value= "SELECT * FROM hotel_california hc WHERE hc.nome = :nome", nativeQuery=true)
	HotelCaliforniaDto findNome(@Param("nome")String nome);
	
	@Query(value="SELECT * FROM hotel_california hc WHERE hc.localizacao = :localizacao", nativeQuery=true)
	HotelCaliforniaDto findLocalizacao(@Param("localizacao")String localizacao);
	
	@Query(value="SELECT * FROM hotel_california hc WHERE hc.id = :id", nativeQuery = true)
	Optional<HotelCaliforniaDto> find(@Param("id")UUID id);

	HotelCaliforniaModel save(HotelCaliforniaModel hotelModel);
}

