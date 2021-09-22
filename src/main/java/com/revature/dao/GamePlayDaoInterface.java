package com.revature.dao;

import java.util.List;

import com.revature.models.Employee;

public interface GamePlayDaoInterface {
	
public List<Employee> getEmployees(); //returns a List of all employees (select *)
	
	public List<Employee> getEmployeesByRoleTitle(String title); //this will get employees with a certain role (select where)
	
	public List<Employee> getEmployeeById(int id); //return an employee given their id
	
	public void addEmployee(Employee employee); //this will add an employee to the database (insert)
	
	public void removeEmployee(int id); //this will remove employees using their id (delete)

}
