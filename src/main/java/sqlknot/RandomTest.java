package sqlknot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

public class RandomTest {
	public static void main(String[] args) {
		try {
			String questionNo = "01";

			String userName = "Reniorc";
			String password = "123456";
			String url = "jdbc:mysql://localhost/sqlknot_question";
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection connection = DriverManager.getConnection(url, userName,
					password);

			Statement statement = connection.createStatement(1004, 1007);
			Statement st2 = connection.createStatement(1004, 1007);

			statement.execute("TRUNCATE TABLE randomtemp");

			int SampleSize = 5;
			String tableName = "Q1_Employee";

			st2.executeQuery("select * from " + tableName);
			ResultSet rs2 = st2.getResultSet();
			ResultSetMetaData rsmd2 = rs2.getMetaData();
			int SetSize = 0;
			while (rs2.next()) {
				SetSize++;
			}
			for (int k = 1; k - 1 < SetSize; k++) {
				int RId = k;
				double RValue = Math.random();
				String sqlInsert = "INSERT INTO RandomTemp VALUES ('" + RId
						+ "','" + RValue + "')";
				statement.executeUpdate(sqlInsert);
			}
			statement.executeQuery("SELECT * FROM RandomTemp order by value");
			ResultSet rs = statement.getResultSet();
			HashMap rshm = new HashMap();
			int j = 1;
			while (rs.next()) {
				System.out.println("sqlknot_question: " + rs.getString(1));
				rshm.put(Integer.valueOf(j), rs.getString(1));
				j++;
			}
			System.out.print("sqlknot_question: SELECT * FROM " + tableName
					+ " LIMIT " + "2,1");
			for (int m = 1; m - 1 < SetSize; m++) {
				Statement RTS = connection.createStatement(1004, 1007);
				RTS.executeQuery("SELECT * FROM " + tableName + " LIMIT "
						+ rshm.get(Integer.valueOf(m)) + ",1");

				ResultSet RTSrs = RTS.getResultSet();
				while (RTSrs.next()) {
					System.out.println("sqlknot_question: "
							+ RTSrs.getString(1));
				}
				RTSrs.close();
			}
			rs2.close();
			rs.close();

			connection.close();
		} catch (Exception exception) {
			String exMessage = exception.getMessage();
			System.out.println("sqlknot_question: " + exMessage);
		}
	}
}
