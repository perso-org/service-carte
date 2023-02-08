package com.tfa.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Carte;

public interface CarteRepository extends JpaRepository<Carte, Long> {

	List<Carte> findByNumeroDetenteur(String numeroDetenteur);
	Carte findByNumeroCarte(String numeroCarte);
}
