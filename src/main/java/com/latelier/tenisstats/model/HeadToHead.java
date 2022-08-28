package com.latelier.tenisstats.model;

import java.util.List;
import lombok.Data;

@Data
public class HeadToHead {
	private List<Players> players;

	@Override
	public String toString() {
		return "HeadToHead [players=" + players + "]";
	}
	
}
