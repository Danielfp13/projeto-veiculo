package com.api.veiculo.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.api.veiculo.entity.Veiculo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VeiculoDTO {
	
	@NotEmpty(message = "O campo veiculo é obrigatório.")
	private String veiculo;
	
	@NotEmpty(message = "O campo marca é obrigatório.")
	private String marca;
	
	@NotNull(message = "O campo ano é obrigatório.")
	private Long ano;
	
	private String descricao;
	
	@NotNull(message = "O campo vendido é obrigatório.")
	private Boolean vendido;

	public VeiculoDTO(Veiculo veiculo) {
		this.veiculo = veiculo.getVeiculo();
		this.marca = veiculo.getMarca();
		this.ano = veiculo.getAno();
		this.descricao = veiculo.getDescricao();
		this.vendido = veiculo.getVendido();
	}
	
	
}
