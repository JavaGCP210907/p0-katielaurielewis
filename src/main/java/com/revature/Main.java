package com.revature;
import java.sql.Connection;
import java.sql.SQLException;
import com.revature.models.Menu;
import com.revature.utils.ConnectionUtil;

public class Main {

	public static void main(String[] args) {
		try(Connection c = ConnectionUtil.getConnection()){
			System.out.println("Connection to database successfully established. \n");
		} catch (SQLException e) {
			System.out.println("Connection to database could not be established. \n");
			e.printStackTrace();
		}
		
		Menu menu = new Menu();
		
		menu.display();
	}

}
