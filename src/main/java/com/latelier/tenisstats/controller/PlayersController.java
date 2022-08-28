package com.latelier.tenisstats.controller;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.latelier.tenisstats.exception.PlayerNotFoundException;
import com.latelier.tenisstats.model.Players;
import com.latelier.tenisstats.service.PlayersService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/latelier")
public class PlayersController {

	@Autowired
	private PlayersService playersService;
	
	/**
	 * Read - Get all players order by data.rank
	 * @return - An Iterable object of Players full filled
	 */
	@GetMapping("/playersOrdredByRank")
	public Iterable<Players> getPlayersOrdredPlayers(){
		log.info("PlayersController - getPlayersOrdredPlayers");
		return playersService.trierDuMeilleurAuMoinBon();
	}
	
	/**
	 * Read - Get one player 
	 * @param id - The id of the player
	 * @return A Player object full filled
	 */
	@GetMapping("/player/{id}")
	public Players getPlayerById(@PathVariable("id") final Long playerId) {
		log.info("PlayersController - getPlayerById ("+playerId+")");
  
        Optional<Players> player = playersService.getPlayerById(playerId);
        log.info("player"+player);
        if(!player.isPresent()) {
        	log.warn("Player not Found with id "+playerId);
        	throw new PlayerNotFoundException("Player not Found with id "+playerId);
        }else {
        	return player.get();
        }
	}
	
	/**
	 * Read - Get Country with the highest win ratio
	 * @return - A Map object of Country full filled
	 */
	@GetMapping("/countryByMaxRatio")
	public Map<String, String> getCountryByMaxRatio(){
		log.info("PlayersController - getCountryByMaxRatio");
		return playersService.getCountryByMaxRatio();
	}
	
	/**
	 * Read - Get Average BMI of all players
	 * @return Double - IMC value
	 */
	@GetMapping("/imcAverage")
	public Double getIMCMoyenForAllPlayer() {
		log.info("PlayersController - getIMCMoyenForAllPlayer");
		return playersService.getIMCMoyenForAllPlayer();
	}
	
	/**
	 * Read - Get The median player height
	 * @return Double - Mediane value
	 */
	@GetMapping("/mediane")
	public Double getMedianeHeightOfPlayers() {
		log.info("PlayersController - getMedianeHeightOfPlayers");
		return playersService.getMedianeHeightOfPlayers();
	}
}
