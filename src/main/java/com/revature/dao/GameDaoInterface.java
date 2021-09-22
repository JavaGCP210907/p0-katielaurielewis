package com.revature.dao;

import java.util.List;

import com.revature.models.Game;

public interface GameDaoInterface {
	public List<Game> getGames(); 
	
	public List<Game> getGameById(int id); 
	
	public void addGame(Game game); 
	
	public void removeGame(String name); 
}
