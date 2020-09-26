package com.Connect4Game.Play.Domain;

import lombok.Data;

@Data
public class MovesList {

	private int gameToken;
	
	private int column;
	
	private int row;
	
	private String player;
	
	
}
