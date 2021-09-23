package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import com.revature.models.GamePlay;
import com.revature.models.Player;
import com.revature.models.Game;
import com.revature.utils.ConnectionUtil;

public class GamePlayDao implements GamePlayDaoInterface {

	@Override
	public List<GamePlay> getGamePlays() {
		List<GamePlay> gamePlaysList = new ArrayList<>();
		GameDao gDao = new GameDao();
		PlayerDao pDao = new PlayerDao();
		
		try(Connection c = ConnectionUtil.getConnection()) { 
				ResultSet rs = null;
				String query = "select * from game_plays";
				Statement s = c.createStatement();
				rs = s.executeQuery(query);
				
				
				while(rs.next()) { 
					int id = rs.getInt("play_id");
					int game_id = rs.getInt("play_game");
					int player_id = rs.getInt("play_winner");
					Date date = rs.getDate("play_date");
					Game game = gDao.getGameById(game_id);
					Player winner = pDao.getPlayerById(player_id);
					GamePlay gp = new GamePlay(id, game, winner, date);
					
					gamePlaysList.add(gp); 
				}
				
			} catch (SQLException e) {
				System.out.println("Failed to get list of games played.");
				e.printStackTrace();
			}
			return gamePlaysList;
	}

	@Override
	public void addGamePlay(GamePlay gamePlay) {
		try (Connection c = new ConnectionUtil().getConnection()){
			
			String query = "insert into game_plays (play_game, play_winner)" + "values (?, ?)";
			
			PreparedStatement prep = c.prepareStatement(query);
			
			prep.setInt(1, gamePlay.getGame().getId());
			prep.setInt(2, gamePlay.getWinner().getId());
			
			prep.executeUpdate();
	
		} catch (SQLException e) {
			System.out.println("Failed to add game played.\n");
			e.printStackTrace();
		}
		
	}

	@Override
	public void removeGamePlay(int id){
		try(Connection c = ConnectionUtil.getConnection()){
			
			String query = "delete from game_plays where play_id = ?";
			
			PreparedStatement ps = c.prepareStatement(query);
			
			ps.setInt(1, id);
			ps.executeUpdate();
			
			System.out.println("Successfully removed game play: " + id + "\n");
			
		} catch (SQLException e) {
			System.out.println("Could not remove game play.\n");
			e.printStackTrace();
		}
		
	}

		
}
