package com.api.parkingcontrol.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.api.parkingcontrol.models.VagaEstacionamentoModel;
import com.api.parkingcontrol.repositories.VagaEstacionamentoRepository;

import jakarta.transaction.Transactional;

@Service
public class VagaEstacionamentoService {

	/*Essa classe fica entre o controler e o repository*/
	
	/**
	 * Injeção de dependência
	 */
	@Autowired
	VagaEstacionamentoRepository vagaEstacionamentoRepository;
	
	@Transactional
	public VagaEstacionamentoModel save(VagaEstacionamentoModel vagaEstacionamentoModel) {
		return vagaEstacionamentoRepository.save(vagaEstacionamentoModel);
	}

	public boolean existsByPlacaCarro(String placaCarro) {
		return vagaEstacionamentoRepository.existsByPlacaCarro(placaCarro);
	}

	public boolean existsByNumeroVaga(String numeroVaga) {
		return vagaEstacionamentoRepository.existsByNumeroVaga(numeroVaga);
	}

	public boolean existsByApartamentoAndBloco(String apartamento, String bloco) {
		return vagaEstacionamentoRepository.existsByApartamentoAndBloco(apartamento, bloco);
	}

	public List<VagaEstacionamentoModel> findAll() {
		return vagaEstacionamentoRepository.findAll();
	}

	public Optional<VagaEstacionamentoModel> findById(Long id) {
		return vagaEstacionamentoRepository.findById(id);
	}
}
