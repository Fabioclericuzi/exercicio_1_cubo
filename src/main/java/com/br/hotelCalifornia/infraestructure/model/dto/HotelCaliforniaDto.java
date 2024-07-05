package com.br.hotelCalifornia.infraestructure.model.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
<<<<<<< HEAD
<<<<<<< HEAD

=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b

@AllArgsConstructor
@NoArgsConstructor
@Builder
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
@Data
=======
@Getter
@Setter
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
@Getter
@Setter
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
@Getter
@Setter
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
public class HotelCaliforniaDto {
		private UUID id;
		
		private String nome;
		private String localizacao;
		private String cnpj;
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
	
=======
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
		
		public String getCnpj() {
			return cnpj;
		}
		public void setCnpj(String cnpj) {
			this.cnpj = cnpj;
		}
		public UUID getId() {
			return id;
		}
		public void setId(UUID id) {
			this.id = id;
		}
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getLocalizacao() {
			return localizacao;
		}
		public void setLocalizacao(String localizacao) {
			this.localizacao = localizacao;
		}
		
<<<<<<< HEAD
<<<<<<< HEAD
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
=======
>>>>>>> 2d45e4b72b8e36fa393571234386b9d09426058b
		
}
