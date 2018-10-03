package model;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Employee {
	
	private String firstName;
	private String lastName;
	private int room;
	private int startTime;
	private int endTime;
	private int workingTime;
	private ObservableList<Employee> employeeList = FXCollections.observableArrayList();
	private Employee employee;
	private Scanner in;
	

	public Employee(String firstName, String lastName, int room, int startTime, int endTime, int workingTime) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.room = room;
		this.startTime = startTime;
		this.endTime = endTime;
		this.workingTime = workingTime;
	}

	public int getWorkingTime() {
		return workingTime;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public int getRoom() {
		return room;
	}

	public int getStartTime() {
		return startTime;
	}

	public int getEndTime() {
		return endTime;
	}
	
	public void openFile(File txtFile) {
		if (txtFile != null) {
			employeeList.clear();
			try {
				in = new Scanner(txtFile);
				while (in.hasNext() && in.hasNextLine()) {
					firstName = in.next();
					lastName = in.next();
					room = in.nextInt();
					startTime = in.nextInt();
					endTime = in.nextInt();
					workingTime = endTime - startTime;
					addEmployeeToEmployeeList(firstName, lastName, room, startTime, endTime, workingTime);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close();
			}
		}
	}
	
	public void addEmployeeToEmployeeList(String firstName2, String lastName2, int room2, int startTime2, int endTime2, int workingTime2) {
		employee = new Employee(firstName, lastName, room, startTime, endTime, workingTime);
		employeeList.add(employee);
	}
	public ObservableList<Employee> getEmployeeList() {
		return employeeList;
	}
}