package com.revature.dao;

import java.util.List;

import com.revature.models.Player;

public interface PlayerDaoInterface {
	
	public Player getPlayerById(int id);
	
	public Player getPlayerByName(String name);
	
	public List<Player> getPlayers(); 
	
	public void addPlayer(Player player); 
	
	public void removePlayer(String name); 

}
