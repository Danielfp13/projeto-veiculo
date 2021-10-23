package com.api.veiculo.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.veiculo.dto.VeiculoDTO;
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
	
	// inserir veiculo
	public Veiculo insert(VeiculoDTO veiculoDTO) {
		Veiculo veiculo = new Veiculo();
		BeanUtils.copyProperties(veiculoDTO, veiculo);
		veiculo.setCreated(LocalDateTime.now());
		return veiculoRepository.save(veiculo);
	}
}
