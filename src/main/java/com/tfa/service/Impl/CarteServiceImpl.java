package com.tfa.service.Impl;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.tfa.dao.CarteRepository;
import com.tfa.dao.DetenteurRepository;
import com.tfa.dto.CarteDto;
import com.tfa.dto.DetenteurDto;
import com.tfa.entite.Carte;
import com.tfa.entite.Detenteur;
import com.tfa.service.CarteService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CarteServiceImpl implements CarteService {

	private final CarteRepository crepo;
	private final DetenteurRepository drepo;
	private final ModelMapper mapper;
	
	@Override
	public CarteDto creeCarte(CarteDto dto,String numeroDetenteur) {
		Detenteur  detenteur = drepo.findByNumeroUnique(numeroDetenteur);
		
		if(detenteur == null) {
			log.info("Il n'existe pas de détenteur avec ce nurmero");
			return null;
		}
		dto.setNumeroDetenteur(numeroDetenteur);
		dto.setNumeroCarte(numeroUnique(16));
		Carte toBeSaved = mapper.map(dto, Carte.class);
		return mapper.map(toBeSaved, CarteDto.class);
	}

	@Override
	public CarteDto modifieCarte(String numeroCarte, CarteDto dto) {
		Carte carte = crepo.findByNumeroCarte(numeroCarte);
		
		if(carte == null) {
			log.warn("Cette carte n'existe pas!");
			return dto;
		}
		carte.setLimiteCarte(dto.getLimiteCarte());
		carte.setMontantUtilise(dto.getMontantUtilise());
		carte.setNumeroDetenteur(dto.getNumeroDetenteur());
		carte.setSolde(dto.getSolde());
		carte.setTypeCarte(dto.getTypeCarte());
		Carte cc = crepo.save(carte);
		return mapper.map(cc, CarteDto.class);
	}

	private String numeroUnique(int taille) {
		while (true) {
			String numeroUnique = RandomStringUtils.randomNumeric(taille);
			Carte carte = crepo.findByNumeroCarte(numeroUnique);
			if (carte == null) {
				return numeroUnique;
			}
		}
	}

	@Override
	public CarteDto obtenirCarte(String numeroCarte) {
		Carte carte = crepo.findByNumeroCarte(numeroCarte);
		
		if(carte == null) {
			log.warn("Cette carte n'existe pas!");
			return null;
		}
		return mapper.map(carte, CarteDto.class);
	}

	@Override
	public List<CarteDto> obtenirCartes() {
		
		List<Carte> cartes = crepo.findAll();
		
		if(CollectionUtils.isEmpty(cartes)) {
			log.warn("Il n'existe pas de carte!");
			return Collections.emptyList();
		}
		
		return cartes.stream().map(carte -> mapper.map(carte, CarteDto.class)).toList();
	}

	@Override
	public List<CarteDto> obtenirCartesParDetenteur(String numeroDetenteur) {
		List<Carte> cartes = crepo.findByNumeroDetenteur(numeroDetenteur);
		
		if(CollectionUtils.isEmpty(cartes)) {
			log.warn("Il n'existe pas de carte!");
			return Collections.emptyList();
		}
		
		return cartes.stream().map(carte -> mapper.map(carte, CarteDto.class)).toList();
	}

	@Override
	public DetenteurDto obtenirDetenteur(String numeroCarte) {
		
		Carte carte = crepo.findByNumeroCarte(numeroCarte);
		if(carte == null) {
			log.warn("Cette carte est inexistante!");
			return null;
		}
		Detenteur detenteur = drepo.findByNumeroUnique(carte.getNumeroDetenteur());
		
		return mapper.map(detenteur, DetenteurDto.class);
	}

}
