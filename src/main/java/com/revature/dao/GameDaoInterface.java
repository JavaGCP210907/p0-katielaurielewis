package com.revature.dao;

import java.util.List;

import com.revature.models.Game;

public interface GameDaoInterface {
	
	public Game getGameByName(String name);
	
	public Game getGameById(int id);
	
	public List<Game> getGames(); 
	
	public void updateGameRating(String name, float rating); 
	
	public Game randomGame();
	
	public void addGame(Game game); 
	
	public void removeGame(String name); 
}
