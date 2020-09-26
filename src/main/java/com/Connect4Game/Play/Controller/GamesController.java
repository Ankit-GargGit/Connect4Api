package com.Connect4Game.Play.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Connect4Game.Play.Domain.MovesList;
import com.Connect4Game.Play.Service.GamesService;

@RestController
public class GamesController {

	@Autowired
	GamesService gameService;
	
	@RequestMapping(value="/START",method = RequestMethod.GET)
	public String startNewGame() {
		return gameService.startNewGame();
	}
	
	@RequestMapping(value="move/{gametoken}/{colid}",method = RequestMethod.GET)
	public String makeMove(@PathVariable("gametoken") int gameid,@PathVariable("colid") int colid) {
		return gameService.makesMove(gameid,colid);
	}
	
	@RequestMapping(value="getAllMoves/{gametoken}",method = RequestMethod.GET)
	public List<MovesList> makeMove(@PathVariable("gametoken") int gameid) {
		return gameService.getAllMoves(gameid);
	}
}
