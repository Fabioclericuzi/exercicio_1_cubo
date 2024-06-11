package com.br.hotelCalifornia.infraestructure.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Repository
public interface hotelCaliforniaRepository extends JpaRepository<HotelCaliforniaModel, UUID>{
	
}