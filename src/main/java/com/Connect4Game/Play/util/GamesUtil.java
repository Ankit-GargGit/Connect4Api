package com.Connect4Game.Play.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Connect4Game.Play.Domain.Games;
import com.Connect4Game.Play.Domain.Moves;
import com.Connect4Game.Play.Repo.GamesRepo;

@Component
public class GamesUtil {

	@Autowired
	GamesRepo gamesRepo;
	
	public int makeMove(Games game,String playBy,int col) {
		 if (!(0 <= col && col < 7)) {		      
		        return 0;
		      }
		
		char[][] grid=getMovesMatrix(game);
		 int lastTop=game.getLastTop();int lastCol=game.getLastCol();
		  for (int h = 5; h >= 0; h--) {
		        if (grid[h][col] == '.') {
		        	grid[lastTop = h][lastCol = col] = playBy.charAt(0);
		          break;
		        }
		      }
		   
		    Moves mv=new Moves();
			mv.setMatcol(col);
			mv.setMatrow(lastTop);
			mv.setGameid(game.getGameid());
			mv.setPlayby(playBy);
			List<Moves> al = game.getMoves();
			al.add(mv);
			game.setLastPlayed(playBy);
			game.setLastCol(lastCol);
			game.setLastTop(lastTop);
			game.setMoves(al);
			gamesRepo.saveAndFlush(game);
		return isWinningPlay(grid, lastCol, lastTop)?2:1;
		
	}
	
	public char[][] getMovesMatrix(Games game){
		char[][] grid=new char[6][7];
		for(int i=0;i<6;i++) {
			for(int j=0;j<7;j++) {
				grid[i][j]='.';
			}
		}
		List<Moves> moves = game.getMoves();
		for(Moves mv:moves) {
			grid[mv.getMatrow()][mv.getMatcol()]=mv.getPlayby().charAt(0);
		}
		return grid;
	}
	
	public String horizontal(char[][] grid,int lastTop) {
	    return new String(grid[lastTop]);
	  }

	  public String vertical(char[][] grid,int lastCol) {
	    StringBuilder sb = new StringBuilder(6);
	    for (int h = 0; h < 6; h++) {
	      sb.append(grid[h][lastCol]);
	    }
	    return sb.toString();
	  }	  
	  public String slashDiagonal(char[][] grid,int lastCol,int lastTop) {
	    StringBuilder sb = new StringBuilder(6);

	    for (int h = 0; h < 6; h++) {
	      int w = lastCol + lastTop - h;

	      if (0 <= w && w < 7) {
	        sb.append(grid[h][w]);
	      }
	    }

	    return sb.toString();
	  }	
	  public String backslashDiagonal(char[][] grid,int lastCol,int lastTop) {
	    StringBuilder sb = new StringBuilder(6);

	    for (int h = 0; h < 6; h++) {
	      int w = lastCol - lastTop + h;

	      if (0 <= w && w < 7) {
	        sb.append(grid[h][w]);
	      }
	    }

	    return sb.toString();
	  }	 
	  public static boolean contains(String str, String substring) {
	    return str.indexOf(substring) >= 0;
	  }
	  public boolean isWinningPlay(char[][] grid,int lastCol,int lastTop) {
		    if (lastCol == -1) {		    
		      return false;
		    }
		    char sym = grid[lastTop][lastCol];		   
		    String streak = String.format("%c%c%c%c", sym, sym, sym, sym);
		    return contains(horizontal(grid,lastTop), streak) || 
		           contains(vertical(grid,lastCol), streak) || 
		           contains(slashDiagonal(grid,lastCol,lastTop), streak) || 
		           contains(backslashDiagonal(grid,lastCol,lastTop), streak);
		  }

}
