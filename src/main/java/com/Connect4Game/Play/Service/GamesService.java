package com.Connect4Game.Play.Service;

import java.util.List;

import com.Connect4Game.Play.Domain.Moves;
import com.Connect4Game.Play.Domain.MovesList;

public interface GamesService {

	public String startNewGame();
	
	public String makesMove(int gameid,int colid);

	public List<MovesList> getAllMoves(int gameid);
}
