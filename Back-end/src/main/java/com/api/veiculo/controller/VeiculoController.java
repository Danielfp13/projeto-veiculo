package com.api.veiculo.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.api.veiculo.dto.VeiculoDTO;
import com.api.veiculo.dto.VeiculoSomatorioDecadaDTO;
import com.api.veiculo.dto.VeiculoSomatorioMarcaDTO;
import com.api.veiculo.dto.VeiculoSomatorioSemanaDTO;
import com.api.veiculo.entity.Veiculo;
import com.api.veiculo.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
@CrossOrigin("http://localhost:4200")
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
	
	// Atualiza os dados de um veiculo
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@Valid @RequestBody VeiculoDTO veiculoDTO, @PathVariable Long id) {
		veiculoService.update(veiculoDTO, id);
		return ResponseEntity.noContent().build();
	}
	
	// Excluir a veiculo
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		veiculoService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
	// Somatório de veiculos por décadas
	@GetMapping("/decadas")
	public ResponseEntity<List<VeiculoSomatorioDecadaDTO>> countVeiculo() {
		List<VeiculoSomatorioDecadaDTO> veiculosDTO = veiculoService.countVeiculo();
		return ResponseEntity.ok().body(veiculosDTO);
	}
	
	// Somatório de veiculos por marca
	@GetMapping("/marcas")
	public ResponseEntity<List<VeiculoSomatorioMarcaDTO>> countMarca() {
		List<VeiculoSomatorioMarcaDTO> veiculosDTO = veiculoService.countMarca();
		return ResponseEntity.ok().body(veiculosDTO);
	}
	
	//Retorna somatorio da semana
	@GetMapping("/semanas")
	public ResponseEntity<List<VeiculoSomatorioSemanaDTO>> countSemana(){
		List<VeiculoSomatorioSemanaDTO> veiculosDTO = veiculoService.countSemana();
		return ResponseEntity.ok().body(veiculosDTO);
	}
	
	//Retorna somatorio de veiculos não vendidos
	@GetMapping("/vendidos")
	public ResponseEntity<List<VeiculoSomatorioSemanaDTO>> countNaoVendidos(){
		List<VeiculoSomatorioSemanaDTO> veiculosDTO = veiculoService.countNaoVendidos();
		return ResponseEntity.ok().body(veiculosDTO);
	}
	
	//Retorna pagina de veiculos
	@GetMapping("/find")
	public ResponseEntity<Page<Veiculo>> finddPage(
			@RequestParam(value = "ultimaSemana", defaultValue = "false") Boolean ultimaSemana,
			@RequestParam(value = "marca", defaultValue = "") String marca,
     		@RequestParam(value = "id", defaultValue = "") String id,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linePerPage", defaultValue = "8") Integer linePerPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "ordeBy", defaultValue = "marca") String orderBy
			){
				Page<Veiculo> veiculos = veiculoService.findPage(ultimaSemana ,page, linePerPage, direction, orderBy, id, marca);
		return ResponseEntity.ok().body(veiculos);
	}
		
}
