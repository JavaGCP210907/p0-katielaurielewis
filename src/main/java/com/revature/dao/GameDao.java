package com.revature.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.*;

import com.revature.models.Game;
import com.revature.utils.ConnectionUtil;

public class GameDao implements GameDaoInterface {
	
	@Override
	public Game getGameById(int id) {
		List<Game> gameList = getGames();
		Game game = null;
		for(Game g: gameList) {
			if (g.getId() == id) {
				game = g;
			}
		}
		return game;
	}
	
	@Override
	public Game getGameByName(String name) {
		List<Game> gameList = getGames();
		Game game = null;
		for(Game g: gameList) {
			if (g.getName().equals(name)) {
				game = g;
			}
		}
		return game;
	}

	@Override
	public List<Game> getGames() {
		List<Game> gameList = new ArrayList<>();
			try(Connection c = ConnectionUtil.getConnection()) { 
					ResultSet rs = null;
					String query = "select * from games";
					Statement s = c.createStatement();
					rs = s.executeQuery(query);
					
					
					
					while(rs.next()) { 
						Game g = new Game(
								rs.getInt("game_id"),
								rs.getString("game_name"),
								rs.getFloat("game_rating")
								);
						
						gameList.add(g); 
					}
					
				} catch (SQLException e) {
					System.out.println("Failed to get list of games.");
					e.printStackTrace();
				}
				return gameList;
	}


	@Override
	public void addGame(Game game) {
		try (Connection c = new ConnectionUtil().getConnection()){
			
			String query = "insert into games (game_name, game_rating)" + "values (?, ?)";
			
			PreparedStatement prep = c.prepareStatement(query);
			
			//prep.setInt(1, game.getId());
			prep.setString(1, game.getName());
			prep.setFloat(2, game.getRating());
			
			prep.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println("Failed to add game.\n");
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeGame(String name) {
		try(Connection c = ConnectionUtil.getConnection()){
			
			String query = "delete from games where game_name = ?";
			
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setString(1, name);
			ps.executeUpdate();
			
			System.out.println("Successfully removed game: " + name + "\n");
			
		} catch (SQLException e) {
			System.out.println("Could not remove game.\n");
			e.printStackTrace();
		}
		
	}


	@Override
	public void updateGameRating(String name, float rating) {
		try(Connection c = ConnectionUtil.getConnection()){
			
			String query = "update games set game_rating = ? where game_name = ?";
			
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setFloat(1, rating);
			ps.setString(2, name);
			
			ps.executeUpdate();
			
			System.out.println("Successfully update rating of game: " + name + "\n");
			
		} catch (SQLException e) {
			System.out.println("Could not update game rating.\n");
			e.printStackTrace();
		}
	}


	@Override
	public Game randomGame() {
		List<Game> gameList = getGames();
		double d = Math.random();
		int i = (int) (d * gameList.size());
		return gameList.get(i);
	}

	

}
