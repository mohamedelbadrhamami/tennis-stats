package com.latelier.tenisstats.service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.latelier.tenisstats.model.Players;
import com.latelier.tenisstats.repository.PlayersRepository;

@Service
public class PlayersService {
	
	@Autowired
	private PlayersRepository playersRepository;
	
	/**
	 * @return - List de tous les joueurs
	 */
	public List<Players> getAllPlayers() {
		return playersRepository.getAllPlayers();
	}
	
	/**
	 * @return - Liste des joueurs trier du meilleur au moin bon
	 * Si j'ai bien compris le meilleur est le mieux classé (selon data.rank)
	 */
	public List<Players> trierDuMeilleurAuMoinBon() {
		List<Players> players=playersRepository.getAllPlayers();
		
		Collections.sort(players,new Comparator<Players>() {
			@Override
			public int compare(Players p1, Players p2) {
				return p1.getData().getRank().compareTo(p2.getData().getRank());
			}
		});
		
		return players;
	}
	
	/**
	 * Récupération des informations d’un joueur grâce à son ID 
	 * Utilison l'API Stream de Java 8
	 * @param PlayerId - id du joueur
	 * @return les informations d’un joueur grâce à son ID 
	 */
	public Optional<Players> getPlayerById(Long PlayerId) {
		
		return playersRepository.getAllPlayers().stream()
				.filter(p -> p.getId() == PlayerId)
				.findAny();
	}
	
	/**
	 * Récupération du pays qui a le plus grand ratio de parties gagnées
	 * @return Map - Country information
	 */
	public Map<String, String> getCountryByMaxRatio() {
		
		Map<Map<String, String>,Double> coutriesByRatio=getCountriesRatio();
		
		for (Map.Entry<Map<String, String>,Double> entry : coutriesByRatio.entrySet()) {
		     System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
		}
		
		Map<String, String> maxRatio=Collections.max(coutriesByRatio.entrySet(), Map.Entry.comparingByValue()).getKey();
		
		return maxRatio;
	}
	
	/**
	 * Calcule de l'IMC moyen de tous les joueurs
	 * @return Double - IMC value
	 */
	public double getIMCMoyenForAllPlayer() {
		
		return playersRepository.getAllPlayers().stream()
				.mapToDouble(p -> p.getIMC())
	            .average().getAsDouble();
	}
	/**
	 * Calcule de La médiane de la taille des joueurs
	 * @return Double - Mediane value
	 */
	public double getMedianeHeightOfPlayers() {
		
		List<Players> players=playersRepository.getAllPlayers();
		double mediane=0;
		int medianePos=0;
		//Si la taille de la liste est impair
		if(players.size()%2 == 1) {
			medianePos=(players.size()+1)/2;
			mediane=players.get(medianePos).getData().getHeight();
		}else {
			medianePos=players.size()/2;
			mediane=(players.get(medianePos).getData().getHeight()
					+players.get(medianePos+1).getData().getHeight()
					/2);
		}
		
		return mediane;
	}
		
	//Fonction qui calcule et retourne tous les pays par leurs ratio de parties gagnées
	private Map<Map<String, String>,Double> getCountriesRatio(){
		
		 Map<Map<String, String>,Double> map = playersRepository.getAllPlayers().stream()
				 .collect(Collectors.groupingBy(p -> p.getCountry(),
						 Collectors.averagingLong(p -> p.getData().getSumLast())));
		 return map;
	}
	
}
