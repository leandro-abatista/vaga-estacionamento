package com.api.parkingcontrol.controllers;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.api.parkingcontrol.dto.VagaEstacionamentoDto;
import com.api.parkingcontrol.models.VagaEstacionamentoModel;
import com.api.parkingcontrol.services.VagaEstacionamentoService;

import jakarta.validation.Valid;

/**
 * Nessa classe são determinadas as requesições
 * @author LYANN
 *
 */
@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/vaga-estacionamento")
public class VagaEstacionamentoController {

	/**
	 * Injeção de dependência
	 */
	@Autowired
	VagaEstacionamentoService vagaEstacionamentoService;
	
	@PostMapping
	public ResponseEntity<Object> salvarVagaEstacionamento(@RequestBody @Valid VagaEstacionamentoDto vagaEstacionamentoDto){
		
		/**
		 * Antes de salvar no banco, precisa realizar algumas verificações
		 */
		
		//VERIFICANDO SE JÁ TEM REGISTRO PARA A PLACA DO CARRO INFORMADA
		if(vagaEstacionamentoService.existsByPlacaCarro(vagaEstacionamentoDto.getPlacaCarro())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: PLaca do carro já está em uso!");
		}
		//VERIFICANDO SE JÁ TEM REGISTRO PARA A PARA A VAGA INFORMADA
		if (vagaEstacionamentoService.existsByNumeroVaga(vagaEstacionamentoDto.getNumeroVaga())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de Estacionamento já está em uso!");
		}
		//VERIFICANDO SE JÁ TEM REGISTRO PARA O APARTAMENTO/BLOCO
		if (vagaEstacionamentoService.existsByApartamentoAndBloco(vagaEstacionamentoDto.getApartamento(), vagaEstacionamentoDto.getBloco())) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Vaga de Estacionamento já está registrado para um apartamento/bloco!");
		}
		
		var vagaEstacionamentoModel = new VagaEstacionamentoModel();
		BeanUtils.copyProperties(vagaEstacionamentoDto, vagaEstacionamentoModel);//Nesse método converte o objeto dto em objeto model. É um método bem simples
		vagaEstacionamentoModel.setDataRegistro(LocalDateTime.now(ZoneId.of("UTC")));
		return ResponseEntity.status(HttpStatus.CREATED).body(vagaEstacionamentoService.save(vagaEstacionamentoModel));
	}
	
	@GetMapping
	public ResponseEntity<List<VagaEstacionamentoModel>> getAllVagasEstacionamento(){
		return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoService.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Object> getOneVagaEstacionamento(@PathVariable(value = "id") Long id){
		Optional<VagaEstacionamentoModel> vagaEstacionamentoModelOptional = vagaEstacionamentoService.findById(id);
		if (!vagaEstacionamentoModelOptional.isPresent()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga de Estacionamento não encontrado!");
		}
		return ResponseEntity.status(HttpStatus.OK).body(vagaEstacionamentoModelOptional.get());
	}
	
	
}
