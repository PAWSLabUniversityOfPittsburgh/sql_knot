package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class AnswerChecking extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	@Resource(name = "jdbc/sqlknot_sakila")
	private DataSource sakilaDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String answer = request.getParameter("Answer");
		String questionNo = request.getParameter("QuestionNo");
		String TopicID = request.getParameter("cid");
		String TemplateID = request.getParameter("tid");

		String Canswer = "";
		String exMessage;
		
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("select answer from Questions where id="
					+ questionNo + " and TopicID = " + TopicID
					+ " and TemplateID = " + TemplateID);
			ResultSet rs = statement.getResultSet();
			rs.next();
			Canswer = rs.getString("answer");
			rs.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
		}
		try {
			Connection connection = sakilaDataSource.getConnection();
			Statement statement = connection.createStatement(1004, 1007);

			Statement statement1 = connection.createStatement(1004, 1007);
			Statement statement2 = connection.createStatement(1004, 1007);

			String queryString1 = answer;
			statement1.executeQuery(queryString1);
			ResultSet rs1 = statement1.getResultSet();
			ResultSetMetaData rsmd1 = rs1.getMetaData();
			int column_count1 = rsmd1.getColumnCount();

			String queryString2 = Canswer;
			statement2.executeQuery(queryString2);
			ResultSet rs2 = statement2.getResultSet();
			ResultSetMetaData rsmd2 = rs2.getMetaData();
			int column_count2 = rsmd2.getColumnCount();

			HashMap rshm1 = new HashMap();
			HashMap rshm2 = new HashMap();
			int rshmKey1 = 1;
			int rshmKey2 = 1;
			while (rs1.next()) {
				String tempString = "";
				for (int j1 = 1; j1 - 1 < column_count1; j1++) {
					tempString = tempString + rs1.getString(j1) + " ; ";
				}
				rshm1.put(Integer.valueOf(rshmKey1), tempString);
				rshmKey1++;
			}
			while (rs2.next()) {
				String tempString = "";
				for (int j2 = 1; j2 - 1 < column_count2; j2++) {
					tempString = tempString + rs2.getString(j2) + " ; ";
				}
				rshm2.put(Integer.valueOf(rshmKey2), tempString);
				rshmKey2++;
			}
			if ((queryString2.contains("order by"))
					|| (queryString1.contains("ORDER BY"))) {
				int CorrectSetSize = 0;
				if (rshm1.size() == rshm2.size()) {
					for (int kk = 1; kk <= rshm1.size(); kk++) {
						if (rshm1.get(Integer.valueOf(kk)).equals(
								rshm2.get(Integer.valueOf(kk)))) {
							CorrectSetSize++;
						}
					}
					if (CorrectSetSize == rshm2.size()) {
						out.print("1");
					} else {
						out.print("0");
					}
				} else {
					out.print("0");
				}
			} else {
				int CorrectSetSize = 0;
				if (rshm1.size() == rshm2.size()) {
					for (int kk = 1; kk <= rshm1.size(); kk++) {
						if (rshm2.containsValue(rshm1.get(Integer.valueOf(kk)))) {
							CorrectSetSize++;
						}
					}
					if (CorrectSetSize == rshm2.size()) {
						out.print("1");
					} else {
						out.print("0");
					}
				} else {
					out.print("0");
				}
			}
			rs1.close();
			rs2.close();

			connection.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
			out.print(exMessage);
		}
		out.close();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	public String getServletInfo() {
		return "Short description";
	}
}
