package employeeCheck.domain;

import java.sql.Timestamp;

import employeeCheck.connection.Action;

public class Employee {
	private String name;
	private String surname;
	private String ID;
	private double hoursLeft;
	private Timestamp latestRecord;
	private double overtime;
	private Action latestAction;

	public Employee(String name, String surname, String ID, double hoursLeft, Timestamp latestRecord, double overtime,
			Action latestAction) {
		super();
		this.name = name;
		this.surname = surname;
		this.ID = ID;
		this.hoursLeft = hoursLeft;
		this.latestRecord = latestRecord;
		this.overtime = overtime;
		this.latestAction = latestAction;
	}

	public Employee() {
		super();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getID() {
		return ID;
	}

	public void setID(String ID) {
		this.ID = ID;
	}

	public double getHoursLeft() {
		return hoursLeft;
	}

	public void setHoursLeft(double hoursLeft) {
		this.hoursLeft = hoursLeft;
	}

	public Timestamp getLatestRecord() {
		return latestRecord;
	}

	public void setLatestRecord(Timestamp latestRecord) {
		this.latestRecord = latestRecord;
	}

	public double getOvertime() {
		return overtime;
	}

	public void setOvertime(double overtime) {
		this.overtime = overtime;
	}

	public Action getLatestAction() {
		return latestAction;
	}

	public void setLatestAction(Action latestAction) {
		this.latestAction = latestAction;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", surname=" + surname + ", ID=" + ID + ", hoursLeft=" + hoursLeft
				+ ", latestRecord=" + latestRecord + ", overtime=" + overtime + ", latestAction=" + latestAction + "]";
	}

}
