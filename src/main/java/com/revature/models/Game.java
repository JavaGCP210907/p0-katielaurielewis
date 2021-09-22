package com.revature.models;

public class Game {
	
	private int id;
	private String name;
	private float rating;
	
	
	
	public Game() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Game(int id, String name, float rating) {
		super();
		this.id = id;
		this.name = name;
		this.rating = rating;
	}
	
	public Game(String name, float rating) {
		super();
		this.name = name;
		this.rating = rating;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		if (rating < 0.0 || rating > 10.0) {
			System.out.println("Invalid rating.");
		} else {
			this.rating = rating;
		}
	}
	
	@Override
	public String toString() {
		return "Game [game_id = " + id + ", game_name = " + name + ", rating = " + rating + "]";
	}


}
