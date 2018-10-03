package model;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataModel {
	private ObservableList<Employee> employeesList = FXCollections.observableArrayList();
	private Employee employee;
	private Scanner in;
	private PrintWriter out = null;
	private String firstName;
	private String lastName;
	private int room;
	private int startTime;
	private int endTime;
	private int workingTime;
	
	public void openFile(File txtFile) {
		if (txtFile != null) {
			employeesList.clear();
			try {
				in = new Scanner(txtFile);
				while (in.hasNext() && in.hasNextLine()) {
					firstName = in.next();
					lastName = in.next();
					room = in.nextInt();
					startTime = in.nextInt();
					endTime = in.nextInt();
					workingTime = endTime - startTime;
					addEmployeeToEmployeesList(firstName, lastName, room, startTime, endTime, workingTime);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				if (in != null)
					in.close();
			}
		}
	}
	
	public void report(File txtFileReport) {
		List<Employee> employeeSorted = Optional.ofNullable(employeesList)
                .map(List::stream)
                .orElseGet(Stream::empty)
                .collect(Collectors.toList());
		employeeSorted.sort(Comparator.comparingInt(Employee::getWorkingTime));
		if (txtFileReport != null) {
			try {
				out = new PrintWriter(txtFileReport);
				for(int i = 0; i < employeeSorted.size(); i++){
					out.printf("%s %s %d %d %d %d\r\n",
						employeeSorted.get(i).getFirstName(),
						employeeSorted.get(i).getLastName(),
						employeeSorted.get(i).getRoom(),
						employeeSorted.get(i).getStartTime(),
						employeeSorted.get(i).getEndTime(),
						employeeSorted.get(i).getWorkingTime());
					}
			}catch (IOException e) {
				e.printStackTrace();
			}finally{
				if (out!=null)
					out.close();
			}
		}
	}
	
	public void saveFile(File txtFile) {
		try {
			out = new PrintWriter(txtFile);
			for(int i = 0; i < employeesList.size(); i++){
				out.printf("%s %s %d %d %d\r\n",
						employeesList.get(i).getFirstName(),
						employeesList.get(i).getLastName(),
						employeesList.get(i).getRoom(),
						employeesList.get(i).getStartTime(),
						employeesList.get(i).getEndTime());
				}
		}catch (IOException e) {
			e.printStackTrace();
		}finally{
			if (out!=null)
				out.close();
		}
	}
	
	public void addEmployeeToEmployeesList(String firstNameAdd, String lastNameAdd,
			int roomAdd, int startTimeAdd, int endTimeAdd, int workingTimeAdd) {
		employee = new Employee(firstNameAdd, lastNameAdd, roomAdd, startTimeAdd, endTimeAdd, workingTimeAdd);
		employeesList.add(employee);
	}
	
	public ObservableList<Employee> getEmployeesList() {
		return employeesList;
	}
}