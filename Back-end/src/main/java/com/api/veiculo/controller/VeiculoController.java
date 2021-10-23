package com.api.veiculo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.veiculo.dto.VeiculoDTO;
import com.api.veiculo.entity.Veiculo;
import com.api.veiculo.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {
	
	@Autowired
	private VeiculoService veiculoService;
	
	//Retorna lista de viculos
	@GetMapping()
	public ResponseEntity<List<Veiculo>> findAll() {
		List<Veiculo> veiculos = veiculoService.findAll();
		return ResponseEntity.ok().body(veiculos);
	}
	
	// Retorna um veiculo por id
	@GetMapping("/{id}")
	public ResponseEntity<Veiculo> find(@PathVariable Long id) {
		Veiculo veiculo = veiculoService.find(id);
		return ResponseEntity.ok().body(veiculo);
	}

	// Adiciona um veiculo
	@PostMapping
	public ResponseEntity<Veiculo> insert(@Valid @RequestBody VeiculoDTO veiculoDTO) {
		Veiculo veiculo = veiculoService.insert(veiculoDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(veiculo.getId())
				.toUri();
		return ResponseEntity.created(uri).body(veiculo);
	}
}
