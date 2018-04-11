package employeeCheck.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import employeeCheck.domain.Employee;
import employeeCheck.domain.SystemLog;

public class Connector {
	// JDBC driver name and database URL
	private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private String db_url = "jdbc:mysql://den1.mysql3.gear.host:3306/";
	private String db_name = "employeecheck1";

	// Database credentials
	private String username = "employeecheck1";
	private String password = "javaDneperska123*";

	public Connector() {
		super();
	}

	public String getDb_name() {
		return db_name;
	}

	public void setDb_name(String db_name) {
		this.db_name = db_name;
	}

	public String getDB_URL() {
		return db_url;
	}

	public String getUSERNAME() {
		return username;
	}

	public String getPASSWORD() {
		return password;
	}

	private static java.sql.Timestamp getCurrentTimeStamp() {

		java.util.Date today = new java.util.Date();
		return new java.sql.Timestamp(today.getTime());

	}

	public void updateDBemployee(String ID, Double overtime) {
		// instead of variables can be inserted class
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			// System.out.println("Creating statement...");

			String query = "UPDATE employees set overtime=? where bornID=?";
			stmt = conn.prepareStatement(query);
			stmt.setDouble(1, overtime);
			stmt.setString(2, ID);

			stmt.execute();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			getExceptions(conn, stmt);
		} // end try
		System.out.println("Done");

	}

	public void updateDBemployee(String ID, String name, String surname) {
		// instead of variables can be inserted class
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			// System.out.println("Creating statement...");

			String query = "UPDATE employees set name=?, surname=? where bornID=?";
			stmt = conn.prepareStatement(query);
			stmt.setString(1, name);
			stmt.setString(2, surname);
			stmt.setString(3, ID);

			stmt.execute();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			getExceptions(conn, stmt);
		} // end try
		System.out.println("Done");

	}

	public void updateDBemployee(String ID, Double hoursLeft, Action action) {
		// instead of variables can be inserted class
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			// System.out.println("Creating statement...");

			String query = "UPDATE employees set hoursleft=?, latest_record=?, latest_action=? where bornID=?";
			stmt = conn.prepareStatement(query);
			stmt.setDouble(1, hoursLeft);
			stmt.setTimestamp(2, getCurrentTimeStamp());
			stmt.setString(3, action.name());
			stmt.setString(4, ID);

			stmt.execute();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			getExceptions(conn, stmt);
		} // end try
		System.out.println("Done");

	}

	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Connection conn;

		Class.forName(JDBC_DRIVER);

		// System.out.println("Connecting to database...");
		conn = DriverManager.getConnection(db_url + db_name + "?autoReconnect=true&useSSL=false", username, password);
		return conn;
	}

	public void saveNewPersonToDBemployee(String name, String surname, String ID, Double hoursLeft) {
		// instead of variables can be inserted class
		Connection conn = null;
		PreparedStatement stmt = null;
		Action action = Action.NEW_USER_CREATED;
		try {
			conn = getConnection();

			// System.out.println("Creating statement...");

			stmt = createStatementToEmployeesDB(name, surname, ID, hoursLeft, action, conn);

			stmt.execute();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			getExceptions(conn, stmt);
		} // end try
		System.out.println("Done");

	}

	public void saveToDBsystemLog(String ID, Action action) {
		Connection conn = null;
		PreparedStatement stmt = null;

		try {
			conn = getConnection();

			// System.out.println("Creating statement...");
			stmt = createStatementToSystemLogDB(ID, action, conn);

			stmt.execute();

			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			getExceptions(conn, stmt);
		} // end try
		System.out.println("Done");

	}

	private void getExceptions(Connection conn, PreparedStatement stmt) {
		try {
			if (stmt != null)
				stmt.close();
		} catch (SQLException se2) {
		} // nothing we can do
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException se) {
			se.printStackTrace();
		} // end finally try
	}

	private PreparedStatement createStatementToSystemLogDB(String ID, Action action, Connection conn)
			throws SQLException {
		PreparedStatement stmt;
		String sql = " insert into systemlog (bornID,action,time)" + " values (?, ?, ?)";
		stmt = conn.prepareStatement(sql);
		stmt.setString(1, ID);
		stmt.setString(2, action.name());
		stmt.setTimestamp(3, getCurrentTimeStamp());

		return stmt;
	}

	private PreparedStatement createStatementToEmployeesDB(String name, String surname, String ID, Double hoursLeft,
			Action action, Connection conn) throws SQLException {
		PreparedStatement stmt;
		String query = " insert into employees (name, surname, bornID,hoursLeft,latest_record,overtime,latest_action)"
				+ " values (?, ?, ?, ?, ?, ?,?)";
		stmt = conn.prepareStatement(query);
		stmt.setString(1, name);
		stmt.setString(2, surname);
		stmt.setString(3, ID);
		stmt.setDouble(4, hoursLeft);
		stmt.setTimestamp(5, getCurrentTimeStamp());
		stmt.setDouble(6, 0.0);
		stmt.setString(7, action.name());
		return stmt;
	}

	public List<SystemLog> getSystemLog() {
		List<SystemLog> systemLog = new ArrayList<SystemLog>();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();

			// System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String query;
			query = "SELECT bornID, action, time FROM systemlog";
			ResultSet rs = stmt.executeQuery(query);

			while (rs.next()) {
				SystemLog line = new SystemLog();
				// Retrieve by column name
				line.setID(rs.getString("bornID"));
				String action = rs.getString("action");
				// System.out.println(action);
				String toUpperCaseAction = action.toUpperCase();
				// System.out.println(toUpperCaseAction);
				line.setAction(Action.valueOf(toUpperCaseAction));
				line.setTime(rs.getTimestamp("time"));
				systemLog.add(line);

			}
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Done");

		return systemLog;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> employee = new ArrayList<Employee>();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();

			// System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String query;
			query = "SELECT name, surname, bornID, hoursleft,latest_record,overtime, latest_action FROM employees";
			ResultSet rs = stmt.executeQuery(query);

			// STEP 5: Extract data from result set
			while (rs.next()) {
				Employee line = new Employee();
				// Retrieve by column name
				line.setName(rs.getString("name"));
				line.setSurname(rs.getString("surname"));
				line.setID(rs.getString("bornID"));
				line.setHoursLeft(rs.getDouble("hoursleft"));
				line.setLatestRecord(rs.getTimestamp("latest_record"));
				line.setOvertime(rs.getDouble("overtime"));
				String action = rs.getString("latest_action");
				// System.out.println(action);
				String toUpperCaseAction = action.toUpperCase();
				// System.out.println(toUpperCaseAction);
				line.setLatestAction(Action.valueOf(toUpperCaseAction));
				employee.add(line);

			}
			// STEP 6: Clean-up environment
			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Done");

		return employee;
	}

	public Employee getOneEmployee(String ID) {
		Employee employee = new Employee();
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();

			// System.out.println("Creating statement...");
			stmt = conn.createStatement();
			String query;
			query = "SELECT name, surname, bornID, hoursleft,latest_record,overtime, latest_action FROM employees WHERE bornID="
					+ ID;
			// System.out.println(query);
			ResultSet rs = stmt.executeQuery(query);

			// Retrieve by column name
			if (rs.next()) {
				employee.setName(rs.getString("name"));
				employee.setSurname(rs.getString("surname"));
				employee.setID(rs.getString("bornID"));
				employee.setHoursLeft(rs.getDouble("hoursleft"));
				employee.setLatestRecord(rs.getTimestamp("latest_record"));
				employee.setOvertime(rs.getDouble("overtime"));
				String action = rs.getString("latest_action");
				// System.out.println(action);
				String toUpperCaseAction = action.toUpperCase();
				// System.out.println(toUpperCaseAction);
				employee.setLatestAction(Action.valueOf(toUpperCaseAction));
			} else
				System.out.println("No matches found.");

			rs.close();
			stmt.close();
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Done");

		return employee;
	}

	public void deleteOneEmployee(String ID) {
		Connection conn = null;
		Statement stmt = null;
		try {
			conn = getConnection();

			// System.out.println("Creating statement...");
			PreparedStatement st = conn.prepareStatement("DELETE FROM employees WHERE bornID = ?");
			st.setString(1, ID);
			st.executeUpdate();

			// STEP 6: Clean-up environment
			conn.close();
		} catch (SQLException se) {
			// Handle errors for JDBC
			se.printStackTrace();
		} catch (Exception e) {
			// Handle errors for Class.forName
			e.printStackTrace();
		} finally {
			// finally block used to close resources
			try {
				if (stmt != null)
					stmt.close();
			} catch (SQLException se2) {
			} // nothing we can do
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			} // end finally try
		} // end try
		System.out.println("Done");

	}
}
