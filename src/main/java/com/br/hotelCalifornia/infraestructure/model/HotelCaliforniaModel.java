package com.br.hotelCalifornia.infraestructure.model;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
@Builder
@Table(name = "hotel_california")
public class HotelCaliforniaModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    //@Column(name = "id")
    private UUID id;
    
    @Column(name = "nome")
    private String nome;
    
    @Column(name = "localizacao")
    private String localizacao; 
    
    @Column(name = "cnpj")
    private String cnpj;

    
}
