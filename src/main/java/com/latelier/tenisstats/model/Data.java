package com.latelier.tenisstats.model;

import java.util.Arrays;

@lombok.Data
public class Data {
	private Long rank;
	private Long points;
	private Long weight;
	private Long height;
	private Long age;
	private int[] last;

	/**
	 * Calcul du Nombre de partie gagnées 
	 * @return La somme du tableau last[]
	 */
	public int getSumLast() {
		return Arrays.stream(this.last).sum();
	}
	
}
