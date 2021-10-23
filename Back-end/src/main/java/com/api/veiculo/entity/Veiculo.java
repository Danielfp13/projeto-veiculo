package com.api.veiculo.entity;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "veiculo")
public class Veiculo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String veiculo;
	private String marca;
	private Long ano;
	private String descricao;
	private Boolean vendido;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime created;
	
	@JsonFormat(pattern = "dd-MM-yyyy")
	private LocalDateTime updated;
}
