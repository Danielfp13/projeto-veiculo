package com.api.veiculo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.api.veiculo.dto.VeiculoSomatorioDecadaDTO;
import com.api.veiculo.dto.VeiculoSomatorioMarcaDTO;
import com.api.veiculo.dto.VeiculoSomatorioSemanaDTO;
import com.api.veiculo.entity.Veiculo;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

	@Query("SELECT new com.api.veiculo.dto.VeiculoSomatorioDecadaDTO( (obj.ano / 10) * 10 , COUNT(obj.ano) ) FROM Veiculo AS obj GROUP BY (obj.ano/ 10) * 10 ")
	public List<VeiculoSomatorioDecadaDTO> somatorioVeiculo();

	@Query("SELECT NEW com.api.veiculo.dto.VeiculoSomatorioMarcaDTO(obj.marca, COUNT(obj.marca) ) FROM Veiculo AS obj GROUP BY obj.marca")
	public List<VeiculoSomatorioMarcaDTO> somatorioMarca();

	@Query("SELECT NEW com.api.veiculo.dto.VeiculoSomatorioSemanaDTO( COUNT(obj.id) ) FROM Veiculo AS obj WHERE obj.created >= :data")
	public List<VeiculoSomatorioSemanaDTO> somatorioSemana(LocalDateTime data);

	@Query("SELECT NEW com.api.veiculo.dto.VeiculoSomatorioSemanaDTO( COUNT(obj.id) ) FROM Veiculo AS obj WHERE obj.vendido  = false")
	public List<VeiculoSomatorioSemanaDTO> somatorioNaoVendido();

	@Query(value = "select * from veiculo v where v.marca like :marca or CAST(id AS TEXT) LIKE :id", nativeQuery = true)
	public Page<Veiculo> findByIdOrMarcaIgnoreCase(Pageable pageRequest, String id, String marca);

}
