package com.Connect4Game.Play.ServiceImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Connect4Game.Play.Domain.Games;
import com.Connect4Game.Play.Domain.Moves;
import com.Connect4Game.Play.Domain.MovesList;
import com.Connect4Game.Play.Repo.GamesRepo;
import com.Connect4Game.Play.Repo.MovesRepo;
import com.Connect4Game.Play.Service.GamesService;
import com.Connect4Game.Play.util.GamesUtil;

@Service
public class GamesServiceImpl implements GamesService {

	@Autowired
	GamesRepo gamesRepo;
	
	@Autowired
	GamesUtil gamesUtil;
	
	@Autowired
	MovesRepo movesRepo;
	
	@Override
	public String startNewGame() {
		Games game=new Games();
		game.setLastCol(-1);
		game.setLastTop(-1);
		game.setMoves(new ArrayList<Moves>());
		// TODO Auto-generated method stub
		game=gamesRepo.saveAndFlush(game);
		return "READY and game token="+game.getGameid();
	}

	@Override
	public String makesMove(int gameid,int colid) {
		// TODO Auto-generated method stub
		Games game = gamesRepo.findById(gameid).get();
		String  playBy=game.getLastPlayed()==null?"Y":(game.getLastPlayed()=="Y"?"R":"Y");
		int res = gamesUtil.makeMove(game, playBy, colid);
		if(res==0)
			return "Invalid";
		else if(res==1)
		 return "Valid";
		else
			return playBy=="Y"?"Yellow wins":"Red wins";
	}

	@Override
	public List<MovesList> getAllMoves(int gameid) {
		
		// TODO Auto-generated method stub
		List<Moves> mvs = movesRepo.findByGameid(gameid);
		List<MovesList> mvList=new ArrayList<MovesList>();
		for(Moves mv:mvs) {
			MovesList ml=new MovesList();
			ml.setGameToken(mv.getGameid());
			ml.setColumn(mv.getMatcol());
			ml.setRow(mv.getMatrow());
			ml.setPlayer(mv.getPlayby());
			mvList.add(ml);
		}
		return mvList;
	}

}
