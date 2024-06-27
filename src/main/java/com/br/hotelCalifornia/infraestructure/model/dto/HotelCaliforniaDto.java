package com.br.hotelCalifornia.infraestructure.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class HotelCaliforniaDto {
		private UUID id;
		
		private String nome;
		private String localizacao;
		private String cnpj;
	
		
}
