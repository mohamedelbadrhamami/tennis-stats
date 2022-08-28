package com.latelier.tenisstats.model;

import java.util.Map;

@lombok.Data
public class Players {
	private Long id;
	private String firstname;
	private String lastname;
	private String shortname;
	private String sex;
	private Map<String, String> country;
	private String picture;
	private Data data;
	
	/**
	 * Calcule de l'IMC d'un joueur
	 * @return IMC d'un joueur
	 */
	public double getIMC() {
		return ((double)data.getWeight()/(double)(data.getHeight()*data.getHeight()))*10;
	}
	
}
