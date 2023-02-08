package com.tfa.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.tfa.dto.CarteDto;
import com.tfa.dto.DetenteurDto;
import com.tfa.service.CarteService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/cartes")
public class CarteController {

	private final CarteService service;

	@PostMapping("creation/{numeroDetenteur}")
	@ResponseStatus(value = HttpStatus.CREATED)
	public ResponseEntity<CarteDto> creeCarte(@RequestBody CarteDto dto,@PathVariable String numeroDetenteur) {

		CarteDto carteDto = service.creeCarte(dto, numeroDetenteur);
		
		return ResponseEntity.ok(carteDto);
	}

	@PutMapping("up/{numeroCarte}")
	public ResponseEntity<CarteDto> modifieCarte(@PathVariable String numeroCarte,@RequestBody CarteDto dto) {

		CarteDto carteDto = service.modifieCarte(numeroCarte, dto);
		
		return ResponseEntity.ok(carteDto);
	}

	@GetMapping
	public ResponseEntity<List<CarteDto>> obtenirCartes() {

		List<CarteDto> dtos = service.obtenirCartes();
		
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("{numeroDetenteur}")
	public ResponseEntity<List<CarteDto>> obtenirCartesParDetenteur(@PathVariable String numeroDetenteur) {

		List<CarteDto> dtos = service.obtenirCartesParDetenteur(numeroDetenteur);
		
		return ResponseEntity.ok(dtos);
	}

	@GetMapping("carte/{numeroCarte}")
	public ResponseEntity<CarteDto> obtenirCarte(@PathVariable String numeroCarte) {

		CarteDto dto = service.obtenirCarte(numeroCarte);
		return ResponseEntity.ok(dto);
	}

	@GetMapping("detenteur/{numeroCarte}")
	public ResponseEntity<DetenteurDto> obtenirDetenteur(@PathVariable String numeroCarte) {
		
		DetenteurDto dto = service.obtenirDetenteur(numeroCarte);
		
		return ResponseEntity.ok(dto);
	}

}
