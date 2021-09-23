package com.revature.dao;

import java.util.List;

import com.revature.models.GamePlay;

public interface GamePlayDaoInterface {
	
	public List<GamePlay> getGamePlays();
		
	public void addGamePlay(GamePlay gamePlay); 
	
	public void removeGamePlay(int id); 

}
