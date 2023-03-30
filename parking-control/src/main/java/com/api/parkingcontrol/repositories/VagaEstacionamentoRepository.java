package com.api.parkingcontrol.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.parkingcontrol.models.VagaEstacionamentoModel;

@Repository
public interface VagaEstacionamentoRepository extends JpaRepository<VagaEstacionamentoModel, Long> {

	//SÓ PRECISO DECLARAR O MÉTODO QUE VAI SER CHAMADO NA CLASSE SERVICE, E NÃO PRECISO IMPLEMENTAR MAIS NADA POR ENQUANTO.
	boolean existsByPlacaCarro(String placaCarro);
	boolean existsByNumeroVaga(String numeroVaga);
	boolean existsByApartamentoAndBloco(String apartamento, String bloco);
	//Optional<VagaEstacionamentoModel> findById(Long id);
}
