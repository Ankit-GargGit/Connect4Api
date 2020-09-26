package com.Connect4Game.Play.Domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class Moves {

	@Id
	@GeneratedValue
	private int id;
	
	private int gameid;
	
	private int matcol;
	
	private int matrow;
	
	private String playby;
	
	
}
