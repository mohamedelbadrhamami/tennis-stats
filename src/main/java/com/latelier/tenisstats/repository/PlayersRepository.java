package com.latelier.tenisstats.repository;

import java.io.InputStream;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latelier.tenisstats.model.HeadToHead;
import com.latelier.tenisstats.model.Players;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class PlayersRepository {
	//Singleton qui va contenir les données des joueurs 
	//récupéré depuis le fichier JSON
	public static List<Players> allPlayers = null;
	private final String PLAYERS_JSON="/data/headtohead.json";
	
	public List<Players> getAllPlayers() {
		if(allPlayers==null) {
			getAllPlayersFromJsonFile();
		}
		return allPlayers;
	}
  
	/**
	 * Récupération des données JSON depuis le fichier headtohead.json
	 */
	private void getAllPlayersFromJsonFile(){
		log.info("Lecture du Fichier JSON : "+PLAYERS_JSON);
		//create ObjectMapper instance
	    ObjectMapper objectMapper = new ObjectMapper();
  
	    //Lecture du fichier json pour le convertir en une liste de Players
		try {
			 InputStream inputStream=TypeReference.class.getResourceAsStream(PLAYERS_JSON);
			 HeadToHead headToHead = objectMapper.readValue(inputStream, HeadToHead.class);
			 allPlayers=headToHead.getPlayers();
			 log.info("Données chargées avec succès ...");
		} catch (Exception e) {
			e.printStackTrace();
		}
	      
	}

	
}
