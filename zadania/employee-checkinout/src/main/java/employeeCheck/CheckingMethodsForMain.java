package employeeCheck;

import employeeCheck.connection.Action;
import employeeCheck.connection.Connector;

import java.util.Scanner;

public class CheckingMethodsForMain {

    public CheckingMethodsForMain() {
        super();
    }

    public static void switchChoiceCheck() {
        String optionFromSwitchMenuString = null;
        int optionFromSwitchMenuInt = 0;
        int exit = 0;
        Scanner sc = new Scanner(System.in);

        do {

            System.out.println("MENU\n*******\n1.Add new employee\n" + "2.Update employee\n" + "3.Delete employee\n"
                    + "4.Action\n5.Show all employees\n6.Show Log\n7.Show specific employee");
            System.out.println("Select your Choice");
            optionFromSwitchMenuString = sc.nextLine();
            try {
                optionFromSwitchMenuInt = Integer.parseInt(optionFromSwitchMenuString);
            } catch (NumberFormatException e) {
                System.out.println("Wrong input! Please try again");
            } finally {
                switchMethod(optionFromSwitchMenuInt);

            }
        } while (exit != 1);
    }

    private static void switchMethod(int choice) {
        Connector connector = new Connector();
        Scanner sc = new Scanner(System.in);
        String id;
        switch (choice) {

            case 1:
                System.out.println("Enter name");
                String name = sc.next();
                System.out.println("Enter surname");
                String surname = sc.next();
                System.out.println("Enter identification number");
                id = sc.next();
                connector.saveNewPersonToDBemployee(name, surname, id, 200.0);
                connector.saveToDBsystemLog(id, Action.NEW_USER_CREATED);
                break;
            case 2:
                System.out.println(
                        "Select what you want to update\n**********************\n1.Update Overtime\n2.Update name & surname\n3.Update hours left & Action");
                switch (sc.nextInt()) {
                    case 1:
                        System.out.println("Enter ID of employee");
                        id = sc.next();
                        System.out.println("Enter new Overtime");
                        double overtime = sc.nextDouble();
                        connector.updateDBemployee(id, overtime);
                        connector.saveToDBsystemLog(id, Action.ELSE);
                        break;
                    case 2:
                        System.out.println("Enter ID of employee");
                        id = sc.next();
                        System.out.println("Enter new Name");
                        name = sc.next();
                        System.out.println("Enter new Surname");
                        surname = sc.next();
                        connector.updateDBemployee(id, name, surname);
                        connector.saveToDBsystemLog(id, Action.ELSE);
                        break;
                    case 3:
                        System.out.println("Enter ID of employee");
                        id = sc.next();
                        System.out.println("Enter updated 'hours left'");
                        double hoursLeft = sc.nextDouble();
                        System.out.println("Enter new action:\n-INCOME\n-OUTCOME\n-NEW_USER_CREATED\n-ELSE");
                        String actionChoice = sc.next();
                        connector.updateDBemployee(id, hoursLeft, Action.valueOf(actionChoice));
                        connector.saveToDBsystemLog(id, Action.ELSE);
                        break;
                    default:
                        System.out.println("Coška plano");
                        break;
                }
                break;

            case 3:
                System.out.println("Enter ID of employee");
                id = sc.next();
                connector.deleteOneEmployee(id);
                break;
            case 4:
                System.out.println("Your ID");
                id = sc.next();
                System.out.println("-INCOME\n-OUTCOME");
                String actionChoice = sc.next();
                connector.saveToDBsystemLog(id, Action.valueOf(actionChoice));
                break;
            case 5:
                System.out.println(connector.getAllEmployees().toString());
                break;
            case 6:
                System.out.println(connector.getSystemLog().toString());
                break;
            case 7:
                System.out.println("Enter ID of employee");
                id = sc.next();
                System.out.println(connector.getOneEmployee(id).toString());
                break;
            default:
                System.out.println("Coška plano");
                break;
        }

    }
}
