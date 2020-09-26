package com.Connect4Game.Play.Repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Connect4Game.Play.Domain.Moves;

public interface MovesRepo extends JpaRepository<Moves, Integer> {

	
	public List<Moves> findByGameid(int gameid);
}
