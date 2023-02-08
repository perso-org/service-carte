package com.tfa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CarteDto {

	private Long id;
	private String numeroCarte;
	private String numeroDetenteur; 
	private String typeCarte;
	private double limiteCarte;
	private double solde;
	private double montantUtilise;
}
