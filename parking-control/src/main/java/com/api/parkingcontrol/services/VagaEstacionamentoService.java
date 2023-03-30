package com.api.parkingcontrol.services;

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
}
