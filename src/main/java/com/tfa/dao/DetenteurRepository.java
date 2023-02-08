/**
 * 
 */
package com.tfa.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tfa.entite.Detenteur;

/**
 * @author fanny
 *
 */
public interface DetenteurRepository extends JpaRepository<Detenteur, Long> {

	Detenteur findByNumeroUnique(String numeroUnique);
}
