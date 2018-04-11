package employeeCheck;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import employeeCheck.connection.Action;
import employeeCheck.connection.Connector;
import employeeCheck.domain.Employee;
import employeeCheck.domain.SystemLog;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		String ID = null;

		CheckingMethodsForMain.switchChoiceCheck();

		// connector.saveNewPersonToDBemployee("A1", "B2", "3333333333", 0.0,
		// 200.0, Action.NEW_USER_CREATED);

		// connector.deleteOneEmployee("3333333333");
		// connector.saveToDBsystemLog("9901136948", Action.OUTCOME);
		//
		// connector.updateDBemployee("9812055656", 50.0, 40.0, Action.INCOME);

		// List<SystemLog> systemLog = connector.getSystemLog();
		// for(int i=0;i<systemLog.size();i++) {
		// System.out.println(systemLog.get(i).toString());
		// }

		// List<Employee>employees = connector.getEmployees();
		// for(int i=0;i<employees.size();i++) {
		// System.out.println(employees.get(i).toString());
		// }
		// System.out.print(connector.getAllEmployees());

		// TOTO PRIDAL ADAM
		// Skuska - toto napisal MAJO

		// CheckingMethodsForMain optionFromSwitchMenuString = new
		// CheckingMethodsForMain();

		// optionFromSwitchMenuString.switchChoiceCheck();

	}

	
	}

