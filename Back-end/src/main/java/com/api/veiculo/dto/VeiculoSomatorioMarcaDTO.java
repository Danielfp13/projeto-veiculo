package com.api.veiculo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VeiculoSomatorioMarcaDTO {
	private String marca;
	private Long somatorio;
}
