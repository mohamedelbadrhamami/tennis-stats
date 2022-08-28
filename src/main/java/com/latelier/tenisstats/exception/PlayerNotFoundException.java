package com.latelier.tenisstats.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(
	    value = HttpStatus.NOT_FOUND, 
	    reason = "Requested player does not exist"
	)
public class PlayerNotFoundException extends RuntimeException{

	private static final long serialVersionUID = 7057056199536464541L;
	
	public PlayerNotFoundException(String message) {
		super(message);
	}
}
