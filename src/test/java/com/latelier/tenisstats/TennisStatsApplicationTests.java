package com.latelier.tenisstats;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.latelier.tenisstats.model.Players;
import com.latelier.tenisstats.repository.PlayersRepository;
import com.latelier.tenisstats.service.PlayersService;

@SpringBootTest
class TennisStatsApplicationTests {
	
	@Autowired
	private PlayersService playersService;
	
	@Autowired
	private PlayersRepository playersRepository;
	
	@Test
	void contextLoads() {
	}
	
   @Test
    public void testDependencyInjection() {
        Assertions.assertThat(playersService).isNotNull();
        Assertions.assertThat(playersRepository).isNotNull();
    }
	   
	@Test
	public void testInitialize() {
		List<Players> players = playersService.getAllPlayers();
		 Assertions.assertThat(players).isNotNull().hasSize(5);
	}
}
