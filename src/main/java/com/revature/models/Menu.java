package com.revature.models;

import java.util.List;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.GameDao;
import com.revature.dao.PlayerDao;
import com.revature.dao.GamePlayDao;

public class Menu {
	
	GameDao gDao = new GameDao();
	PlayerDao pDao = new PlayerDao();
	GamePlayDao gpDao = new GamePlayDao();
	Logger log = LogManager.getLogger(Menu.class);

	public void display() {
		
		Scanner s = new Scanner(System.in);
		boolean menuOn = true;
		
		while(menuOn) {
			
			System.out.println("Welcome to the board game winner tracker!\n");
			System.out.println("1. Board game menu");
			System.out.println("2. Player menu");
			System.out.println("3. Games tracker menu");
			System.out.println("4. Exit");

			String choice = s.nextLine();
			
			switch(choice){
				case("1"): {
					boolean gameMenu = true;
					
					while (gameMenu) {
						System.out.println("1. Add a board game");
						System.out.println("2. Remove a board game");
						System.out.println("3. Update a board game's rating.");
						System.out.println("4. List all board games");
						System.out.println("5. Suggest a random board game");
						System.out.println("6. Return to main menu");
						String gameChoice = s.nextLine();
						switch(gameChoice) {
						case("1"):{
							System.out.println("Enter the board game name: ");
							String name = s.nextLine();
							
							System.out.println("Enter the board game rating: ");
							float rating = s.nextFloat();
							s.nextLine();
							while (rating < 0 || rating > 10 ) {
								System.out.println("Please enter a rating between 0 and 10.");
								rating = s.nextFloat();
								s.nextLine();
							}
							Game game = new Game(name, rating);	
							gDao.addGame(game);
							System.out.println("Successfully added game.\n");
							log.info("User created game " + game.getId() + ".");
							break;
						} 
						case("2"):{
							System.out.println("Enter the name of the game you would like to delete:");
							String name = s.nextLine();
							List<Game> games = gDao.getGames();
							boolean gameExists = false;
							for (Game game : games) {
								if (game.getName().equals(name)) {
									gameExists = true;
								}
							}
							if (gameExists) {
								gDao.removeGame(name);
								log.warn("User deleted game " + name + ".");
							} else {
								System.out.println("Game does not exist in database.");
							}
							break;
						}
						case("3"):{
							System.out.println("Enter the name of the game you woud like to update the rating for:");
							String name = s.nextLine();
							List<Game> games = gDao.getGames();
							boolean gameExists = false;
							for (Game game : games) {
								if (game.getName().equals(name)) {
									gameExists = true;
								}
							} 
							if (gameExists) {
								System.out.println("Enter the new rating:");
								float rating = s.nextFloat();
								s.nextLine();
								while (rating < 0 || rating > 10 ) {
									System.out.println("Please enter a rating between 0 and 10.");
									rating = s.nextFloat();
									s.nextLine();
								} 
								gDao.updateGameRating(name, rating);
								log.info("User updated rating for game " + name + " to rating " + rating + ".");
								
							} else {
								System.out.println("Game does not exist in database.");
							}
							break;
						}
						case("4"):{
							List<Game> games = gDao.getGames();
							
							for(Game g : games) {
								System.out.println(g);
							}
							
							log.info("User retrieved list of games.");
							
							break;
						}
						case("5"):{
							Game game = gDao.randomGame();
							System.out.println(game);
							log.info("User retrieved random game " + game.getName() + ".");
							break;
						}
						case("6"):{
							gameMenu = false;
							log.info("User returned to main menu from board game menu.");
							break;
						}
						default:{
							System.out.println("Please enter a valid option.\n");
						}
						}
					}
					break;
				}
				case("2"): {
					boolean playerMenu = true;
					while (playerMenu) {
						System.out.println("1. Add a player");
						System.out.println("2. Remove a player");
						System.out.println("3. List all players");
						System.out.println("4. Return to main menu");
						String gameChoice = s.nextLine();
						switch(gameChoice) {
						case("1"):{
							System.out.println("Enter the player's name:");
							String name = s.nextLine();
							
							System.out.println("This player late to arrive every single time you plan something (true/false):");
							String late = s.nextLine();
							while (!late.equals("true") && !late.equals("false")) {
								System.out.println("Please enter true or false.");
								late = s.nextLine();
							}
							boolean lateBoolean = Boolean.parseBoolean(late);
							Player player = new Player(name, lateBoolean);	
							pDao.addPlayer(player);
							System.out.println("Successfully added player.\n");
							log.info("User added new player " + player.getId() + ".");
							break;
						} 
						case("2"):{
							System.out.println("Enter the name of the player you would like to delete:");
							String name = s.nextLine();
							List<Player> players = pDao.getPlayers();
							boolean playerExists = false;
							for (Player player : players) {
								if (player.getName().equals(name)) {
									playerExists = true;
								}
							}
							if (playerExists) {
								pDao.removePlayer(name);
								log.warn("User deleted player " + name + ".");
							} else {
								System.out.println("Player does not exist in database.\n");
							}
							break;
						}
						case("3"):{
							List<Player> players = pDao.getPlayers();
							
							for(Player p : players) {
								System.out.println(p);
							}
							log.info("User retrieved list of players.");
							break;
						}
						case("4"):{
							playerMenu = false;
							log.info("User returned to main menu from player menu.");
							break;
						}
						default:{
							System.out.println("Please enter a valid option.\n");
						}
						}
						}
					break;
				}
				case("3"): {
					boolean trackerMenu = true;
					while (trackerMenu) {
						System.out.println("1. Add game played to the tracker");
						System.out.println("2. Remove a game played from the tracker");
						System.out.println("3. List all the games played");
						System.out.println("4. Return to main menu");
						String gameChoice = s.nextLine();
						switch(gameChoice) {
						case("1"):{
							System.out.println("Enter the name of the game played:");
							String name = s.nextLine();
							Game game = gDao.getGameByName(name);
							System.out.println("Enter the name of the player who won:");
							String player = s.nextLine();
							Player winner = pDao.getPlayerByName(player);
							GamePlay gamePlay = new GamePlay(game, winner);	
							System.out.println(gamePlay);
							gpDao.addGamePlay(gamePlay);
							System.out.println("Successfully added game play.\n");
							log.info("User added new game play " + gamePlay.getId() + ".");
							break;
						} 
						case("2"):{
							System.out.println("Enter the id of the game play record you would like to delete:");
							int id = s.nextInt();
							s.nextLine();
							List<GamePlay> gamePlays = gpDao.getGamePlays();
							boolean gamePlayExists = false;
							for (GamePlay gamePlay : gamePlays) {
								if (gamePlay.getId() == id) {
									gamePlayExists = true;
								}
							}
							if (gamePlayExists) {
								gpDao.removeGamePlay(id);
								log.warn("User deleted game play " + id + " .");
							} else {
								System.out.println("Player does not exist in database.\n");
							}
							
							break;
						}
						case("3"):{
							List<GamePlay> gamePlays = gpDao.getGamePlays();
							
							for(GamePlay gp : gamePlays) {
								System.out.println(gp);
							}
							log.info("User retrieved list of game plays.");
							break;
						}
						case("4"):{
							trackerMenu = false;
							log.info("User returned to main menu from game tracker menu.");
							break;
						}
						default:{
							System.out.println("Please enter a valid option.\n");
						}
						}
						}
					break;
				}
				case("4"): {
					System.out.println("Goodbye.\n");
					log.info("User exiting program.");
					menuOn = false;
					break;
				}
				default:{
					System.out.println("Please enter a valid option.\n");
				}
			}
		}
		s.close();
		
	}

}
