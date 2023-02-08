/**
 * 
 */
package com.tfa.service;

import java.util.List;

import com.tfa.dto.CarteDto;
import com.tfa.dto.DetenteurDto;

/**
 * @author fanny
 *
 */
public interface CarteService {

	CarteDto creeCarte(CarteDto dto,String numeroDetenteur);
	CarteDto modifieCarte(String numeroCarte,CarteDto dto);
	List<CarteDto> obtenirCartes();
	List<CarteDto> obtenirCartesParDetenteur(String numeroDetenteur);
	CarteDto obtenirCarte(String numeroCarte);
	
	DetenteurDto obtenirDetenteur(String numeroCarte);
}
