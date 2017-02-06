package sqlknot;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

public class AnswerChecking1 extends HttpServlet {
	
	@Resource(name = "jdbc/sqlknot_question")
	private DataSource questionDataSource;
	
	protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();

		String answerAnswer = "";
		answerAnswer = request.getParameter("Answer");
		String answerMAT = "";
		answerMAT = request.getParameter("MAT");
		String answerPAT = "";
		answerPAT = request.getParameter("PAT");
		String answer = "";

		answer = answerAnswer;

		String questionNo = request.getParameter("QuestionNo");
		String TopicID = request.getParameter("cid");
		String TemplateID = request.getParameter("tid");

		String Canswer = "";

		String answerDB = "";
		String exMessage;
		try {
			Connection connection = questionDataSource.getConnection();
			Statement statement = connection.createStatement();
			statement.executeQuery("select answer from Questions where id="
					+ questionNo + " and TopicID = " + TopicID
					+ " and TemplateID = " + TemplateID);
			ResultSet rs = statement.getResultSet();
			rs.next();
			answerDB = rs.getString("answer");
			rs.close();
		} catch (Exception exception) {
			exMessage = exception.getMessage();
		}
		Canswer = answerDB;

		Pattern pattern = Pattern.compile(Canswer);
		Matcher matcher = pattern.matcher(answer);
		if (matcher.matches()) {
			out.print("1");
		} else {
			out.print("0");
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
