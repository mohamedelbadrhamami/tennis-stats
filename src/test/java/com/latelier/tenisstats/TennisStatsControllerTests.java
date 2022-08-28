package com.latelier.tenisstats;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class TennisStatsControllerTests {
	 @Autowired
	 private MockMvc mockMvc;
	 
	 /**
	 * Test après le trie de la liste
	 * le premier joueur doit avoir le meilleur classement (data.rank = 1)
	 */
	 @Test
	 public void testGetPlayersOrdredPlayers() throws Exception {
		 mockMvc.perform(get("/api/latelier/playersOrdredByRank"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("$[0].data.rank", is(1)));
		 
	 }
	 
	 /**
	 * Test get player by id
	 * Cas Exists, id = 17, lastname must be Nadal
	 */
	 @Test
	 public void testGetPlayer() throws Exception {
		 mockMvc.perform(get("/api/latelier/player/17"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("lastname", is("Nadal")));
	 }
	 
	 /**
	 * Test get player by id
	 * Cas not Exists, id = 10, PlayerNotFoundException
	 */
	 @Test
	 public void testGetPlayerNotExists() throws Exception {
		 mockMvc.perform(get("/api/latelier/player/10"))
         .andExpect(status().isNotFound());
	 }
	 
	 /**
	 * Test Get Country with the highest win ratio
	 * Result must be SRB
	 */
	 @Test
	 public void testGetCountryByMaxRatio() throws Exception {
		 mockMvc.perform(get("/api/latelier/countryByMaxRatio"))
         .andExpect(status().isOk())
         .andExpect(jsonPath("code", is("SRB")));
	 }
	 
}
