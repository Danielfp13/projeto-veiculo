package com.api.veiculo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.api.veiculo.dto.VeiculoDTO;
import com.api.veiculo.dto.VeiculoSomatorioDecadaDTO;
import com.api.veiculo.dto.VeiculoSomatorioMarcaDTO;
import com.api.veiculo.dto.VeiculoSomatorioSemanaDTO;
import com.api.veiculo.entity.Veiculo;
import com.api.veiculo.repository.VeiculoRepository;
import com.api.veiculo.service.exception.ObjectNotFoundException;


@Service
public class VeiculoService {

	@Autowired
	private VeiculoRepository veiculoRepository;
	
	// Buscar todas veiculo
	public List<Veiculo> findAll() {
		return veiculoRepository.findAll();
	}
	
	// Buscar veiculo por id
	public Veiculo find(Long id) {
		return veiculoRepository.findById(id)
				.orElseThrow(() -> new ObjectNotFoundException("Não exite veiculo com id = " + id + "."));
	}
	
	// Inserir veiculo
	public Veiculo insert(VeiculoDTO veiculoDTO) {
		Veiculo veiculo = new Veiculo();
		BeanUtils.copyProperties(veiculoDTO, veiculo);
		veiculo.setCreated(LocalDateTime.now());
		return veiculoRepository.save(veiculo);
	}
	
	// Alterar veiculo
	public Veiculo update(VeiculoDTO veiculoDTO, Long id) {
		Veiculo newVeiculo = find(id);
		BeanUtils.copyProperties(veiculoDTO, newVeiculo);
		newVeiculo.setId(id);
		newVeiculo.setUpdated(LocalDateTime.now());
		return veiculoRepository.save(newVeiculo);
	}
	
	// Excluir veiculo
	public void delete(Long id) {
		find(id);
		try {
			veiculoRepository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityViolationException("Não pode excluir veiculos com associações.");
		}
	}

	// Somatório de veiculos por década
	public List<VeiculoSomatorioDecadaDTO> countVeiculo() {
		return veiculoRepository.somatorioVeiculo();
	}
	
	// Somatório de veiculos por marca
	public List<VeiculoSomatorioMarcaDTO> countMarca() {
		return veiculoRepository.somatorioMarca();
	}
	
	// Somatorio de veiculos cadastradas na ultima semana
	public List<VeiculoSomatorioSemanaDTO> countSemana() {
		return veiculoRepository.somatorioSemana(LocalDateTime.now().minusWeeks(1));
	}
}
