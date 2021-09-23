package com.revature.models;

public class Player {
	
	private int id;
	private String name;
	private boolean alwaysLate;
	
	public Player() {
		super();
	}

	public Player(int id, String name, boolean alwaysLate) {
		super();
		this.setId(id);
		this.setName(name);
		this.setAlwaysLate(alwaysLate);
	}

	public Player(String name, boolean alwaysLate) {
		super();
		this.setName(name);
		this.setAlwaysLate(alwaysLate);
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

	public boolean getAlwaysLate() {
		return alwaysLate;
	}

	public void setAlwaysLate(boolean alwaysLate) {
		this.alwaysLate = alwaysLate;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", name=" + name + ", alwaysLate=" + alwaysLate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (alwaysLate ? 1231 : 1237);
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (alwaysLate != other.alwaysLate)
			return false;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	

}
