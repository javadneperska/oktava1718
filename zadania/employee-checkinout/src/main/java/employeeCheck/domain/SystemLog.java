package employeeCheck.domain;

import java.sql.Timestamp;

import employeeCheck.connection.Action;

public class SystemLog {
	private String ID;
	private Action action;
	private Timestamp time;

	public SystemLog(String iD, Action action, Timestamp time) {
		super();
		ID = iD;
		this.action = action;
		this.time = time;
	}

	public SystemLog() {
		super();
	}

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public Timestamp getTime() {
		return time;
	}

	public void setTime(Timestamp time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SystemLog [ID=" + ID + ", action=" + action + ", time=" + time + "]";
	}

}
