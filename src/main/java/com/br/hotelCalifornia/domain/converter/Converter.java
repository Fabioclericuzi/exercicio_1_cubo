package com.br.hotelCalifornia.domain.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;

import com.br.hotelCalifornia.api.dto.HotelCaliforniaDto;
import com.br.hotelCalifornia.infraestructure.model.HotelCaliforniaModel;

public class Converter {

	 public HotelCaliforniaDto toDto(HotelCaliforniaModel hotelCalifornia) {
         HotelCaliforniaDto dto = new HotelCaliforniaDto();
         BeanUtils.copyProperties(hotelCalifornia, dto);
         return dto;
     }
     
     public HotelCaliforniaModel toModel(HotelCaliforniaDto dto) {
         HotelCaliforniaModel hotelCalifornia = new HotelCaliforniaModel();
         BeanUtils.copyProperties(dto, hotelCalifornia);
         return hotelCalifornia;
     }
     
     public List<HotelCaliforniaDto> DtoTOList(List<HotelCaliforniaModel> Lista){
         return Lista.stream().map(this::toDto).collect(Collectors.toList());
     }
}
