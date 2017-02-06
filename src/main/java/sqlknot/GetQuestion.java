package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class GetQuestion extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String questionNo = request.getParameter("QuestionNo");
		String TopicID = request.getParameter("cid");
		String TemplateID = request.getParameter("tid");
		String exMessage;
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();

			String questionsql = "select body from Questions where id="
					+ questionNo + " and TopicID = " + TopicID
					+ " and TemplateID = " + TemplateID;

			statement.executeQuery(questionsql);
			ResultSet rs = statement.getResultSet();

			ResultSetMetaData rsmd = rs.getMetaData();
			int column_count = rsmd.getColumnCount();
			while (rs.next()) {
				String QuestionBody = rs.getString("body");
				out.println("<font color=\"teal\">" + QuestionBody + "</font>");
			}
			rs.close();
			connection.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
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
