package sqlknot;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class TestField {
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

			int TopicID = 12;
			int TemplateID = 2;

			String queryString = "SELECT max(id) from questions where TopicID = "
					+ TopicID + " and TemplateID = " + TemplateID;

			System.out.println(queryString);
			statement.execute(queryString);

			ResultSet rs = statement.getResultSet();
			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();
			rs.next();
			int maxID = rs.getInt(1);
			System.out.println("maxID: " + maxID);

			double RandomValue = Math.random() * 1.0E8D;
			Double DRandomValue = new Double(RandomValue);
			int seed = DRandomValue.intValue();
			int QID = seed % maxID;
			Integer IQID = new Integer(QID);
			String QuestionID = IQID.toString();
			System.out.println("RandomSeed: " + RandomValue);
			System.out.println("RandomID: " + QuestionID);

			rs.close();

			connection.close();
		} catch (Exception exception) {
			String exMessage = exception.getMessage();
			System.out.println(exMessage);
		}
	}
}
