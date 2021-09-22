package com.revature.models;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.GameDao;

public class Menu {
	
	GameDao gDao = new GameDao();

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
							break;
						} 
						case("2"):{
							System.out.println("Enter the name of the game you would like to delete.\n");
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
							} if (gameExists) {
								System.out.println("Please enter the new rating:");
								float rating = s.nextFloat();
								s.nextLine();
								while (rating < 0 || rating > 10 ) {
									System.out.println("Please enter a rating between 0 and 10.");
									rating = s.nextFloat();
									s.nextLine();
								} 
								
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
							
							break;
						}
						case("5"):{
							break;
						}
						case("6"):{
							gameMenu = false;
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
							break;
						} 
						case("2"):{
							break;
						}
						case("3"):{
							break;
						}
						case("4"):{
							playerMenu = false;
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
						System.out.println("2. List all the games played");
						System.out.println("3. List all players by number of wins");
						System.out.println("4. Return to main menu");
						String gameChoice = s.nextLine();
						switch(gameChoice) {
						case("1"):{
							break;
						} 
						case("2"):{
							break;
						}
						case("3"):{
							break;
						}
						case("4"):{
							trackerMenu = false;
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
