package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Game;
import com.revature.models.Player;
import com.revature.utils.ConnectionUtil;

public class PlayerDao implements PlayerDaoInterface {
	
	@Override
	public Player getPlayerById(int id) {
		List<Player> playerList = getPlayers();
		Player player = null;
		for(Player p : playerList) {
			if (p.getId() == id) {
				player = p;
			}
		}
		return player;
	}
	
	@Override
	public Player getPlayerByName(String name) {
		List<Player> playerList = getPlayers();
		Player player = null;
		for(Player p : playerList) {
			if (p.getName().equals(name)) {
				player = p;
			}
		}
		return player;
	}

	@Override
	public List<Player> getPlayers() {
		List<Player> playerList = new ArrayList<>();
		try(Connection c = ConnectionUtil.getConnection()) { 
				ResultSet rs = null;
				String query = "select * from players";
				Statement s = c.createStatement();
				rs = s.executeQuery(query);
				
				
				
				while(rs.next()) { 
					Player p= new Player(
							rs.getInt("player_id"),
							rs.getString("player_name"),
							rs.getBoolean("always_late")
							);
					
					playerList.add(p); 
				}
				
			} catch (SQLException e) {
				System.out.println("Failed to get list of players.");
				e.printStackTrace();
			}
			return playerList;
	}

	@Override
	public void addPlayer(Player player) {
		try (Connection c = new ConnectionUtil().getConnection()){
			
			String query = "insert into players (player_name, always_late)" + "values (?, ?)";
			
			PreparedStatement prep = c.prepareStatement(query);
			
			prep.setString(1, player.getName());
			prep.setBoolean(2, player.getAlwaysLate());
			
			prep.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println("Failed to add player.\n");
			e.printStackTrace();
		}
		
	}

	@Override
	public void removePlayer(String name) {
		try(Connection c = ConnectionUtil.getConnection()){
			
			String query = "delete from players where player_name = ?";
			
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setString(1, name);
			ps.executeUpdate();
			
			System.out.println("Successfully removed player: " + name + "\n");
			
		} catch (SQLException e) {
			System.out.println("Could not remove player.\n");
			e.printStackTrace();
		}
		
	}


}
