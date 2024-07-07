package com.br.hotelCalifornia.api.dto;

import java.util.UUID;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class HotelCaliforniaDto {
		
		@JsonProperty(access = JsonProperty.Access.READ_ONLY)
		private UUID id;
		
		@NotNull(message="Nome do hotel é obrigatório")
		@Size(min = 1, max =100, message="O nome do hotel deve ter entre 1 e 100 caracteres")
		private String nome;
		
		@NotNull(message ="A localização do hotel é obrigatória")
		@Size(min = 1, max = 100, message = "A localidade precisa ter entre 1 e 100 caracteres")
		private String localizacao;
		
		@NotNull(message = "O CNPJ do hotel é obrigatório")
		@Size(min = 1, max = 14, message = "O CNPJ precisa ter entre 1 e 14 caracteres")
		private String cnpj;
		
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
		
		
}