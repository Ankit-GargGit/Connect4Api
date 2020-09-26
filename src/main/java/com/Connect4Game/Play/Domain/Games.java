package com.Connect4Game.Play.Domain;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.Data;

@Entity
@Data
public class Games {

	@Id
	@GeneratedValue
	private int gameid;
	
	private String lastPlayed;
	
	private int lastTop;
	
	private int lastCol;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="gameid",referencedColumnName="gameid")
	private List<Moves> moves;
}
